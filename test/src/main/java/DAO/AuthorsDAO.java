package DAO;

import java.util.*;

import Helpers.Helper;
import Tables.Authors;

import java.sql.*;

public class AuthorsDAO {

	private Connection myConn;

	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	public AuthorsDAO() throws Exception {

		// connect to database
		myConn = Helper.getConnection();
		System.out.println("DB connection successful to: " + Helper.getUrl());
	}

	public List<Authors> getAllAuthorss() throws Exception {
		List<Authors> list = new ArrayList<>();

		Statement myStmt = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Authors");

			while (myRs.next()) {
				Authors tempAuthors = convertRowToAuthors(myRs);
				list.add(tempAuthors);
			}

			return list;
		} finally {
			//Helper.closeConnection(myConn, myStmt, myRs);
		}
	}

	public List<Authors> searchAuthors(int id, String firstName, String lastName, String email) throws Exception {
		List<Authors> list = new ArrayList<>();

		try {
			firstName += "%";
			myStmt = myConn.prepareStatement("select * from Authors where Firstname like ? and ");

			myStmt.setString(1, firstName);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Authors tempAuthors = convertRowToAuthors(myRs);
				list.add(tempAuthors);
			}

			return list;
		} finally {
			//Helper.closeConnection(myConn, myStmt, myRs);
		}

	}

	public void addAuthor(int id, String firstName, String lastName, String email) throws SQLException {

		try {
			if(getId(id) == id)
			{
				System.out.println("ERROR ID EXIST");
				return;			
			}
			myStmt = myConn.prepareStatement("insert into Authors(id, Firstname, Lastname, mail) values(?,?,?,?)");

			myStmt.setInt(1, id);
			myStmt.setString(2, firstName);
			myStmt.setString(3, lastName);
			myStmt.setString(4, email);
			myStmt.executeUpdate();

		} finally {
			//Helper.closeConnection(myConn, myStmt, myRs);
		}

	}

	public int getAuthor(int id, String firstName, String lastName, String email) throws SQLException
	{
		myStmt = myConn.prepareStatement("select id from Authors where id=? and Firstname=? and Lastname=? and mail=?");
		myStmt.setInt(1, id);
		myStmt.setString(2, firstName);
		myStmt.setString(3, lastName);
		myStmt.setString(4, email);
		myRs =  myStmt.executeQuery();
		int res = -1;
		if(myRs.next())
		{
			res = 1;
		}

		return res;
		
	}

	public void delAuthor(int id, String firstName, String lastName, String email) throws SQLException {

		try {
			if(getAuthor(id, firstName, lastName, email) == -1)
			{
				System.out.println("ERROR Author does not exist");
				return;			
			}

			myStmt = myConn.prepareStatement("delete from Authors where id=?");
			myStmt.setInt(1, id);
			myStmt.executeUpdate();

		} finally {
			//Helper.closeConnection(myConn, myStmt, myRs);
		}

	}

	public int getId(int id) throws SQLException {

		int res = -1;
		Statement statement = null;
		statement = myConn.createStatement();
		myRs = statement.executeQuery("select id from Authors where id=" + id);
		if(myRs.next())
			res = myRs.getInt(1);
		return res;
	}

	private Authors convertRowToAuthors(ResultSet myRs) throws SQLException {

		int id = myRs.getInt("id");
		String lastName = myRs.getString("Lastname");
		String firstName = myRs.getString("Firstname");
		String mail = myRs.getString("mail");

		Authors tempAuthors = new Authors(id, firstName, lastName, mail);
		return tempAuthors;
	}

	public static void main(String[] args) throws Exception {

		AuthorsDAO dao = new AuthorsDAO();
		dao.delAuthor(1, "Iheb", "Moussa", "iheb.moussa@gmail.com");
		// System.out.println(dao.searchAuthorss("Iheb"));
		//dao.addAuthor(7, "t", "f", "g");
		// System.out.println(dao.getAllAuthorss());
	}

}
