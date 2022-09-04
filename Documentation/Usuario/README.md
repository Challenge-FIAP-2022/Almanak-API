# Documentação Usuário

## List All
<br>

Como todas as demais classes sempre existirá um endpoint para a listagem completa dos itens, conforme link abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/usuario?page=0&size=5

<details>
  <summary>Resposta JSON</summary>
  
```json
{
	"content": [
		{
			"id": 1,
			"name": "AlmanaK",
			"email": "almanak",
			"senha": "almanak",
			"dtNascimento": "2022-08-28",
			"dtRegistro": "2022-01-01T00:00:00",
			"maioridade": false,
			"planoValido": {
				"id": 1,
				"name": "Gamer",
				"desc": "Plano gratuito, com anúncios.",
				"valor": 0.0,
				"valido": "sim",
				"dtEncerramento": null,
				"dtRegistro": "2022-01-01T00:00:00"
			}
		},
		{
			"id": 2,
			"name": "Carla Louise da Rocha",
			"email": "carla_louise_darocha@mundivox.com.br",
			"senha": "5YKUEmmHXW",
			"dtNascimento": "1996-07-01",
			"dtRegistro": "2022-01-10T00:00:00",
			"maioridade": false,
			"planoValido": {
				"id": 1,
				"name": "Gamer",
				"desc": "Plano gratuito, com anúncios.",
				"valor": 0.0,
				"valido": "sim",
				"dtEncerramento": null,
				"dtRegistro": "2022-01-01T00:00:00"
			}
		},
		{
			"id": 3,
			"name": "Thomas Ruan Benício Farias",
			"email": "thomas-farias80@facebook.com",
			"senha": "0ctdOMUd0d",
			"dtNascimento": "1981-02-26",
			"dtRegistro": "2022-01-25T00:00:00",
			"maioridade": false,
			"planoValido": {
				"id": 1,
				"name": "Gamer",
				"desc": "Plano gratuito, com anúncios.",
				"valor": 0.0,
				"valido": "sim",
				"dtEncerramento": null,
				"dtRegistro": "2022-01-01T00:00:00"
			}
		},
		{
			"id": 4,
			"name": "Alana Tereza Clara Freitas",
			"email": "alana-freitas71@stembalagens.com.br",
			"senha": "BCGK695qhT",
			"dtNascimento": "1945-05-21",
			"dtRegistro": "2022-02-13T00:00:00",
			"maioridade": false,
			"planoValido": {
				"id": 1,
				"name": "Gamer",
				"desc": "Plano gratuito, com anúncios.",
				"valor": 0.0,
				"valido": "sim",
				"dtEncerramento": null,
				"dtRegistro": "2022-01-01T00:00:00"
			}
		},
		{
			"id": 5,
			"name": "Ruan Ryan Rezende",
			"email": "ruan-rezende84@comercialrizzo.com",
			"senha": "5Sc1zFhyij",
			"dtNascimento": "1965-05-08",
			"dtRegistro": "2022-02-15T00:00:00",
			"maioridade": false,
			"planoValido": {
				"id": 1,
				"name": "Gamer",
				"desc": "Plano gratuito, com anúncios.",
				"valor": 0.0,
				"valido": "sim",
				"dtEncerramento": null,
				"dtRegistro": "2022-01-01T00:00:00"
			}
		}
	],
	"pageable": {
		"sort": {
			"sorted": false,
			"unsorted": true,
			"empty": true
		},
		"pageNumber": 0,
		"pageSize": 5,
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"totalPages": 5,
	"totalElements": 22,
	"last": false,
	"first": true,
	"sort": {
		"sorted": false,
		"unsorted": true,
		"empty": true
	},
	"size": 5,
	"number": 0,
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

O primeiro passo para acessar o sistema é fazer cadastro do usuário. Segue um exemplo de arquivo JSON para cadastro do usuário:

Método: POST

Link: https://almanak-fiap.herokuapp.com/api/usuario/signin

Body JSON:
```json
{
	"name": "UsuarioTeste3",
	"email": "usuarifoTesdte8@teste.com.br",
	"senha": "senhateste",
	"dtNascimento": "2000-01-01"
}
```

Lembre-se, como é uma base produtiva as vezes outra pessoas já cadastrou esse usuário, nesse caso basta alterar o e-mail para um valor valido. Caso já seja um e-mail existente o valor 401 (Unauthorized) será retornado.

<br>
<hr>
<br>

## Busca por ID
<br>

Logo após o cadastro do usuário um DTO da classe usuário será retornado e podemos busca-lo pelo id criado. Obteremos a seguinte resposta conforme o link abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/usuario/1

Reposta JSON:
```json
{
	"id": 1,
	"maioridade": false,
	"plano": {
		"id": 3,
		"name": "Elite",
		"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
		"valor": 9.99
	}
}
```
 

<br>
<hr>
<br>

## Login
<br>

Outra funcionalidade é o usuário realizar a busca pelo e-mail e senha dele, esse metodo também irá acionar o metodo de login, preenchendo a tabela de atividades com a informação que esse usuário realizou o login. Segue o ling para essa operação:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/usuario/login?email=almanak&senha=almanak

Reposta JSON:
```json
{
	"id": 1,
	"maioridade": false,
	"plano": {
		"id": 3,
		"name": "Elite",
		"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
		"valor": 9.99
	}
}
```
 

<br>
<hr>
<br>

## Entrar no App
<br>

Assim como o login, podemos informar que o usuário voltou a entrar no app, enquanto já está logado, dessa forma chamamos o método de entrar no app para obtermos as informações mais atualizada dele e registrar essa atividade na nossa tabela de atividades. Para isso basta informar o id do usuário conforme o link abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/usuario/enterapp/1

Reposta JSON:
```json
{
	"id": 1,
	"maioridade": false,
	"plano": {
		"id": 3,
		"name": "Elite",
		"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
		"valor": 9.99
	}
}
```
 

<br>
<hr>
<br>

## Atualizar
<br>

Podemos também querer realizar a atualização de algum dado cadastral do usuario, para isso realizaremos o método PUT no link abaixo, informando no body o id do usuário que se deseja atualizar bem como suas demais informações.

Método: PUT

Link: https://almanak-fiap.herokuapp.com/api/usuario

```json
{
    "id": 1,
	"name": "UsuarioTeste3",
	"email": "almanak@almanak.com.br",
	"senha": "senhateste",
	"dtNascimento": "2000-01-01"
}
```
 

<br>
<hr>
<br>

## Delete
<br>

Como dito na documentação original da API, não permitimos métodos de DELETE, no caso do usuário se ele não encontrar o sistema retornará 401 (Unauthorized) e caso não encontre o 404 (Not Found). Segue o link para teste, basta apenas informar o id do usuário ao final.

Método: DELETE

Link: https://almanak-fiap.herokuapp.com/api/usuario/1
