package model;

import dto.EstudanteCursaDisciplinaDTO;

public class EstudanteCursaDisciplina {
    private Estudante estudante;
    private Disciplina disciplina;

    public EstudanteCursaDisciplina(Estudante estudante, Disciplina disciplina) {
        this.estudante = estudante;
        this.disciplina = disciplina;
    }
    
    public EstudanteCursaDisciplina(EstudanteCursaDisciplinaDTO estudanteCursaDisciplinaDTO) {
        this.estudante = new Estudante(estudanteCursaDisciplinaDTO.getEstudante());
        this.disciplina = new Disciplina(estudanteCursaDisciplinaDTO.getDisciplina());
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
