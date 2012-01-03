package models;

import models.Game;
import models.Player;
import models.Role;
import models.Room;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.GameException;

public class GameTest {
    

    @Test
    public void testRandomRole() {
        Room room = new Room("The room1");
        room.join(new Player("xxx", new Role.RandomRole()));
        Role role1 = new Role("role1", "face");
        role1.save();
        Game game = new Game(room);
        assertEquals("role1", game.players.get(0).role.name);
    }
    
    @Test
    public void testRandomRoleTwo() {
        Room room = new Room("The room2");
        room.join(new Player("xxx1", new Role.RandomRole()));
        room.join(new Player("xxx2", new Role.RandomRole()));
        Role role1 = new Role("role1", "face");
        role1.save();
        Role role2 = new Role("role2", "face");
        role2.save();
        Game game = new Game(room);
        assertTrue(game.players.get(0).role.name.equals("role1") || game.players.get(0).role.name.equals("role2"));
        assertTrue(game.players.get(1).role.name.equals("role1") || game.players.get(1).role.name.equals("role2"));
    }
    
    @Test(expected=GameException.class)
    public void testRandomRoleNotEnough() {
        Room room = new Room("The room3");
        room.join(new Player("xxx3", new Role.RandomRole()));
        room.join(new Player("xxx4", new Role.RandomRole()));
        Role role1 = new Role("role1", "face");
        role1.save();
        new Game(room);
    }
    
    @Test
    public void testRandomRoleOneSpecific() {
        Room room = new Room("The room4");
        room.join(new Player("xxx5", new Role("guolin2", "guolin2.png")));
        room.join(new Player("xxx6", new Role.RandomRole()));
        Role role1 = new Role("role1", "face");
        role1.save();
        Game game = new Game(room);
        assertEquals("role1", game.players.get(0).role.name);
    }
    
    @Test
    public void testRandomRoleTwoSpecific() {
        Room room = new Room("The room5");
        room.join(new Player("xxx7", new Role("guolin2", "guolin2.png")));
        room.join(new Player("xxx8", new Role("guolin3", "guolin3.png")));
        Role role1 = new Role("role1", "face");
        role1.save();
        Game game = new Game(room);
        assertEquals("guolin3", game.players.get(0).role.name);
        assertEquals("guolin2", game.players.get(1).role.name);
    }
    
    @After
    public void tearDown() {
        Role.STORE.clear();
    }
    
}
