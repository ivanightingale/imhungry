package servlet;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import info.Info;
import info.RecipeInfo;
import info.RestaurantInfo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

public class DatabaseTest
{
    @Test
    //database class constructor test
    public void constructorTest() throws NoSuchFieldException, IllegalAccessException{
        Database db = new Database();
        Field connField = db.getClass().getDeclaredField("conn");
        connField.setAccessible(true);
        assertNotNull(connField.get(db));
    }

    @Test
    //check if user is in database test
    public void checkUserTest() {
        Database db = new Database();
        assertTrue(db.checkUser("testuser"));
    }

    @Test
    //retrieve hashed password and salt
    public void getPasswordInfoTest() {
        Database db = new Database();
        String[] pwInfo = db.getPasswordInfo("testuser");
        assertEquals(2, pwInfo.length);
        assertEquals("abcdefgh", pwInfo[0]);
        assertEquals("01aNr7Y+2naXyH1+6tXqHc3KBDV86mDDFSHdn2Mjl2vB5oAxQjSX1wUkwzN/o7oTovwN0APxHf1W1hKJb5DBfw==", pwInfo[1]);
    }

    @Test
    //retrieve userID
    public void getUserIDTest() {
        Database db = new Database();
        assertEquals(1, db.getUserID("testuser"));
    }

    @Test
    //user creation
    public void createUserTest() {
        Database db = new Database();
        Boolean madeUser = db.createUser("testuser2", "abcdefghhashvalue", "abcdefgh");
        assertTrue(madeUser);
        checkUserTest();
        getPasswordInfoTest();
    }

    @Test
    //retrieve lists
    public void getListsTest() {
        Database db = new Database();
        //restaurant test
        RestaurantInfo rinfo = new RestaurantInfo("testRest", 5, "placeID", "adress" , 8, "drivetime", 8, "phone", "url");
        ArrayList<Info> list = new ArrayList<>(Collections.singletonList(rinfo));
        db.updateLists(1, true,"Favorites",  rinfo);
        db.updateLists(1, true,"To Explore",  rinfo);
        db.updateLists(1, true,"Do Not Show",  rinfo);

        //recipe test
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url", 1);
        list.addAll(Collections.singletonList(info));
        db.updateLists(1, true,"Favorites",  info);
        db.updateLists(1, true,"To Explore",  info);
        db.updateLists(1, true,"Do Not Show",  info);
        assertEquals(list, db.getLists(1, "Favorites"));
        assertEquals(list, db.getLists(1, "To Explore"));
        assertEquals(list, db.getLists(1, "Do Not Show"));
        //assertEquals(list, db.getLists(1, "Favorites"));
        //assertEquals(list, db.getLists(1, "To Explore"));
        //assertEquals(list, db.getLists(1, "Do Not Show"));

    }

    @Test
    //update lists
    public void updateListsTest() {
        Database db = new Database();
        Gson gson = new Gson();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url",1);
        //ArrayList<Info> list = new ArrayList<>(Collections.singletonList(info));
        //removing recipes from the list
        Boolean favUpdate = db.updateLists(1, false,"Favorites",  info);
        System.out.println(favUpdate);
        Boolean expUpdate = db.updateLists(1, false,"To Explore", info);
        System.out.println(expUpdate);
        Boolean dnsUpdate = db.updateLists(1, false,"Do Not Show", info);
        System.out.println(dnsUpdate);
        //adding recipes back to the list
        Boolean favUpdate2 = db.updateLists(1, true,"Favorites",  info);
        System.out.println(favUpdate2);
        Boolean expUpdate2 = db.updateLists(1, true,"To Explore", info);
        System.out.println(expUpdate2);
        Boolean dnsUpdate2 = db.updateLists(1, true,"Do Not Show", info);
        System.out.println(dnsUpdate2);
        assertTrue(favUpdate && expUpdate && dnsUpdate && favUpdate2 && expUpdate2 && dnsUpdate2);
        RestaurantInfo rinfo = new RestaurantInfo("testRest", 5, "placeID", "adress" , 8, "drivetime", 8, "phone", "url");
        //removing restaurants from the lists
        Boolean rfavUpdate = db.updateLists(1, false,"Favorites",  rinfo);
        Boolean rexpUpdate = db.updateLists(1, false,"To Explore", rinfo);
        Boolean rdnsUpdate = db.updateLists(1, false,"Do Not Show", rinfo);
        //adding restaurants to lists
        Boolean rfavUpdate2 = db.updateLists(1, true,"Favorites",  rinfo);
        Boolean rexpUpdate2 = db.updateLists(1, true,"To Explore", rinfo);
        Boolean rdnsUpdate2 = db.updateLists(1, true,"Do Not Show", rinfo);
        assertTrue( rfavUpdate && rexpUpdate && rdnsUpdate && rfavUpdate2 && rexpUpdate2 && rdnsUpdate2 );
        getListsTest();
    }

    @Test
    //get password info for a fake user
    public void notAUserPasswordTest() {
        Database db = new Database();
        String[] pwInfo = db.getPasswordInfo("notauser");
        assertEquals("", pwInfo[0]);
        assertEquals("", pwInfo[1]);
    }

    @Test
    //Get user ID for a fake user
    public void notAUserIDTest() {
        Database db = new Database();
        assertEquals(-1, db.getUserID("notauser"));
    }

    @Test
    //Create a user that already exists
    public void makeExistingUserTest() {
        Database db = new Database();
        assertTrue(!db.createUser("testuser", "hash", "salt"));
    }

    @Test
    //Remove a nonexistent recipe and restaurant
    public void removeFakeRecipeRestaurant() {
        Database db = new Database();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 329853468, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url", 1);
        assertTrue(!db.updateLists(1, false, "Favorites", info));
        RestaurantInfo rinfo = new RestaurantInfo("testRest", 5, "notaplaceID", "adress" , 8, "drivetime", 8, "phone", "url");
        assertTrue(!db.updateLists(1, false, "Favorites", rinfo));
    }

    @Test
    //Remove a recipe and restaurant not in a users list
    public void removeRecipeRestaurantFromWrongUser() {
        Database db = new Database();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url", 1);
        assertTrue(!db.updateLists(3, false, "Favorites", info));
        RestaurantInfo rinfo = new RestaurantInfo("testRest", 5, "placeID", "adress" , 8, "drivetime", 8, "phone", "url");
        assertTrue(!db.updateLists(3, false, "Favorites", rinfo));
    }

    @Test
    //Add a new, random, recipe and restaurant
    public void addBrandNewRecipeRestaurant() {
        Database db = new Database();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url", 1);
        RestaurantInfo rinfo = new RestaurantInfo("testRest", 5, "placeID", "adress" , 8, "drivetime", 8, "phone", "url");
        int randID = new Random().nextInt();
        info.recipeID = randID;
        rinfo.placeID = String.valueOf(randID);
        assertTrue(db.updateLists(1, true, "Favorites", info));
        assertTrue(db.updateLists(1, true, "Favorites", rinfo));
        db.updateLists(1, false, "Favorites", info);
        db.updateLists(1, false, "Favorites", rinfo);
    }
}
