package service;

import java.util.List;

import dao.EstudanteDAO;
import dto.EstudanteDTO;

public class EstudanteService {

    private EstudanteDAO estudanteDAO;

    public EstudanteService() {
        estudanteDAO = new EstudanteDAO();
    }

    public EstudanteDTO salvar(EstudanteDTO estudanteDTO) {
        return estudanteDAO.salvar(estudanteDTO);
    }

    public List<EstudanteDTO> listar() {
        return estudanteDAO.listar();
    }

    public void atualizar(EstudanteDTO estudanteDTO) {
        estudanteDAO.atualizar(estudanteDTO);
    }

    public void deletar(int idEstudante) {
        estudanteDAO.deletar(idEstudante);
    }

}
