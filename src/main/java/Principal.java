import java.sql.Connection;
import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperaConexao();
		System.out.println("Conectado com a base de dados.");
		
		connection.close();
		System.out.println("Desconectado da base de dados.");
	}

}
