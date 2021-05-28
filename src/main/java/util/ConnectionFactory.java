package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
	    Class.forName("com.mysql.jdbc.Driver");
	    return DriverManager.getConnection(
		            "jdbc:mysql://localhost/faculdadeHepta",
	                "root",
	                "root");
	}
}
