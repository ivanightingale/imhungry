package servlet;

import com.google.gson.Gson;
import info.Info;
import info.Searches;
import java.util.*;
import info.Message;
import security.PasswordHashing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.stream.Collectors;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    //POST method used to add and remove items from a list
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        PrintWriter respWriter = response.getWriter();
        Gson gson = new Gson();
        String reqBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator())); //Java 8 magic to collect all lines from a BufferedReadder, in this case the request.
       // while(reqBody.length()>0 && reqBody.charAt(0) != '{') {
         //   reqBody = reqBody.substring(1);
        //}
        Message reqMessage = gson.fromJson(reqBody, Message.class);
        String username = reqMessage.header;
        String password = (String) reqMessage.body;
        //telling whether it is a login or a signup
        String logOrsign = request.getParameter("signOrLog");
        try{
            Database db = new Database();
            // check about password & password salt with the thing david did
            switch (logOrsign)
            {
                case "login":
                    if (db.checkUser(username))
                    {
                        String[] pInfo = db.getPasswordInfo(username);
                        System.out.println(PasswordHashing.hashPassword(password, pInfo[0]));
                        if ((PasswordHashing.hashPassword(password, pInfo[0])).equals(pInfo[1]))
                        {
                            // log IN!

                            int userIDstore = db.getUserID(username);
                            session.setAttribute("hello", "Hello " + username);
                            session.setAttribute("userID", userIDstore);
                            session.setAttribute("Favorites", db.getLists(userIDstore, "Favorites"));
                            session.setAttribute("Do Not Show", db.getLists(userIDstore, "Do Not Show"));
                            session.setAttribute("To Explore", db.getLists(userIDstore, "To Explore"));
                            session.setAttribute("Grocery", db.getLists(userIDstore, "Grocery"));
                            session.setAttribute("PreviousSearches", db.getPrevSearch(userIDstore));
                            respWriter.println(gson.toJson(new Message("LoggedIn", userIDstore)));
                        }

                        //wrong password
                        else
                        {
                            respWriter.println(gson.toJson(new Message("Invalid Password!")));
                            respWriter.close();
                            return;
                        }
                    }
                    //user does not exist
                    else
                    {
                        respWriter.println(gson.toJson(new Message("Invalid Username!")));
                        respWriter.close();
                        return;
                    }
                    break;
                //sign up
                case "signup":
                    if (!db.checkUser(username))
                    {
                        String salt = PasswordHashing.getRandomSalt();
                        db.createUser(username, PasswordHashing.hashPassword(password, salt), salt);

                        //REFACTOR!!!!!! make it empty arraylists because no need to call to database when we know it is empty
                        int userIDstore = db.getUserID(username);
                        session.setAttribute("hello", "Hello " + username);
                        session.setAttribute("userID", userIDstore);
                        session.setAttribute("Favorites", new ArrayList<Info>());
                        session.setAttribute("Do Not Show", new ArrayList<Info>());
                        session.setAttribute("To Explore",new ArrayList<Info>());
                        session.setAttribute("Grocery", new ArrayList<Info>());
                        session.setAttribute("PreviousSearches",new ArrayList<Searches>() );
                        respWriter.println(gson.toJson(new Message("Created", userIDstore)));
                    }
                    else
                    {
                        respWriter.println(gson.toJson(new Message("Username already exists!")));
                        respWriter.close();
                        return;
                    }
                    break;
                case "verify":
                    if (session.getAttribute("userID") != null && Integer.parseInt(password) == (int)session.getAttribute("userID")) {
                        respWriter.println(gson.toJson(new Message("Verified")));
                        return;
                    }
                    else {
                        respWriter.println(gson.toJson(new Message("Unverified")));
                        return;
                    }
                    //break;
            }
        } catch(Exception e) { //Handle exceptions
            e.printStackTrace();
            respWriter.println(gson.toJson(new Message("Invalid Request!")));
            respWriter.close();
        }
        respWriter.close();
    }
}
