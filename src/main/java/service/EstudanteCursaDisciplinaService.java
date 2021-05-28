package service;

import java.util.List;

import dao.EstudanteCursaDisciplinaDAO;
import dto.DisciplinaDTO;
import dto.EstudanteCursaDisciplinaDTO;
import dto.EstudanteDTO;

public class EstudanteCursaDisciplinaService {

    private EstudanteCursaDisciplinaDAO estudanteCursaDisciplinaDAO;

    public EstudanteCursaDisciplinaService() {
        estudanteCursaDisciplinaDAO = new EstudanteCursaDisciplinaDAO();
    }

    public EstudanteCursaDisciplinaDTO salvaEstudanteCursaDisciplina(EstudanteCursaDisciplinaDTO cursaDTO) {
        return estudanteCursaDisciplinaDAO.salvaEstudanteCursaDisciplina(cursaDTO);
    }

    public List<EstudanteDTO> listarEstudantes(int idDisciplina) {
        return estudanteCursaDisciplinaDAO.listarEstudantes(idDisciplina);
    }

    public List<DisciplinaDTO> listarDisciplinas(int idEstudante) {
        return estudanteCursaDisciplinaDAO.listarDisciplinas(idEstudante);
    }

    public void deletar(EstudanteCursaDisciplinaDTO estudanteCursaDisciplinaDTO) {
        estudanteCursaDisciplinaDAO.deletar(estudanteCursaDisciplinaDTO);
    }
}
