package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

import dao.DisciplinaDAO;
import dto.DisciplinaDTO;

public class DisciplinaService {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaService() {
        disciplinaDAO = new DisciplinaDAO();
    }

    public DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO, File arquivo) throws ClassNotFoundException, SQLException, IOException {
        disciplinaDTO.setPlanoEnsinoArquivo(Files.readAllBytes(arquivo.toPath()));
        
        return disciplinaDAO.salvar(disciplinaDTO);
    }

    public List<DisciplinaDTO> listar() throws ClassNotFoundException, SQLException {
        return disciplinaDAO.listar();
    }

    public void atualizar(DisciplinaDTO disciplinaDTO, File arquivo) throws ClassNotFoundException, SQLException, IOException {
        disciplinaDTO.setPlanoEnsinoArquivo(Files.readAllBytes(arquivo.toPath()));

        disciplinaDAO.atualizar(disciplinaDTO);
    }

    public void deletar(int idDisciplina) throws ClassNotFoundException, SQLException {
        disciplinaDAO.deletar(idDisciplina);
    }
    
    public DisciplinaDTO detalhar(int idDisciplina) throws ClassNotFoundException, SQLException {
        return disciplinaDAO.detalhar(idDisciplina);
    }
    
}
