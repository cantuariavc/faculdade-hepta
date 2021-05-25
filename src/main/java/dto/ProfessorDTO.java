package dto;

import model.Professor;

public class ProfessorDTO {
    private int idProfessor;
    private String nomeCompleto;

    public ProfessorDTO(int idProfessor, String nomeCompleto) {
        this.idProfessor = idProfessor;
        this.nomeCompleto = nomeCompleto;
    }
    
    public ProfessorDTO(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public ProfessorDTO(Professor professor) {
        this.idProfessor = professor.getIdProfessor();
        this.nomeCompleto = professor.getNomeCompleto();
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
        return "ProfessorDTO [idProfessor=" + idProfessor + ", nomeCompleto=" + nomeCompleto + "]";
    }
}
