package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Estudante;

public class EstudanteDAO {
	
	private Connection connection;

	public EstudanteDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Estudante estudante) throws SQLException {
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
	
	public List<Estudante> listar() throws SQLException {
		List<Estudante> estudantes = new ArrayList<Estudante>();
		String sql = "SELECT idEstudante, nomeCompleto FROM ESTUDANTE";
		
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
	
	public void atualizar(Estudante estudante) throws SQLException {
		String sql = "UPDATE ESTUDANTE SET nomeCompleto='"+estudante.getNomeCompleto()+"' "
					+ "WHERE idEstudante='"+estudante.getIdEstudante()+"'";
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
		}
	}
	
	public void deletar(int idEstudante) throws SQLException {
		String sql = "DELETE FROM ESTUDANTE WHERE idEstudante='"+idEstudante+"'";
	
	try (PreparedStatement pstm = connection.prepareStatement(sql)) {
		pstm.execute();
	}

} 
	
}
