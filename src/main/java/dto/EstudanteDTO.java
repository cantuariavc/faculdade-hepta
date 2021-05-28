package dto;

import model.Estudante;

public class EstudanteDTO {
    private int idEstudante;
    private String nomeCompleto;

    public EstudanteDTO() {
    }

    public EstudanteDTO(int idEstudante, String nomeCompleto) {
        this.idEstudante = idEstudante;
        this.nomeCompleto = nomeCompleto;
    }

    public EstudanteDTO(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public EstudanteDTO(Estudante estudante) {
        this.idEstudante = estudante.getIdEstudante();
        this.nomeCompleto = estudante.getNomeCompleto();
    }

    public int getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(int idEstudante) {
        this.idEstudante = idEstudante;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    @Override
    public String toString() {
        return "EstudanteDTO [idEstudante=" + idEstudante + ", nomeCompleto=" + nomeCompleto + "]";
    }

}
