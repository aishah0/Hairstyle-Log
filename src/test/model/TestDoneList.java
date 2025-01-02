package model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Tests for the DoneList Class
public class TestDoneList {

    DoneList testDoneList;
    Hairstyle h4;
    Hairstyle h5;
    Hairstyle h6;

    @BeforeEach
    void runBefore() {
        testDoneList = new DoneList();
        h4 = new Hairstyle("island twists", 160);
        h5 = new Hairstyle("mini boho braids", 200);
        h6 = new Hairstyle("medium faux locs", 130);
    }

    @Test
    void testConstructor() {
        assertNull(testDoneList.getFirstDoneHairstyle());
        testDoneList.addDoneHairstyle(h4);
        testDoneList.addDoneHairstyle(h5);
        assertEquals(h4, testDoneList.getFirstDoneHairstyle());
        assertEquals("island twists", h4.getName());
        assertEquals(160, h4.getCost());
        assertTrue(testDoneList.containsDoneHairstyle(h4));
        assertTrue(testDoneList.containsDoneHairstyle(h5));
        assertFalse(testDoneList.containsDoneHairstyle(h6));
    }

    @Test
    void testAddDoneHairstyle() {
        assertNull(testDoneList.getFirstDoneHairstyle());
        testDoneList.addDoneHairstyle(h4);
        assertEquals(h4, testDoneList.getFirstDoneHairstyle());
        testDoneList.addDoneHairstyle(h5);
        testDoneList.addDoneHairstyle(h6);
        assertTrue(testDoneList.containsDoneHairstyle(h4));
        assertTrue(testDoneList.containsDoneHairstyle(h5));
        assertTrue(testDoneList.containsDoneHairstyle(h6));
    }

    @Test
    void testcontainsDoneHairstyle() {
        testDoneList.addDoneHairstyle(h6);
        assertTrue(testDoneList.containsDoneHairstyle(h6));
        assertFalse(testDoneList.containsDoneHairstyle(h4));
        testDoneList.addDoneHairstyle(h4);
        assertTrue(testDoneList.containsDoneHairstyle(h4));
        assertTrue(testDoneList.containsDoneHairstyle(h6));
        assertFalse(testDoneList.containsDoneHairstyle(h5));
    }

    @Test
    void testGetFirstDoneHairstyle() {
        assertEquals(null, testDoneList.getFirstDoneHairstyle());
        testDoneList.addDoneHairstyle(h4);
        testDoneList.addDoneHairstyle(h5);
        assertEquals(h4, testDoneList.getFirstDoneHairstyle());
    }


    @Test
    void testGetAllDoneHairstyles() {
        testDoneList.addDoneHairstyle(h4);
        testDoneList.addDoneHairstyle(h5);
        testDoneList.addDoneHairstyle(h6);
        ArrayList<Hairstyle> list2 = new ArrayList<>();
        list2.add(h4);
        list2.add(h5);
        list2.add(h6);
        assertEquals(testDoneList.getAllDoneHairstyles(), list2);
    }



}
