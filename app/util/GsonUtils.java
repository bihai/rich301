package util;

import java.util.Set;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonUtils {
	/**
     * Strategy of property exclusion.
     * 
     * @author GuoLin
     *
     */
    public static class GsonPropertyExclusionStrategy implements ExclusionStrategy {

        public GsonPropertyExclusionStrategy() {
        	
        }
        
        
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
        
        @Override
        public boolean shouldSkipField(FieldAttributes attributes) {
        	return attributes.getDeclaredType().toString().contains("class models.MapCell");
        }
    }
}
