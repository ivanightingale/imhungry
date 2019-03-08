package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import info.Info;
import info.Message;
import info.RecipeInfo;
import info.RestaurantInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ListServlet", urlPatterns = "/Lists")
public class ListServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String listName = request.getParameter("list");
        PrintWriter respWriter = response.getWriter();
        Gson gson = new Gson();
        if(!listName.equals("Favorites") && !listName.equals("To Explore") && !listName.equals("Do Not Show"))
        {
            respWriter.println(gson.toJson(new Message("Invalid List!")));
            respWriter.close();
            return;
        }
        List<Info> list = (List<Info>)session.getAttribute(listName);
        respWriter.println(gson.toJson(new Message(listName,list)));
        respWriter.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String reqBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        PrintWriter respWriter = response.getWriter();
        try
        {
            Message reqMessage = gson.fromJson(reqBody, Message.class);
            Message reqListAndItem = gson.fromJson((String)reqMessage.body, Message.class);
            String listName = reqListAndItem.header;
            if(!listName.equals("Favorites") && !listName.equals("To Explore") && !listName.equals("Do Not Show"))
                throw new Exception("Invalid list name.");
            String infoJson = (String)reqListAndItem.body;

            JsonObject info = new JsonParser().parse(infoJson).getAsJsonObject();
            Type infoType;
            if(info.has("prepTime")) infoType = RecipeInfo.class;
            else if(info.has("placeID")) infoType = RestaurantInfo.class;
            else throw new Exception("Unknown item type.");

            Info item = gson.fromJson(infoJson, infoType);
            List<Info> list = (List<Info>)session.getAttribute(listName);
            switch(reqMessage.header)
            {
                case "addItem":
                    if(!list.contains(item)) list.add(item);
                    respWriter.println(gson.toJson(new Message("Added to list "+listName)));
                    break;
                case "removeItem":
                    list.remove(item);
                    respWriter.println(gson.toJson(new Message("Removed from list "+listName)));
                    break;
                case "resetLists":
                    session.invalidate();
                    break;
                default:
                    throw new Exception("Invalid action.");
            }
        } catch(Exception e) {
            e.printStackTrace();
            respWriter.println(gson.toJson(new Message("Invalid Response!\n"+e.getMessage())));
            respWriter.close();
        }
        respWriter.close();
    }
}
