package info;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchesTest {
    @Test
    //testing with a string, and two ints
    public void instantiation() {
        String searchy = "search";
        int radius = 3;
        int result = 5;
        Searches s = new Searches(searchy, radius, result);
        assertEquals(searchy, s.searchTerm);
        assertEquals(radius, s.specifiedRadius);
        assertEquals(result, s.expectedResults);
    }

    @Test
    //test equality method
    public void equality() {
        String searchy = "search";
        int radius = 3;
        int result = 5;
        Searches s1 = new Searches(searchy, radius, result);
        Searches s2 = new Searches(searchy, radius, result);
        assertEquals(s1, s2);
        s2.expectedResults++;
        assertNotEquals(s1, s2);
    }
}
