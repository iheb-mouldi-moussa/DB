package DAO;

import java.sql.*;
import java.util.*;


import Helpers.Helper;
import Tables.AllTables;
public class TablesDAO {

    private Connection myConn;

    
	public TablesDAO() throws Exception {
		
		// connect to database
		myConn = Helper.getConnection();
		System.out.println("DB connection successful to: " + Helper.getUrl());
    }


    public List<AllTables> getAllTables() throws Exception
    {
        List<AllTables> result = new ArrayList<>();
        Statement myStmt = null;
        ResultSet myRSet = null;

        try {
           myStmt = myConn.createStatement();
           myRSet = myStmt.executeQuery("show tables;"); 

           while(myRSet.next())
           {
               AllTables tmp = convertToTable(myRSet);
               result.add(tmp);
           }

           return result;
        } finally {
            //Helper.closeConnection(myConn, myStmt, myRSet);
        }
        
    }

    private AllTables convertToTable(ResultSet rs) throws SQLException
    {
        String name = rs.getString("Tables_in_r17DB");
        return new AllTables(name);
    }


    public static void main(String[] args) throws Exception {
        
        TablesDAO tablesDAO = new TablesDAO();
        System.out.println(tablesDAO.getAllTables());
    
    }
}
