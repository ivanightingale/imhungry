package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import info.Info;
import info.Message;
import info.RecipeInfo;
import info.RestaurantInfo;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ListServletTest {

	@Test
	public void testReorderingAlphabetically() throws Exception {
		ListServlet listServlet = mock(ListServlet.class, CALLS_REAL_METHODS);//Have to partial mock so loginServlet.getServletContext() can be mocked
		Method doPostMethod = listServlet.getClass().getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
		doPostMethod.setAccessible(true);
		HttpServletRequest request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		Map<String, Object> sessionObj = new TreeMap<>();
		// create a test list
		List<Info> actual = new ArrayList<Info>();
		actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "1", 1, "1", "1"));
		actual.add(new RestaurantInfo("Wendy's", 3, "1", "1", 1, "1", 1, "1", "1"));
		actual.add(new RestaurantInfo("Burger King", 5, "1", "1", 1, "1", 1, "1", "1"));
		actual.add(new RecipeInfo("Chicken Marsala", 2, 1, 1, 1, null, null, "1"));
		actual.add(new RecipeInfo("Clam Chowder", 6, 1, 1, 1, null, null, "1"));
		actual.add(new RecipeInfo("Mac & Cheese", 4, 1, 1, 1, null, null, "1"));
		sessionObj.put("Favorites", actual);
		sessionObj.put("hello", "Hello testuser");
		sessionObj.put("userID", 1);

		when(request.getSession()).thenReturn(session);
		StringWriter stringWriter = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
		RecipeInfo ri = new RecipeInfo("Alphabetically", 0, 0, 0, 0, null, null, "");
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new StringReader(gson.toJson(new Message("reorderList", gson.toJson(new Message("Favorites", gson.toJson(ri)))))));
		when(request.getReader()).thenReturn(br);
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
		//RequestDispatcher rd = mock(RequestDispatcher.class);
		//ServletContext sc = mock(ServletContext.class);
		//doReturn(sc).when(listServlet).getServletContext();
		//when(sc.getRequestDispatcher(anyString())).thenReturn(rd);
		//doNothing().when(rd).forward(any(), any());
		doPostMethod.invoke(listServlet, request, response);

		//Make sure the correct response was set
		assertEquals(stringWriter.toString(), (new Gson().toJson(new Message("Changed order of lists to Alphabetically"))+System.lineSeparator()));

	}

	@Test
	public void testReorderingRating() throws Exception {
		ListServlet listServlet = mock(ListServlet.class, CALLS_REAL_METHODS);//Have to partial mock so loginServlet.getServletContext() can be mocked
		Method doPostMethod = listServlet.getClass().getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
		doPostMethod.setAccessible(true);
		HttpServletRequest request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		Map<String, Object> sessionObj = new TreeMap<>();
		// create a test list
		List<Info> actual = new ArrayList<Info>();
		actual.add(new RestaurantInfo("McDonald's", 1, "1", "1", 1, "1", 1, "1", "1"));
		actual.add(new RestaurantInfo("Wendy's", 3, "1", "1", 1, "1", 1, "1", "1"));
		actual.add(new RestaurantInfo("Burger King", 5, "1", "1", 1, "1", 1, "1", "1"));
		actual.add(new RecipeInfo("Chicken Marsala", 2, 1, 1, 1, null, null, "1"));
		actual.add(new RecipeInfo("Clam Chowder", 6, 1, 1, 1, null, null, "1"));
		actual.add(new RecipeInfo("Mac & Cheese", 4, 1, 1, 1, null, null, "1"));
		sessionObj.put("Favorites", actual);
		sessionObj.put("hello", "Hello testuser");
		sessionObj.put("userID", 1);

		when(request.getSession()).thenReturn(session);
		StringWriter stringWriter = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
		RecipeInfo ri = new RecipeInfo("Rating", 0, 0, 0, 0, null, null, "");
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new StringReader(gson.toJson(new Message("reorderList", gson.toJson(new Message("Favorites", gson.toJson(ri)))))));
		when(request.getReader()).thenReturn(br);
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
		//RequestDispatcher rd = mock(RequestDispatcher.class);
		//ServletContext sc = mock(ServletContext.class);
		//doReturn(sc).when(listServlet).getServletContext();
		//when(sc.getRequestDispatcher(anyString())).thenReturn(rd);
		//doNothing().when(rd).forward(any(), any());
		doPostMethod.invoke(listServlet, request, response);


		//Make sure the correct response was set
		assertEquals(stringWriter.toString(), (new Gson().toJson(new Message("Changed order of lists to Rating"))+System.lineSeparator()));

	}

	@Test
	//Check getting grocery list
	public void getGroceryList() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ServletException
	{
		ListServlet listServlet = mock(ListServlet.class, CALLS_REAL_METHODS);//Have to partial mock so loginServlet.getServletContext() can be mocked
		Method doPostMethod = listServlet.getClass().getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
		doPostMethod.setAccessible(true);
		HttpServletRequest request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		Map<String, Object> sessionObj = new TreeMap<>();
		sessionObj.put("hello", "Hello testuser");
		sessionObj.put("userID", 1);
		List<Info> list = new ArrayList<>();
		list.add(new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url", 1));
		sessionObj.put("Grocery", list);
		when(request.getSession()).thenReturn(session);
		StringWriter stringWriter = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
		when(request.getParameter("list")).thenReturn("Grocery");
		//BufferedReader br = new BufferedReader(new StringReader(new Gson().toJson(new Message("userID","2"))));
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
		//RequestDispatcher rd = mock(RequestDispatcher.class);
		//ServletContext sc = mock(ServletContext.class);
		//doReturn(sc).when(listServlet).getServletContext();
		//when(sc.getRequestDispatcher(anyString())).thenReturn(rd);
		//doNothing().when(rd).forward(any(), any());
		doPostMethod.invoke(listServlet, request, response);


		//Make sure the correct response was set
		assertEquals(stringWriter.toString(), (new Gson().toJson(new Message("Grocery", new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")))))+System.lineSeparator());
	}

	@Test
	//doGet test if given input is not one of the predefined lists
	public void doGettest1() throws ServletException, IOException { 
		ListServlet servlet = new ListServlet();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		List<Info> list = new ArrayList<Info>();
		when(request.getParameter("list")).thenReturn("wrongList");
		when(request.getSession().getAttribute("Favorites")).thenReturn(list);
		when(request.getSession().getAttribute("Do Not Show")).thenReturn(list);
		when(request.getSession().getAttribute("To Explore")).thenReturn(list);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);
		servlet.doGet(request, response);
		String written = sw.toString();
		JsonObject json = new Gson().fromJson(written, JsonObject.class);
		String result = json.get("header").getAsString();
		assertEquals("invalid list mistake","Invalid List!",result);
	}
	
	@Test
	//doGet test if given input is empty string
	public void doGettest2() throws ServletException, IOException {
		ListServlet servlet = new ListServlet();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		List<Info> list = new ArrayList<Info>();
		when(request.getParameter("list")).thenReturn("");
		when(request.getSession().getAttribute("Favorites")).thenReturn(list);
		when(request.getSession().getAttribute("Do Not Show")).thenReturn(list);
		when(request.getSession().getAttribute("To Explore")).thenReturn(list);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);
		servlet.doGet(request, response);
		String written = sw.toString();
		JsonObject json = new Gson().fromJson(written, JsonObject.class);
		String result = json.get("header").getAsString();
		assertEquals("invalid list mistake", "Invalid List!",result);
	}
	
	@Test
	//doGet test the fetching of favorite list
	public void doGettest3() throws ServletException, IOException {
		ListServlet servlet = new ListServlet();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		List<Info> listAc = new ArrayList<Info>();
		List<Info> list = new ArrayList<Info>();
		RestaurantInfo ri = new RestaurantInfo("string", 767812, "string", "string", 0, "string", 0, "string", "string");
		listAc.add(ri);
		when(request.getParameter("list")).thenReturn("Favorites");
		when(request.getSession().getAttribute("Favorites")).thenReturn(listAc);
		when(request.getSession().getAttribute("Do Not Show")).thenReturn(list);
		when(request.getSession().getAttribute("To Explore")).thenReturn(list);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);
		servlet.doGet(request, response);
		String written = sw.toString();
		JsonObject json = new Gson().fromJson(written, JsonObject.class);
		String result = json.get("header").getAsString();
		JsonElement jsonelement = json.get("body");
		JsonArray jsonarray = jsonelement.getAsJsonArray();
		JsonObject json2 = jsonarray.get(0).getAsJsonObject();
		String subresult = json2.get("rating").getAsString();
		assertEquals("invalid list name mistake","Favorites",result);
		assertEquals("invalid list content mistake","767812",subresult);
	}
	
	@Test
	//doGet test the fetching of the Do Not Show list test
	public void doGettest4() throws ServletException, IOException {
		ListServlet servlet = new ListServlet();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		List<Info> listAc = new ArrayList<Info>();
		List<Info> list = new ArrayList<Info>();
		RestaurantInfo ri = new RestaurantInfo("string", 244, "string", "string", 0, "string", 0, "string", "github.com");
		listAc.add(ri);
		when(request.getParameter("list")).thenReturn("Do Not Show");
		when(request.getSession().getAttribute("Favorites")).thenReturn(list);
		when(request.getSession().getAttribute("Do Not Show")).thenReturn(listAc);
		when(request.getSession().getAttribute("To Explore")).thenReturn(list);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);
		servlet.doGet(request, response);
		String written = sw.toString();
		JsonObject json = new Gson().fromJson(written, JsonObject.class);
		String result = json.get("header").getAsString();
		JsonElement jsonelement = json.get("body");
		JsonArray jsonarray = jsonelement.getAsJsonArray();
		JsonObject json2 = jsonarray.get(0).getAsJsonObject();
		String subresult = json2.get("url").getAsString();
		String subresult2 = json2.get("rating").getAsString();
		assertEquals("invalid list name mistake","Do Not Show",result);
		assertEquals("invalid list url mistake", "github.com",subresult);
		assertEquals("invalid list content mistake","244",subresult2);
	}
	
	@Test
	//doGet test the fetching of the To Explore list test
	public void doGettest5() throws ServletException, IOException {
		ListServlet servlet = new ListServlet();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		List<Info> listAc = new ArrayList<Info>();
		List<Info> list = new ArrayList<Info>();
		RestaurantInfo ri = new RestaurantInfo("string", 20, "string", "string", 0, "string", 0, "string", "google.com");
		listAc.add(ri);
		when(request.getParameter("list")).thenReturn("To Explore");
		when(request.getSession().getAttribute("Favorites")).thenReturn(list);
		when(request.getSession().getAttribute("Do Not Show")).thenReturn(list);
		when(request.getSession().getAttribute("To Explore")).thenReturn(listAc);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);
		servlet.doGet(request, response);
		String written = sw.toString();
		JsonObject json = new Gson().fromJson(written, JsonObject.class);
		String result = json.get("header").getAsString();
		JsonElement jsonelement = json.get("body");
		JsonArray jsonarray = jsonelement.getAsJsonArray();
		JsonObject json2 = jsonarray.get(0).getAsJsonObject();
		String subresult = json2.get("url").getAsString();
		String subresult2 = json2.get("rating").getAsString();
		assertEquals("invalid list name mistake","To Explore",result);
		assertEquals("invalid list url mistake", "google.com",subresult);
		assertEquals("invalid list content mistake","20",subresult2);
	}

	
