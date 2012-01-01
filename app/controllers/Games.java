package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import controllers.Secure.Security;

import models.Event;
import models.Game;
import models.GameMap;
import models.MapGenerator;
import models.Player;
import models.Room;
import play.libs.F.IndexedEvent;
import play.mvc.Controller;
import util.RichUtil;
import play.mvc.With;

/**
 * Control games.
 * 
 * @author GuoLin
 *
 */
@With(Secure.class)
public class Games extends Controller {

    public static void game(String gameName) {
        Game game = Game.findByName(gameName);
        if (game == null) {
            Room room = Room.findByName(gameName);
            notFoundIfNull(room);
            GameMap map = MapGenerator.generateTestMap();
            game = new Game(room, map);
            game.save();
        }
        render(game);
    }

    public static void waitEvents(String gameName, Long lastReceived) {
        Game game = Game.findByName(gameName);
        notFoundIfNull(game);
        List<IndexedEvent<Event>> events = await(Event.nextEvents(lastReceived));
        renderJSON(events, new TypeToken<List<IndexedEvent<Event>>>() {}.getType());
    }

    public static void action(String gameName, String actionName) {
        Game game = Game.findByName(gameName);
        notFoundIfNull(game);
        String connected = Security.connected();
        if (!game.validPlayer(connected)) {
            unauthorized();
        }
        Game.Action currentAction = Game.Action.valueOf(actionName.toUpperCase());
        notFoundIfNull(currentAction);
        currentAction.doAction(game);
    }
}
