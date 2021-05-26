package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dto.ProfessorDTO;

class ProfessorDAOTest {

    private static List<ProfessorDTO> professoresDTO = new ArrayList<ProfessorDTO>();
    private static ProfessorDAO professorDAO = new ProfessorDAO();
    private static ProfessorDTO professorDTO;
    
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        professoresDTO.add(new ProfessorDTO("Albert Einstein"));
        professoresDTO.add(new ProfessorDTO("Isaac Newton"));
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        for (ProfessorDTO professorDTO : professoresDTO) {
            professorDAO.deletar(professorDTO.getIdProfessor());
        }
    }

    
    @Test
    void testSalvar() throws SQLException {
        for (ProfessorDTO pDTO : professoresDTO) {
            professorDTO = professorDAO.salvar(pDTO);
            assertNotNull(professorDTO.getIdProfessor());
            pDTO.setIdProfessor(professorDTO.getIdProfessor());
        }
    }

    @Test
    void testListar() throws SQLException {
        List<ProfessorDTO> professoresDTOBD = professorDAO.listar();

        for (ProfessorDTO professorDTO : professoresDTOBD) {
            assertNotNull(professorDTO.getIdProfessor());
            assertNotNull(professorDTO.getNomeCompleto());
        }
    }

    @Test
    void testAtualizar() throws SQLException {
        professorDTO = professoresDTO.get(0);
        int idProfessor = professorDTO.getIdProfessor(); 
        String nomeCompleto = professorDTO.getNomeCompleto();

        professorDTO.setNomeCompleto("Roberto Carlos");
        professorDAO.atualizar(professorDTO);

        for (ProfessorDTO professorDTO : professoresDTO) {
            if (professorDTO.getIdProfessor() == idProfessor)
                assertFalse(professorDTO.getNomeCompleto().equals(nomeCompleto));
        }
    }

    @Test
    void testDeletar() throws SQLException {
        for (ProfessorDTO professorDTO : professoresDTO) {
            professorDAO.deletar(professorDTO.getIdProfessor());
        }

        List<ProfessorDTO> professoresDTOBD = professorDAO.listar();
        for (ProfessorDTO professorDTO : professoresDTO) {
            for (ProfessorDTO professorDTOBD : professoresDTOBD) {
                assertFalse(professorDTOBD.getIdProfessor() == professorDTO.getIdProfessor());
            }
        }
    }

}
