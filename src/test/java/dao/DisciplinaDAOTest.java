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

    private static List<DisciplinaDTO> listaDisciplinaDTO = new ArrayList<DisciplinaDTO>();
    private static List<ProfessorDTO> listaProfessorDTO = new ArrayList<ProfessorDTO>();
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static DisciplinaDTO disciplinaDTO;
    private static ProfessorDAO professorDAO = new ProfessorDAO();

    @BeforeAll
    static void setUpBeforeClass() {
        try {
            ProfessorDTO professor1 = professorDAO.salvar(new ProfessorDTO("Albert Einstein"));
            listaProfessorDTO.add(professor1);
            ProfessorDTO professor2 = professorDAO.salvar(new ProfessorDTO("Isaac Newton"));
            listaProfessorDTO.add(professor2);

            listaDisciplinaDTO.add(new DisciplinaDTO("EDA2", null, null, null, professor1));
            listaDisciplinaDTO.add(new DisciplinaDTO("SBD2", null, null, null, professor2));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterAll
    static void tearDownAfterClass() {
        try {
            for (DisciplinaDTO disciplinaDTO : listaDisciplinaDTO) {
                disciplinaDAO.deletar(disciplinaDTO.getIdDisciplina());
                professorDAO.deletar(disciplinaDTO.getProfessorDTO().getIdProfessor());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testSalvar() {
        for (DisciplinaDTO dDTO : listaDisciplinaDTO) {
            disciplinaDTO = disciplinaDAO.salvar(dDTO);
            assertNotNull(disciplinaDTO.getIdDisciplina());
            dDTO.setIdDisciplina(disciplinaDTO.getIdDisciplina());
        }
    }

    @Test
    void testListar() {
        List<DisciplinaDTO> disciplinasDTOBD = disciplinaDAO.listar();

        for (DisciplinaDTO disciplinaDTO : disciplinasDTOBD) {
            assertNotNull(disciplinaDTO.getIdDisciplina());
            assertNotNull(disciplinaDTO.getNome());
        }
    }

    @Test
    void testAtualizar() {
        disciplinaDTO = listaDisciplinaDTO.get(0);
        int idDisciplina = disciplinaDTO.getIdDisciplina();
        String nome = disciplinaDTO.getNome();

        disciplinaDTO.setNome("EPS");
        disciplinaDAO.atualizar(disciplinaDTO);

        for (DisciplinaDTO disciplinaDTO : listaDisciplinaDTO)
            if (disciplinaDTO.getIdDisciplina() == idDisciplina)
                assertFalse(disciplinaDTO.getNome().equals(nome));
    }

    @Test
    void testDeletar() {
        for (DisciplinaDTO disciplinaDTO : listaDisciplinaDTO)
            disciplinaDAO.deletar(disciplinaDTO.getIdDisciplina());

        List<DisciplinaDTO> disciplinasDTOBD = disciplinaDAO.listar();
        for (DisciplinaDTO disciplinaDTO : listaDisciplinaDTO)
            for (DisciplinaDTO disciplinaDTOBD : disciplinasDTOBD)
                assertFalse(disciplinaDTOBD.getIdDisciplina() == disciplinaDTO.getIdDisciplina());
    }

}
