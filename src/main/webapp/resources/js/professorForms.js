document.getElementById('professorForm').addEventListener('submit', event => {
	event.preventDefault();

	var professor = {};

	var nomeCompleto = document.getElementById('nomeCompleto').value;
	if (nomeCompleto) professor.nomeCompleto = nomeCompleto;
	else return

	fetch("http://localhost:8080/Faculdade-Hepta/rest/professores/", {
		method: "POST",
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

