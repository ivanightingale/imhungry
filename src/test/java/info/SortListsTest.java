package info;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortListsTest {
    List<Info> actual;

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
        actual.add(new RecipeInfo("Chicken Marsala", 1, 1, 1, 1, null, null, "1"));
        actual.add(new RecipeInfo("Clam Chowder", 1, 1, 1, 1, null, null, "1"));
        actual.add(new RecipeInfo("Mac & Cheese", 1, 1, 1, 1, null, null, "1"));

        actual = SortLists.sortAlphabetically(actual);
        assertEquals("Burger King", actual.get(0).name);
        assertEquals("Chicken Marsala", actual.get(1).name);
        assertEquals("Clam Chowder", actual.get(2).name);
        assertEquals("Mac & Cheese", actual.get(3).name);
        assertEquals("McDonald's", actual.get(4).name);
        assertEquals("Wendy's", actual.get(5).name);

        // tests if in order
        actual = new ArrayList<Info>();
        actual.add(new RestaurantInfo("Burger King", 1, "1", "1", 1, "1", 1, "1", "1"));
        actual.add(new RecipeInfo("Chicken Marsala", 1, 1, 1, 1, null, null, "1"));
        actual.add(new RecipeInfo("Clam Chowder", 1, 1, 1, 1, null, null, "1"));
        actual.add(new RecipeInfo("Mac & Cheese", 1, 1, 1, 1, null, null, "1"));
        actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "1", 1, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 1, "1", "1", 1, "1", 1, "1", "1"));

        actual = SortLists.sortAlphabetically(actual);
        assertEquals("Burger King", actual.get(0).name);
        assertEquals("Chicken Marsala", actual.get(1).name);
        assertEquals("Clam Chowder", actual.get(2).name);
        assertEquals("Mac & Cheese", actual.get(3).name);
        assertEquals("McDonald's", actual.get(4).name);
        assertEquals("Wendy's", actual.get(5).name);
    }

    // tests sorting by distance
    @Test
    public void sortByRatingTest() {
        // tests if out of order
        actual.add(new RestaurantInfo("McDonald's", 5, "1", "1", 1, "1", 10, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 2, "1", "1", 1, "1", 15, "1", "1"));
        actual.add(new RestaurantInfo("Burger King", 1, "1", "1", 1, "1", 5, "1", "1"));
        actual.add(new RecipeInfo("Chicken Marsala", 3, 1, 1, 1, null, null, "1"));
        actual.add(new RecipeInfo("Clam Chowder", 6, 1, 1, 1, null, null, "1"));
        actual.add(new RecipeInfo("Mac & Cheese", 4, 1, 1, 1, null, null, "1"));


        actual = SortLists.sortByRating(actual);
        Info ri;
        ri = actual.get(0);
        assertEquals(6, ri.rating);
        ri = actual.get(1);
        assertEquals(5, ri.rating);
        ri = actual.get(2);
        assertEquals(4, ri.rating);
        ri = actual.get(3);
        assertEquals(3, ri.rating);
        ri = actual.get(4);
        assertEquals(2, ri.rating);
        ri = actual.get(5);
        assertEquals(1, ri.rating);

        // tests if in order
        actual = new ArrayList<Info>();
        actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "10", 5, "1", "1"));
        actual.add(new RestaurantInfo("Wendy's", 2, "1", "1", 1, "15", 10, "1", "1"));
        actual.add(new RestaurantInfo("Burger King", 3, "1", "1", 1, "5", 15, "1", "1"));
        actual.add(new RecipeInfo("Chicken Marsala", 4, 1, 1, 1, null, null, "1"));
        actual.add(new RecipeInfo("Clam Chowder", 5, 1, 1, 1, null, null, "1"));
        actual.add(new RecipeInfo("Mac & Cheese", 6, 1, 1, 1, null, null, "1"));

        actual = SortLists.sortByRating(actual);
        ri = actual.get(0);
        assertEquals(6, ri.rating);
        ri = actual.get(1);
        assertEquals(5, ri.rating);
        ri = actual.get(2);
        assertEquals(4, ri.rating);
        ri = actual.get(3);
        assertEquals(3, ri.rating);
        ri = actual.get(4);
        assertEquals(2, ri.rating);
        ri = actual.get(5);
        assertEquals(1, ri.rating);
    }
}

