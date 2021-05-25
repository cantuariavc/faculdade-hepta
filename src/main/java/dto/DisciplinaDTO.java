package dto;

import model.Disciplina;

public class DisciplinaDTO {
    private int idDisciplina;
    private String nome;
    private byte[] ementaArquivo;
    private String ementaNomeArquivo;
    private String ementaTipoArquivo;
    private ProfessorDTO professorDTO;

    public DisciplinaDTO(int idDisciplina, String nome, byte[] ementaArquivo, String ementaNomeArquivo, 
            String ementaTipoArquivo, ProfessorDTO professorDTO) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.ementaArquivo = ementaArquivo;
        this.ementaNomeArquivo = ementaNomeArquivo;
        this.ementaTipoArquivo = ementaTipoArquivo;
        this.professorDTO = professorDTO;
    }

    public DisciplinaDTO(String nome, byte[] ementaArquivo, String ementaNomeArquivo, 
            String ementaTipoArquivo, ProfessorDTO professorDTO) {
        this.nome = nome;
        this.ementaArquivo = ementaArquivo;
        this.ementaNomeArquivo = ementaNomeArquivo;
        this.ementaTipoArquivo = ementaTipoArquivo;
        this.professorDTO = professorDTO;
    }

    public DisciplinaDTO(Disciplina disciplina) {
        this.idDisciplina = disciplina.getIdDisciplina();
        this.nome = disciplina.getNome();
        this.ementaArquivo = disciplina.getEmentaArquivo();
        this.ementaNomeArquivo = disciplina.getEmentaNomeArquivo();
        this.ementaTipoArquivo = disciplina.getEmentaTipoArquivo();
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

    public ProfessorDTO getProfessorDTO() {
        return professorDTO;
    }

    public void setProfessorDTO(ProfessorDTO professorDTO) {
        this.professorDTO = professorDTO;
    }

    @Override
    public String toString() {
        return "DisciplinaDTO [idDisciplina=" + idDisciplina + ", nome=" + nome + ", ementaArquivo=" + ementaArquivo 
                + ", ementaNomeArquivo=" + ementaNomeArquivo + ", ementaTipoArquivo=" + ementaTipoArquivo
                + ", ProfessorDTO=" + professorDTO.getNomeCompleto() + "]";
    }

}
