package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DisciplinaDTO;
import dto.EstudanteCursaDisciplinaDTO;
import dto.EstudanteDTO;
import model.Disciplina;
import model.Estudante;
import model.EstudanteCursaDisciplina;
import model.Professor;
import util.ConnectionFactory;

public class EstudanteCursaDisciplinaDAO {

    public EstudanteCursaDisciplinaDTO salvaEstudanteCursaDisciplina(EstudanteCursaDisciplinaDTO cursaDTO) {
        EstudanteCursaDisciplina cursa = new EstudanteCursaDisciplina(cursaDTO);

        try {
            try (Connection connection = ConnectionFactory.getConnection()) {
                String sql = "INSERT INTO cursa(idEstudante, idDisciplina) VALUES (?, ?)";

                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.setInt(1, cursa.getEstudante().getIdEstudante());
                    pstm.setInt(2, cursa.getDisciplina().getIdDisciplina());

                    pstm.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return new EstudanteCursaDisciplinaDTO(cursa);
    }

    public List<EstudanteDTO> listarEstudantes(int idDisciplina) {
        List<EstudanteDTO> estudantes = new ArrayList<EstudanteDTO>();

        try {
            try (Connection connection = ConnectionFactory.getConnection()) {
                String sql = "SELECT e.idEstudante, e.nomeCompleto " + "FROM cursa c INNER JOIN ESTUDANTE e ON e.idEstudante = c.idEstudante "
                        + "WHERE c.idDisciplina = " + idDisciplina + "";

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
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return estudantes;
    }

    public List<DisciplinaDTO> listarDisciplinas(int idEstudante) {
        List<DisciplinaDTO> disciplinas = new ArrayList<DisciplinaDTO>();

        try {
            try (Connection connection = ConnectionFactory.getConnection()) {
                String sql = "SELECT d.idDisciplina, d.nome, d.ementaArquivo, d.ementaNomeArquivo, d.ementaTipoArquivo, p.idProfessor, p.nomeCompleto "
                        + "FROM cursa c " + "INNER JOIN DISCIPLINA d ON d.idDisciplina = c.idDisciplina "
                        + "INNER JOIN PROFESSOR p ON p.idProfessor = d.idProfessor " + "WHERE c.idEstudante = " + idEstudante + "";

                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.execute();

                    try (ResultSet rst = pstm.getResultSet()) {
                        Disciplina disciplina;

                        while (rst.next()) {
                            disciplina = new Disciplina(rst.getInt("idDisciplina"), rst.getString("nome"), rst.getBytes("ementaArquivo"),
                                    rst.getString("ementaNomeArquivo"), rst.getString("ementaTipoArquivo"),
                                    new Professor(rst.getInt("idProfessor"), rst.getString("nomeCompleto")));

                            disciplinas.add(new DisciplinaDTO(disciplina));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return disciplinas;
    }

    public void deletar(EstudanteCursaDisciplinaDTO estudanteCursaDisciplinaDTO) {
        EstudanteCursaDisciplina estudanteCursaDisciplina = new EstudanteCursaDisciplina(estudanteCursaDisciplinaDTO);

        try {
            try (Connection connection = ConnectionFactory.getConnection()) {
                String sql = "DELETE FROM cursa " + "WHERE idEstudante = '" + estudanteCursaDisciplina.getEstudante().getIdEstudante() + "' "
                        + "AND idDisciplina='" + estudanteCursaDisciplina.getDisciplina().getIdDisciplina() + "'";

                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
