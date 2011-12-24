package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import play.libs.F.ArchivedEventStream;
import play.libs.F.EventStream;
import play.libs.F.IndexedEvent;
import play.libs.F.Promise;

/**
 * Game module.
 * 
 * @author GuoLin
 *
 */
public class Room {

    transient private final ArchivedEventStream<Room> events = new ArchivedEventStream<Room>(100);
    
    public String name;

    public Set<Player> players;

    public Status status;

    public Room(String name) {
        this.name = name;
        this.status = Status.WAITING;
    }

    public void join(Player player) {
        if (players == null) {
            players = new HashSet<Player>();
        }
        players.add(player);
        events.publish(this);
    }

    public void leave(Player player) {
        players.remove(player);
        events.publish(this);
    }

    public Promise<List<IndexedEvent<Room>>> nextEvents(long lastReceived) {
        return events.nextEvents(lastReceived);
    }

    public static enum Status {

        WAITING,

        PLAYING,

        COMPLETED
    }

}
