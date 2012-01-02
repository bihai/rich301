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
    
    public int lastReceived;

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
        this.round = 0;
        this.lastReceived = 0;
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
    
    public void recordLastAvtive(Integer playerId) {
        for (Player player : players) {
            if (playerId.equals(player.id)) {
                
            }
        }
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
            this.nextRound();
        }
        else {
            int currentIndex = players.indexOf(currentPlayer);
            int nextIndex = (currentIndex + 1) % players.size();
            currentPlayer = players.get(nextIndex);
            if (nextIndex == 0) {
                this.nextRound();
            }
        }
        if (currentPlayer.survive == false) {
            nextPlayer();
        }
        this.events.publish(new NextPlayerEvent(currentPlayer.name));
    }
    
    private void nextRound() {
        if (!this.gameEnd()) {
            round++;
            NextRoundEvent nextRoundEvent = new NextRoundEvent(round);
            this.events.publish(nextRoundEvent);
        }
    }
    
    private boolean gameEnd() {
        int aliveCount = 0;
        for (Player player : players) {
            if (player.survive == true) {
                aliveCount++;
            }
        }
        if (aliveCount == 1) {
            this.events.publish(new EndGameEvent());
            return true;
        }
        return false;
    }
    
    public void recordHeartbeat(Integer playerId) {
        
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

    static class StartEvent extends Event {

        public final Game game;
        
        public StartEvent(Game game) {
            this.game = game;
        }
    }
    
    static class NextPlayerEvent extends Event {
        
        public final String playerName;
        
        public NextPlayerEvent(String playerName) {
            this.playerName = playerName;
        }
        
    }
    
    static class NextRoundEvent extends Event {
        
        public final int round;
        
        public NextRoundEvent(int round) {
            this.round = round;
        }
    }
    
    static class EndGameEvent extends Event {
        
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

}
