package persistence;

import org.json.JSONObject;

//Interface for classes that needs JSON
//REFERENCES: JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
