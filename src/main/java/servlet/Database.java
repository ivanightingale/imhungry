package servlet;

import java.sql.*;
import java.util.ArrayList;

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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root", "root");
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

    public ArrayList<String> getLists(String username, String listname) {
        return new ArrayList<>();
    }

    public Boolean updateLists(String username, String listname, ArrayList<String> newList) {
        return false;
    }
}
