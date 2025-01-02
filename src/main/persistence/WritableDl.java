package persistence;

import org.json.JSONObject;

public interface WritableDl {
    // EFFECTS: returns this as JSON object
    JSONObject toJsonDl(); 
}

// Citation: this class was modeled using the sample application
// "JsonSerializationDemo"
