package model;

import java.util.Arrays;

import dto.DisciplinaDTO;

public class Disciplina {
    private int idDisciplina;
    private String nome;
    private byte[] ementaArquivo;
    private String ementaNomeArquivo;
    private String ementaTipoArquivo;
    private Professor professor;

    public Disciplina(int idDisciplina, String nome, byte[] ementaArquivo, String ementaNomeArquivo,
            String ementaTipoArquivo, Professor professor) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.ementaArquivo = ementaArquivo;
        this.ementaNomeArquivo = ementaNomeArquivo;
        this.ementaTipoArquivo = ementaTipoArquivo;
        this.professor = professor;
    }

    public Disciplina(DisciplinaDTO disciplinaDTO) {
        this.idDisciplina = disciplinaDTO.getIdDisciplina();
        this.nome = disciplinaDTO.getNome();
        this.ementaArquivo = disciplinaDTO.getEmentaArquivo();
        this.ementaNomeArquivo = disciplinaDTO.getEmentaNomeArquivo();
        this.ementaTipoArquivo = disciplinaDTO.getEmentaTipoArquivo();
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

    public byte[] getEmentaArquivo() {
        return ementaArquivo;
    }

    public void setEmentaArquivo(byte[] ementaArquivo) {
        this.ementaArquivo = ementaArquivo;
    }

    public String getEmentaNomeArquivo() {
        return ementaNomeArquivo;
    }

    public void setEmentaNomeArquivo(String ementaNomeArquivo) {
        this.ementaNomeArquivo = ementaNomeArquivo;
    }

    public String getEmentaTipoArquivo() {
        return ementaTipoArquivo;
    }

    public void setEmentaTipoArquivo(String ementaTipoArquivo) {
        this.ementaTipoArquivo = ementaTipoArquivo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Disciplina [idDisciplina=" + idDisciplina + ", nome=" + nome + ", ementaArquivo="
                + Arrays.toString(ementaArquivo) + ", ementaNomeArquivo=" + ementaNomeArquivo + ", ementaTipoArquivo="
                + ementaTipoArquivo + ", Professor=" + professor.getNomeCompleto() + "]";
    }
}
