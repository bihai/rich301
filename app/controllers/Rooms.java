package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Player;
import models.Room;

import org.apache.commons.lang.StringUtils;

import play.libs.F.IndexedEvent;
import play.mvc.Controller;

import com.google.gson.reflect.TypeToken;

import controllers.Secure.Security;

/**
 * Control the game rooms.
 * 
 * @author GuoLin
 *
 */
public class Rooms extends Controller {

    private static Map<String, Room> rooms = new HashMap<String, Room>();

    public static void list() {
        Collection<Room> rooms = Rooms.rooms.values();
        render(rooms);
    }

    public static void create(String roomName) {
        if (rooms.containsKey(roomName) || StringUtils.isBlank(roomName)) {
            return;
        }
        Room room = new Room(roomName);
        String connected = Security.connected();
        Player player = new Player(connected);
        room.join(player);
        rooms.put(roomName, room);
        room(roomName);
    }

    public static void join(String roomName) {
        String connected = Security.connected();
        Room room = rooms.get(roomName);
        Player player = new Player(connected);
        room.join(player);
        room(roomName);
    }

    public static void leave(String roomName) {
        String connected = Security.connected();
        Room room = rooms.get(roomName);
        Player player = new Player(connected);
        room.leave(player);
        list();
    }

    public static void room(String roomName) {
        Room room = rooms.get(roomName);
        notFoundIfNull(room);
        render(room);
    }

    public static void waitState(String roomName, Long lastReceived) {
        Room room = rooms.get(roomName);
        notFoundIfNull(room);
        List messages = await(room.nextEvents(lastReceived));
        renderJSON(messages, new TypeToken<List<IndexedEvent<Room>>>() {}.getType());
    }
}
