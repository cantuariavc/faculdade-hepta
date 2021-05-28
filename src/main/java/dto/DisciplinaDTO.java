package dto;

import java.util.Arrays;

import model.Disciplina;

public class DisciplinaDTO {
    private int idDisciplina;
    private String nome;
    private byte[] planoEnsinoArquivo;
    private ProfessorDTO professorDTO;
    
    public DisciplinaDTO() {
    }

    public DisciplinaDTO(int idDisciplina, String nome, byte[] planoEnsinoArquivo, ProfessorDTO professorDTO) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.planoEnsinoArquivo = planoEnsinoArquivo;
        this.professorDTO = professorDTO;
    }

    public DisciplinaDTO(String nome, ProfessorDTO professorDTO) {
        this.nome = nome;
        this.professorDTO = professorDTO;
    }
    
    public DisciplinaDTO(String nome, byte[] planoEnsinoArquivo, ProfessorDTO professorDTO) {
        this.nome = nome;
        this.planoEnsinoArquivo = planoEnsinoArquivo;
        this.professorDTO = professorDTO;
    }

    public DisciplinaDTO(Disciplina disciplina) {
        this.idDisciplina = disciplina.getIdDisciplina();
        this.nome = disciplina.getNome();
        this.planoEnsinoArquivo = disciplina.getPlanoEnsinoArquivo();
        this.professorDTO = new ProfessorDTO(disciplina.getProfessor());
    }


    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getPlanoEnsinoArquivo() {
        return planoEnsinoArquivo;
    }

    public void setPlanoEnsinoArquivo(byte[] planoEnsinoArquivo) {
        this.planoEnsinoArquivo = planoEnsinoArquivo;
    }

    public ProfessorDTO getProfessorDTO() {
        return professorDTO;
    }

    public void setProfessorDTO(ProfessorDTO professorDTO) {
        this.professorDTO = professorDTO;
    }

    @Override
    public String toString() {
        return "DisciplinaDTO [idDisciplina=" + idDisciplina + ", nome=" + nome + ", planoEnsinoArquivo=" + Arrays.toString(planoEnsinoArquivo)
                + ", professorDTO=" + professorDTO + "]";
    }
}
