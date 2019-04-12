package servlet;

import com.google.gson.Gson;
import info.Info;
import info.Message;
import info.RecipeInfo;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import info.Searches;
import java.io.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doAnswer;

public class PrevSearchServletTest {

    @Test
    //Check getting previous searches list
    public void getPrevSearchTest() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ServletException
    {
        PrevSearchServlet prevSearchServlet = mock(PrevSearchServlet.class, CALLS_REAL_METHODS);//Have to partial mock so loginServlet.getServletContext() can be mocked
        Method doGetMethod = prevSearchServlet.getClass().getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
        doGetMethod.setAccessible(true);
        HttpServletRequest request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        Map<String, Object> sessionObj = new TreeMap<>();
        sessionObj.put("hello", "Hello testuser");
        sessionObj.put("userID", 1);
        List<Searches> list = new ArrayList<>();
        list.add(new Searches("testSearch", 1, 1));
        sessionObj.put("PreviousSearches", list);
        when(request.getSession()).thenReturn(session);
        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
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
        doGetMethod.invoke(prevSearchServlet, request, response);

        //Make sure the correct response was set
        assertEquals(stringWriter.toString(), (new Gson().toJson(new Message("PreviousSearches", list))+System.lineSeparator()));
    }
}
