package models;

import java.util.List;

import play.libs.F.ArchivedEventStream;
import play.libs.F.IndexedEvent;
import play.libs.F.Promise;
import util.RichUtil;

public class Event {

    public final String type = getClass().getSimpleName();

}
