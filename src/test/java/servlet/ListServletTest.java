package servlet;

import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import static org.mockito.Mockito.*;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import info.Info;
import info.RestaurantInfo;

public class ListServletTest {

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
