package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.EstudanteDTO;
import model.Estudante;
import util.ConnectionFactory;

public class EstudanteDAO {

    public EstudanteDTO salvar(EstudanteDTO estudanteDTO) throws SQLException, ClassNotFoundException {
        Estudante estudante = new Estudante(estudanteDTO);
        try (Connection connection = ConnectionFactory.getConnection()) {
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

        return new EstudanteDTO(estudante);
    }

    public List<EstudanteDTO> listar() throws SQLException, ClassNotFoundException {
        List<EstudanteDTO> estudantes = new ArrayList<EstudanteDTO>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "SELECT idEstudante, nomeCompleto FROM ESTUDANTE";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    Estudante estudante;
                    while (rst.next()) {
                        estudante = new Estudante(rst.getInt("idEstudante"), rst.getString("nomeCompleto"));
                        estudantes.add(new EstudanteDTO(estudante));
                    }
                }
            }
        }

        return estudantes;
    }

    public void atualizar(EstudanteDTO estudanteDTO) throws SQLException, ClassNotFoundException {
        Estudante estudante = new Estudante(estudanteDTO);

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE ESTUDANTE SET nomeCompleto='" + estudante.getNomeCompleto() + "' " + "WHERE idEstudante='" + estudante.getIdEstudante() + "'";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
            }
        }
    }

    public void deletar(int idEstudante) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM ESTUDANTE WHERE idEstudante='" + idEstudante + "'";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
            }
        }
    }

}
