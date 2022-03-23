package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Communicator {
	public static void executeUpdate(String query, String[] vars) throws SQLException {
		PreparedStatement stm = getPreparedStatement(query, vars);
		stm.executeUpdate();
	}
	
	public static ResultSet executeQuery(String query, String[] vars) throws SQLException {
		PreparedStatement stm = getPreparedStatement(query, vars);
		return stm.executeQuery();
	}
	
	private static PreparedStatement getPreparedStatement(String query, String[] vars) throws SQLException {
		PreparedStatement stm = Connector.getConnection().prepareStatement(query);
		for (int i = 0; i < vars.length; i++) 
			stm.setString(i + 1, vars[i]);
		return stm;
	}
}
