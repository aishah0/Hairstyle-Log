package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.WritableDl;

// Represents a list of completed hairstyles containing the name and estimated cost of each hairstyle
public class DoneList implements WritableDl {
    private final List<Hairstyle> donehairstyles;

    // REQUIRES: list only contains hairstyles, cost of each hairstyle is always >=0
    // EFFECTS: creates an empty list of completed hairstyles which have a name and
    // an estimated
    // cost (in dollars)
    public DoneList() {
        this.donehairstyles = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a hairstyle to the list
    public void addDoneHairstyle(Hairstyle hairstyle) {
        donehairstyles.add(hairstyle);
        EventLog.getInstance().logEvent(new Event("Hairstyle:" + hairstyle.getName() + " added to completed list."));
    }

    // EFFECTS: returns true if the list contains specified hairstyle, false
    // otherwise
    public boolean containsDoneHairstyle(Hairstyle hairstyle) {
        return donehairstyles.contains(hairstyle);
    }

    // EFFECTS: returns the first hairstyle on the list, returns null if list is
    // empty
    public Hairstyle getFirstDoneHairstyle() {
        if (!(donehairstyles.isEmpty())) {
            return donehairstyles.get(0);
        } else {
            return null;
        }
    }

    // Returns all hairstyles in the completed list
    public List<Hairstyle> getAllDoneHairstyles() {
        EventLog.getInstance().logEvent(new Event("All hairstyles in completed list displayed."));
        return donehairstyles;
    }

    @Override
    public JSONObject toJsonDl() {
        JSONObject json = new JSONObject();
        json.put("donehairstyles", hairstylesToJsonDl());
        return json;
    }
    // Citation: this method was modeled using the sample application
    // "JsonSerializationDemo"

    // EFFECTS: returns things in this completed list as a JSON array
    private JSONArray hairstylesToJsonDl() {
        JSONArray jsonArray = new JSONArray();

        for (Hairstyle h : donehairstyles) {
            jsonArray.put(h.toJsonDl());
        }

        return jsonArray;
    }
    // Citation: this method was modeled using the sample application
    // "JsonSerializationDemo"

}
