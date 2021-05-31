window.onload = () => {
	fetch("http://localhost:8080/Faculdade-Hepta/rest/disciplinas/")
		.then(res => res.json())
		.then(data => {
			var tableBody = document.getElementById('tableBody');
			data.forEach(disciplina => {
				// console.log(disciplina);
				
				var tr = document.createElement('tr');
				
				var id = document.createElement('td');
				id.appendChild(document.createTextNode(disciplina.idDisciplina));
				tr.appendChild(id);

				var nome = document.createElement('td');
				nome.appendChild(document.createTextNode(disciplina.nome));
				tr.appendChild(nome);
				
				var planoEnsino = document.createElement('td');
				var planoEnsinoNome = document.createElement('a');
				var nomeArquivo = `${disciplina.planoEnsinoNome}.pdf`;
				planoEnsinoNome.appendChild(document.createTextNode(nomeArquivo));
				planoEnsinoNome.setAttribute('href', `data:application/pdf;base64,${disciplina.planoEnsinoArquivo}`);
				planoEnsinoNome.setAttribute('download', nomeArquivo);
				planoEnsino.appendChild(planoEnsinoNome);
				tr.appendChild(planoEnsino);
				
				var professor = document.createElement('td'); 
				professor.appendChild(document.createTextNode(disciplina.professorDTO.nomeCompleto));
				tr.appendChild(professor);
				
				tr.appendChild(document.createElement('td'));
				tr.appendChild(document.createElement('td'));
				
				tableBody.appendChild(tr);
			})
		})
}