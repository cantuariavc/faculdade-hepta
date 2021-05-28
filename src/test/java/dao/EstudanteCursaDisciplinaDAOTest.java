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

    private static List<EstudanteDTO> listaEstudanteDTO = new ArrayList<EstudanteDTO>();
    private static List<DisciplinaDTO> listaDisciplinaDTO = new ArrayList<DisciplinaDTO>();
    private static List<EstudanteCursaDisciplinaDTO> listaCursaDTO = new ArrayList<EstudanteCursaDisciplinaDTO>();
    private static EstudanteDAO estudanteDAO = new EstudanteDAO();
    private static ProfessorDAO professorDAO = new ProfessorDAO();
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static EstudanteCursaDisciplinaDAO cursaDAO = new EstudanteCursaDisciplinaDAO();

    @BeforeAll
    static void setUpBeforeClass() {
        try {
            // TODO: verificar motivo para estudantes não estarem sendo salvos na lista.
            listaEstudanteDTO.add(estudanteDAO.salvar(new EstudanteDTO("Elvis Presley")));
            listaEstudanteDTO.add(estudanteDAO.salvar(new EstudanteDTO("Frank Sinatra")));

            ProfessorDTO professor1 = professorDAO.salvar(new ProfessorDTO("Albert Einstein"));
            ProfessorDTO professor2 = professorDAO.salvar(new ProfessorDTO("Isaac Newton"));

            listaDisciplinaDTO.add(disciplinaDAO.salvar(new DisciplinaDTO("EDA2", null, null, null, professor1)));
            listaDisciplinaDTO.add(disciplinaDAO.salvar(new DisciplinaDTO("SBD2", null, null, null, professor2)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterAll
    static void tearDownAfterClass() {
        try {
            for (EstudanteCursaDisciplinaDTO cursaDTO : listaCursaDTO)
                cursaDAO.deletar(cursaDTO);

            for (EstudanteDTO estudanteDTO : listaEstudanteDTO)
                estudanteDAO.deletar(estudanteDTO.getIdEstudante());

            for (DisciplinaDTO disciplinaDTO : listaDisciplinaDTO) {
                disciplinaDAO.deletar(disciplinaDTO.getIdDisciplina());
                professorDAO.deletar(disciplinaDTO.getProfessorDTO().getIdProfessor());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testSalvaEstudanteCursaDisciplina() {
        for (DisciplinaDTO disciplinaDTO : listaDisciplinaDTO) {
            for (EstudanteDTO estudanteDTO : listaEstudanteDTO) {
                EstudanteCursaDisciplinaDTO cursaDTO = new EstudanteCursaDisciplinaDTO(estudanteDTO, disciplinaDTO);
                assertNotNull(cursaDTO);
                cursaDAO.salvaEstudanteCursaDisciplina(cursaDTO);
                listaCursaDTO.add(cursaDTO);
            }
        }
    }

    @Test
    void testListarEstudantes() {
        for (DisciplinaDTO disciplinaDTO : listaDisciplinaDTO) {
            listaEstudanteDTO = cursaDAO.listarEstudantes(disciplinaDTO.getIdDisciplina());
            for (EstudanteDTO estudanteDTO : listaEstudanteDTO) {
                System.out.println(estudanteDTO.toString());
                assertNotNull(estudanteDTO.getIdEstudante());
            }
        }
    }

    @Test
    void testListarDisciplinas() {
        List<DisciplinaDTO> disciplinasDTO;
        for (EstudanteDTO estudanteDTO : listaEstudanteDTO) {
            disciplinasDTO = cursaDAO.listarDisciplinas(estudanteDTO.getIdEstudante());
            for (DisciplinaDTO disciplinaDTO : disciplinasDTO)
                assertNotNull(disciplinaDTO.getIdDisciplina());
        }
    }

    @Test
    void testDeletar() {
        for (EstudanteCursaDisciplinaDTO cursaDTO : listaCursaDTO)
            cursaDAO.deletar(cursaDTO);
    }

}
