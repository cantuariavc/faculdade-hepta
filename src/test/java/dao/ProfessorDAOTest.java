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

    private static List<ProfessorDTO> listaProfessorDTO = new ArrayList<ProfessorDTO>();
    private static ProfessorDAO professorDAO = new ProfessorDAO();
    private static ProfessorDTO professorDTO;

    @BeforeAll
    static void setUpBeforeClass() {
        try {
            listaProfessorDTO.add(new ProfessorDTO("Albert Einstein"));
            listaProfessorDTO.add(new ProfessorDTO("Isaac Newton"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterAll
    static void tearDownAfterClass() {
        try {
            for (ProfessorDTO professorDTO : listaProfessorDTO)
                professorDAO.deletar(professorDTO.getIdProfessor());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testSalvar() throws ClassNotFoundException, SQLException {
        for (ProfessorDTO pDTO : listaProfessorDTO) {
            professorDTO = professorDAO.salvar(pDTO);
            assertNotNull(professorDTO.getIdProfessor());
            pDTO.setIdProfessor(professorDTO.getIdProfessor());
        }
    }

    @Test
    void testListar() throws ClassNotFoundException, SQLException {
        List<ProfessorDTO> professoresDTOBD = professorDAO.listar();

        for (ProfessorDTO professorDTO : professoresDTOBD) {
            assertNotNull(professorDTO.getIdProfessor());
            assertNotNull(professorDTO.getNomeCompleto());
        }
    }

    @Test
    void testAtualizar() throws ClassNotFoundException, SQLException {
        professorDTO = listaProfessorDTO.get(0);
        int idProfessor = professorDTO.getIdProfessor();
        String nomeCompleto = professorDTO.getNomeCompleto();

        professorDTO.setNomeCompleto("Roberto Carlos");
        professorDAO.atualizar(professorDTO);

        for (ProfessorDTO professorDTO : listaProfessorDTO)
            if (professorDTO.getIdProfessor() == idProfessor)
                assertFalse(professorDTO.getNomeCompleto().equals(nomeCompleto));
    }

    @Test
    void testDeletar() throws ClassNotFoundException, SQLException {
        for (ProfessorDTO professorDTO : listaProfessorDTO)
            professorDAO.deletar(professorDTO.getIdProfessor());

        List<ProfessorDTO> professoresDTOBD = professorDAO.listar();
        for (ProfessorDTO professorDTO : listaProfessorDTO)
            for (ProfessorDTO professorDTOBD : professoresDTOBD)
                assertFalse(professorDTOBD.getIdProfessor() == professorDTO.getIdProfessor());
    }

}
