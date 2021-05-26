package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dto.EstudanteDTO;

class EstudanteDAOTest {

    private static List<EstudanteDTO> estudantesDTO = new ArrayList<EstudanteDTO>();
    private static EstudanteDAO estudanteDAO;
    private static EstudanteDTO estudanteDTO;

    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        estudantesDTO.add(new EstudanteDTO("Elvis Presley"));
        estudantesDTO.add(new EstudanteDTO("Frank Sinatra"));
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        for (EstudanteDTO estudanteDTO : estudantesDTO) {
            estudanteDAO.deletar(estudanteDTO.getIdEstudante());
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        estudanteDAO = new EstudanteDAO();
    }

    
    @Test
    void testSalvar() throws SQLException {   
        for (EstudanteDTO eDTO : estudantesDTO) {
            estudanteDTO = estudanteDAO.salvar(eDTO);
            assertNotNull(estudanteDTO.getIdEstudante());
            eDTO.setIdEstudante(estudanteDTO.getIdEstudante());
        }
    }

    @Test
    void testListar() throws SQLException {
        List<EstudanteDTO> estudantesDTOBD = estudanteDAO.listar();

        for (EstudanteDTO estudanteDTO : estudantesDTOBD) {
            assertNotNull(estudanteDTO.getIdEstudante());
            assertNotNull(estudanteDTO.getNomeCompleto());
        }
    }

    @Test
    void testAtualizar() throws SQLException {
        estudanteDTO = estudantesDTO.get(0);
        int idEstudante = estudanteDTO.getIdEstudante(); 
        String nomeCompleto = estudanteDTO.getNomeCompleto();

        estudanteDTO.setNomeCompleto("Roberto Carlos");
        estudanteDAO.atualizar(estudanteDTO);

        for (EstudanteDTO estudanteDTO : estudantesDTO) {
            if (estudanteDTO.getIdEstudante() == idEstudante)
                assertFalse(estudanteDTO.getNomeCompleto().equals(nomeCompleto));
        }
    }

    @Test
    void testDeletar() throws SQLException {
        for (EstudanteDTO estudanteDTO : estudantesDTO) {
            estudanteDAO.deletar(estudanteDTO.getIdEstudante());
        }

        List<EstudanteDTO> estudantesDTOBD = estudanteDAO.listar();
        for (EstudanteDTO estudanteDTO : estudantesDTO) {
            for (EstudanteDTO estudanteDTOBD : estudantesDTOBD) {
                assertFalse(estudanteDTOBD.getIdEstudante() == estudanteDTO.getIdEstudante());
            }
        }
    }

}
