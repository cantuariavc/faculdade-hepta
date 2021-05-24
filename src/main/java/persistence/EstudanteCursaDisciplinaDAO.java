package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Disciplina;
import model.Estudante;

public class EstudanteCursaDisciplinaDAO {

	private Connection connection;

	public EstudanteCursaDisciplinaDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvaEstudanteCursaDisciplina(Estudante estudante, Disciplina disciplina) throws SQLException {
		String sql = "INSERT INTO cursa(idEstudante, idDisciplina) VALUES (?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1, estudante.getIdEstudante());
			pstm.setInt(2, disciplina.getIdDisciplina());
			
			pstm.execute();
		}
	}
	
}
