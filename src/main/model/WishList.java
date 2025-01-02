package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.WritableWl;

// Represents a list of hairstyles containing the name and estimated cost of each hairstyle
public class WishList implements WritableWl {
    private final List<Hairstyle> hairstylestodo;

    // REQUIRES: list only contains hairstyles, cost of each hairstyle is always >=0
    // EFFECTS: creates an empty list of hairstyles which have a name and an  
    // estimated cost (in dollars)
    public WishList() {
        this.hairstylestodo = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a hairstyle to the wishlist
    public void addHairstyle(Hairstyle hairstyle) {
        hairstylestodo.add(hairstyle);
        EventLog.getInstance().logEvent(new Event("Hairstyle:" + hairstyle.getName() + " added to wishlist."));
    }

    // MODIFIES: this
    // EFFECTS: removes a hairstyle from the wishlist. If wishlist is empty, do nothing.
    public boolean removeHairstyle(String hairstyleName) {
        for (int i = 0; i < hairstylestodo.size(); i++) {
            if (hairstyleName.equals(hairstylestodo.get(i).getName())) {
                hairstylestodo.remove(i);
                EventLog.getInstance().logEvent(new Event("Hairstyle:" + hairstyleName + " removed from wishlist."));
                return true;
            } 
        }
        return false;
    }

        // REQUIRES: hairstyleName is not an empty string, newCost>=0
    // MODIFIES: this
    // EFFECTS: finds the hairstyle with the given name, and changes its cost to the 
    // new cost provided.
    public void editHairstyleCost(String hairstyleName, double newCost) {
        for (Hairstyle h : hairstylestodo) {
            if (h.getName().equals(hairstyleName)) {
                h.setCost(newCost);
            }
        }

        EventLog.getInstance().logEvent(new Event("Cost of Hairstyle:" + hairstyleName + " changed."));
    }

    // EFFECTS: returns true if the wishlist contains specified hairstyle, 
    // false otherwise
    public boolean containsHairstyle(Hairstyle hairstyle) {
        return hairstylestodo.contains(hairstyle);
    }

    // EFFECTS: returns the first hairstyle on the wishlist, returns null 
    // if wishlist is empty
    public Hairstyle getFirstHairstyle() {
        if (!(hairstylestodo.isEmpty())) {
            return hairstylestodo.get(0);
        } else {
            return null;
        }
    }

    // Returns all hairstyles in the wishlist
    public List<Hairstyle> getAllHairstyles() {
        EventLog.getInstance().logEvent(new Event("All hairstyles in wishlist displayed."));
        return hairstylestodo;
    }

    @Override
    public JSONObject toJsonWl() {
        JSONObject json = new JSONObject();
        json.put("hairstylestodo", hairstylesToJsonWl());
        return json;
    }
    // Citation: this method was modeled using the sample application
    // "JsonSerializationDemo"

    // EFFECTS: returns things in this wishlist as a JSON array
    private JSONArray hairstylesToJsonWl() {
        JSONArray jsonArray = new JSONArray();

        for (Hairstyle h : hairstylestodo) {
            jsonArray.put(h.toJsonWl());
        }

        return jsonArray;
    }
    // Citation: this method was modeled using the sample application
    // "JsonSerializationDemo"

}
