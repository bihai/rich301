package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import models.Game;
import models.Player;
import models.Game.Event;
import models.Room;
import play.libs.F.IndexedEvent;
import play.mvc.Controller;

/**
 * Control games.
 * 
 * @author GuoLin
 *
 */
public class Games extends Controller {

    public static void game(String gameName) {
        Game game = Game.findByName(gameName);
        if (game == null) {
            Room room = Room.findByName(gameName);
            notFoundIfNull(room);
            game = new Game(room);
            game.save();
        }
        render(game);
    }

    public static void waitEvents(String gameName, Long lastReceived) {
        Game game = Game.findByName(gameName);
        notFoundIfNull(game);
        List<IndexedEvent<Event>> events = await(game.nextEvents(lastReceived));
        renderJSON(events, new TypeToken<List<IndexedEvent<Game.Event>>>() {}.getType());
    }

    public static void action(String actionName) {
        
    }
}
