package service;

import java.sql.SQLException;
import java.util.List;

import dao.DisciplinaDAO;
import dto.DisciplinaDTO;

public class DisciplinaService {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaService() {
        disciplinaDAO = new DisciplinaDAO();
    }

    public DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO) throws ClassNotFoundException, SQLException {
        return disciplinaDAO.salvar(disciplinaDTO);
    }

    public List<DisciplinaDTO> listar() throws ClassNotFoundException, SQLException {
        return disciplinaDAO.listar();
    }

    public void atualizar(DisciplinaDTO disciplinaDTO) throws ClassNotFoundException, SQLException {
        disciplinaDAO.atualizar(disciplinaDTO);
    }

    public void deletar(int idDisciplina) throws ClassNotFoundException, SQLException {
        disciplinaDAO.deletar(idDisciplina);
    }
}
