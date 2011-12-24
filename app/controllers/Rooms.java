package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import models.Room;
import models.Player;
import play.mvc.Controller;
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
        room.addPlayer(player);
        rooms.put(roomName, room);
        //redirect("Rooms.room", false, roomName);
        room(roomName);
    }

    public static void join(String roomName) {
        String connected = Security.connected();
        Room room = rooms.get(roomName);
        Player player = new Player(connected);
        room.addPlayer(player);
        room(roomName);
    }
    
    public static void room(String roomName) {
        render(roomName);
    }

}
