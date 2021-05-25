package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;
import model.Estudante;
import model.EstudanteCursaDisciplina;
import model.Professor;

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
	
	public List<Estudante> listarEstudantes(int idDisciplina) throws SQLException {
		List<Estudante> estudantes = new ArrayList<Estudante>();
		String sql = "SELECT e.idEstudante, e.nomeCompleto "
					+ "FROM cursa c INNER JOIN ESTUDANTE e ON e.idEstudante = c.idEstudante "
					+ "WHERE c.idDisciplina = "+idDisciplina+"";
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				Estudante estudante;
				while (rst.next()) {
					estudante = new Estudante(rst.getInt("idEstudante"), rst.getString("nomeCompleto"));
					estudantes.add(estudante);
				}
			}
		}
		
		return estudantes;
	}
	
	public List<Disciplina> listarDisciplinas(int idEstudante) throws SQLException {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		String sql = "SELECT d.idDisciplina, d.nome, d.ementaArquivo, d.ementaNomeArquivo, d.ementaTipoArquivo, p.idProfessor, p.nomeCompleto "
					+ "FROM cursa c "
					+ "INNER JOIN DISCIPLINA d ON d.idDisciplina = c.idDisciplina "
					+ "INNER JOIN PROFESSOR p ON p.idProfessor = d.idProfessor "
					+ "WHERE c.idEstudante = "+idEstudante+"";
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				Disciplina disciplina;
				Professor professor;
				while (rst.next()) {
					professor = new Professor(rst.getInt("idProfessor"), rst.getString("nomeCompleto"));
					disciplina = new Disciplina(
							rst.getInt("idDisciplina"), 
							rst.getString("nome"), 
							rst.getBytes("ementaArquivo"), 
							rst.getString("ementaNomeArquivo"), 
							rst.getString("ementaTipoArquivo"),
							professor);
					
					disciplinas.add(disciplina);
				}
			}
		}
		
		return disciplinas;
	}
	
	public void deletar(EstudanteCursaDisciplina estudanteCursaDisciplina) throws SQLException {
		String sql = "DELETE FROM cursa "
					+ "WHERE idEstudante = '"+estudanteCursaDisciplina.getEstudante().getIdEstudante()+"' "
						+ "AND idDisciplina='"+estudanteCursaDisciplina.getDisciplina().getIdDisciplina()+"'";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
		}
	}
	
}
