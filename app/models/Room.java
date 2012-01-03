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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Game module.
 * 
 * @author GuoLin
 *
 */
public class Room {

    static Map<Integer, Room> STORE = new HashMap<Integer, Room>();

    transient private final ArchivedEventStream<Room.Event> events = new ArchivedEventStream<Room.Event>(100);

    public Integer id;

    public String name;

    public Set<Player> players;

    public Status status;

    public Player master;

    public Room(String name) {
        this.id = IdGenerator.generate();
        this.name = name;
        this.status = Status.WAITING;
    }

    public synchronized Player getPlayer(String name) {
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

    public synchronized void join(Player player) {
        if (players == null) {
            players = new HashSet<Player>();
        }
        players.add(player);
        onPlayerChange();
    }

    public synchronized void leave(Player player) {
        if (players == null || players.isEmpty()) {
            return;
        }
        players.remove(player);
        onPlayerChange();
    }

    public synchronized boolean isEmpty() {
        return players.isEmpty();
    }

    public void save() {
        STORE.put(id, this);
    }

    public void delete() {
        STORE.remove(id);
    }

    public synchronized void startGame() {
        if (status != Status.WAITING) {
            return;
        }
        status = Status.PLAYING;
        new Game(this).save();
        events.publish(new StateChangeEvent(this));
    }

    public void onPlayerChange() {
        if (players.size() == 1) {
            master = players.iterator().next();
        }
        events.publish(new PlayerChangeEvent(this));
    }

    public Promise<List<IndexedEvent<Room.Event>>> nextEvents(long lastReceived) {
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

    abstract public static class Event {

        public final String eventType = getClass().getSimpleName();

        public final Integer id;

        public final String name;

        public Event(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    public static class StateChangeEvent extends Event {
        
        public final String status;

        public StateChangeEvent(Room room) {
            super(room.id, room.name);
            this.status = room.status.toString();
        }

    }

    public static class PlayerChangeEvent extends Event {

        public final Set<Player> players;

        public final Player master;

        public PlayerChangeEvent(Room room) {
            super(room.id, room.name);
            this.players = room.players;
            this.master = room.master;
        }

    }

    public static enum Status {

        WAITING,

        PLAYING,

        COMPLETED

    }

}
