package models;

import java.util.List;

import play.libs.F.ArchivedEventStream;
import play.libs.F.IndexedEvent;
import play.libs.F.Promise;
import util.RichUtil;

public class Event {

    public static final ArchivedEventStream<Event> events = new ArchivedEventStream<Event>(100);

    public static Promise<List<IndexedEvent<Event>>> nextEvents(long lastReceived) {
        return events.nextEvents(lastReceived);
    }
    
    public final String type = getClass().getSimpleName();
    
}
