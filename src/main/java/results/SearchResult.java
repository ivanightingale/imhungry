package results;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import info.Info;
import java.net.*;
import java.io.Reader.*;
import java.util.ArrayList;
import java.util.Random;
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

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//From previous page, extract parameters
		String userSearch = request.getParameter("search");
		String numResults = request.getParameter("numResults");
	
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
	
		//return content
		if (!success){
			//create error message
			out.print("{ 'head': \n");
			out.print(errorMsg + " \n}");

		}else{
			//create success message
			String recListJson = new Gson().toJson(recipeList);
			String restListJson = new Gson().toJson(restaurantList);
			
			out.print("{ 'head': 'Success', \n");
			out.print("'body': {" + recListJson + ",\n" + restListJson +",\n"+ collageURL+"}");
			out.print( " \n}");	
		
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

			  String title = null;
			  String prepTime = null;
			  String cookTime = null;
			  String ingredients = null;
			  String instructions = null;
			 	
			  
			 for(int i = 0 ; i < arr.size(); i ++){
				 //add code to add info objects to info array
				 
				 JsonObject pagemapObj = arr.get(i).getAsJsonObject().get("pagemap").getAsJsonObject();
				 JsonObject recipeObj = null;
				 
				 //get recipe cook time
				 if (pagemapObj.get("recipe") != null){
					 recipeObj = pagemapObj.get("recipe").getAsJsonArray().get(1).getAsJsonObject();
					 
					 if (recipeObj.get("name") != null){
						 title = recipeObj.get("name").getAsString();		
					 }else{
						 title = "No name available";
					 }
					 
					 if (recipeObj.get("preptime") != null){
						 prepTime = recipeObj.get("preptime").getAsString();						 
					 }else{
						 prepTime = "No preptime available";
					 }
					 
					 if (recipeObj.get("cooktime") != null){
						 cookTime = recipeObj.get("cooktime").getAsString();
					 }else{
						 cookTime = "No cooktime available";
					 }
					 
					if(recipeObj.get("recipeingredient") != null){
						ingredients = recipeObj.get("recipeingredient").getAsString();
					}else{
						ingredients = "No ingredients available";
					}
					
					if(recipeObj.get("recipeinstructions") != null){
						instructions = recipeObj.get("recipeinstructions").getAsString();
					}else{
						instructions = "No instructions available";
					}
				 }
				
				 toReturn.add(new Info(title, prepTime, cookTime, ingredients, instructions));
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
			  
			  String name = null;
			  int rating = 0;
			  String address = null;
			  String price = null;
			  int driveTime = 0;
			  String phone =null;
			  String website = null;

			 for(int i = 0 ; i < arr.size(); i ++){
				 //add code to add info objects to info array
				// name = arr.get(i).getAsJsonObject().get("title").getAsString();
				 website = arr.get(i).getAsJsonObject().get("link").getAsString();
				 
				 JsonObject pagemapObj = arr.get(i).getAsJsonObject().get("pagemap").getAsJsonObject();
				 JsonObject postalAddressObj = null;
				 JsonObject nameObj = null;
				 JsonObject restaurantObj = null;
				 
				 
				 //get restaurant name
				 if(pagemapObj.get("foodestablishment") != null){
					 nameObj = pagemapObj.get("foodestablishment").getAsJsonArray().get(0).getAsJsonObject();
					 
				 	 if (nameObj.get("name") != null){
				 		name = nameObj.get("name").getAsString();
				 	 }else{
				 		name = "No name available";
				 	 }
				 }
				 //get restaurant address, phone, and distance
				 if(pagemapObj.get("postaladdress") != null){
					 
					 postalAddressObj = pagemapObj.get("postaladdress").getAsJsonArray().get(0).getAsJsonObject(); 
					 	
						if (postalAddressObj.get("streetaddress") != null){
						 address = postalAddressObj.get("streetaddress").getAsString();
						 
						 //has address get distance
						 String origin = "801+Childs+Way+Los+Angeles+CA+90089";
						 String destination = address.replaceAll(" ", "+");
						 
						 URL disurl = new URL ("https://maps.googleapis.com/maps/api/directions/json?origin="+origin+"&destination="+destination+"&key=AIzaSyAxd0-sB86ZtXFFzfscUqDbukAFWvP1V9o");
						
						 
						 System.out.println(disurl);
						 
						 HttpURLConnection disconn;
						 disconn = (HttpURLConnection) disurl.openConnection();
						 disconn.setRequestMethod("GET");
						 disconn.setRequestProperty("Accept", "application/json");
	
						 BufferedReader br2 = new BufferedReader(new InputStreamReader ( ( disconn.getInputStream() ) ) );
						
						 //get data from json
						  JsonObject directionObj =  new JsonParser().parse(br2).getAsJsonObject();
						 // System.out.println(directionObj.get("routes").getAsJsonArray().get(2).getAsString());
						  driveTime = directionObj.get("routes").getAsJsonArray().get(0).getAsJsonObject().get("legs").getAsJsonArray().get(0).getAsJsonObject().get("duration").getAsJsonObject().get("value").getAsInt();
						  driveTime /= 60;
						  
						  disconn.disconnect();
					 }
					 if (postalAddressObj.get("telephone") != null){
						 phone = postalAddressObj.get("telephone").getAsString();	
					 }
					 
				 }else{
					 address = "No address available";
					 phone = "No phone available";
					 driveTime = 0;
				 }
				 
				 //get restaurant price
				 if (pagemapObj.getAsJsonObject().get("restaurant") != null){
					 price = pagemapObj.getAsJsonObject().get("restaurant").getAsJsonArray().get(0).getAsJsonObject().get("pricerange").getAsString();
					 
				 }else{
					 price = "No price available";
				 }
				 
				 //get restaurant rating
				 Random rand = new Random();
				 // Obtain a number between [0 - 5].
				 int n = rand.nextInt(50);
				 // Add 1 to the result to get a number from the required range
				 // (i.e., [1 - 50]).
				 n += 2;
				 
				 rating = n;
			
				 toReturn.add( new Info(name, rating, address, price, driveTime, phone, website) );
				 
				//testing purposes
//				 System.out.println(name);
//				 System.out.println(address);
//				 System.out.println(phone);
//				 System.out.println(price);
//				 System.out.println(driveTime + " min");
				 
				 //create info objects
				 
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
	
				e.printStackTrace();
			}
		  
		 
		return toReturn;
	}
	
	
	String getCollageURLs(String s){
		String toReturn = null;
		 //collage api stuff
		//didn't put anything here cause ivan did it already
		return toReturn;
	}

}
