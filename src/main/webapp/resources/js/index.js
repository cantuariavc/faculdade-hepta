window.onload = () => {
	fetch("http://localhost:8080/Faculdade-Hepta/rest/disciplinas/")
		.then(res => res.json())
		.then(data => {
			var tableBody = document.getElementById('tableBody');
			data.forEach(disciplina => {
				var tr = document.createElement('tr');

				tr.appendChild(createIdCol(disciplina));
				tr.appendChild(createNomeCol(disciplina));
				tr.appendChild(createPlanoEnsinoCol(disciplina));
				tr.appendChild(createProfessorCol(disciplina));
				tr.appendChild(createEditarCol());
				tr.appendChild(createExcluirCol());

				tableBody.appendChild(tr);
			});
		});
}

function createIdCol(disciplina) {
	var id = document.createElement('td');
	id.appendChild(document.createTextNode(disciplina.idDisciplina));

	return id;
}

function createNomeCol(disciplina) {
	var nome = document.createElement('td');
	nome.appendChild(document.createTextNode(disciplina.nome));

	return nome;
}

function createPlanoEnsinoCol(disciplina) {
	var planoEnsino = document.createElement('td');
	var planoEnsinoNome = document.createElement('a');
	if (disciplina.planoEnsinoNome) {
		var nomeArquivo = `${disciplina.planoEnsinoNome}.pdf`;
		planoEnsinoNome.appendChild(document.createTextNode(nomeArquivo));
		planoEnsinoNome.setAttribute('href', `data:application/pdf;base64,${disciplina.planoEnsinoArquivo}`);
		planoEnsinoNome.setAttribute('download', nomeArquivo);
	} else {
		planoEnsinoNome.appendChild(document.createTextNode(''));
	}
	planoEnsino.appendChild(planoEnsinoNome);

	return planoEnsino;
}

function createProfessorCol(disciplina) {
	var professor = document.createElement('td');
	professor.appendChild(document.createTextNode(disciplina.professorDTO.nomeCompleto));

	return professor;
}

function createEditarCol() {
	var editarTD = document.createElement('td');
	var editar = document.createElement('span');
	editar.appendChild(document.createTextNode('editar'));
	editarTD.appendChild(editar);

	return editarTD;
}

function createExcluirCol() {
	var excluirTD = document.createElement('td');
	var excluir = document.createElement('span');
	excluir.appendChild(document.createTextNode('excluir'));
	excluirTD.appendChild(excluir);

	return excluirTD;
}

