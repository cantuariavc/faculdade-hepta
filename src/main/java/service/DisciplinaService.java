package service;

import java.util.List;

import dao.DisciplinaDAO;
import dto.DisciplinaDTO;

public class DisciplinaService {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaService() {
        disciplinaDAO = new DisciplinaDAO();
    }

    public DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO) {
        return disciplinaDAO.salvar(disciplinaDTO);
    }

    public List<DisciplinaDTO> listar() {
        return disciplinaDAO.listar();
    }

    public void atualizar(DisciplinaDTO disciplinaDTO) {
        disciplinaDAO.atualizar(disciplinaDTO);
    }

    public void deletar(int idDisciplina) {
        disciplinaDAO.deletar(idDisciplina);
    }
}
