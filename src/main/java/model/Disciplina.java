package model;

import java.util.Arrays;

import dto.DisciplinaDTO;

public class Disciplina {
    private int idDisciplina;
    private String nome;
    private byte[] planoEnsinoArquivo;
    private Professor professor;

    public Disciplina(int idDisciplina, String nome, byte[] planoEnsinoArquivo, Professor professor) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.planoEnsinoArquivo = planoEnsinoArquivo;
        this.professor = professor;
    }

    public Disciplina(DisciplinaDTO disciplinaDTO) {
        this.idDisciplina = disciplinaDTO.getIdDisciplina();
        this.nome = disciplinaDTO.getNome();
        this.planoEnsinoArquivo = disciplinaDTO.getPlanoEnsinoArquivo();
        this.professor = new Professor(disciplinaDTO.getProfessorDTO());
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Disciplina [idDisciplina=" + idDisciplina + ", nome=" + nome + ", planoEnsinoArquivo=" + Arrays.toString(planoEnsinoArquivo) + ", professor="
                + professor + "]";
    }

}
