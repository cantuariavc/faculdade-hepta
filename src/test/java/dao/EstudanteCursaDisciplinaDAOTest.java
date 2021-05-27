package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dto.DisciplinaDTO;
import dto.EstudanteCursaDisciplinaDTO;
import dto.EstudanteDTO;
import dto.ProfessorDTO;

class EstudanteCursaDisciplinaDAOTest {

    private static List<EstudanteDTO> estudantesDTO = new ArrayList<EstudanteDTO>();
    private static List<DisciplinaDTO> disciplinasDTO = new ArrayList<DisciplinaDTO>();
    private static List<EstudanteCursaDisciplinaDTO> listaCursaDTO = new ArrayList<EstudanteCursaDisciplinaDTO>();
    private static EstudanteDAO estudanteDAO = new EstudanteDAO();
    private static ProfessorDAO professorDAO = new ProfessorDAO();
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static EstudanteCursaDisciplinaDAO cursaDAO = new EstudanteCursaDisciplinaDAO();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        estudantesDTO.add(estudanteDAO.salvar(new EstudanteDTO("Elvis Presley")));
        estudantesDTO.add(estudanteDAO.salvar(new EstudanteDTO("Frank Sinatra")));

        ProfessorDTO professor1 = professorDAO.salvar(new ProfessorDTO("Albert Einstein"));
        ProfessorDTO professor2 = professorDAO.salvar(new ProfessorDTO("Isaac Newton"));

        disciplinasDTO.add(new DisciplinaDTO("EDA2", null, null, null, professor1));
        disciplinasDTO.add(new DisciplinaDTO("SBD2", null, null, null, professor2));
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        for (EstudanteDTO estudanteDTO : estudantesDTO)
            estudanteDAO.deletar(estudanteDTO.getIdEstudante());
        
        for (DisciplinaDTO disciplinaDTO : disciplinasDTO) {
            disciplinaDAO.deletar(disciplinaDTO.getIdDisciplina());
            professorDAO.deletar(disciplinaDTO.getProfessorDTO().getIdProfessor());
        }
        
        for (EstudanteCursaDisciplinaDTO cDTO : listaCursaDTO)
            cursaDAO.deletar(cDTO);
    }

    @Test
    void testSalvaEstudanteCursaDisciplina() throws SQLException {
        for (DisciplinaDTO disciplinaDTO : disciplinasDTO) {
            for (EstudanteDTO estudanteDTO : estudantesDTO) {
                EstudanteCursaDisciplinaDTO cursaDTO = new EstudanteCursaDisciplinaDTO(estudanteDTO, disciplinaDTO);
                assertNotNull(cursaDTO);
                cursaDAO.salvaEstudanteCursaDisciplina(cursaDTO);
                listaCursaDTO.add(cursaDTO);
            }
        }
    }

    @Test
    void testListarEstudantes() throws SQLException {
        List<EstudanteDTO> estudantesDTO;
        for (DisciplinaDTO disciplinaDTO : disciplinasDTO) {
            estudantesDTO = cursaDAO.listarEstudantes(disciplinaDTO.getIdDisciplina());
            for (EstudanteDTO estudanteDTO : estudantesDTO) {
                assertNotNull(estudanteDTO.getIdEstudante());
            }
        }
    }

    @Test
    void testListarDisciplinas() throws SQLException {
        List<DisciplinaDTO> disciplinasDTO;
        for (EstudanteDTO estudanteDTO : estudantesDTO) {
            disciplinasDTO = cursaDAO.listarDisciplinas(estudanteDTO.getIdEstudante());
            for (DisciplinaDTO disciplinaDTO : disciplinasDTO) {
                assertNotNull(disciplinaDTO.getIdDisciplina());
            }
        }
    }

    @Test
    void testDeletar() throws SQLException {
        for (EstudanteCursaDisciplinaDTO cursaDTO : listaCursaDTO) {
            cursaDAO.deletar(cursaDTO);
        }
    }

}
