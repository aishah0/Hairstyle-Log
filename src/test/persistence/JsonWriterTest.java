package persistence;

import model.WishList;
import model.DoneList;
import model.Hairstyle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidWishListFile() {
        try {
            WishList wl = new WishList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.openWishList();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvalidDoneListFile() {
        try {
            DoneList dl = new DoneList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.openDoneList();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWishList() {
        try {
            WishList wl = new WishList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWishlist.json");
            writer.openWishList();
            writer.writeWishList(wl);
            writer.closeWishList();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWishlist.json");
            wl = reader.readWishList();
            assertTrue(wl.getAllHairstyles().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyDoneList() {
        try {
            DoneList dl = new DoneList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDonelist.json");
            writer.openDoneList();
            writer.writeDoneList(dl);
            writer.closeDoneList();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDonelist.json");
            dl = reader.readDoneList();
            assertTrue(dl.getAllDoneHairstyles().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWishList() {
        try {
            WishList wl = new WishList();
            wl.addHairstyle(new Hairstyle("box braids", 90));
            wl.addHairstyle(new Hairstyle("cornrows", 65));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWishlist.json");
            writer.openWishList();
            writer.writeWishList(wl);
            writer.closeWishList();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWishlist.json");
            wl = reader.readWishList();
            List<Hairstyle> hairstyles = wl.getAllHairstyles();
            assertEquals(2, hairstyles.size());
            assertEquals(hairstyles.get(0).getName(), "box braids");
            assertEquals(hairstyles.get(1).getName(), "cornrows");
            assertEquals(hairstyles.get(0).getCost(), 90);
            assertEquals(hairstyles.get(1).getCost(), 65);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDoneList() {
        try {
            DoneList dl = new DoneList();
            dl.addDoneHairstyle(new Hairstyle("silk press", 120));
            dl.addDoneHairstyle(new Hairstyle("braided ponytail", 115));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDonelist.json");
            writer.openDoneList();
            writer.writeDoneList(dl);
            writer.closeDoneList();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDoneList.json");
            dl = reader.readDoneList();
            List<Hairstyle> hairstyles = dl.getAllDoneHairstyles();
            assertEquals(2, hairstyles.size());
            assertEquals(hairstyles.get(0).getName(), "silk press");
            assertEquals(hairstyles.get(1).getName(), "braided ponytail");
            assertEquals(hairstyles.get(0).getCost(), 120);
            assertEquals(hairstyles.get(1).getCost(), 115);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
