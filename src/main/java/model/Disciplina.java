package model;

import dto.DisciplinaDTO;

public class Disciplina {
    private int idDisciplina;
    private String nome;
    private byte[] planoEnsinoArquivo;
    private String planoEnsinoNome;
    private Professor professor;

    public Disciplina(int idDisciplina, String nome, byte[] planoEnsinoArquivo, String planoEnsinoNome, Professor professor) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.planoEnsinoArquivo = planoEnsinoArquivo;
        this.planoEnsinoNome = planoEnsinoNome;
        this.professor = professor;
    }

    public Disciplina(DisciplinaDTO disciplinaDTO) {
        this.idDisciplina = disciplinaDTO.getIdDisciplina();
        this.nome = disciplinaDTO.getNome();
        this.planoEnsinoArquivo = disciplinaDTO.getPlanoEnsinoArquivo();
        this.planoEnsinoNome = disciplinaDTO.getPlanoEnsinoNome();
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

    public String getPlanoEnsinoNome() {
        return planoEnsinoNome;
    }

    public void setPlanoEnsinoNome(String planoEnsinoNome) {
        this.planoEnsinoNome = planoEnsinoNome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Disciplina [idDisciplina=" + idDisciplina + ", nome=" + nome + ", planoEnsinoNome=" + planoEnsinoNome + ", professor=" + professor + "]";
    }
}
