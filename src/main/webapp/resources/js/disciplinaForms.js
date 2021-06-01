window.onload = () => getProfessores();

function getProfessores() {
	fetch("http://localhost:8080/Faculdade-Hepta/rest/professores/")
		.then(res => res.json())
		.then(data => {
			data.forEach(professor => {
				var option = document.createElement('option');
				option.setAttribute('value', professor.idProfessor);
				option.appendChild(document.createTextNode(professor.nomeCompleto));
				document.getElementById('professores').appendChild(option);
			});
		});
}


document.getElementById('disciplinaForm').addEventListener('submit', event => {
	event.preventDefault();

	var disciplina = {
		professorDTO: {}
	};
	var arquivo = null;

	var nomeDisciplina = document.getElementById('nomeDisciplina').value;
	if (nomeDisciplina) disciplina.nome = nomeDisciplina
	else return

	var planoEnsinoArquivo = document.getElementById('planoEnsinoArquivo').files;
	if (planoEnsinoArquivo.length !== 0) {
		arquivo = planoEnsinoArquivo[0];
		disciplina.planoEnsinoNome = arquivo.name.split('.')[0];
	} else {
		arquivo = null;
		disciplina.planoEnsinoNome = null;
	}

	disciplina.professorDTO.idProfessor = document.getElementById('professores').value;


	const formData = new FormData();
	formData.append("disciplina", JSON.stringify(disciplina));
	formData.append("arquivo", arquivo);

	fetch("http://localhost:8080/Faculdade-Hepta/rest/disciplinas/", {
		method: "POST",
		body: formData
	})
		.then((res) => {
			if (res.status === 200) window.location.href = "http://localhost:8080/Faculdade-Hepta/index.html";
		})
		.catch((err) => {
			window.location.href = "http://localhost:8080/Faculdade-Hepta/resources/pages/disciplinaForms.html";
			console.log(err);
		});
});

