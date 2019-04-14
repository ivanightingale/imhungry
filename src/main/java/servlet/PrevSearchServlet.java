package servlet;

import com.google.gson.Gson;
import info.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PrevSearchServlet", urlPatterns = "/PrevSearch")
public class PrevSearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter respWriter = response.getWriter();
        Gson gson = new Gson();
        ArrayList<Searches> prevSearches = new ArrayList<Searches>();
        if(session.getAttribute("userID")!=null) {
           prevSearches = (ArrayList<Searches>) session.getAttribute("PreviousSearches");
        }
        respWriter.println(gson.toJson(new Message("Previous Searches", prevSearches)));
    }
}
