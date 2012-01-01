package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Player;
import models.Role;
import models.Room;

import org.apache.commons.lang.StringUtils;

import play.libs.F.IndexedEvent;
import play.mvc.Controller;
import play.mvc.With;

import com.google.gson.reflect.TypeToken;

import controllers.Secure.Security;

/**
 * Control the game rooms.
 * 
 * @author GuoLin
 *
 */
@With(Secure.class)
public class Rooms extends Controller {

    public static void list() {
        Collection<Room> rooms = Room.all();
        render(rooms);
    }

    public static void create(String roomName) {
        if (Room.exists(roomName) || StringUtils.isBlank(roomName)) {
            return;
        }
        Room room = new Room(roomName);
        String connected = Security.connected();
        System.out.println(connected);
        Player player = new Player(connected);
        room.join(player);
        room.save();
        room(roomName);
    }

    public static void join(String roomName) {
        String connected = Security.connected();
        Room room = Room.findByName(roomName);
        Player player = new Player(connected);
        room.join(player);
        room(roomName);
    }

    public static void leave(String roomName) {
        String connected = Security.connected();
        Room room = Room.findByName(roomName);
        Player player = room.getPlayer(connected);
        room.leave(player);
        if (room.isEmpty()) {
            room.delete();
        }
        list();
    }

    public static void selectRole(String roomName, String roleName) {
        Room room = Room.findByName(roomName);
        notFoundIfNull(room);
        Role role = Role.findByName(roleName);
        notFoundIfNull(role);
        String connected = Security.connected();
        Player player = room.getPlayer(connected);
        player.role = role;
    }

    public static void room(String roomName) {
        Room room = Room.findByName(roomName);
        notFoundIfNull(room);
        Collection<Role> roles = Role.all();
        render(room, roles);
    }

    public static void waitState(String roomName, Long lastReceived) {
        Room room = Room.findByName(roomName);
        notFoundIfNull(room);
        List messages = await(room.nextEvents(lastReceived));
        renderJSON(messages, new TypeToken<List<IndexedEvent<Room>>>() {}.getType());
    }

}