//	@Test
	//doPost if given a correctlist
//	public void doPosttest1() throws ServletException, IOException {
//		ListServlet servlet = new ListServlet();
//		HttpServletRequest request = mock(HttpServletRequest.class);
//		HttpServletResponse response = mock(HttpServletResponse.class);
//		HttpSession session = mock(HttpSession.class);
//		when(request.getSession()).thenReturn(session);
//		List<Info> listAc = new ArrayList<Info>();
//		List<Info> list = new ArrayList<Info>();
//		RestaurantInfo ri = new RestaurantInfo("string", 20, "string", "string", 0, "string", 0, "string", "google.com");
//		listAc.add(ri);
//		when(request.getParameter("list")).thenReturn("To Explore");
//		when(request.getSession().getAttribute("Favorites")).thenReturn(list);
//		when(request.getSession().getAttribute("Do Not Show")).thenReturn(list);
//		when(request.getSession().getAttribute("To Explore")).thenReturn(listAc);
//		FileReader fr = new FileReader("src/test/resources/testinput.txt");
//		BufferedReader br = new BufferedReader(fr);
//		String ok = br.lines().collect(Collectors.joining(System.lineSeparator()));
//		when(request.getReader()).thenReturn(br);
//		System.out.println(ok);
//		StringWriter sw = new StringWriter();
//		PrintWriter pw = new PrintWriter(sw);
//		when(response.getWriter()).thenReturn(pw);
//		servlet.doPost(request, response);
//		assertEquals(true,true);
//		String written = sw.toString();
//		JsonObject json = new Gson().fromJson(written, JsonObject.class);
//		String result = json.get("header").getAsString();
//		JsonElement jsonelement = json.get("body");
//		JsonArray jsonarray = jsonelement.getAsJsonArray();
//		JsonObject json2 = jsonarray.get(0).getAsJsonObject();
//		String subresult = json2.get("url").getAsString();
//		String subresult2 = json2.get("rating").getAsString();
//		assertEquals("invalid list name mistake","To Explore",result);
//		assertEquals("invalid list url mistake", "google.com",subresult);
//		assertEquals("invalid list content mistake","20",subresult2);
//	}

	
	
}
