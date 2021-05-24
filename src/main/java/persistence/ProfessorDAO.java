package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Professor;

public class ProfessorDAO {
	
	private Connection connection;

	public ProfessorDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvaProfessor(Professor professor) throws SQLException {
		String sql = "INSERT INTO PROFESSOR(nomeCompleto) VALUES (?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, professor.getNomeCompleto());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					professor.setIdProfessor(rst.getInt(1));
				}
			}
		}
	}
	
}
