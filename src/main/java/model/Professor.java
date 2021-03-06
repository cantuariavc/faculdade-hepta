package model;

import dto.ProfessorDTO;

public class Professor {
    private int idProfessor;
    private String nomeCompleto;
    
    public Professor(int idProfessor, String nomeCompleto) {
        super();
        this.idProfessor = idProfessor;
        this.nomeCompleto = nomeCompleto;
    }

    public Professor(ProfessorDTO professorDTO) {
        this.idProfessor = professorDTO.getIdProfessor();
        this.nomeCompleto = professorDTO.getNomeCompleto();
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    @Override
    public String toString() {
        return "Professor [idProfessor=" + idProfessor + ", nomeCompleto=" + nomeCompleto + "]";
    }
}
