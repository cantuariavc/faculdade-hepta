package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.ProfessorDTO;
import model.Professor;
import util.ConnectionFactory;

public class ProfessorDAO {

    public ProfessorDTO salvar(ProfessorDTO professorDTO) {
        Professor professor = new Professor(professorDTO);

        try (Connection connection = ConnectionFactory.getConnection()) {
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
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return new ProfessorDTO(professor);
    }

    public List<ProfessorDTO> listar() {
        List<ProfessorDTO> professores = new ArrayList<ProfessorDTO>();

        try {
            try (Connection connection = ConnectionFactory.getConnection()) {
                String sql = "SELECT idProfessor, nomeCompleto FROM PROFESSOR";

                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.execute();

                    try (ResultSet rst = pstm.getResultSet()) {
                        Professor professor;
                        while (rst.next()) {
                            professor = new Professor(rst.getInt("idProfessor"), rst.getString("nomeCompleto"));
                            professores.add(new ProfessorDTO(professor));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return professores;
    }

    public void atualizar(ProfessorDTO professorDTO) {
        Professor professor = new Professor(professorDTO);

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE PROFESSOR SET nomeCompleto='" + professor.getNomeCompleto() + "' " + "WHERE idProfessor='" + professor.getIdProfessor() + "'";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void deletar(int idProfessor) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM PROFESSOR WHERE idProfessor='" + idProfessor + "'";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
