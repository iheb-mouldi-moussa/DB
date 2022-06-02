package DAO;

import java.util.*;

import javax.persistence.Lob;

import Helpers.HelperDB;
import Tables.Articles;
import Tables.Authors;

import java.sql.*;

public class ArticlesDAO {
    
	private Connection myConn;
    private AuthorsDAO authorsDAO = new AuthorsDAO();

	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	public ArticlesDAO() throws Exception {

		// connect to database
		myConn = HelperDB.getConnection();
		System.out.println("DB connection successful to: " + HelperDB.getUrl());
	}

	public List<Articles> getAllArticles() throws Exception {
		List<Articles> list = new ArrayList<>();

		Statement myStmt = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Articles");

			while (myRs.next()) {
				Articles tempArticles = convertRowToArticles(myRs);
				list.add(tempArticles);
			}

			return list;
		} finally {
			//Helper.closeConnection(myConn, myStmt, myRs);
		}
	}


    public boolean validArg(int id, String title, String content, String summary, int likes, int author_id) throws SQLException
    {
        if(getId(id) == id || id == -1)
        {
            System.err.println("ERROR ID EXIST OR EMPTY !!!!!");
            return false;
        }

        int isAuthorExist = authorsDAO.getId(author_id);
        if(isAuthorExist == -1)
        {
            System.err.println("ERROR AUTHOR DOES NOT EXIST CHECK AUTHOR_ID");
            return false;
        }
        if(title.isEmpty() || content.isEmpty() || summary.isEmpty())
        {
            System.err.println("ERROR THIS FIELD: TITLE,CONTENT,SUMMARY CAN NOT BE EMPTY ");
            return false;
        }
        
        return true;
    }

    /*public void likeArticle(Articles articles)
    {
        int currLike = articles.getLikes();
        articles.setLikes(++currLike);
    }

    public void dislikeArticle(Articles articles)
    {
        int currLike = articles.getLikes();
        articles.setLikes(--currLike);
    }*/

	public void addArticle(int id, String title, String content, String summary, int likes, int author_id) throws SQLException {

		try {
			if(!validArg(id, title, content, summary, likes, author_id))
                return;			
			
			myStmt = myConn.prepareStatement("insert into Articles(id, title, content, summary, likes, author_id) values(?,?,?,?,?,?)");

			myStmt.setInt(1, id);
            myStmt.setString(2, title);
			myStmt.setString(3, content);
			myStmt.setString(4, summary);
			myStmt.setInt(5, likes);
            myStmt.setInt(6, author_id);
			myStmt.executeUpdate();

		} finally {
			//Helper.closeConnection(myConn, myStmt, myRs);
		}

	}

    public Articles geArticles(int articleId) throws SQLException
    {
        Articles article = null;
        myStmt = myConn.prepareStatement("select * from Articles where id=?");
        myStmt.setInt(1, articleId);
        myRs = myStmt.executeQuery();
        if(myRs.next())
            article = convertRowToArticles(myRs);
        return article;
    }

	public Authors getArticleAuthor(int author_id) throws SQLException
	{
        Authors author = null;
		myStmt = myConn.prepareStatement("select * from Authors where Authors.id=?");
		myStmt.setInt(1, author_id);
		myRs =  myStmt.executeQuery();
		//int res = -1;
		if(myRs.next())
		{
			author = new Authors(myRs.getInt("id"), myRs.getString("Firstname"),
             myRs.getString("Lastname"), myRs.getString("mail"));
        }
		return author;
		
	}



	public void delArticle(int articleId) throws SQLException {

		try {
			if(getId(articleId) == -1)
			{
				System.out.println("ERROR Article does not exist");
				return;			
			}

			myStmt = myConn.prepareStatement("delete from Articles where id=?");
			myStmt.setInt(1, articleId);
			myStmt.executeUpdate();

		} finally {
			//Helper.closeConnection(myConn, myStmt, myRs);
		}

	}

	public int getId(int id) throws SQLException {

		int res = -1;
		Statement statement = null;
		statement = myConn.createStatement();
		myRs = statement.executeQuery("select id from Articles where id=" + id);
		if(myRs.next())
			res = myRs.getInt(1);
		return res;
	}

	private Articles convertRowToArticles(ResultSet myRs) throws SQLException {

		int id = myRs.getInt("id");
		String title = myRs.getString("title");
		String content = myRs.getString("content");
        String summary = myRs.getString("summary");
        int likes = myRs.getInt("likes");
        int author_id = myRs.getInt("Author_id");

		Articles tempArticles = new Articles(id, title, content, summary, likes, author_id);
		return tempArticles;
	}

    public static void main(String[] args) throws Exception {
        ArticlesDAO articlesDAO = new ArticlesDAO();
        System.out.print(articlesDAO.geArticles(1));
        //System.out.println(articlesDAO.getArticleAuthor(1));
        //System.out.println(articlesDAO.getAllArticles());
    }
}
