package servlet;

import com.google.gson.Gson;
import info.Info;
import info.RecipeInfo;
import info.RestaurantInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Database
{
    private Connection conn;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    public Database() {
        conn = null;
        ps = null;
        rs = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imhungry","root", "root1234!");
        }
        catch (SQLException sqle) {
            System.out.println ("SQLException: " + sqle.getMessage());
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
        }
    }

    public Boolean checkUser(String username) {

        return false;
    }

    public String[] getPasswordInfo(String username) {
        return new String[]{"",""};
    }

    public int getUserID(String username) {
        return -1;
    }

    public Boolean createUser(String username, String passwordHash, String salt) {
        return false;
    }

    public ArrayList<Info> getLists(int userID, String listname) {
        ArrayList<Info> pList = new ArrayList<Info>();
        try {
            if(listname.equals("Favorites")) {
                ps = conn.prepareStatement("SELECT DISTINCT  rest.userID, rest.rID, r.rname, r.rating, r.placeID, r.address, r.priceL, r.driveTimeT, r.driveTimeV, r.phone, r.url,  rest.rID FROM RestFavorites rest JOIN Restaurant r WHERE rest.userID=? AND rest.rID = r.restaurantID");
            }
            else if(listname.equals("To Explore")) {
                ps = conn.prepareStatement("SELECT DISTINCT  rest.userID, rest.rID, r.rname, r.rating, r.placeID, r.address, r.priceL, r.driveTimeT, r.driveTimeV, r.phone, r.url, rest.rID FROM RestToExplore rest JOIN Restaurant r WHERE rest.userID=? AND rest.rID = r.restaurantID");
            }
            else if(listname.equals("Do Not Show")) {
                ps = conn.prepareStatement("SELECT DISTINCT  rest.userID, rest.rID, r.rname, r.rating, r.placeID, r.address, r.priceL, r.driveTimeT, r.driveTimeV, r.phone, r.url, rest.rID FROM Restdonotshow rest JOIN Restaurant r WHERE rest.userID=? AND rest.rID = r.restaurantID");
            }
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            System.out.println(rs);
            while(rs.next()){
                String restname = rs.getString("rname");
                int rating = rs.getInt("rating");
                String placeID = rs.getString("placeID");
                String restaddress = rs.getString("address");
                int priceLevel = rs.getInt("priceL");
                String driveTimeT = rs.getString("driveTimeT");
                int driveTimeV = rs.getInt("driveTimeV");
                String phone = rs.getString("phone");
                String url = rs.getString("url");
                RestaurantInfo p = new RestaurantInfo(restname, rating, placeID, restaddress, priceLevel,driveTimeT, driveTimeV, phone, url);
                pList.add(p);
            }
            if(listname.equals("Favorites")) {
                ps = conn.prepareStatement("SELECT DISTINCT rec.rID, rec.userID, r.recipeIDapi, r.prepTime, r.rating, r.CookTime, r.ingredient, r.instructions, r.imageURL, r.rname, rec.rID FROM RecipeFavorites rec JOIN Recipe r WHERE rec.userID=? AND rec.rID = r.recipID");
            }
            else if(listname.equals("To Explore")) {
                ps = conn.prepareStatement("SELECT DISTINCT rec.rID, rec.userID, r.recipeIDapi, r.prepTime, r.rating, r.CookTime, r.ingredient, r.instructions, r.imageURL, r.rname, rec.rID FROM RecipeToExplore rec JOIN Recipe r WHERE rec.userID=? AND rec.rID = r.recipID");
            }
            else if(listname.equals("Do Not Show")) {
                ps = conn.prepareStatement("SELECT DISTINCT rec.rID, rec.userID, r.recipeIDapi, r.prepTime, r.rating, r.CookTime, r.ingredient, r.instructions, r.imageURL, r.rname, rec.rID FROM Recipedonotshow rec JOIN Recipe r WHERE rec.userID=? AND rec.rID = r.recipID");
            }
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            System.out.println(rs);
            while(rs.next()){
                String rname = rs.getString("rname");
                int rating = rs.getInt("rating");
                int recipeIDapi = rs.getInt("recipeIDapi");
                int prepTime = rs.getInt("prepTime");
                int cookTime = rs.getInt("cookTime");
                Gson gson = new Gson();
                String ingredientsString   = rs.getString("ingredient");
                String[] ingredientsArray = gson.fromJson(ingredientsString, String[].class);
                ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList(ingredientsArray));
                String instructionString   = rs.getString("instructions");
                String[] instructionArray = gson.fromJson(instructionString, String[].class);
                ArrayList<String> instructions = new ArrayList<String>(Arrays.asList(instructionArray));
                String imageurl = rs.getString("imageURL");
                RecipeInfo p = new RecipeInfo(rname, rating, recipeIDapi, prepTime, cookTime, ingredients, instructions,imageurl);
                pList.add(p);
            }
            return pList;
        } catch (SQLException e) {
            System.out.println("SQLException in function \"validate\"");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Boolean updateLists(int userID, String listname, ArrayList<Info> newList) {
        return false;
    }
}
