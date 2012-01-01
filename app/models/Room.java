package models;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import play.libs.F.ArchivedEventStream;
import play.libs.F.IndexedEvent;
import play.libs.F.Promise;
import util.IdGenerator;

/**
 * Game module.
 * 
 * @author GuoLin
 *
 */
public class Room {

    private static Map<Integer, Room> STORE = new HashMap<Integer, Room>();

    transient private final ArchivedEventStream<Room> events = new ArchivedEventStream<Room>(100);
    
    public Integer id;

    public String name;

    public Set<Player> players;

    public Status status;

    public Room(String name) {
        this.id = IdGenerator.generate();
        this.name = name;
        this.status = Status.WAITING;
    }

    public Player getPlayer(String name) {
        if (players == null || players.isEmpty()) {
            return null;
        }
        for (Player player : players) {
            if (player.name.equals(name)) {
                return player;
            }
        }
        return null;
    }

    public void join(Player player) {
        if (players == null) {
            players = new HashSet<Player>();
        }
        players.add(player);
        publish();
    }

    public void leave(Player player) {
        if (players == null || players.isEmpty()) {
            return;
        }
        players.remove(player);
        publish();
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public void save() {
        STORE.put(id, this);
    }

    public void delete() {
        STORE.remove(id);
    }

    public void publish() {
        events.publish(this);
    }

    public Promise<List<IndexedEvent<Room>>> nextEvents(long lastReceived) {
        return events.nextEvents(lastReceived);
    }

    public static Collection<Room> all() {
        return STORE.values();
    }

    public static Room get(Integer id) {
        return STORE.get(id);
    }

    public static boolean exists(String name) {
        for (Room room : STORE.values()) {
            if (room.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static enum Status {

        WAITING,

        PLAYING,

        COMPLETED

    }

}
