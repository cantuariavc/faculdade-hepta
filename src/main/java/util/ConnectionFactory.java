package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;
    
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
		    connection = DriverManager.getConnection(
		            "jdbc:mysql://localhost/faculdadeHepta",
	                "root",
	                "root");
		}
	    
	    return connection;
	}
}
