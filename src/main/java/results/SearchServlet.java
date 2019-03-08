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
		
		//From previous page, extract parameters
		String userSearch = request.getParameter("search");
		String numResults = request.getParameter("number");
	
		//printwriter object
		PrintWriter out = response.getWriter();
		
		//Set up variables to store return value
		boolean success = true;
		String errorMsg = "";
	
		//get lists
		ArrayList<Info> recipeList = null;
		recipeList = recipeSearch(userSearch, numResults);
		ArrayList<Info> restaurantList = null;
		restaurantList = restaurantSearch(userSearch, numResults);
		String collageURL = null;
		collageURL=getCollageURLs(userSearch);
		
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
