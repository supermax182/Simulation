package org.example.SimulateAis.context;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScenarioContext {

    private final Map<ScenarioContextKey, Object> contextData = new HashMap<>();

    public void save(ScenarioContextKey key, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot save null value for key: " + key);
        }
        contextData.put(key, value);
    }

    public <T> T get(ScenarioContextKey key, Class<T> type) {
        return type.cast(contextData.get(key));
    }

    public void reset() {
        contextData.clear();
    }

    public boolean containsKey(ScenarioContextKey key) {
        return contextData.containsKey(key);
    }

    public void printContext() {
        System.out.println("ScenarioContext Data: " + contextData);
    }

}
