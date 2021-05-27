package service;

import java.util.List;

import dao.ProfessorDAO;
import dto.ProfessorDTO;

public class ProfessorService {

    private ProfessorDAO professorDAO;

    public ProfessorService() {
        professorDAO = new ProfessorDAO();
    }

    public ProfessorDTO salvar(ProfessorDTO professorDTO) {
        return professorDAO.salvar(professorDTO);
    }

    public List<ProfessorDTO> listar() {
        return professorDAO.listar();
    }

    public void atualizar(ProfessorDTO professorDTO) {
        professorDAO.atualizar(professorDTO);
    }

    public void deletar(int idProfessor) {
        professorDAO.deletar(idProfessor);
    }
}
