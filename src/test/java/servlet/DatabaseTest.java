package servlet;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import info.Info;
import info.RecipeInfo;
import info.RestaurantInfo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

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
        assertEquals("hashvalue", pwInfo[1]);
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
        Boolean madeUser = db.createUser("testuser2", "hashvalue", "abcdefgh");
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
/*
    @Test
    //update lists
    public void updateListsTest() {
        Database db = new Database();
        Gson gson = new Gson();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url",1);
        //ArrayList<Info> list = new ArrayList<>(Collections.singletonList(info));
        //removing recipes from the list
        Boolean favUpdate = db.updateLists(1, false,"Favorites",  info);
        Boolean expUpdate = db.updateLists(1, false,"To Explore", info);
        Boolean dnsUpdate = db.updateLists(1, false,"Do Not Show", info);
        //adding recipes back to the list
        Boolean favUpdate2 = db.updateLists(1, true,"Favorites",  info);
        Boolean expUpdate2 = db.updateLists(1, true,"To Explore", info);
        Boolean dnsUpdate2 = db.updateLists(1, true,"Do Not Show", info);
        assertTrue(favUpdate && expUpdate && dnsUpdate && favUpdate2 && expUpdate2 && dnsUpdate2);
        RestaurantInfo rinfo = new RestaurantInfo("testRest", 5, "placeID", "adress" , 8, "drivetime", 8, "phone", "url");
        //adding restaurants to lists
        Boolean rfavUpdate2 = db.updateLists(1, true,"Favorites",  rinfo);
        Boolean rexpUpdate2 = db.updateLists(1, true,"To Explore", rinfo);
        Boolean rdnsUpdate2 = db.updateLists(1, true,"Do Not Show", rinfo);
        //removing restaurants from the lists
        Boolean rfavUpdate = db.updateLists(1, false,"Favorites",  rinfo);
        Boolean rexpUpdate = db.updateLists(1, false,"To Explore", rinfo);
        Boolean rdnsUpdate = db.updateLists(1, false,"Do Not Show", rinfo);
        assertTrue( rfavUpdate && rexpUpdate && rdnsUpdate &&favUpdate2 && rexpUpdate2 && rdnsUpdate2 );
        getListsTest();
    }
    */
    @Test
    //move from list to list
    public void moveListTest(){
        Database db = new Database();
        RestaurantInfo rinfo = new RestaurantInfo("testRest2", 5, "newplaceID", "address" , 8, "drivetime", 8, "phone", "url");
        db.updateLists(1, true, "To Explore", rinfo);
        ArrayList<Info> list = db.getLists(1, "Favorites");
        list.add(rinfo);
        ArrayList<String> stringlist = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            stringlist.add(list.get(i).name);
        }
        HashSet<String> hashlistexpected= new HashSet<String>(stringlist);


        db.moveList(1, rinfo, "Favorites", "To Explore");
        ArrayList<Info> dbinfo = (db.getLists(1, "Favorites"));
        ArrayList<String> stringdb = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            stringdb.add((list.get(i).name));
        }
        HashSet<String> hashlistresult = new HashSet<String>(stringdb);

        assertFalse(db.getLists(1, "To Explore").contains(rinfo));
        assertEquals(hashlistexpected, hashlistresult);
    }
}
