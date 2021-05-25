package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Professor;

public class ProfessorDAO {

	private Connection connection;

	public ProfessorDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Professor professor) throws SQLException {
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

	public List<Professor> listar() throws SQLException {
		List<Professor> professores = new ArrayList<Professor>();
		String sql = "SELECT idProfessor, nomeCompleto FROM PROFESSOR";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				Professor professor;
				while (rst.next()) {
					professor = new Professor(rst.getInt("idProfessor"), rst.getString("nomeCompleto"));
					professores.add(professor);
				}
			}
		}

		return professores;
	}

	public void atualizar(Professor professor) throws SQLException {
		String sql = "UPDATE PROFESSOR SET nomeCompleto='"+professor.getNomeCompleto()+"' "
				+ "WHERE idProfessor='"+professor.getIdProfessor()+"'";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
		}

	}


	public void deletar(int idProfessor) throws SQLException {
		String sql = "DELETE FROM PROFESSOR WHERE idProfessor='"+idProfessor+"'";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
		}
	}
	
}
