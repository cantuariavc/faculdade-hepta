window.onload = () => getProfessores();

function getProfessores() {
	fetch("http://localhost:8080/Faculdade-Hepta/rest/professores/")
		.then(res => res.json())
		.then(data => {
			var select = document.getElementById('professoresSelect');
			data.forEach(professor => {
				var option = document.createElement('option');
				option.setAttribute('value', professor.idProfessor);
				option.appendChild(document.createTextNode(professor.nomeCompleto));
				select.appendChild(option);
			});
		});
}
