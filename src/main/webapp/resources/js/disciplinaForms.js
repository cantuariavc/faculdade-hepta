const NOME_DISCIPLINA = document.getElementById('nomeDisciplina');
const PLANO_ENSINO_ARQUIVO = document.getElementById('planoEnsinoArquivo');
const PROFESSORES = document.getElementById('professores');

const ID = getUrlParam('id');

window.onload = () => {
	getProfessores();

	if (ID)
		getDisciplina();

}

function getUrlParam(name) {
	var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return (results && results[1]) || undefined;
}

function getDisciplina() {
	fetch(`http://localhost:8080/Faculdade-Hepta/rest/disciplinas/${ID}`)
		.then(res => res.json())
		.then(disciplina => {
			NOME_DISCIPLINA.value = disciplina.nome;
			PROFESSORES.value = disciplina.professorDTO.idProfessor;

			var list = new DataTransfer();
			list.items.add(dataURLtoFile(`data:application/pdf;base64,${disciplina.planoEnsinoArquivo}`, `${disciplina.planoEnsinoNome}.pdf`));
			PLANO_ENSINO_ARQUIVO.files = list.files;
		});
}

function dataURLtoFile(dataurl, filename) {
	var arr = dataurl.split(',');
	var mime = arr[0].match(/:(.*?);/)[1];
	var bstr = atob(arr[1]);
	var n = bstr.length;
	var u8arr = new Uint8Array(n);

	while (n--)
		u8arr[n] = bstr.charCodeAt(n);

	return new File([u8arr], filename, { type: mime });
}


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


	if (NOME_DISCIPLINA.value)
		disciplina.nome = NOME_DISCIPLINA.value;
	else
		return;

	if (ID)
		disciplina.idDisciplina = ID;

	if (PLANO_ENSINO_ARQUIVO.files.length !== 0) {
		arquivo = PLANO_ENSINO_ARQUIVO.files[0];
		disciplina.planoEnsinoNome = arquivo.name.split('.')[0];
	} else {
		arquivo = null;
		disciplina.planoEnsinoNome = null;
	}

	disciplina.professorDTO.idProfessor = PROFESSORES.value;


	const formData = new FormData();
	formData.append("disciplina", JSON.stringify(disciplina));
	formData.append("arquivo", arquivo);


	if (ID)
		var method = "PUT";
	else
		var method = "POST"

	fetch("http://localhost:8080/Faculdade-Hepta/rest/disciplinas/", {
		method,
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

