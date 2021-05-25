package dto;

import model.EstudanteCursaDisciplina;

public class EstudanteCursaDisciplinaDTO {
    private EstudanteDTO estudanteDTO;
    private DisciplinaDTO disciplinaDTO;

    public EstudanteCursaDisciplinaDTO(EstudanteDTO estudanteDTO, DisciplinaDTO disciplinaDTO) {
        this.estudanteDTO = estudanteDTO;
        this.disciplinaDTO = disciplinaDTO;
    }
    
    public EstudanteCursaDisciplinaDTO(EstudanteCursaDisciplina estudanteCursaDisciplina) {
        this.estudanteDTO = new EstudanteDTO(estudanteCursaDisciplina.getEstudante());
        this.disciplinaDTO = new DisciplinaDTO(estudanteCursaDisciplina.getDisciplina());
    }
    

    public EstudanteDTO getEstudante() {
        return estudanteDTO;
    }

    public void setEstudanteDTO(EstudanteDTO estudanteDTO) {
        this.estudanteDTO = estudanteDTO;
    }

    public DisciplinaDTO getDisciplina() {
        return disciplinaDTO;
    }

    public void setDisciplinaDTO(DisciplinaDTO disciplinaDTO) {
        this.disciplinaDTO = disciplinaDTO;
    }
}
