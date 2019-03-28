package servlet;

import static org.junit.Assert.*;

import com.google.gson.Gson;
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
        Field stField = db.getClass().getDeclaredField("st");
        stField.setAccessible(true);
        assertNotNull(stField.get(db));
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
        assertEquals("hashvalue", pwInfo[1]); //TODO: Find hashed password for test user
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
        Gson gson = new Gson();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url");
        String infoString = gson.toJson(info);
        ArrayList<String> list = new ArrayList<>(Collections.singletonList(infoString));
        assertEquals(list, db.getLists("testuser", "Favorites"));
        assertEquals(list, db.getLists("testuser", "To Explore"));
        assertEquals(list, db.getLists("testuser", "Do Not Show"));
    }

    @Test
    //update lists
    public void updateListsTest() {
        Database db = new Database();
        Gson gson = new Gson();
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url");
        String infoString = gson.toJson(info);
        ArrayList<String> list = new ArrayList<>(Collections.singletonList(infoString));
        Boolean favUpdate = db.updateLists("testuser", "Favorites", list);
        Boolean expUpdate = db.updateLists("testuser", "To Explore", list);
        Boolean dnsUpdate = db.updateLists("testuser", "Do Not Show", list);
        assertTrue(favUpdate && expUpdate && dnsUpdate);
        getListsTest();
    }
}
