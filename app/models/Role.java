package models;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import util.IdGenerator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Role.
 * 
 * @author GuoLin
 *
 */
public class Role {

    public static final Role RANDOM = new RandomRole(); 

    private static final Map<Integer, Role> STORE = new LinkedHashMap<Integer, Role>();

    public Integer id;

    public String name;

    public String face;

    public Role(String name, String face) {
        this.id = IdGenerator.generate();
        this.name = name;
        this.face = face;
    }

    public boolean isRandom() {
        return false;
    }

    public void save() {
        STORE.put(id, this);
    }

    public static Role get(Integer id) {
        return STORE.get(id);
    }

    public static Collection<Role> all() {
        return STORE.values();
    }

    public static class RandomRole extends Role {

        public RandomRole() {
<<<<<<< HEAD
            super(null, "role_random.png");
        }

        public String getName() {
            return Messages.get("room.role.random");
=======
            super("room.role.random", "role_random.png");
>>>>>>> db694e6edb73290116f6d5e01996a18ac4acb31e
        }

        @Override
        public boolean isRandom() {
            return true;
        }

    }

}
