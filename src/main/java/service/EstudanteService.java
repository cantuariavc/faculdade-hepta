package service;

import java.sql.SQLException;
import java.util.List;

import dao.EstudanteDAO;
import dto.EstudanteDTO;

public class EstudanteService {

    private EstudanteDAO estudanteDAO;

    public EstudanteService() {
        estudanteDAO = new EstudanteDAO();
    }

    public EstudanteDTO salvar(EstudanteDTO estudanteDTO) throws SQLException, ClassNotFoundException {
        return estudanteDAO.salvar(estudanteDTO);
    }

    public List<EstudanteDTO> listar() throws SQLException, ClassNotFoundException {
        return estudanteDAO.listar();
    }

    public void atualizar(EstudanteDTO estudanteDTO) throws SQLException, ClassNotFoundException {
        estudanteDAO.atualizar(estudanteDTO);
    }

    public void deletar(int idEstudante) throws SQLException, ClassNotFoundException {
        estudanteDAO.deletar(idEstudante);
    }

}
