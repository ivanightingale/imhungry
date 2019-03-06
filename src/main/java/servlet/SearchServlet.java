package servlet;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import info.Info;
import info.RestaurantInfo;
import info.RecipeInfo;
import java.net.*;
import java.io.Reader.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * Servlet implementation class SearchResult
 */
@WebServlet(name = "SearchServlet", urlPatterns = "/Search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String GOOGLE_MAPS_API_PREFIX = "https://maps.googleapis.com/maps/api";
	private static final String MAPS_API_KEY = "AIzaSyC-iVaMeUT0xoM_wNIxJPOZrvlfLQMrI1A";
	private static final String TOMMY_TROJAN_LOC = "34.0205663,-118.2876355";

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Info> favoritesList, toExploreList, doNotShowList;
		favoritesList = (ArrayList<Info>)session.getAttribute("Favorites");
		toExploreList = (ArrayList<Info>)session.getAttribute("To Explore");
		doNotShowList = (ArrayList<Info>)session.getAttribute("Do Not Show");
        
        //From previous page, extract parameters
        //uncomment once testing is complete
        String userSearch = request.getParameter("search");
        int numResults = Integer.parseInt(request.getParameter("numResults"));

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
        
        //ArrayList<Info> restaurantList = restaurantSearch(userSearch, Integer.getInteger(numResults), doNotShowList, favoritesList);
        ArrayList<RecipeInfo> recipeList = recipeSearch(userSearch, numResults);
        ArrayList<RestaurantInfo> restaurantList = restaurantSearch(userSearch, numResults);
    
        //return content
        if (!success){
            //create error message
            out.println(errorMsg);
            
        } else {
            //create success message
            out.println("success!");

            //session.setAttribute("recipeList", recipeList);
            session.setAttribute("restaurantList", restaurantList);
            session.setAttribute("collageURL", collageURL);    
        }
        
    }
	
	
	
	//Sends a "GET" request to the specified API URL and obtains the result as a String.
	public static String getJSONResponse(String url) {
		try {
			URL requestURL = new URL(url);
			HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
			con.setRequestProperty("X-RapidAPI-Key", "5d400066d1msh1a0901e6bb0917dp1b2dc1jsn1dcafa5afeb5");
			int responseCode = con.getResponseCode();
			System.out.println("Response Code: " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	//TODO: add comments
	public ArrayList<RecipeInfo> recipeSearch(String query, int numResults){
		ArrayList<RecipeInfo> recipes = new ArrayList<RecipeInfo>();
		String recipeSearchURL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?"
				+ "number=" + (numResults + doNotShowList.size()) + "&query=" + query;
		//extract key part of the JSON response
		JSONArray recipesJSON = new JSONObject(getJSONResponse(recipeSearchURL)).getJSONArray("results");
		  
		for(int i = 0 ; i < recipesJSON.length(); i++){
			JSONObject currentRecipe = recipesJSON.getJSONObject(i);
			RecipeInfo recipe = new RecipeInfo("[No name available]", 0, 0, 0, new ArrayList<String>(), 
					new ArrayList<String>());
			recipe.name = currentRecipe.getString("title");
			int recipeID = currentRecipe.getInt("id");
			String recipeDetailURL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"
					+ recipeID +"/information";
			JSONObject recipeDetailJSON = new JSONObject(getJSONResponse(recipeDetailURL));
			recipe.rating = recipeDetailJSON.getDouble("spoonacularScore") / 20.0;
			try {
				recipe.prepTime = recipeDetailJSON.getInt("preparationMinutes");
			} catch(Exception e) {}
			try {
				recipe.cookTime = recipeDetailJSON.getInt("cookingMinutes");
			} catch(Exception e) {}
			JSONArray ingredientsJSON = recipeDetailJSON.getJSONArray("extendedIngredients");
			for(int j = 0; j < ingredientsJSON.length(); j++) {
				recipe.ingredients.add("" + (j + 1) + ". " + ingredientsJSON.getJSONObject(j).getString("name"));
			}
			JSONArray instructionsJSON = recipeDetailJSON.getJSONArray("analyzedInstructions").getJSONObject(0)
					.getJSONArray("steps");
			for(int j = 0; j < instructionsJSON.length(); j++) {
				recipe.instructions.add("" + (j + 1) + ". " + instructionsJSON.getJSONObject(j).getString("step"));
			}
			recipes.add(recipe);
		}
		for(Info doNotShowInfo : doNotShowList) {
			recipes.remove(doNotShowInfo);
		}
		Collections.sort(recipes);  //sort RestaurantInfo in ascending order based on drive time
		
		//move restaurants in Favorites List to the top
		for(int i = recipes.size() - 1; i > 0; i--) {
			if(favoritesList.contains(recipes.get(i))) {
				recipes.add(0, recipes.get(i));
				i++;
				recipes.remove(i);
			}
		}
		return recipes;
	}
	
	
	//Given a String query and number of results expected, return an ArrayList of RestaurantInfo. The function
	//uses several Google Maps APIs and refer to Do Not Show List and Favorites List.
	public ArrayList<RestaurantInfo> restaurantSearch(String query, int numResults) {
		ArrayList<RestaurantInfo> restaurants = new ArrayList<RestaurantInfo>();
		String searchURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
				+ TOMMY_TROJAN_LOC +"&rankby=distance&type=restaurant&keyword="
				+ query + "&key=" + MAPS_API_KEY;
		//extract key part of the JSON response
		JSONArray places = new JSONObject(getJSONResponse(searchURL)).getJSONArray("results");
		
		//assuming the worst possible case (all items in Do Not Show List appear) to encapsulate sufficient
		//amount of restaurant information from the response
		for(int i = 0; i < numResults + doNotShowList.size(); i++) {
			JsonObject currentPlace = places.get(i).getAsJsonObject();
			restaurants.add(new RestaurantInfo(currentPlace.get("name").getAsString(),
					currentPlace.get("rating").getAsDouble(), currentPlace.get("place_id").getAsString(),
					currentPlace.get("vicinity").getAsString(), currentPlace.get("price_level").getAsInt(), "", 0, "", ""));
		}
		
		//remove all items in Do Not Show List that appear in the result
		for(Info doNotShowInfo : doNotShowList) {
			restaurants.remove(doNotShowInfo);
		}
		getDriveTimes(restaurants);
		getPhoneAndURL(restaurants);
		
		Collections.sort((List<RestaurantInfo>)(Object)restaurants);  //sort RestaurantInfo in ascending order based on drive time
		
		//move restaurants in Favorites List to the top
		for(int i = restaurants.size() - 1; i > 0; i--) {
			if(favoritesList.contains(restaurants.get(i))) {
				restaurants.add(0, restaurants.get(i));
				i++;
				restaurants.remove(i);
			}
		}
    	return restaurants;
	}
	
	//Create a request using place_id of all RestaurantInfo and send one request to obtain all drive times.
	public void getDriveTimes(ArrayList<Info> restaurants) {
		String driveTimeURL = "https://maps.googleapis.com/maps/api/distancematrix/" +
				"json?units=imperial&origins=" + TOMMY_TROJAN_LOC + "&destinations=";

		//concatenate the request URL to make use of the Distance Matrix API, obtaining drive times of multiple
		//destinations in one request
		for(int i = 0; i < restaurants.size(); i++) {
			driveTimeURL += "place_id:" + restaurants.get(i).placeID + "%7C";
		}
		driveTimeURL += "&key=" + MAPS_API_KEY;
		

		//JsonArray driveTimes = new JsonParser().parse(getJSONResponse(driveTimeURL)).getAsJsonObject().get("rows").getAsJsonArray().get(0).getAsJsonObject().get("elements").getAsJsonArray();
		//extract key part of the JSON response
		JSONArray driveTimes = new JSONObject(getJSONResponse(driveTimeURL)).getJSONArray("rows")
				.getJSONObject(0).getJSONArray("elements");
		//modify respective RestaurantInfo objects, store drive time data
		for(int i = 0; i < restaurants.size(); i++) {
			JsonObject durationJSON = driveTimes.get(i).getAsJsonObject().get("duration").getAsJsonObject();
			((RestaurantInfo)(restaurants.get(i))).driveTimeText = durationJSON.get("text").getAsString();
			((RestaurantInfo)(restaurants.get(i))).driveTimeValue = durationJSON.get("value").getAsInt();
		}
	}
	
	//A separate request is needed to get detailed information including phone and URL.
	public void getPhoneAndURL(ArrayList<RestaurantInfo> restaurants) {
		for(RestaurantInfo restaurant : restaurants) {
			String detailURL = GOOGLE_MAPS_API_PREFIX + "/place/details/json?placeid="
/*
					+ ((RestaurantInfo)restaurant).placeID + "&fields=formatted_phone_number,url&key=" + API_KEY;
			JsonObject detailsJSON = new JsonParser().parse(getJSONResponse(detailURL)).getAsJsonObject().get("result").getAsJsonObject();
			((RestaurantInfo)restaurant).phone = detailsJSON.get("formatted_phone_number").getAsString();
			((RestaurantInfo)restaurant).url = detailsJSON.get("url").getAsString();
*/
					+ restaurant.placeID + "&fields=formatted_phone_number,url&key=" + MAPS_API_KEY;
			//extract main body of the JSON response
			JSONObject detailsJSON = new JSONObject(getJSONResponse(detailURL)).getJSONObject("result");
			//modify respective RestaurantInfo objects, store detail information
			restaurant.phone = detailsJSON.getString("formatted_phone_number");
			restaurant.url = detailsJSON.getString("url");
		}
	}
       
	
	
	
	//TODO
	private ArrayList<String> getImageURLs(String s){
		ArrayList<String> toReturn = null;
		
		//search query variables
		 String key = "AIzaSyDBH2Gj3T72zj8oDA436dO8aJ_et7rftmQ";
		 String qry = s; 
		 String cxRecipe  = "009244685651437371811:xeqi10eq9ts";
		 
		 try {
			  //recipeSearch URL
			  URL url = new URL ("https://www.googleapis.com/customsearch/v1?key="+key+"&num="+ 10+"&cx="+cxRecipe+"&q="+qry+"&alt=json");
			
			  //get Recipe results  
			  HttpURLConnection conn;
			  conn = (HttpURLConnection) url.openConnection();
			  conn.setRequestMethod("GET");
			  conn.setRequestProperty("Accept", "application/json");

			  BufferedReader br = new BufferedReader(new InputStreamReader ( ( conn.getInputStream() ) ) );
			
			  //get data from json
			  JsonObject jObj = new JsonParser().parse(br).getAsJsonObject();
			  JsonArray arr = jObj.getAsJsonArray("items");

			  for(int i = 0; i < arr.size(); i ++){
				
				  JsonObject pagemap = arr.get(i).getAsJsonObject().get("pagemap").getAsJsonObject();
				  JsonArray arrRecipe = pagemap.get("recipe").getAsJsonArray();
			 
				  JsonArray arrImages = pagemap.get("cse_image").getAsJsonArray();
				  for(int k = 0; k < 1; k++){
					
					 toReturn.add(arrImages.get(k).getAsJsonObject().get("src").getAsString());
		
				  }
				 
				
				  
			  }
			  conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		 
		return toReturn;
	}

}
