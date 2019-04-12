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
}
