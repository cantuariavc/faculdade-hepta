import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/faculdadeHepta",
				"root",
				"root");
		System.out.println("Conectado com a base de dados.");
		
		connection.close();
		System.out.println("Desconectado da base de dados.");
	}

}
