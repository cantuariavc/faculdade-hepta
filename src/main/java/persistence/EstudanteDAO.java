package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Estudante;

public class EstudanteDAO {
	
	private Connection connection;

	public EstudanteDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvaEstudante(Estudante estudante) throws SQLException {
		String sql = "INSERT INTO ESTUDANTE(nomeCompleto) VALUES (?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, estudante.getNomeCompleto());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					estudante.setIdEstudante(rst.getInt(1));
				}
			}
		}

	}
	
}
