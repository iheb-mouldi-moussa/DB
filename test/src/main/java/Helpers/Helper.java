package Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Helper {

    private static Properties props;
    private static Properties getConnectionData() {

        props = new Properties();

        String fileName = "/home/iheb/Desktop/dev/sql/DB/test/src/main/resources/db.properties";

        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
        } catch (IOException ex) {
            Logger lgr = Logger.getLogger(Helper.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return props;
    }


    public static Connection getConnection() throws Exception {
        
        props = getConnectionData();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String passwd = props.getProperty("db.password");

        Connection myConn = DriverManager.getConnection(url, user, passwd);
        // connect to database
        //System.out.println("DB connection successful to: " + Helper.getMySQLDataSource().getUrl());
        return myConn;

    }

    public static String getUrl() throws Exception{
        props = getConnectionData();
        return props.getProperty("db.url");
    }

    public static void closeConnection(Connection conn, Statement se,ResultSet rs) throws SQLException
    {

		if (rs != null) {
			rs.close();
		}

		if (se != null) {
            se.close();	
		}
		
		if (conn != null) {
			conn.close();
        }
    }

}
