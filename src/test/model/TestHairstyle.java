package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Tests for the Hairstyle class
public class TestHairstyle {
    Hairstyle h1;
    Hairstyle h2;
    
    @BeforeEach
    void runBefore() {
        h1 = new Hairstyle("shoulder length butterfly locs", 140);
        h2 = new Hairstyle("knotless box braids", 200);

    }

    @Test
    void testGetName() {
        assertEquals("shoulder length butterfly locs", h1.getName());
        assertEquals("knotless box braids", h2.getName());
    }

    @Test
    void testGetCost() {
        assertEquals(140, h1.getCost());
        assertEquals(200, h2.getCost());
    }

    @Test
    void testSetCost() {
        h1.setCost(250);
        assertEquals(250, h1.getCost());
        h2.setCost(150);
        assertEquals(150, h2.getCost());
    }
}
