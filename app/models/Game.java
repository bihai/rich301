package models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Player.Serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import play.libs.F.ArchivedEventStream;
import play.libs.F.IndexedEvent;
import play.libs.F.Promise;

import util.IdGenerator;
import util.RichUtil;

/**
 * Game model.
 * 
 * @author GuoLin
 *
 */
public class Game {

    private static final Map<Integer, Game> STORE = new HashMap<Integer, Game>();

    public Integer id;

    public String name;

    public List<Player> players = new ArrayList<Player>();
    
    public Player currentPlayer;
    
    public GameMap gameMap;
    
    public int round;
    
    public final ArchivedEventStream<Event> events = new ArchivedEventStream<Event>(100);

    public Promise<List<IndexedEvent<Event>>> nextEvents(long lastReceived) {
        return events.nextEvents(lastReceived);
    }
    
    public Game(Room room) {
        id = IdGenerator.generate();
        this.name = room.name;
        this.gameMap = MapGenerator.generateMap();
        for (Player player : room.players) {
            players.add(player);
            player.randomStart(gameMap);
            player.game = this;
        }
        this.events.publish(new StartEvent(this));
        this.nextPlayer();
    }

    public Game save() {
        STORE.put(id, this);
        return this;
    }

    public static Game get(Integer id) {
        return STORE.get(id);
    }
    
    public boolean validPlayer(String connected) {
        return currentPlayer != null && connected.equals(currentPlayer.name);
    }
    
    /**
     * Switch to the next player.
     * This method would be applied at the start of the game.
     * Or when the turn of one player is ended.
     * A {@link NextPlayerEvent} would be generated in the process.
     */
    public void nextPlayer() {
        if (currentPlayer == null) {
            currentPlayer = players.get(0);
            round = 1;
        }
        else {
            int currentIndex = players.indexOf(currentPlayer);
            currentPlayer = players.get((currentIndex + 1) % players.size());
            round++;
        }
        this.events.publish(new NextPlayerEvent(currentPlayer.name));
    }
    
    public enum Action {
        
        ROLL {
            @Override
            public void doAction(Game currentGame) {
                int value = currentGame.currentPlayer.roll();
                currentGame.currentPlayer.move(value);
            }
        },
        
        PASS {
            @Override
            public void doAction(Game currentGame) {
                currentGame.currentPlayer.pass();
            }
            
        },
        
        BUY {
            @Override
            public void doAction(Game currentGame) {
                currentGame.currentPlayer.buyCell();
                currentGame.nextPlayer();
            }
        },
        
        END {
            @Override
            public void doAction(Game currentGame) {
                currentGame.nextPlayer();
            }
            
        };
        
        public abstract void doAction(Game currentGame);
    }

    public static class StartEvent extends Event {

        public final Game game;
        
        public StartEvent(Game game) {
            this.game = game;
        }
    }
    
    public static class NextPlayerEvent extends Event {
        
        public final String playerName;
        
        public NextPlayerEvent(String playerName) {
            this.playerName = playerName;
        }
        
    }
    
    public static class Serializer implements JsonSerializer<Game> {

        @Override
        public JsonElement serialize(Game src, Type type,
                JsonSerializationContext ctx) {
            JsonObject gameObject = new JsonObject();
            gameObject.add("id", new JsonPrimitive(src.id));
            gameObject.add("name", new JsonPrimitive(src.name));
            gameObject.add("players", ctx.serialize(src.players));
            gameObject.add("currentPlayer", ctx.serialize(src.currentPlayer));
            gameObject.add("gameMap", ctx.serialize(src.gameMap));
            gameObject.add("round", new JsonPrimitive(src.round));
            return gameObject;
        }
    }

    static {
        RichUtil.builder.registerTypeAdapter(Game.class, new Serializer());
    }
}
