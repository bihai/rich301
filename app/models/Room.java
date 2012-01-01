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

/**
 * Game module.
 * 
 * @author GuoLin
 *
 */
public class Room {

    private static Map<String, Room> store = new HashMap<String, Room>();

    transient private final ArchivedEventStream<Room> events = new ArchivedEventStream<Room>(100);
    
    public String name;

    public Set<Player> players;

    public Status status;

    public Room(String name) {
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
        events.publish(this);
    }

    public void leave(Player player) {
        if (players == null || players.isEmpty()) {
            return;
        }
        players.remove(player);
        events.publish(this);
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public void save() {
        store.put(name, this);
    }

    public void delete() {
        store.remove(this);
    }

    public Promise<List<IndexedEvent<Room>>> nextEvents(long lastReceived) {
        return events.nextEvents(lastReceived);
    }

    public static Collection<Room> all() {
        return store.values();
    }

    public static Room findByName(String name) {
        return store.get(name);
    }

    public static boolean exists(String name) {
        return store.containsKey(name);
    }

    public static enum Status {

        WAITING,

        PLAYING,

        COMPLETED

    }

}
