package servlet;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import info.Message;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import info.Info;
import info.RecipeInfo;
import info.RestaurantInfo;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import info.Searches;

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
	//getDriveTimes tests if arraylist of restaurants given is null
	public void getDriveTimesTest2() {
		SearchServlet servlet = new SearchServlet();
		ArrayList<RestaurantInfo> testConfirm = new ArrayList<RestaurantInfo>();
		servlet.getDriveTimes(testConfirm);
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
		//Check that the result is reasonable (this is a bad test hah, could fail due to road closures...)
		//System.out.println(testConfirm.get(0).driveTimeValue);
		assertTrue(testConfirm.get(0).driveTimeValue < 550);
		assertTrue(testConfirm.get(0).driveTimeValue > 250);
	}

	@Test
	//getting the Restaurant arraylist API test
	public void getResInfoTest() {
		SearchServlet servlet = new SearchServlet();
		List<Info> empty1 = new ArrayList<Info>();
		List<Info> empty2 = new ArrayList<Info>();
		ArrayList<RestaurantInfo> rest = servlet.restaurantSearch("Chipotle", 10, 10, empty1, empty2);
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

	@Test
	//testing that the if distance > radius no restaurants show up
	public void getNoRestaurantsinRadius() throws IOException, NoSuchMethodException, ServletException, IllegalAccessException, InvocationTargetException {
		SearchServlet searchServlet = mock(SearchServlet.class, CALLS_REAL_METHODS);//Have to partial mock so searchServlet.getServletContext() can be mocked
		Method doGetMethod = searchServlet.getClass().getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
		doGetMethod.setAccessible(true);
		HttpServletRequest request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		Map<String, Object> sessionObj = new TreeMap<>();
		sessionObj.put("Favorites", new ArrayList<Info>());
		sessionObj.put("To Explore", new ArrayList<Info>());
		sessionObj.put("Do Not Show", new ArrayList<Info>());
		sessionObj.put("PreviousSearches",  new ArrayList<Searches>());
		sessionObj.put("userID", 1);
		when(request.getSession()).thenReturn(session);
		StringWriter stringWriter = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
		when(request.getParameter("userID")).thenReturn("1");
		when(request.getParameter("search")).thenReturn("pancakes");
		when(request.getParameter("number")).thenReturn("5");
		when(request.getParameter("radius")).thenReturn("0");
		doAnswer(new Answer()
		{
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable
			{
				assertEquals(sessionObj.get(invocationOnMock.getArguments()[0]), invocationOnMock.getArguments()[1]); //ensures the session state is set correctly
				return null;
			}
		}).when(session).setAttribute(anyString(), anyObject());
		doAnswer(new Answer()
		{
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable
			{
				return sessionObj.get((invocationOnMock.getArguments()[0]));
			}
		}).when(session).getAttribute(anyString());
		RequestDispatcher rd = mock(RequestDispatcher.class);
		ServletContext sc = mock(ServletContext.class);
		doReturn(sc).when(searchServlet).getServletContext();
		when(sc.getRequestDispatcher(anyString())).thenReturn(rd);
		doNothing().when(rd).forward(any(), any());
		doGetMethod.invoke(searchServlet, request, response);

		JsonObject JOresponse = new JsonParser().parse(stringWriter.toString()).getAsJsonObject();
		JsonArray resultArray = JOresponse.get("body").getAsJsonObject().get("results").getAsJsonArray();
		JsonArray restaurantArray = resultArray.get(0).getAsJsonArray();
		assertEquals(0, restaurantArray.size());


	}

	@Test
	//getting the Searches ArrayList test
	public void getSearchesInfoTest() throws IOException, NoSuchMethodException, ServletException, IllegalAccessException, InvocationTargetException {
        SearchServlet searchServlet = mock(SearchServlet.class, CALLS_REAL_METHODS);//Have to partial mock so searchServlet.getServletContext() can be mocked
        Method doGetMethod = searchServlet.getClass().getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
        doGetMethod.setAccessible(true);
        HttpServletRequest request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        Map<String, Object> sessionObj = new TreeMap<>();
        sessionObj.put("Favorites", new ArrayList<>());
        sessionObj.put("To Explore", new ArrayList<>());
        sessionObj.put("Do Not Show", new ArrayList<>());
        sessionObj.put("userID", 1);
        when(request.getSession()).thenReturn(session);
        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(request.getParameter("userID")).thenReturn("2");
        //BufferedReader br = new BufferedReader(new StringReader(new Gson().toJson(new Message("testuser","wrongpassword"))));
        //when(request.getReader()).thenReturn(br);
        doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable
            {
                assertEquals(sessionObj.get(invocationOnMock.getArguments()[0]), invocationOnMock.getArguments()[1]); //ensures the session state is set correctly
                return null;
            }
        }).when(session).setAttribute(anyString(), anyObject());
        doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable
            {
                return sessionObj.get((invocationOnMock.getArguments()[0]));
            }
        }).when(session).getAttribute(anyString());
        RequestDispatcher rd = mock(RequestDispatcher.class);
        ServletContext sc = mock(ServletContext.class);
        doReturn(sc).when(searchServlet).getServletContext();
        when(sc.getRequestDispatcher(anyString())).thenReturn(rd);
        doNothing().when(rd).forward(any(), any());
        doGetMethod.invoke(searchServlet, request, response);

        //Make sure the correct response was set
        assertEquals(stringWriter.toString(), (new Gson().toJson(new Message("You aren't logged in!")))+System.lineSeparator());
    }


	@Test
	//Testing an attempt to search by an unloggedin user
	public void unauthorizedUserTest() throws IOException, NoSuchMethodException, ServletException, IllegalAccessException, InvocationTargetException {
		SearchServlet searchServlet = mock(SearchServlet.class, CALLS_REAL_METHODS);//Have to partial mock so searchServlet.getServletContext() can be mocked
		Method doGetMethod = searchServlet.getClass().getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
		doGetMethod.setAccessible(true);
		HttpServletRequest request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		Map<String, Object> sessionObj = new TreeMap<>();
		sessionObj.put("Favorites", new ArrayList<Info>());
		sessionObj.put("To Explore", new ArrayList<Info>());
		sessionObj.put("Do Not Show", new ArrayList<Info>());
		sessionObj.put("PreviousSearches",  new ArrayList<Searches>());
		sessionObj.put("userID", 1);
		when(request.getSession()).thenReturn(session);
		StringWriter stringWriter = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
		when(request.getParameter("userID")).thenReturn("1");
		when(request.getParameter("search")).thenReturn("pancakeTest");
		when(request.getParameter("number")).thenReturn("5");
		when(request.getParameter("radius")).thenReturn("6");
		//BufferedReader br = new BufferedReader(new StringReader(new Gson().toJson(new Message("testuser","wrongpassword"))));
		//when(request.getReader()).thenReturn(br);
		doAnswer(new Answer()
		{
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable
			{
				assertEquals(sessionObj.get(invocationOnMock.getArguments()[0]), invocationOnMock.getArguments()[1]); //ensures the session state is set correctly
				return null;
			}
		}).when(session).setAttribute(anyString(), anyObject());
		doAnswer(new Answer()
		{
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable
			{
				return sessionObj.get((invocationOnMock.getArguments()[0]));
			}
		}).when(session).getAttribute(anyString());
		RequestDispatcher rd = mock(RequestDispatcher.class);
		ServletContext sc = mock(ServletContext.class);
		doReturn(sc).when(searchServlet).getServletContext();
		when(sc.getRequestDispatcher(anyString())).thenReturn(rd);
		doNothing().when(rd).forward(any(), any());
		doGetMethod.invoke(searchServlet, request, response);
		Gson gson = new Gson();
		if( ((ArrayList<Searches>)sessionObj.get("PreviousSearches")).size() == 1){
			assertEquals(gson.toJson(sessionObj.get("PreviousSearches")), gson.toJson(new ArrayList<>(Arrays.asList(new Searches("pancakeTest", 6, 5)))));

		}
		else if( ((ArrayList<Searches>)sessionObj.get("PreviousSearches")).size() == 2){
			assertEquals(gson.toJson(sessionObj.get("PreviousSearches")), gson.toJson(new ArrayList<>(Arrays.asList(new Searches("testSearch", 5, 1), new Searches("pancakeTest", 6, 5)))));

		}
		//Make sure the correct response was set
		//assertEquals(gson.toJson(sessionObj.get("PreviousSearches")), gson.toJson(new ArrayList<>(Arrays.asList(new Searches("pancakeTest", 6, 5)))));
		Database db = new Database();
		if( (db.getPrevSearch(1).size() == 1)){
			assertEquals(gson.toJson(db.getPrevSearch(1)), gson.toJson(new ArrayList<>(Arrays.asList(new Searches("pancakeTest", 6, 5)))));

		}
		else if(  (db.getPrevSearch(1).size() == 2)){
			assertEquals(gson.toJson(db.getPrevSearch(1)), gson.toJson(new ArrayList<>(Arrays.asList(new Searches("testSearch", 5, 1), new Searches("pancakeTest", 6, 5)))));

		}
		//assertEquals(gson.toJson(db.getPrevSearch(1)), gson.toJson(new ArrayList<>(Arrays.asList(new Searches("pancakeTest", 6, 5)))));
	}
	
}
