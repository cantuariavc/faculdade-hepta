package dto;

import model.Disciplina;

public class DisciplinaDTO {
    private int idDisciplina;
    private String nome;
    private byte[] planoEnsinoArquivo;
    private String planoEnsinoNome;
    private ProfessorDTO professorDTO;

    public DisciplinaDTO() {
    }

    public DisciplinaDTO(int idDisciplina, String nome, byte[] planoEnsinoArquivo, String planoEnsinoNome, ProfessorDTO professorDTO) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.planoEnsinoArquivo = planoEnsinoArquivo;
        this.planoEnsinoNome = planoEnsinoNome;
        this.professorDTO = professorDTO;
    }

    public DisciplinaDTO(String nome, ProfessorDTO professorDTO) {
        this.nome = nome;
        this.professorDTO = professorDTO;
    }

    public DisciplinaDTO(String nome, byte[] planoEnsinoArquivo, String planoEnsinoNome, ProfessorDTO professorDTO) {
        this.nome = nome;
        this.planoEnsinoArquivo = planoEnsinoArquivo;
        this.planoEnsinoNome = planoEnsinoNome;
        this.professorDTO = professorDTO;
    }

    public DisciplinaDTO(Disciplina disciplina) {
        this.idDisciplina = disciplina.getIdDisciplina();
        this.nome = disciplina.getNome();
        this.planoEnsinoArquivo = disciplina.getPlanoEnsinoArquivo();
        this.planoEnsinoNome = disciplina.getPlanoEnsinoNome();
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

    public String getPlanoEnsinoNome() {
        return planoEnsinoNome;
    }

    public void setPlanoEnsinoNome(String planoEnsinoNome) {
        this.planoEnsinoNome = planoEnsinoNome;
    }

    public ProfessorDTO getProfessorDTO() {
        return professorDTO;
    }

    public void setProfessorDTO(ProfessorDTO professorDTO) {
        this.professorDTO = professorDTO;
    }

    @Override
    public String toString() {
        return "DisciplinaDTO [idDisciplina=" + idDisciplina + ", nome=" + nome + ", planoEnsinoNome=" + planoEnsinoNome + ", professorDTO=" + professorDTO
                + "]";
    }
}
