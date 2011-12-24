package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.Game;
import models.Player;
import play.mvc.Controller;
import controllers.Secure.Security;

/**
 * Control the games.
 * 
 * @author GuoLin
 *
 */
public class Games extends Controller {

    private static Map<String, Game> games = new HashMap<String, Game>();

    public static void list() {
        Collection<Game> games = Games.games.values();
        render(games);
    }

    public static void create(String name) {
        if (games.containsKey(name)) {
            return;
        }
        Game game = new Game(name);
        String connected = Security.connected();
        Player player = new Player(connected);
        game.addPlayer(player);
        games.put(name, game);
        list();
    }

    public static void join(String gameName) {
        String connected = Security.connected();
        Game game = games.get(gameName);
        Player player = new Player(connected);
        game.addPlayer(player);
        list();
    }
    
    public static void start(String name) {
        
    }

    public static void destroy() {
        
    }

}
