package servlet;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import info.Info;
import info.RecipeInfo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
        Boolean madeUser = db.createUser("testuser", "hashvalue", "abcdefgh");
        assertTrue(madeUser);
        checkUserTest();
        getPasswordInfoTest();
    }

    @Test
    //retrieve lists
    public void getListsTest() {
        Database db = new Database();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url", 1);
        ArrayList<Info> list = new ArrayList<>(Collections.singletonList(info));
        assertEquals(list, db.getLists(1, "Favorites"));
        assertEquals(list, db.getLists(1, "To Explore"));
        assertEquals(list, db.getLists(1, "Do Not Show"));
    }

    @Test
    //update lists
    public void updateListsTest() {
        Database db = new Database();
        Gson gson = new Gson();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url",1);
        //ArrayList<Info> list = new ArrayList<>(Collections.singletonList(info));
        //Boolean favUpdate = db.updateLists(1, false,"Favorites",  info);
        //Boolean expUpdate = db.updateLists(1, false,"To Explore", info);
        //Boolean dnsUpdate = db.updateLists(1, false,"Do Not Show", info);
        Boolean favUpdate2 = db.updateLists(1, true,"Favorites",  info);
        Boolean expUpdate2 = db.updateLists(1, true,"To Explore", info);
        Boolean dnsUpdate2 = db.updateLists(1, true,"Do Not Show", info);
        //assertTrue(favUpdate && expUpdate && dnsUpdate && favUpdate2 && expUpdate2 && dnsUpdate2);
        assertTrue(favUpdate2 && expUpdate2 && dnsUpdate2);
        getListsTest();
    }
}
