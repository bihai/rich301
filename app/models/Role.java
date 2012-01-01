package models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import play.i18n.Messages;

/**
 * Role.
 * 
 * @author GuoLin
 *
 */
public class Role {

    public static final Role RANDOM = new EmptyRole(); 

    private static final Map<String, Role> STORE = new HashMap<String, Role>();

    public String name;

    public String face;

    public Role(String name, String face) {
        this.name = name;
        this.face = face;
    }

    public void save() {
        STORE.put(name, this);
    }

    public static Role findByName(String name) {
        return STORE.get(name);
    }

    public static Collection<Role> all() {
        return STORE.values();
    }

    public static class EmptyRole extends Role {

        public EmptyRole() {
            super(Messages.get("room.role.random"), "role_random.png");
        }

    }

}
