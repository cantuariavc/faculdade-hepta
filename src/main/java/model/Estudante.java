package model;

import dto.EstudanteDTO;

public class Estudante {
    private int idEstudante;
    private String nomeCompleto;

    public Estudante(int idEstudante, String nomeCompleto) {
        this.idEstudante = idEstudante;
        this.nomeCompleto = nomeCompleto;
    }

    public Estudante(EstudanteDTO estudanteDTO) {
        this.idEstudante = estudanteDTO.getIdEstudante();
        this.nomeCompleto = estudanteDTO.getNomeCompleto();
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

}
