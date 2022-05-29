package Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Helper {

    public static MysqlDataSource getMySQLDataSource() {

        Properties props = new Properties();
        String fileName = "/home/iheb/Desktop/dev/sql/test/src/main/resources/db.properties";

        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException ex) {
            Logger lgr = Logger.getLogger(Helper.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(props.getProperty("mysql.url"));
        ds.setUser(props.getProperty("mysql.username"));
        ds.setPassword(props.getProperty("mysql.password"));

        return ds;
    }

    public static Connection getConnection() throws Exception {
        Connection myConn;
        // connect to database
        myConn = getMySQLDataSource().getConnection();
        //System.out.println("DB connection successful to: " + Helper.getMySQLDataSource().getUrl());
        return myConn;

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
