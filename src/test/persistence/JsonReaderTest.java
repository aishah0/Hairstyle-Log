package persistence;

import model.WishList;
import model.DoneList;
import model.Hairstyle;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentWishListFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WishList wl = reader.readWishList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNonExistentDoneListFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DoneList dl = reader.readDoneList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWishList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWishlist.json");
        try {
            WishList wl = reader.readWishList();
            assertTrue(wl.getAllHairstyles().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyDoneList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDonelist.json");
        try {
            DoneList dl = reader.readDoneList();
            assertTrue(dl.getAllDoneHairstyles().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWishList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWishlist.json");
        try {
            WishList wl = reader.readWishList();
            List<Hairstyle> hairstylestodo = wl.getAllHairstyles();
            assertEquals(2, hairstylestodo.size());
            checkHairstyle("butterfly locs", 180, hairstylestodo.get(0));
            checkHairstyle("long twists", 150, hairstylestodo.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDoneList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDonelist.json");
        try {
            DoneList dl = reader.readDoneList();
            List<Hairstyle> donehairstyles = dl.getAllDoneHairstyles();
            assertEquals(2, donehairstyles.size());
            checkHairstyle("box braids", 200, donehairstyles.get(0));
            checkHairstyle("low taper", 30, donehairstyles.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
