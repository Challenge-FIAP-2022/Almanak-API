# Documentação Atividade

## List All
<br>

Como todas as demais classes sempre existirá um endpoint para a listagem completa dos itens, conforme link abaixo:

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/atividade?page=0&size=5

<details>
  <summary>Resposta JSON</summary>
  
```json
{
	"content": [
		{
			"id": 1,
			"tabelaAlterada": null,
			"desc": null,
			"dtRegistro": "2022-08-28T22:02:34.861176",
			"usuario": {
				"id": 1,
				"name": "AlmanaK",
				"email": "almanak",
				"senha": "almanak",
				"dtNascimento": "2022-08-28",
				"dtRegistro": "2022-01-01T00:00:00",
				"maioridade": false,
				"planoValido": {
					"id": 3,
					"name": "Elite",
					"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
					"valor": 9.99,
					"valido": "sim",
					"dtEncerramento": null,
					"dtRegistro": "2022-01-01T00:00:00"
				}
			},
			"tipoAtividade": {
				"id": 10,
				"grupo": "Login",
				"subGrupo": "Abrir App",
				"dtRegistro": "2022-01-01T00:00:00"
			}
		},
		{
			"id": 2,
			"tabelaAlterada": null,
			"desc": null,
			"dtRegistro": "2022-08-28T22:05:33.978865",
			"usuario": {
				"id": 1,
				"name": "AlmanaK",
				"email": "almanak",
				"senha": "almanak",
				"dtNascimento": "2022-08-28",
				"dtRegistro": "2022-01-01T00:00:00",
				"maioridade": false,
				"planoValido": {
					"id": 3,
					"name": "Elite",
					"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
					"valor": 9.99,
					"valido": "sim",
					"dtEncerramento": null,
					"dtRegistro": "2022-01-01T00:00:00"
				}
			},
			"tipoAtividade": {
				"id": 10,
				"grupo": "Login",
				"subGrupo": "Abrir App",
				"dtRegistro": "2022-01-01T00:00:00"
			}
		},
		{
			"id": 3,
			"tabelaAlterada": null,
			"desc": null,
			"dtRegistro": "2022-08-28T23:18:00.803784",
			"usuario": {
				"id": 1,
				"name": "AlmanaK",
				"email": "almanak",
				"senha": "almanak",
				"dtNascimento": "2022-08-28",
				"dtRegistro": "2022-01-01T00:00:00",
				"maioridade": false,
				"planoValido": {
					"id": 3,
					"name": "Elite",
					"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
					"valor": 9.99,
					"valido": "sim",
					"dtEncerramento": null,
					"dtRegistro": "2022-01-01T00:00:00"
				}
			},
			"tipoAtividade": {
				"id": 10,
				"grupo": "Login",
				"subGrupo": "Abrir App",
				"dtRegistro": "2022-01-01T00:00:00"
			}
		},
		{
			"id": 4,
			"tabelaAlterada": null,
			"desc": null,
			"dtRegistro": "2022-08-28T21:02:19.830858",
			"usuario": {
				"id": 1,
				"name": "AlmanaK",
				"email": "almanak",
				"senha": "almanak",
				"dtNascimento": "2022-08-28",
				"dtRegistro": "2022-01-01T00:00:00",
				"maioridade": false,
				"planoValido": {
					"id": 3,
					"name": "Elite",
					"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
					"valor": 9.99,
					"valido": "sim",
					"dtEncerramento": null,
					"dtRegistro": "2022-01-01T00:00:00"
				}
			},
			"tipoAtividade": {
				"id": 1,
				"grupo": "Login",
				"subGrupo": "Login",
				"dtRegistro": "2022-01-01T00:00:00"
			}
		},
		{
			"id": 5,
			"tabelaAlterada": null,
			"desc": null,
			"dtRegistro": "2022-08-28T21:04:14.381572",
			"usuario": {
				"id": 22,
				"name": "UsuarioTeste",
				"email": "usuarifoTesdte2@teste.com.br",
				"senha": "senhateste",
				"dtNascimento": "2000-01-01",
				"dtRegistro": "2022-08-28T21:04:13.740282",
				"maioridade": false,
				"planoValido": {
					"id": 1,
					"name": "Gamer",
					"desc": "Plano gratuito, com anúncios.",
					"valor": 0.0,
					"valido": "sim",
					"dtEncerramento": null,
					"dtRegistro": "2022-08-31T00:46:01.018212"
				}
			},
			"tipoAtividade": {
				"id": 1,
				"grupo": "Login",
				"subGrupo": "Login",
				"dtRegistro": "2022-01-01T00:00:00"
			}
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
	"totalPages": 24,
	"totalElements": 118,
	"last": false,
	"size": 5,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"numberOfElements": 5,
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

## Cadastro/ Deletar/ Atualizar
<br>

Por se tratar de uma tabela para acompanhamento do usuário não é possivel chamar esses métodos manualmente, apenas acessando outros endpoints, como os listados no endpoint de usuário.


<br>
<hr>
<br>

## Busca por ID
<br>

Podemos querer buscar uma atividade especifica executada por um usúario e para isso buscammos pelo Id dessa atividade gerada, conforme requisição abaixo.

Método: GET

Link: https://almanak-fiap.herokuapp.com/api/atividade/112


<details>
  <summary>Resposta JSON</summary>

```json
{
	"id": 112,
	"tabelaAlterada": null,
	"desc": null,
	"dtRegistro": "2022-09-04T19:15:51.391734",
	"usuario": {
		"id": 1,
		"name": "AlmanaK",
		"email": "almanak",
		"senha": "almanak",
		"dtNascimento": "2022-08-28",
		"dtRegistro": "2022-01-01T00:00:00",
		"maioridade": false,
		"planoValido": {
			"id": 3,
			"name": "Elite",
			"desc": "Acesso ilimitado a plataforma e maior peso nas decisões da comunidade.",
			"valor": 9.99,
			"valido": "sim",
			"dtEncerramento": null,
			"dtRegistro": "2022-01-01T00:00:00"
		}
	},
	"tipoAtividade": {
		"id": 8,
		"grupo": "Cadastro",
		"subGrupo": "Cadastro de cartao",
		"dtRegistro": "2022-01-01T00:00:00"
	}
}
```
 </details>