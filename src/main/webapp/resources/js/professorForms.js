const ID = getUrlParam('id');

window.onload = () => {
	if (ID)
		getProfessor();
	//else
	//	document.getElementById('deletarProfessor').style.visibility = 'hidden';
}

function getUrlParam(name) {
	var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return (results && results[1]) || undefined;
}

function getProfessor() {
	fetch(`http://localhost:8080/Faculdade-Hepta/rest/professores/${ID}`)
		.then(res => res.json())
		.then(professor => document.getElementById('nomeCompleto').value = professor.nomeCompleto);
}

document.getElementById('professorForm').addEventListener('submit', event => {
	event.preventDefault();
	var nomeCompleto = document.getElementById('nomeCompleto').value;
	if (!nomeCompleto) return

	var professor = {
		idProfessor: ID,
		nomeCompleto
	};


	fetch("http://localhost:8080/Faculdade-Hepta/rest/professores/", {
		method: ID ? "PUT" : "POST",
		headers: new Headers({ 'Content-Type': 'application/json' }),
		body: JSON.stringify(professor)
	})
		.then((res) => {
			if (res.status === 200) window.location.href = "http://localhost:8080/Faculdade-Hepta/index.html";
		})
		.catch((err) => {
			window.location.href = "http://localhost:8080/Faculdade-Hepta/resources/pages/professorForms.html";
			console.log(err);
		});
});

//function deletarProfessor() {
//	fetch(`http://localhost:8080/Faculdade-Hepta/rest/professores/${ID}`, {
//		method: "DELETE"
//	})
//		.then((res) => {
//			if (res.status === 200) window.location.href = "http://localhost:8080/Faculdade-Hepta/index.html";
//		})
//		.catch((err) => {
//			window.location.href = `http://localhost:8080/Faculdade-Hepta/resources/pages/professorForms.html?id=${ID}`;
//			console.log(err);
//		});
//}