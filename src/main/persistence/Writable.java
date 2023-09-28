package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: makes the object a JSON object
    // Functionality from JSON Serialization Demo
    JSONObject toJson();
}