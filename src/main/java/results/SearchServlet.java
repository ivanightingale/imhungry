package results;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import info.Info;
import info.RestaurantInfo;
import info.RecipeInfo;
import java.net.*;
import java.io.Reader.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Info> favoritesList, toExploreList, doNotShowList;
		if(session.isNew() || session.getAttribute("Favorites") == null) {
			favoritesList = new ArrayList<>();
			toExploreList = new ArrayList<>();
			doNotShowList = new ArrayList<>();
			session.setAttribute("Favorites", favoritesList);
			session.setAttribute("To Explore", toExploreList);
			session.setAttribute("Do Not Show", doNotShowList);
		}
		else
		{
			favoritesList = (ArrayList<Info>) session.getAttribute("Favorites");
			toExploreList = (ArrayList<Info>) session.getAttribute("To Explore");
			doNotShowList = (ArrayList<Info>) session.getAttribute("Do Not Show");
		}

        //From previous page, extract parameters
        String userSearch = request.getParameter("search");
        int numResults = Integer.parseInt(request.getParameter("number"));
		//printwriter object
		PrintWriter out = response.getWriter();
		
		//Set up variables to store return value
		boolean success = true;
		String errorMsg = "";
	
		 //get lists

        //ArrayList<Info> restaurantList = restaurantSearch(userSearch, Integer.getInteger(numResults), doNotShowList, favoritesList);
        ArrayList<RecipeInfo> recipeList = recipeSearch(userSearch, numResults, doNotShowList, favoritesList);
        ArrayList<RestaurantInfo> restaurantList = restaurantSearch(userSearch, numResults, doNotShowList, favoritesList);
        ArrayList<String>collageURL = getImageURLs(userSearch);
        
		if (recipeList == null || restaurantList == null || collageURL == null){
			success=false;
			errorMsg += "Failed to generate results!";
			
		}
		
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

		//return content
		if (!success){
			//create error message
			String returnResult = "{ \"head\": \" "+errorMsg + "\" }";
			
			out.println( gson.toJson ( new Message(errorMsg, returnResult) )  ) ;


		}else{
			//create success message
			errorMsg = "success";
			
			
			String recListJson = new Gson().toJson(recipeList);
			String restListJson = new Gson().toJson(restaurantList);
			String collageJson = new Gson().toJson(collageURL);
			
			//return result array
			out.print("{ \n \"head\": \""+ errorMsg +"\", \n");
            out.print("\"body\" :[ { \"recipelist\": " +recipeJson+ "}, { \"restaurantlist\": " + restaurantJson+"}, { \"collageURLs\": "+collageJson+"  } ]");
            out.print("\n}");
			
            //other way with 2d arraylist : incomplete
			//out.println(gsonn.toJson(new Message(errorMsg, )) );
		
		
		}
	
	}
	
}
