package models;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import play.libs.F.ArchivedEventStream;
import play.libs.F.IndexedEvent;
import play.libs.F.Promise;

/**
 * Game model.
 * 
 * @author GuoLin
 *
 */
public class Game {

    private static java.util.Map<String, Game> store = new HashMap<String, Game>();

    transient private final ArchivedEventStream<Game.Event> events = new ArchivedEventStream<Game.Event>(100);
    
    public String name;

    public Set<Player> players;

    public Game(Room room) {
        this.name = room.name;
        this.players = room.players;
        events.publish(new StartEvent());
    }

    public Promise<List<IndexedEvent<Game.Event>>> nextEvents(long lastReceived) {
        return events.nextEvents(lastReceived);
    }

    public void save() {
        store.put(name, this);
    }

    public static Game findByName(String name) {
        return store.get(name);
    }

    public static boolean exists(String name) {
        return store.containsKey(name);
    }

    /**
     * Abstract event class.
     * <p>
     * This class is root class for all kind of events. For JSON serialization, provide a 
     * {@link #type} property to indicate which type of event it is.</p>
     * 
     * @author GuoLin
     *
     */
    public static abstract class Event {

        public final String type = getClass().getSimpleName();

    }

    public static class StartEvent extends Event {

    }

}
