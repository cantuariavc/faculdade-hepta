package service;

import java.sql.SQLException;
import java.util.List;

import dao.ProfessorDAO;
import dto.DisciplinaDTO;
import dto.ProfessorDTO;

public class ProfessorService {

    private ProfessorDAO professorDAO;

    public ProfessorService() {
        professorDAO = new ProfessorDAO();
    }

    public ProfessorDTO salvar(ProfessorDTO professorDTO) throws ClassNotFoundException, SQLException {
        return professorDAO.salvar(professorDTO);
    }

    public List<ProfessorDTO> listar() throws ClassNotFoundException, SQLException {
        return professorDAO.listar();
    }

    public void atualizar(ProfessorDTO professorDTO) throws ClassNotFoundException, SQLException {
        professorDAO.atualizar(professorDTO);
    }

    public void deletar(int idProfessor) throws ClassNotFoundException, SQLException {
        professorDAO.deletar(idProfessor);
    }
    
    public ProfessorDTO detalhar(int idProfessor) throws ClassNotFoundException, SQLException {
        return professorDAO.detalhar(idProfessor);
    }
}
