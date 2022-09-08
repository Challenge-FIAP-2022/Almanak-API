# Documentação Plano

## List All
<br>

Como todas as demais classes sempre existirá um endpoint para a listagem completa dos itens, conforme link abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/plano?page=0&size=5

<details>
  <summary>Resposta JSON</summary>
  
```json
{
	"content": [
		{
			"id": 2,
			"name": "Pro Gamer",
			"desc": "Plano limitado com acesso a alguns jogos.",
			"valor": 2.99,
			"valido": "sim",
			"dtEncerramento": null,
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 3,
			"name": "Elite",
			"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
			"valor": 9.99,
			"valido": "sim",
			"dtEncerramento": null,
			"dtRegistro": "2022-01-01T00:00:00"
		},
		{
			"id": 8,
			"name": "teste5",
			"desc": null,
			"valor": 0.0,
			"valido": "nao",
			"dtEncerramento": "2022-08-29T07:56:42.040763",
			"dtRegistro": "2022-08-29T07:56:17.31084"
		},
		{
			"id": 9,
			"name": "Gamer3",
			"desc": "Plano gratuito, com anúncios.",
			"valor": 0.0,
			"valido": "nao",
			"dtEncerramento": "2022-08-29T07:57:00.318166",
			"dtRegistro": "2022-08-29T07:56:01.028043"
		},
		{
			"id": 1,
			"name": "Gamer",
			"desc": "Plano gratuito, com anúncios.",
			"valor": 0.0,
			"valido": "sim",
			"dtEncerramento": null,
			"dtRegistro": "2022-08-31T00:46:01.018212"
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
		"pageSize": 5,
		"paged": true,
		"unpaged": false
	},
	"last": true,
	"totalPages": 1,
	"totalElements": 5,
	"size": 5,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"first": true,
	"numberOfElements": 5,
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

Podemos querer registrar um novo plano para isso podemos inserir um JSON no body e acessar o link conforme abaixo:

Método: POST

Link: https://almanak-fiap.herokuapp.com/api/plano

Body JSON:
```json
{
	"name": "Gamer5",
	"desc": "Plano gratuito, com anúncios.",
	"valor": 0.0
}
```

Lembre-se, como é uma base produtiva as veze outra pessoas já cadastrou esse plano, nesse caso basta alterar o nome para um valor valido. Caso já seja um nome já existente o valor 401 (Unauthorized) será retornado.

<br>
<hr>
<br>

## Busca por ID
<br>

Logo após o cadastro do plano um DTO da classe plano será retornado e podemos busca-lo pelo id criado. Obteremos a seguinte resposta conforme o link abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/plano/1

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

## Busca por nome
<br>

As vezes não queremos buscar o plano pelo id mas sim pelo nome, para isso podemos acessar o método abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/plano/nome/Elite

Reposta JSON:
```json
{
	"id": 3,
	"name": "Elite",
	"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
	"valor": 9.99
}
```

<br>
<hr>
<br>

## Busca por planos validos
<br>

Ao acessar a tela de planos o usuário gostará de se deparar com todos os possíveis planos que ele pode contrar, para isso é necessário um endpoint capaz de listar todos os planos validos, ou seja que estão habilitados para contratação, para isso basta acessar o seguinte metódo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/plano/valido/sim

<details>
  <summary>Resposta JSON:</summary>

```json
[
	{
		"id": 1,
		"name": "Gamer",
		"desc": "Plano gratuito, com anúncios.",
		"valor": 0.0
	},
	{
		"id": 2,
		"name": "Pro Gamer",
		"desc": "Plano limitado com acesso a alguns jogos.",
		"valor": 2.99
	},
	{
		"id": 3,
		"name": "Elite",
		"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
		"valor": 9.99
	}
]
```
</details>

<br>
<hr>
<br>

## Atualizar
<br>

Podemos também querer realizar a atualização de algum dado cadastral do plano, para isso realizaremos a chamada do link abaixo informando no body o id do plano que se deseja atualizar bem como suas demais informações.

Método: POST

Link: https://almanak-fiap.herokuapp.com/api/plano

```json
{
	"id": 1,
	"name": "Gamer",
	"desc": "Plano gratuito, com anúncios.",
	"valor": 0.0,
	"valido": "sim",
	"dtEncerramento": null
}
```
 

<br>
<hr>
<br>

## Delete
<br>

Como dito na documentação original da API, não permitimos métodos de DELETE deletem informações da base de dados. Ao ser chamado ele irá acionar o flag de não valido para esse plano e registrar uma data de encerramento. Você poderá realizar esse teste chamando o link abaixo e em seguida, chamar o de listar planos validos para ver que o plano 1 não aparece mais, contudo se vc buscar pelo id, ele ainda terá reposta.

Método: DELETE

Link: https://almanak-fiap.herokuapp.com/api/plano/1
