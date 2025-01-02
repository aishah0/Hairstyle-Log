package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Tests for the WishList class
public class TestWishList {
    
    WishList testWishList;
    Hairstyle h1;
    Hairstyle h2;
    Hairstyle h3;


    @BeforeEach
    void runBefore() {
        testWishList = new WishList();
        h1 = new Hairstyle("shoulder length butterfly locs", 140);
        h2 = new Hairstyle("knotless box braids", 200);
        h3 = new Hairstyle("natural cornrows", 80);
    }

    @Test
    void testConstructor() {
        assertNull(testWishList.getFirstHairstyle());
        testWishList.addHairstyle(h1);
        testWishList.addHairstyle(h2);
        assertEquals(h1, testWishList.getFirstHairstyle());
        assertEquals("shoulder length butterfly locs", h1.getName());
        assertEquals(140, h1.getCost());
        assertTrue(testWishList.containsHairstyle(h1));
        assertTrue(testWishList.containsHairstyle(h2));
        assertFalse(testWishList.containsHairstyle(h3));
    }

    @Test
    void testAddHairstyle() {
        assertNull(testWishList.getFirstHairstyle());
        testWishList.addHairstyle(h1);
        assertEquals(h1, testWishList.getFirstHairstyle());
        testWishList.addHairstyle(h2);
        testWishList.addHairstyle(h3);
        assertTrue(testWishList.containsHairstyle(h1));
        assertTrue(testWishList.containsHairstyle(h2));
        assertTrue(testWishList.containsHairstyle(h3));
    }
    
    @Test
    void testRemoveHairstyle() {
        testWishList.addHairstyle(h1);
        assertTrue(testWishList.removeHairstyle(h1.getName()));
        assertNull(testWishList.getFirstHairstyle());
        testWishList.addHairstyle(h1);
        testWishList.addHairstyle(h2);
        assertFalse(testWishList.removeHairstyle(h3.getName()));
        assertTrue(testWishList.removeHairstyle(h2.getName()));
        assertFalse(testWishList.containsHairstyle(h2));
        assertTrue(testWishList.containsHairstyle(h1));
        
    }


    @Test
    void testRemoveMultipleHairstyles() {
        testWishList.addHairstyle(h1);
        testWishList.addHairstyle(h2);
        testWishList.addHairstyle(h3);
        assertTrue(testWishList.removeHairstyle(h2.getName()));
        assertTrue(testWishList.removeHairstyle(h3.getName()));
        assertFalse(testWishList.containsHairstyle(h2));
        assertTrue(testWishList.containsHairstyle(h1));
        assertFalse(testWishList.containsHairstyle(h3));
    }

    @Test
    void testContainsHairstyle() {
        testWishList.addHairstyle(h3);
        assertTrue(testWishList.containsHairstyle(h3));
        assertFalse(testWishList.containsHairstyle(h1));
        testWishList.addHairstyle(h1);
        assertTrue(testWishList.containsHairstyle(h1));
        assertTrue(testWishList.containsHairstyle(h3));
        assertFalse(testWishList.containsHairstyle(h2));
    }

    @Test
    void testEditHairstyleCost() {
        testWishList.addHairstyle(h1);
        testWishList.addHairstyle(h2);
        assertEquals(h1.getCost(), 140);
        assertEquals(h2.getCost(), 200);
        testWishList.editHairstyleCost(h1.getName(), 100);
        testWishList.editHairstyleCost(h2.getName(), 210);
        assertEquals(h1.getCost(), 100);
        assertEquals(h2.getCost(), 210);
    }

    @Test
    void testGetFirstHairstyle() {
        assertEquals(null, testWishList.getFirstHairstyle());
        testWishList.addHairstyle(h1);
        testWishList.addHairstyle(h2);
        assertEquals(h1, testWishList.getFirstHairstyle());
    }

    @Test
    void testGetAllHairstyles() {
        testWishList.addHairstyle(h1);
        testWishList.addHairstyle(h2);
        testWishList.addHairstyle(h3);
        ArrayList<Hairstyle> list2 = new ArrayList<>();
        list2.add(h1);
        list2.add(h2);
        list2.add(h3);
        assertEquals(testWishList.getAllHairstyles(), list2);
    }

}
