package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import info.Info;
import info.Message;
import info.RecipeInfo;
import info.RestaurantInfo;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginServletTest
{
    @Test
    //logging in with correct credentials
    public void correctLoginTest() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ServletException
    {
        LoginServlet loginServlet = mock(LoginServlet.class, CALLS_REAL_METHODS);//Have to partial mock so loginServlet.getServletContext() can be mocked
        Method doPostMethod = loginServlet.getClass().getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
        doPostMethod.setAccessible(true);
        HttpServletRequest request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        Map<String, Object> sessionObj = new TreeMap<>();
        sessionObj.put("hello", "Hello testuser");
        sessionObj.put("userID", 1);
        RestaurantInfo rinfo = new RestaurantInfo("testRest", 5, "placeID", "adress" , 8, "drivetime", 8, "phone", "url");
        ArrayList<Info> list = new ArrayList<>(Collections.singletonList(rinfo));
        RecipeInfo info = new RecipeInfo("testrecipe", 5, 12345, 10, 10, new ArrayList<>(Arrays.asList("1. ingredient", "2. ingredient")), new ArrayList<>(Arrays.asList("1. step", "2. step")), "url", 1);
        list.add(info);
        sessionObj.put("Favorites",list);
        sessionObj.put("To Explore",list);
        sessionObj.put("Do Not Show",list);
        when(request.getSession()).thenReturn(session);
        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(request.getParameter("signOrLog")).thenReturn("login");
        BufferedReader br = new BufferedReader(new StringReader(new Gson().toJson(new Message("testuser","hashvalue"))));
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
        RequestDispatcher rd = mock(RequestDispatcher.class);
        ServletContext sc = mock(ServletContext.class);
        doReturn(sc).when(loginServlet).getServletContext();
        when(sc.getRequestDispatcher(anyString())).thenReturn(rd);
        doNothing().when(rd).forward(any(), any());
        doPostMethod.invoke(loginServlet, request, response);

        //Make sure the correct response was set
        assertEquals(stringWriter.toString(), (new Gson().toJson(new Message("LoggedIn",1)))+System.lineSeparator());
    }

    @Test
    //logging in with incorrect credentials
    public void incorrectLoginTest() {

    }

    @Test
    //creating a new user successfully
    public void newUserTest() {

    }

    @Test
    //creating user with a taken name
    public void badUsernameTest() {

    }
}
