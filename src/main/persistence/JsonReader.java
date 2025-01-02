package persistence;

import model.DoneList;
import model.Hairstyle;
import model.WishList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
import org.junit.experimental.categories.Category;

// Represents a reader that reads WishList and DoneListfrom JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads wishlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WishList readWishList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWishList(jsonObject);
    }

    // EFFECTS: reads completed list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DoneList readDoneList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDoneList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses wishlist from JSON object and returns it
    private WishList parseWishList(JSONObject jsonObject) {
        WishList wl = new WishList(); // Create an empty WishList
        addHairstylesToWishList(wl, jsonObject); // Parse and add hairstyles
        return wl;
    }

    // EFFECTS: parses completed list from JSON object and returns it
    private DoneList parseDoneList(JSONObject jsonObject) {
        DoneList dl = new DoneList(); // Create an empty DoneList
        addHairstylesToDoneList(dl, jsonObject); // Parse and add hairstyles
        return dl;
    }

    // MODIFIES: wl
    // EFFECTS: parses hairstyles from JSON object and adds them to wishlist
    private void addHairstylesToWishList(WishList wl, JSONObject jsonObject) {
        JSONArray hairstylestodo = jsonObject.getJSONArray("hairstylestodo");
        for (int i = 0; i < hairstylestodo.length(); i++) {
            JSONObject hairstyletodoJson = hairstylestodo.getJSONObject(i);
            addHairstyleToWishList(wl, hairstyletodoJson);
        }
    }

    // MODIFIES: dl
    // EFFECTS: parses hairstyles from JSON object and adds them to completed list
    private void addHairstylesToDoneList(DoneList dl, JSONObject jsonObject) {
        JSONArray donehairstyles = jsonObject.getJSONArray("donehairstyles");
        for (int i = 0; i < donehairstyles.length(); i++) {
            JSONObject donehairstyleJson = donehairstyles.getJSONObject(i);
            addHairstyleToDoneList(dl, donehairstyleJson);
        }
    }

    // MODIFIES: wl
    // EFFECTS: parses hairstyle from JSON object and adds it to wishlist
    private void addHairstyleToWishList(WishList wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double cost = jsonObject.getDouble("cost");
        Hairstyle hairstyle = new Hairstyle(name, cost);
        wl.addHairstyle(hairstyle);
    }

    // MODIFIES: dl
    // EFFECTS: parses hairstyle from JSON object and adds it to completed list
    private void addHairstyleToDoneList(DoneList dl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double cost = jsonObject.getDouble("cost");
        Hairstyle hairstyle = new Hairstyle(name, cost);
        dl.addDoneHairstyle(hairstyle);;
    }

}

// Citation: this class was modeled using the sample application
// "JsonSerializationDemo"
