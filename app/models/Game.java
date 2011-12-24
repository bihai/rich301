package models;

import java.util.HashSet;
import java.util.Set;

/**
 * Game module.
 * 
 * @author GuoLin
 *
 */
public class Game {

    public String name;

    public Set<Player> players;

    public boolean running;

    public Game(String name) {
        this.name = name;
    }

    public Game addPlayer(Player player) {
        if (players == null) {
            players = new HashSet<Player>();
        }
        players.add(player);
        return this;
    }

}
