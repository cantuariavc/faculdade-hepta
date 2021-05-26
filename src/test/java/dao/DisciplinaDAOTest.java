package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dto.DisciplinaDTO;
import dto.ProfessorDTO;

class DisciplinaDAOTest {

    private static List<DisciplinaDTO> disciplinasDTO = new ArrayList<DisciplinaDTO>();
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static DisciplinaDTO disciplinaDTO;
    private static ProfessorDAO professorDAO = new ProfessorDAO();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        ProfessorDTO professor1 = professorDAO.salvar(new ProfessorDTO("Albert Einstein"));
        ProfessorDTO professor2 = professorDAO.salvar(new ProfessorDTO("Isaac Newton"));
        
        disciplinasDTO.add(new DisciplinaDTO("EDA2", null, null, null, professor1));
        disciplinasDTO.add(new DisciplinaDTO("SBD2", null, null, null, professor2));
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        for (DisciplinaDTO disciplinaDTO : disciplinasDTO) {
            disciplinaDAO.deletar(disciplinaDTO.getIdDisciplina());
            professorDAO.deletar(disciplinaDTO.getProfessorDTO().getIdProfessor());
        }
    }

    @Test
    void testSalvar() throws SQLException {        
        for (DisciplinaDTO dDTO : disciplinasDTO) {
            disciplinaDTO = disciplinaDAO.salvar(dDTO);
            assertNotNull(disciplinaDTO.getIdDisciplina());
            dDTO.setIdDisciplina(disciplinaDTO.getIdDisciplina());
        }
    }

    @Test
    void testListar() throws SQLException {
        List<DisciplinaDTO> disciplinasDTOBD = disciplinaDAO.listar();

        for (DisciplinaDTO disciplinaDTO : disciplinasDTOBD) {
            assertNotNull(disciplinaDTO.getIdDisciplina());
            assertNotNull(disciplinaDTO.getNome());
        }
    }

    @Test
    void testAtualizar() throws SQLException {
        disciplinaDTO = disciplinasDTO.get(0);
        int idDisciplina = disciplinaDTO.getIdDisciplina(); 
        String nome = disciplinaDTO.getNome();

        disciplinaDTO.setNome("EPS");
        disciplinaDAO.atualizar(disciplinaDTO);

        for (DisciplinaDTO disciplinaDTO : disciplinasDTO) {
            if (disciplinaDTO.getIdDisciplina() == idDisciplina)
                assertFalse(disciplinaDTO.getNome().equals(nome));
        }
    }

    @Test
    void testDeletar() throws SQLException {
        for (DisciplinaDTO disciplinaDTO : disciplinasDTO) {
            disciplinaDAO.deletar(disciplinaDTO.getIdDisciplina());
        }

        List<DisciplinaDTO> disciplinasDTOBD = disciplinaDAO.listar();
        for (DisciplinaDTO disciplinaDTO : disciplinasDTO) {
            for (DisciplinaDTO disciplinaDTOBD : disciplinasDTOBD) {
                assertFalse(disciplinaDTOBD.getIdDisciplina() == disciplinaDTO.getIdDisciplina());
            }
        }
    }

}
