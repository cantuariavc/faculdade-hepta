package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DisciplinaDTO;
import model.Disciplina;
import model.Professor;
import util.ConnectionFactory;

public class DisciplinaDAO {

    public DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO) throws SQLException, ClassNotFoundException {
        Disciplina disciplina = new Disciplina(disciplinaDTO);

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO DISCIPLINA(nome, planoEnsinoArquivo, planoEnsinoNome, idProfessor) " + "VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, disciplina.getNome());
                pstm.setBytes(2, disciplina.getPlanoEnsinoArquivo());
                pstm.setString(3, disciplina.getPlanoEnsinoNome());
                pstm.setInt(4, disciplina.getProfessor().getIdProfessor());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next())
                        disciplina.setIdDisciplina(rst.getInt(1));
                }
            }
        }

        return new DisciplinaDTO(disciplina);

    }

    public List<DisciplinaDTO> listar() throws ClassNotFoundException, SQLException {
        List<DisciplinaDTO> disciplinas = new ArrayList<DisciplinaDTO>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "SELECT d.idDisciplina, d.nome, d.planoEnsinoArquivo, d.planoEnsinoNome, " + "p.idProfessor, p.nomeCompleto "
                    + "FROM faculdadeHepta.DISCIPLINA d " + "INNER JOIN faculdadeHepta.PROFESSOR p on d.idProfessor = p.idProfessor";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    Disciplina disciplina;
                    while (rst.next()) {
                        disciplina = new Disciplina(rst.getInt("idDisciplina"), rst.getString("nome"), rst.getBytes("planoEnsinoArquivo"),
                                rst.getString("planoEnsinoNome"), new Professor(rst.getInt("idProfessor"), rst.getString("nomeCompleto")));

                        disciplinas.add(new DisciplinaDTO(disciplina));
                    }
                }
            }
        }

        return disciplinas;
    }

    public void atualizar(DisciplinaDTO disciplinaDTO) throws ClassNotFoundException, SQLException {
        Disciplina disciplina = new Disciplina(disciplinaDTO);

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE DISCIPLINA SET nome='" + disciplina.getNome() + "', planoEnsinoArquivo='" + disciplina.getPlanoEnsinoArquivo()
                    + "', planoEnsinoNome='" + disciplina.getPlanoEnsinoNome() + "', idProfessor='" + disciplina.getProfessor().getIdProfessor()
                    + "' WHERE idDisciplina='" + disciplina.getIdDisciplina() + "'";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
            }
        }

    }

    public void deletar(int idDisciplina) throws ClassNotFoundException, SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM DISCIPLINA WHERE idDisciplina='" + idDisciplina + "'";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
            }
        }
    }

}
