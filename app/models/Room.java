package models;

import java.util.HashSet;
import java.util.Set;

/**
 * Game module.
 * 
 * @author GuoLin
 *
 */
public class Room {

    public String name;

    public Set<Player> players;

    public Status status;

    public Room(String name) {
        this.name = name;
        this.status = Status.WAITING;
    }

    public Room addPlayer(Player player) {
        if (players == null) {
            players = new HashSet<Player>();
        }
        players.add(player);
        return this;
    }

    public static enum Status {

        WAITING,

        PLAYING,

        COMPLETED
    }

}
