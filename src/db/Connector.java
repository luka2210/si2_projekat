package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Connector {
	private static String serverName = "localhost";
	private static String userName = "root";
	private static String dbName = "si2_projekat";
	private static int portNumber = 3306;
	private static String password = ""; 
	private static Connection cn = null;
	
	public static Connection getConnection() {
		if (cn == null) 
			return newConnection();
		return cn;
	}
	
	private static Connection newConnection() {
		MysqlDataSource data = new MysqlDataSource();
		
		data.setServerName(serverName);
		data.setUser(userName);
		data.setDatabaseName(dbName);
		data.setPortNumber(portNumber);
		data.setPassword(password);
		
		try {
			cn = data.getConnection();
		}
		catch (SQLException e) {
			Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return cn;
	}
}
