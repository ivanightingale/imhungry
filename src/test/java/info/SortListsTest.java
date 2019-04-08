package info;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SortListsTest {
    ArrayList<Info> actual;
    @Before
    public void initialize() {
        actual = new ArrayList<Info>();
    }

    // tests sorting alphabetically
    @Test
    public void sortAlphabeticallyTest() {
        // tests if out of order
        actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "1", 1, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 1, "1", "1", 1, "1", 1, "1", "1"));
        actual.add(new RestaurantInfo("Burger King", 1, "1", "1", 1, "1", 1, "1", "1"));

        actual = SortLists.sortAlphabetically(actual);
        assertEquals("Burger King", actual.get(0).name);
        assertEquals("McDonald's", actual.get(1).name);
        assertEquals("Wendy's", actual.get(2).name);

        // tests if in order
        actual = new ArrayList<Info>();
        actual.add(new RestaurantInfo("Burger King", 1, "1", "1", 1, "1", 1, "1", "1"));
        actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "1", 1, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 1, "1", "1", 1, "1", 1, "1", "1"));

        actual = SortLists.sortAlphabetically(actual);
        assertEquals("Burger King", actual.get(0).name);
        assertEquals("McDonald's", actual.get(1).name);
        assertEquals("Wendy's", actual.get(2).name);
    }

    // tests sorting by distance
    @Test
    public void sortByDriveTimeTest() {
        // tests if out of order
        actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "10", 10, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 1, "1", "1", 1, "15", 15, "1", "1"));
        actual.add(new RestaurantInfo("Burger King", 1, "1", "1", 1, "5", 5, "1", "1"));

        actual = SortLists.sortByDriveTime(actual);
        RestaurantInfo ri;
        ri = (RestaurantInfo) actual.get(0);
        assertEquals(5, ri.driveTimeValue);
        ri = (RestaurantInfo) actual.get(1);
        assertEquals(10, ri.driveTimeValue);
        ri = (RestaurantInfo) actual.get(2);
        assertEquals(15, ri.driveTimeValue);

        // tests if in order
        actual = new ArrayList<Info>();
        actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "10", 5, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 1, "1", "1", 1, "15", 10, "1", "1"));
        actual.add(new RestaurantInfo("Burger King", 1, "1", "1", 1, "5", 15, "1", "1"));

        actual = SortLists.sortByDriveTime(actual);
        ri = (RestaurantInfo) actual.get(0);
        assertEquals(5, ri.driveTimeValue);
        ri = (RestaurantInfo) actual.get(1);
        assertEquals(10, ri.driveTimeValue);
        ri = (RestaurantInfo) actual.get(2);
        assertEquals(15, ri.driveTimeValue);
    }

    // tests sorting by price
    @Test
    public void sortByPriceTest() {
        // tests if out of order
        actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 2, "1", 1, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 1, "1", "1", 3, "1", 1, "1", "1"));
        actual.add(new RestaurantInfo("Burger King", 1, "1", "1", 1, "1", 1, "1", "1"));

        actual = SortLists.sortByPrice(actual);
        RestaurantInfo ri;
        ri = (RestaurantInfo) actual.get(0);
        assertEquals("$", ri.priceLevel);
        ri = (RestaurantInfo) actual.get(1);
        assertEquals("$$", ri.priceLevel);
        ri = (RestaurantInfo) actual.get(2);
        assertEquals("$$$", ri.priceLevel);

        // tests if in order
        actual = new ArrayList<Info>();
        actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "10", 5, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 1, "1", "1", 2, "15", 10, "1", "1"));
        actual.add(new RestaurantInfo("Burger King", 1, "1", "1", 3, "5", 15, "1", "1"));

        actual = SortLists.sortByPrice(actual);
        ri = (RestaurantInfo) actual.get(0);
        assertEquals("$", ri.priceLevel);
        ri = (RestaurantInfo) actual.get(1);
        assertEquals("$$", ri.priceLevel);
        ri = (RestaurantInfo) actual.get(2);
        assertEquals("$$$", ri.priceLevel);
    }
}
