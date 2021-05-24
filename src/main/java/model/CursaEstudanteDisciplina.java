package model;

public class CursaEstudanteDisciplina {
	private Estudante estudante;
	private Disciplina disciplina;
	
	public CursaEstudanteDisciplina(Estudante estudante, Disciplina disciplina) {
		this.estudante = estudante;
		this.disciplina = disciplina;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}
