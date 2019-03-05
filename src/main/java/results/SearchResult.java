package results;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import info.Info;
import java.net.*;
import java.io.Reader.*;
import java.util.ArrayList;
import com.google.gson.*;
/**
 * Servlet implementation class SearchResult
 */
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//From previous page, extract parameters
		//uncomment once testing is complete
	//	String userSearch = request.getParameter("search");
		//int numResults = request.getParrameter("numResults");
		String userSearch = "thai";
		int numResults = 3;
				
		//Set up variables to store return value
		boolean success = true;
		String errorMsg = "";
		
		//Check for null input
		if (userSearch == null) {
			success = false;
			errorMsg += "The file doesn't exist!";
		}
		
		ArrayList<Info> recipeList = recipeSearch(userSearch, numResults);
		ArrayList<Info> restaurantList = restaurantSearch(userSearch, numResults);
		ArrayList<String> collageURLs = getCollageURLs(userSearch);
	
		//return content
		if (!success){
			//create error message
			
		}else{
			//create success message
		}
		
	
		
	}
	
	int getDriveTime(String s){
		return 0;
	}
	
	ArrayList<Info> recipeSearch(String s, int num){
		ArrayList<Info> toReturn = new ArrayList<Info>();
		
		//search query variables
		 String key = "AIzaSyDBH2Gj3T72zj8oDA436dO8aJ_et7rftmQ";
		 String qry = s; 
		 String cxRecipe  = "009244685651437371811:xeqi10eq9ts";
		 
		 try {
			  //recipeSearch URL
			  URL url = new URL ("https://www.googleapis.com/customsearch/v1?key="+key+"&num="+ num+"&cx="+cxRecipe+"&q="+qry+"&alt=json");
			
			  //get Recipe results  
			  HttpURLConnection conn;
			  conn = (HttpURLConnection) url.openConnection();
			  conn.setRequestMethod("GET");
			  conn.setRequestProperty("Accept", "application/json");

			  BufferedReader br = new BufferedReader(new InputStreamReader ( ( conn.getInputStream() ) ) );
			
			  //get data from json
			  JsonObject jObj = new JsonParser().parse(br).getAsJsonObject();
			  JsonArray arr = jObj.getAsJsonArray("items");

			 for(int i = 0 ; i < arr.size(); i ++){
				 //add code to add info objects to info array
				 
			 }
			  conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		
		return toReturn;
	}

	ArrayList<Info> restaurantSearch(String s, int num){
		ArrayList<Info> toReturn = new ArrayList<Info>();
		
		//search query variables
		 String key = "AIzaSyDBH2Gj3T72zj8oDA436dO8aJ_et7rftmQ";
		 String qry = s; 
		 String cxRestaurant  = "009244685651437371811:glpfeejptyo";
		 
		 try {
			  //recipeSearch URL
			  URL url = new URL ("https://www.googleapis.com/customsearch/v1?key="+key+"&num="+ num+"&cx="+cxRestaurant+"&q="+qry+"&alt=json");
			
			  //get Recipe results  
			  HttpURLConnection conn;
			  conn = (HttpURLConnection) url.openConnection();
			  conn.setRequestMethod("GET");
			  conn.setRequestProperty("Accept", "application/json");

			  BufferedReader br = new BufferedReader(new InputStreamReader ( ( conn.getInputStream() ) ) );
			
			  //get data from json
			  JsonObject jObj = new JsonParser().parse(br).getAsJsonObject();
			  JsonArray arr = jObj.getAsJsonArray("items");

			 for(int i = 0 ; i < arr.size(); i ++){
				 //add code to add info objects to info array
				 
			 }
			  conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		
		return toReturn;
	}
	
	ArrayList<String> getImageURLs(String s){
		ArrayList<String> toReturn = null;
		
		return toReturn;
	}
	
	
	ArrayList<String> getCollageURLs(String s){
		ArrayList<String> toReturn = new ArrayList<String>();
		
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
				
				 JsonObject jObj2 = arr.get(i).getAsJsonObject().get("pagemap").getAsJsonObject();
				 JsonArray arr2 = jObj2.get("recipe").getAsJsonArray();
				 
				 for(int j = 0 ; j < arr2.size(); j ++){
					
					 JsonArray arr3 = jObj2.get("cse_image").getAsJsonArray();
					 for(int k = 0; k < 1; k++){
							toReturn.add( arr3.get(k).getAsJsonObject().get("src").getAsString() );
					 }
					 	
				
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
