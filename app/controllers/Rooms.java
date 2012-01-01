package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Game;
import models.GameMap;
import models.MapGenerator;
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
        if (Room.exists(roomName)) {
            error(409, "Room already exists.");
        }
        Room room = new Room(roomName);
        String connected = Security.connected();
        Player player = new Player(connected);
        room.join(player);
        room.save();
        room(room.id);
    }

    public static void join(Integer roomId) {
        String connected = Security.connected();
        Room room = Room.get(roomId);
        notFoundIfNull(room, "Room not found.");
        Player player = new Player(connected);
        room.join(player);
        room(room.id);
    }

    public static void leave(Integer roomId) {
        String connected = Security.connected();
        Room room = Room.get(roomId);
        notFoundIfNull(room, "Room not found.");
        Player player = room.getPlayer(connected);
        room.leave(player);
        if (room.isEmpty()) {
            room.delete();
        }
        list();
    }

    public static void selectRole(Integer roomId, Integer roleId) {
        Room room = Room.get(roomId);
        notFoundIfNull(room, "Room not found.");
        Role role = Role.get(roleId);
        notFoundIfNull(role, "Role not found.");
        String connected = Security.connected();
        Player player = room.getPlayer(connected);
        player.role = role;
        room.publish();
    }

    public static void room(Integer roomId) {
        Room room = Room.get(roomId);
        notFoundIfNull(room, "Room not found.");
        Collection<Role> roles = Role.all();
        render(room, roles);
    }

    public static void start(Integer roomId) {
        Room room = Room.get(roomId);
        notFoundIfNull(room, "Room not found.");
        room.startGame();
        Games.game(room.id);
    }

    public static void waitState(Integer roomId, Long lastReceived) {
        Room room = Room.get(roomId);
        notFoundIfNull(room, "Room not found.");
        List messages = await(room.nextEvents(lastReceived));
        renderJSON(messages, new TypeToken<List<IndexedEvent<Room>>>() {}.getType());
    }

}
