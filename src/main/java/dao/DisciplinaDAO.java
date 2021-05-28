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
            String sql = "INSERT INTO DISCIPLINA(nome, ementaArquivo, ementaNomeArquivo, ementaTipoArquivo, idProfessor) " + "VALUES (?, ?, ?, ?, ?)";

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

        return new DisciplinaDTO(disciplina);

    }

    public List<DisciplinaDTO> listar() throws ClassNotFoundException, SQLException {
        List<DisciplinaDTO> disciplinas = new ArrayList<DisciplinaDTO>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "SELECT d.idDisciplina, d.nome, d.ementaArquivo, d.ementaNomeArquivo, d.ementaTipoArquivo, " + "p.idProfessor, p.nomeCompleto "
                    + "FROM faculdadeHepta.DISCIPLINA d " + "INNER JOIN faculdadeHepta.PROFESSOR p on d.idProfessor = p.idProfessor";

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

        return disciplinas;
    }

    public void atualizar(DisciplinaDTO disciplinaDTO) throws ClassNotFoundException, SQLException {
        Disciplina disciplina = new Disciplina(disciplinaDTO);

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE DISCIPLINA " + "SET nome='" + disciplina.getNome() + "', " + "ementaArquivo='" + disciplina.getEmentaArquivo() + "', "
                    + "ementaNomeArquivo='" + disciplina.getEmentaNomeArquivo() + "', " + "ementaTipoArquivo='" + disciplina.getEmentaTipoArquivo() + "', "
                    + "idProfessor='" + disciplina.getProfessor().getIdProfessor() + "' " + "WHERE idDisciplina='" + disciplina.getIdDisciplina() + "'";

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
