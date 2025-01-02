package persistence;

import model.WishList;
import model.DoneList;
import model.Hairstyle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkHairstyle(String name,  int cost, Hairstyle hairstyle) {
        assertEquals(name, hairstyle.getName());
        assertEquals(cost, hairstyle.getCost());
    }
}
