package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import info.Info;
import info.RecipeInfo;
import info.RestaurantInfo;

public class SearchServletTest {
	
	@Test
	//URL API value test 1
	public void getImageUrlTest1() {
		SearchServlet servlet = new SearchServlet();
		ArrayList<String> testConfirm = servlet.getImageURLs("Noodle");
		assertEquals(10,testConfirm.size());
	}
	
	@Test
	//URL API value test 2
	public void getImageUrlTest2() {
		SearchServlet servlet = new SearchServlet();
		ArrayList<String> testConfirm = servlet.getImageURLs("Dumpling");
		assertEquals(10,testConfirm.size());
	}
	
	@Test
	//getPhoneandURL test
	public void getPhoneURLTest1() {
		SearchServlet servlet = new SearchServlet();
		RestaurantInfo ri = new RestaurantInfo("Chipotle", 1, "ChIJXQa3SffHwoARbWWdPwK_Ums", "1", 1, "1", 1, "1", "1");
		ArrayList<RestaurantInfo> testConfirm = new ArrayList<RestaurantInfo>();
		testConfirm.add(ri);
		servlet.getPhoneAndURL(testConfirm);
		assertEquals(1,testConfirm.size());
		assertEquals("(323) 766-9775",testConfirm.get(0).phone);
		assertEquals("https://himalayanhouse.menufy.com/#/",testConfirm.get(0).url);
	}
	
	@Test
	//getDriveTimes test
	public void getDriveTimesTest1() {
		SearchServlet servlet = new SearchServlet();
		RestaurantInfo ri = new RestaurantInfo("Chipotle", 1, "ChIJXQa3SffHwoARbWWdPwK_Ums", "1", 1, "1", 1, "1", "1");
		ArrayList<RestaurantInfo> testConfirm = new ArrayList<RestaurantInfo>();
		testConfirm.add(ri);
		servlet.getDriveTimes(testConfirm);
		assertEquals(1,testConfirm.size());
		assertEquals(442,testConfirm.get(0).driveTimeValue);
	}
	@Test
	//getting the Restaurant arraylist API test
	public void getResInfoTest() {
		SearchServlet servlet = new SearchServlet();
		List<Info> empty1 = new ArrayList<Info>();
		List<Info> empty2 = new ArrayList<Info>();
		ArrayList<RestaurantInfo> rest = servlet.restaurantSearch("Chipotle", 10, empty1, empty2);
		assertEquals(10,rest.size());
	}
	
	@Test
	//getting the Recipe arraylist API test
	public void getReciInfoTest() {
		SearchServlet servlet = new SearchServlet();
		List<Info> empty1 = new ArrayList<Info>();
		List<Info> empty2 = new ArrayList<Info>();
		ArrayList<RecipeInfo> rest = servlet.recipeSearch("beef", 5, empty1, empty2);
		assertEquals(5,rest.size());
	}
	
}
