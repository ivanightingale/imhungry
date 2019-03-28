package servlet;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Database
{
    private Connection conn;
    private Statement st;

    public Boolean checkUser(String username) {
        return false;
    }

    public String[] getPasswordInfo(String username) {
        return new String[]{"",""};
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
