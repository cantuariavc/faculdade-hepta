window.onload = () => {
	fetch("http://localhost:8080/Faculdade-Hepta/rest/disciplinas/")
		.then(res => res.json())
		.then(data => {
			var tableBody = document.getElementById('tableBody');
			data.forEach(disciplina => {
				var tr = document.createElement('tr');

				tr.appendChild(createIdCol(disciplina.idDisciplina));
				tr.appendChild(createNomeCol(disciplina.idDisciplina, disciplina.nome));
				tr.appendChild(createPlanoEnsinoCol(disciplina.planoEnsinoNome, disciplina.planoEnsinoArquivo));
				tr.appendChild(createProfessorCol(disciplina.professorDTO.idProfessor, disciplina.professorDTO.nomeCompleto));
				tr.appendChild(createExcluirCol(disciplina.idDisciplina));

				tableBody.appendChild(tr);
			});
		});
}

function createIdCol(id) {
	var idTD = document.createElement('td');
	idTD.appendChild(document.createTextNode(id));

	return idTD;
}

function createNomeCol(idDisciplina, nome) {
	var nomeTD = document.createElement('td');
	var nomeA = document.createElement('a');
	nomeA.appendChild(document.createTextNode(nome));
	nomeA.setAttribute('href', `http://localhost:8080/Faculdade-Hepta/resources/pages/disciplinaForms.html?id=${idDisciplina}`);	
	nomeTD.appendChild(nomeA);

	return nomeTD;
}

function createPlanoEnsinoCol(nomePlanoEnsino, arquivoPlanoEnsino) {
	var planoEnsinoTD = document.createElement('td');
	var planoEnsinoNomeA = document.createElement('a');
	if (nomePlanoEnsino) {
		var nomeArquivo = `${nomePlanoEnsino}.pdf`;
		planoEnsinoNomeA.appendChild(document.createTextNode(nomeArquivo));
		planoEnsinoNomeA.setAttribute('href', `data:application/pdf;base64,${arquivoPlanoEnsino}`);
		planoEnsinoNomeA.setAttribute('download', nomeArquivo);
	} else {
		planoEnsinoNomeA.appendChild(document.createTextNode(''));
	}
	planoEnsinoTD.appendChild(planoEnsinoNomeA);

	return planoEnsinoTD;
}

function createProfessorCol(idProfessor, nomeCompleto) {
	var professorTD = document.createElement('td');
	var professorA = document.createElement('a');
	professorA.appendChild(document.createTextNode(nomeCompleto));
	professorA.setAttribute('href', `http://localhost:8080/Faculdade-Hepta/resources/pages/professorForms.html?id=${idProfessor}`);
	professorTD.appendChild(professorA);

	return professorTD;
}

function createExcluirCol(id) {
	var excluirTD = document.createElement('td');
	var excluirA = document.createElement('a');
	excluirA.appendChild(document.createTextNode('excluir'));
	excluirTD.appendChild(excluirA);

	return excluirTD;
}

