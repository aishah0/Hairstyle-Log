package model;

import org.json.JSONObject;
import persistence.WritableWl;
import persistence.WritableDl;

//Represents a hairstyle with a name and a cost
public class Hairstyle implements WritableWl, WritableDl {
    String name;
    double cost;

    // REQUIRES: name isn't an empty string, cost >= 0
    // EFFECTS: creates a hairstyle with a name and a price (in dollars)
    public Hairstyle(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    // EFFECTS: returns the name of the hairstyle
    public String getName() {
        return name;
    }

    // EFFECTS: returns the cost of the hairstyle (in dollars)
    public double getCost() {
        return cost;
    }

    // EFFECTS: sets the cost of the hairstyle to newCost
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    @Override
    public JSONObject toJsonWl() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cost", cost);
        return json;
    }
    // Citation: this method was modeled using the sample application
    // "JsonSerializationDemo"

    @Override
    public JSONObject toJsonDl() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cost", cost);
        return json;
    }
    // Citation: this method was modeled using the sample application
    // "JsonSerializationDemo"
}
