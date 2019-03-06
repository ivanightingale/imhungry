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
		
		//Check for null input
		if (userSearch == null) {
			success = false;
			errorMsg += "The file doesn't exist!";
		}
		
		//get lists
		ArrayList<Info> recipeList = recipeSearch(userSearch, numResults);
		ArrayList<Info> restaurantList = restaurantSearch(userSearch, numResults);
		String collageURL = getCollageURLs(userSearch);
		
		if (recipeList == null || restaurantList == null || collageURL == null){
			success=false;
			errorMsg += "Failed to generate results!";
		}
	
		//return content
		if (!success){
			//create error message
			out.print("{ 'head': \n");
			out.print(errorMsg + " \n}");

		}else{
			//create success message
			
			out.print("success!!!");
			String recListJson = new Gson().toJson(recipeList);
			String restListJson = new Gson().toJson(restaurantList);
			
			out.print("{ 'head': 'Success', \n");
			out.print("'body': {" + recListJson + ",\n" + restListJson +",\n"+ collageURL+"}");
			out.print( " \n}");	
		
		}
	
	}
	
}
