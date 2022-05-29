package DAO;

import java.util.*;

import Helpers.Helper;
import Tables.Authors;

import java.sql.*;

public class AuthorsDAO {


    private Connection myConn;
	
	public AuthorsDAO() throws Exception {
		
		// connect to database
		myConn = Helper.getConnection();
		System.out.println("DB connection successful to: " + Helper.getMySQLDataSource().getUrl());
	}
	
	public List<Authors> getAllAuthorss() throws Exception {
		List<Authors> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Authors");
			
			while (myRs.next()) {
				Authors tempAuthors = convertRowToAuthors(myRs);
				list.add(tempAuthors);
			}

			return list;		
		}
		finally {
			Helper.closeConnection(myConn, myStmt, myRs);
		}
	}
	
	public List<Authors> searchAuthorss(String firstName) throws Exception {
		List<Authors> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			firstName += "%";
			myStmt = myConn.prepareStatement("select * from Authors where Firstname like ?");
			
			myStmt.setString(1, firstName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Authors tempAuthors = convertRowToAuthors(myRs);
				list.add(tempAuthors);
			}
			
			return list;
		}
		finally {
			Helper.closeConnection(myConn, myStmt, myRs);
		}


	}

	public void addAuthor(int id, String firstName, String lastName, String email) throws SQLException
	{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("insert into Authors(id, Firstname, Lastname, mail) values(?,?,?,?)");
			
			myStmt.setInt(1, id);
			myStmt.setString(2, firstName);
			myStmt.setString(3, lastName);
			myStmt.setString(4, email);
			myStmt.executeUpdate();
			
		}
		finally {
			Helper.closeConnection(myConn, myStmt, myRs);
		}

	}
	
	private Authors convertRowToAuthors(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String lastName = myRs.getString("Lastname");
		String firstName = myRs.getString("Firstname");
		String mail = myRs.getString("mail");
		
		Authors tempAuthors = new Authors(id, lastName, firstName, mail);
		
		return tempAuthors;
	}

	

	public static void main(String[] args) throws Exception {
		
		AuthorsDAO dao = new AuthorsDAO();
		//System.out.println(dao.searchAuthorss("Iheb"));
		dao.addAuthor(7, "t", "f", "g");
		//System.out.println(dao.getAllAuthorss());
	}
    
}
