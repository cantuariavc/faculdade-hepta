package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Disciplina;

public class DisciplinaDAO {

	private Connection connection;

	public DisciplinaDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvaDisciplina(Disciplina disciplina) throws SQLException {
		String sql = "INSERT INTO DISCIPLINA(nome, ementaArquivo, ementaNomeArquivo, ementaTipoArquivo, idProfessor) "
					+ "VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, disciplina.getNome());
			pstm.setBytes(2, disciplina.getEmentaArquivo());
			pstm.setString(3, disciplina.getEmentaNomeArquivo());
			pstm.setString(4, disciplina.getEmentaTipoArquivo());
			pstm.setInt(5, disciplina.getProfessor().getIdProfessor());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					disciplina.setIdDisciplina(rst.getInt(1));
				}
			}
		}
	}
	
}
