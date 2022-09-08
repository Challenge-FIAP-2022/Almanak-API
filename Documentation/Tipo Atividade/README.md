# Documentação Tipo de Atividade

## List All
<br>

Como todas as demais classes sempre existirá um endpoint para a listagem completa dos itens, conforme link abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/tipoAtividade

<details>
  <summary>Resposta JSON</summary>
  
```json
{
	"content": [
		{
			"id": 1,
			"grupo": "Login",
			"subGrupo": "Login",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 2,
			"grupo": "Busca",
			"subGrupo": "Busca por categoria",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 3,
			"grupo": "Busca",
			"subGrupo": "Busca geral",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 4,
			"grupo": "Busca",
			"subGrupo": "Busca por jogo",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 5,
			"grupo": "Busca",
			"subGrupo": "Jogo Recomendado",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 6,
			"grupo": "Filtro",
			"subGrupo": "Filtro por categoria",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 7,
			"grupo": "Filtro",
			"subGrupo": "Filtro por Item",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 8,
			"grupo": "Cadastro",
			"subGrupo": "Cadastro de cartao",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 9,
			"grupo": "Jogo",
			"subGrupo": "Abertura tela do jogo",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 10,
			"grupo": "Login",
			"subGrupo": "Abrir App",
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 11,
			"grupo": "Teste",
			"subGrupo": "Teste",
			"dtRegistro": "2022-08-29T08:06:01.01242"
		}
	],
	"pageable": {
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"offset": 0,
		"pageNumber": 0,
		"pageSize": 20,
		"paged": true,
		"unpaged": false
	},
	"totalPages": 1,
	"totalElements": 11,
	"last": true,
	"size": 20,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"numberOfElements": 11,
	"first": true,
	"empty": false
}
```
</details>

<br>
Por se tratar de uma resposta do tipo "Pageable" os parâmetros de "size" e "page" podem ser alterados, conforme o desejo do cliente. 

<br>
<hr>
<br>

## Cadastro
<br>

Podemos querer registrar um novo tipo de atividade para isso podemos inserir um JSON no body e acessar o link conforme abaixo:

Método: POST

Link: https://almanak-fiap.herokuapp.com/api/tipoAtividade

Body JSON:
```json
{
	"grupo": "Teste",
	"subGrupo": "Teste",
	"dtRegistro": null
}
```

<br>
<hr>
<br>

## Busca por ID
<br>

Logo após o cadastro do tipo de atividade um DTO da classe tipo de atividade será retornado e podemos busca-lo pelo id criado. Obteremos a seguinte resposta conforme o link abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/tipoAtividade/1

Reposta JSON:
```json
{
	"id": 1,
	"name": "Gamer",
	"desc": "Plano gratuito, com anúncios.",
	"valor": 0.0
}
```

<br>
<hr>
<br>

## Delete
<br>

Aqui temos uma das poucas exceções da base de dados, tendo um delete que de fato deleta um item, ao deleterar obtemos como resposta um 204 (No Content), informanado que o tipo de atividade passo pelo lonk foi deletado. Segue o link para realizar esse tipo de operação.

Método: DELETE

Link: https://almanak-fiap.herokuapp.com/api/tipoAtividade/1
