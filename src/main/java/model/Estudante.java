package model;

public class Estudante {
	private int idEstudante;
	private String nomeCompleto;
	
	public Estudante(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public Estudante(int idEstudante, String nomeCompleto) {
		this.idEstudante = idEstudante;
		this.nomeCompleto = nomeCompleto;
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
		return "Estudante [idEstudante=" + idEstudante + ", nomeCompleto=" + nomeCompleto + "]";
	}
}
