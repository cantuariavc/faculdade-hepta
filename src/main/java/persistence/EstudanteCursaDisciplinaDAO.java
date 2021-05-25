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
	
}
