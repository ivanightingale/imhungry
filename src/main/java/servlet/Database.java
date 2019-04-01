package servlet;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
        try {
            ps = conn.prepareStatement("SELECT u.userID FROM User u WHERE username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            System.out.println("SQLException in function \"validate\"");
            e.printStackTrace();
        }

        return false;
    }

    public String[] getPasswordInfo(String username) {
        try {
            if (checkUser(username)) {
                int userID = getUserID(username);
                ps = conn.prepareStatement("SELECT u.pw, u.salt FROM User u WHERE username=? AND userID =?");
                ps.setString(1, username);
                ps.setInt(2, userID);
                rs = ps.executeQuery();
                while(rs.next()) {
                    String password = rs.getString("pw");
                    String salt = rs.getString("salt");
                    String[] pINfo = new String[2];
                    pINfo[0] = salt;
                    pINfo[1] = password;
                    return pINfo;
                }
            }
        }catch (SQLException e) {
            System.out.println("SQLException in function \"validate\"");
            e.printStackTrace();
        }
        return new String[]{"",""};
    }

    public int getUserID(String username) {
        try {
            ps = conn.prepareStatement("SELECT u.userID FROM User u WHERE username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("userID");
            }

        } catch (SQLException e) {
            System.out.println("SQLException in function \"validate\"");
            e.printStackTrace();
        }
        return -1;
    }

    public Boolean createUser(String username, String passwordHash, String salt) {
        if(checkUser(username)){
            return false;
        }
        try {
            ps = conn.prepareStatement("INSERT INTO User(username, pw, salt) VALUES(?,?,?)");
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            ps.setString(3, salt);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException in function \"validate\"");
            e.printStackTrace();
        }
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
                int dbid = rs.getInt("rID");
                String restname = rs.getString("rname");
                int rating = rs.getInt("rating");
                String placeID = rs.getString("placeID");
                String restaddress = rs.getString("address");
                String priceLevel = rs.getString("priceL");
                int price = priceLevel.length();
                String driveTimeT = rs.getString("driveTimeT");
                int driveTimeV = rs.getInt("driveTimeV");
                String phone = rs.getString("phone");
                String url = rs.getString("url");
                RestaurantInfo p = new RestaurantInfo(restname, rating, placeID, restaddress, price,driveTimeT, driveTimeV, phone, url, dbid);
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


            while(rs.next()){
                int dbid = rs.getInt("rID");
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
                RecipeInfo p = new RecipeInfo(rname, rating, recipeIDapi, prepTime, cookTime, ingredients, instructions,imageurl, dbid);
                pList.add(p);
            }
            return pList;
        } catch (SQLException e) {
            System.out.println("SQLException in function \"validate\"");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Boolean addToList(int userID, Boolean isRecipe, String listname, Info i){
        //adding recipes
        try {
            if (isRecipe) {
                //
                ps = conn.prepareStatement("SELECT r.recipeIDapi, r.recipID FROM Recipe r WHERE r.recipeIDapi = ?");
                ps.setInt(1, ((RecipeInfo) i).recipeID);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    ps = conn.prepareStatement("INSERT INTO Recipe(recipeIDapi, prepTime, cookTime, ingredient, instructions, imageurl, rating, rname) VALUES(?,?,?,?,?,?,?,?)");
                    ps.setInt(1, ((RecipeInfo) i).recipeID);
                    ps.setInt(2, ((RecipeInfo) i).prepTime);
                    ps.setInt(3, ((RecipeInfo) i).cookTime);
                    Gson gson = new Gson();
                    String ingredientString = gson.toJson(((RecipeInfo) i).ingredients);
                    ps.setString(4, ingredientString);
                    String instructionString = gson.toJson(((RecipeInfo) i).instructions);
                    ps.setString(5, instructionString);
                    ps.setString(6, ((RecipeInfo) i).imageURL);
                    ps.setInt(7, i.rating);
                    ps.setString(8, ((RecipeInfo)i).name);
                    ps.executeUpdate();
                    ps = conn.prepareStatement("SELECT r.recipeIDapi, r.recipID FROM Recipe r WHERE r.recipeIDapi = ?");
                    ps.setInt(1, ((RecipeInfo) i).recipeID);
                    rs = ps.executeQuery();
                    rs.next();
                }
                int dbids = rs.getInt("recipID");
                if (listname.equals("Favorites")) {
                    //checking that the specified user has the specified recipe in the Favorites list
                    ps = conn.prepareStatement("SELECT r.rID AND r.userID FROM RecipeFavorites r WHERE r.rID = ? & r.userID = ?");
                } else if (listname.equals("Do Not Show")) {
                    //checking that the specified user has the specified recipe in the Donotshow list
                    ps = conn.prepareStatement("SELECT r.rID FROM RecipeDonotshow r WHERE r.rID = ? & r.userID = ?");
                } else if (listname.equals("To Explore")) {
                    //checking that the specified user has the specified recipe in the to explore list
                    ps = conn.prepareStatement("SELECT r.rID FROM RecipeToExplore r WHERE r.rID = ? & r.userID = ?");
                }
                ps.setInt(1, dbids);
                ps.setInt(2, userID);
                rs = ps.executeQuery();
                //Did already exist in the specified list
                if(rs.next()){
                    return false;
                }
                if (listname.equals("Favorites")) {
                    //checking that the specified user has the specified recipe in the Favorites list
                    ps = conn.prepareStatement("INSERT INTO RecipeFavorites(rID, userid) VALUES(?,?)");
                } else if (listname.equals("Do Not Show")) {
                    //checking that the specified user has the specified recipe in the Donotshow list
                    ps = conn.prepareStatement("INSERT INTO Recipedonotshow(rID, userid) VALUES(?,?)");
                } else if (listname.equals("To Explore")) {
                    //checking that the specified user has the specified recipe in the to explore list
                    ps = conn.prepareStatement("INSERT INTO RecipeToExplore(rID, userid) VALUES(?,?)");
                }
                ps.setInt(1, dbids);
                ps.setInt(2, userID);
                ps.executeUpdate();
                return true;
            }

            //for adding restaurants
            else {
                ps = conn.prepareStatement("SELECT r.placeID, r.restaurantID FROM Restaurant r WHERE r.placeID = ?");
                ps.setString(1, ((RestaurantInfo) i).placeID);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    ps = conn.prepareStatement("INSERT INTO Restaurant(rname, address, priceL, driveTimeT, driveTimeV, phone, url, rating, placeID) VALUES(?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, ((RestaurantInfo) i).name);
                    ps.setString(2, ((RestaurantInfo) i).address);
                    ps.setString(3, ((RestaurantInfo) i).priceLevel);
                    ps.setString(4, ((RestaurantInfo) i).driveTimeText);
                    ps.setInt(5, ((RestaurantInfo)i).driveTimeValue);
                    ps.setString(6, ((RestaurantInfo) i).phone);
                    ps.setString(7, ((RestaurantInfo) i).url);
                    ps.setInt(8, ((RestaurantInfo)i).rating);
                    ps.setString(9, ((RestaurantInfo) i).placeID);
                    ps.executeUpdate();
                    ps = conn.prepareStatement("SELECT r.restaurantID FROM Restaurant r WHERE r.placeID = ?");
                    System.out.println("HERERE");
                    ps.setString(1, ((RestaurantInfo) i).placeID);
                    rs = ps.executeQuery();
                    rs.next();
                }
                int dbids = rs.getInt("restaurantID");
                if (listname.equals("Favorites")) {
                    ps = conn.prepareStatement("SELECT r.rID FROM RestFavorites r WHERE r.rID= ? AND r.userID= ?");
                } else if (listname.equals("Do Not Show")) {
                    ps = conn.prepareStatement("SELECT r.rID FROM Restdonotshow r WHERE r.rID= ? & r.userID= ?");
                } else if (listname.equals("To Explore")) {
                    ps = conn.prepareStatement("SELECT r.rID FROM RestToExplore r WHERE r.rID= ? & r.userID= ?");
                }
                ps.setInt(1, dbids);
                ps.setInt(2, userID);
                rs = ps.executeQuery();
                //Did already exist in the specified list
                if(rs.next()){
                    System.out.println("Here I am ");
                    return false;
                }
                if (listname.equals("Favorites")) {
                    ps = conn.prepareStatement("INSERT INTO RestFavorites(rID, userid) VALUES(?,?)");
                } else if (listname.equals("Do Not Show")) {
                    ps = conn.prepareStatement("INSERT INTO Restdonotshow(rID, userid) VALUES(?,?)");
                } else if (listname.equals("To Explore")) {
                    ps = conn.prepareStatement("INSERT INTO RestToExplore(rID, userid) VALUES(?,?)");
                }
                ps.setInt(1, dbids);
                ps.setInt(2, userID);
                ps.executeUpdate();
                return true;
            }

        }catch(SQLException e){
            System.out.println("SQLException in function \"validate\"");
            e.printStackTrace();
        }
        return false;

    }

    private Boolean removeFromList(int userID, Boolean isRecipe, String listname, Info i) {
        try {
            //removing recipe

            if (isRecipe) {

                //checking if added to Recipe Database in the past
                // finding the ID of recipe in the database by identifying the unique api recipe ID
                ps = conn.prepareStatement("SELECT r.recipeIDapi, r.recipID FROM Recipe r WHERE r.recipeIDapi = ?");
                ps.setInt(1, ((RecipeInfo) i).recipeID);
                rs = ps.executeQuery();
                // cannot remove an item that has not been added
                if(!rs.next()){
                    System.out.println("IM not supposed to be HERE ");
                    return false;
                }

                //storing the database ID for the recipe to remove
                int dbids = rs.getInt("recipID");
                if (listname.equals("Favorites")) {
                    //checking that the specified user has the specified recipe in the Favorites list
                    ps = conn.prepareStatement("SELECT r.rID FROM RecipeFavorites r WHERE r.rID = ? AND r.userID = ?");
                } else if (listname.equals("Do Not Show")) {
                    //checking that the specified user has the specified recipe in the Do Not Show list
                    ps = conn.prepareStatement("SELECT r.rID FROM RecipeDonotshow r WHERE r.rID = ? AND r.userID = ?");
                } else if (listname.equals("To Explore")) {
                    //checking that the specified user has the specified recipe in the To Explore list
                    ps = conn.prepareStatement("SELECT r.rID FROM RecipeToExplore r WHERE r.rID = ? AND r.userID = ?");
                }
                ps.setInt(1, dbids);
                ps.setInt(2, userID);
                rs = ps.executeQuery();
                //Did not exist in the specified Recipe list
                if(!rs.next()){
                    System.out.println("Cant delete what you dont have - recipe");
                    return false;
                }
                if (listname.equals("Favorites")) {
                    //checking that the specified user has the specified recipe in the Favorites list
                    ps = conn.prepareStatement("DELETE FROM RecipeFavorites WHERE rID = ? AND userID = ?");
                } else if (listname.equals("Do Not Show")) {
                    //checking that the specified user has the specified recipe in the Do Not Show list
                    ps = conn.prepareStatement("DELETE FROM RecipeDonotshow WHERE rID = ? AND userID = ?");
                } else if (listname.equals("To Explore")) {
                    //checking that the specified user has the specified recipe in the To Explore list
                    ps = conn.prepareStatement("DELETE FROM RecipeToExplore  WHERE rID = ? AND userID = ?");
                }
                ps.setInt(1, dbids);
                ps.setInt(2, userID);
                ps.executeUpdate();
                System.out.println("about to return true");
                return true;
            }
            //for removing restaurants
            else {
                //checking if added to Restaurant  Database in the past
                // finding the ID of recipe in the database by identifying the unique api recipe ID
                ps = conn.prepareStatement("SELECT r.placeID, r.restaurantID FROM Restaurant r WHERE r.placeID = ?");
                ps.setString(1, ((RestaurantInfo) i).placeID);
                rs = ps.executeQuery();
                // cannot remove an item that has not been added
                if(!rs.next()){
                    //System.out.println("IM not supposed to be HERE ");
                    return false;
                }
                //storing the database ID for the restaurant to remove
                int dbids = rs.getInt("restaurantID");
                if (listname.equals("Favorites")) {
                    //checking that the specified user has the specified recipe in the Favorites list
                    ps = conn.prepareStatement("SELECT r.rID FROM RestFavorites r WHERE r.rID = ? AND r.userID = ?");
                } else if (listname.equals("Do Not Show")) {
                    //checking that the specified user has the specified recipe in the Do Not Show list
                    ps = conn.prepareStatement("SELECT r.rID FROM RestDonotshow r WHERE r.rID = ? AND r.userID = ?");
                } else if (listname.equals("To Explore")) {
                    //checking that the specified user has the specified recipe in the To Explore list
                    ps = conn.prepareStatement("SELECT r.rID FROM RestToExplore r WHERE r.rID = ? AND r.userID = ?");
                }
                ps.setInt(1, dbids);
                ps.setInt(2, userID);
                rs = ps.executeQuery();
                //Did not exist in the specified restaurant list
                if(!rs.next()){
                    //Cannot delete what you do not have restaurant edition
                    System.out.println(dbids + " " + userID);
                    return false;
                }
                if (listname.equals("Favorites")) {
                    //checking that the specified user has the specified recipe in the Favorites list
                    ps = conn.prepareStatement("DELETE FROM RestFavorites WHERE rID = ? AND userID = ?");
                } else if (listname.equals("Do Not Show")) {
                    //checking that the specified user has the specified recipe in the Do Not Show list
                    ps = conn.prepareStatement("DELETE FROM RestDonotshow WHERE rID = ? AND userID = ?");
                } else if (listname.equals("To Explore")) {
                    //checking that the specified user has the specified recipe in the To Explore list
                    ps = conn.prepareStatement("DELETE FROM RestToExplore WHERE rID = ? AND userID = ?");
                }
                ps.setInt(1, dbids);
                ps.setInt(2, userID);
                ps.executeUpdate();
                return true;
            }
        }catch(SQLException e){
            System.out.println("SQLException in function \"validate\"");
            e.printStackTrace();
        }
        return false;

    }



    public Boolean updateLists(int userID, Boolean add, String listname, Info i) {
        Boolean isRecipe = i.getClass().equals(RecipeInfo.class);
        if (add) {
            Boolean succ = addToList(userID, isRecipe, listname, i);
            //have to return true and not the actual value because executeUpdate returns before can return a bool
            return getLists(userID, listname).contains(i) && succ;
        } else {
            Boolean succ = removeFromList(userID, isRecipe, listname, i);
            //have to return true and not the actual value because executeUpdate returns before can return a bool
            return !getLists(userID, listname).contains(i) && succ;
        }
    }
}
