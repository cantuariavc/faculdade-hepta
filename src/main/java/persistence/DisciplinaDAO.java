package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;
import model.Professor;

public class DisciplinaDAO {

	private Connection connection;

	public DisciplinaDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Disciplina disciplina) throws SQLException {
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
	
	public List<Disciplina> listar() throws SQLException {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		String sql = "SELECT d.idDisciplina, d.nome, d.ementaArquivo, d.ementaNomeArquivo, d.ementaTipoArquivo, p.idProfessor, p.nomeCompleto "
					+ "FROM faculdadeHepta.DISCIPLINA d "
					+ "INNER JOIN faculdadeHepta.PROFESSOR p on d.idProfessor = p.idProfessor";
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				Disciplina disciplina;
				Professor professor;
				while (rst.next()) {
					professor = new Professor(rst.getInt("idProfessor"), rst.getString("nomeCompleto"));
					if (rst.getBytes("ementaArquivo") != null) {
						disciplina = new Disciplina(
								rst.getInt("idDisciplina"), 
								rst.getString("nome"), 
								rst.getBytes("ementaArquivo"), 
								rst.getString("ementaNomeArquivo"), 
								rst.getString("ementaTipoArquivo"));
					} else {						
						disciplina = new Disciplina(rst.getInt("idDisciplina"), rst.getString("nome"));
					}
					
					disciplina.setProfessor(professor);
					disciplinas.add(disciplina);
				}
			}
		}
		
		return disciplinas;
	}
	
}
