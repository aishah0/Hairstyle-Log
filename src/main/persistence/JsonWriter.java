package persistence;

import model.DoneList;
import model.Hairstyle;
import model.WishList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representations of wishlist and donelist to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter wlwriter;
    private PrintWriter dlwriter;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens wishlist writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void openWishList() throws FileNotFoundException {
        wlwriter = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: opens donelist writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void openDoneList() throws FileNotFoundException {
        dlwriter = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of wishlist to file
    public void writeWishList(WishList wl) {
        JSONObject json = wl.toJsonWl();
        saveToWishListFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of donelist to file
    public void writeDoneList(DoneList dl) {
        JSONObject json = dl.toJsonDl();
        saveToDoneListFile(json.toString(TAB));
    }


    // MODIFIES: this
    // EFFECTS: closes wishlist writer
    public void closeWishList() {
        wlwriter.close();
    }

    // MODIFIES: this
    // EFFECTS: closes donelist writer
    public void closeDoneList() {
        dlwriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to wishlist file
    private void saveToWishListFile(String json) {
        wlwriter.print(json);
    }

    // MODIFIES: this
    // EFFECTS: writes string to donelist file
    private void saveToDoneListFile(String json) {
        dlwriter.print(json);
    }
}

// Citation: this class was modeled using the sample application
// "JsonSerializationDemo"
