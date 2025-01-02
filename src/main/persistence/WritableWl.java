package persistence;

import org.json.JSONObject;

public interface WritableWl {
    // EFFECTS: returns this as JSON object
    JSONObject toJsonWl(); 
}

// Citation: this class was modeled using the sample application
// "JsonSerializationDemo"
