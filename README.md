# Documentação AlmanaK API

## Integrantes do Grupo
- Bianca Man Tchuin Chang Lee (RM: 89171)
- Danilo Zequim de Moura (RM: 89045)
- Eric Brianez Giannetti (RM: 87087)
- Matheus Pismel de Jeronymo (RM: 86931)
- Otavio de Magalhães Gomes (RM: 87680)
- Zack Lorenzzo Corrêa (RM: 87149)
<hr>
<br>

## Disclaimer
Como o referido projeto está vinculado a uma base de dados de produção, os dados obtidos como respostas de cada requisição 
podem variar de acordo com a interação dos demais usuários.
<hr>
<br>

## Descrição do projeto
O projeto consiste na criação de um aplicativo mobile, onde os usuários podem consultar jogos presenciais para jogar no seu tempo de lazer. 
Com a pandemia, muitas crianças ficaram sem brincar com seus amigos, então o app poderá dar sugestões de brincadeiras! Mas não para por ai... 
a aplicação não se limita a crianças, podendo ser para adultos também. Podem ser selecionados itens ou categorias para trazer diferentes opções
de jogos na tela. Você pode consultar as regras do jogo, avaliar, comentar, entre outras funções.
<hr>
<br>

## Base de dados
Para entender melhor o layout da base de dados que esta API está consultando, recomandamos olhar o layout antes. Segue o link abaixo com a referida arquitetura:
Link: https://almanak-db-report.herokuapp.com/
<hr>
<br>

## API Endpoints
Como panorama geral da aplicação, vale a pena entender que nenhum dado é deletado, apenas flegado como não mais válido e registrado uma data de encerramento. Utilizamos esse mecanismo pra termos uma visão ampla do histórico da aplicação.

Para poder visualizar todos os testes que realizamos na API basta acessar o arquivo do <a href="Insomnia_Tests.json">Insomnia</a> .

Classe de Endpoint														| Descrição																				|
--- 																	| ---																					|
<a href="/Documentation/Usuario/README.md">Usuário</a>					| Endpoints para manipulação de dados dos usuários.										|
<a href="/Documentation/Plano/README.md">Plano</a>						| Endpoints para manipulação de dados dos planos que o usuário pode estar atrelado.		|
<a href="/Documentation/Atividade/README.md">Atividade</a>				| Endpoints para rastreamento do usuário no uso do app.									|
<a href="/Documentation/Tipo Atividade/README.md">Tipo de Atividades</a>| Endpoints para listagem dos tipos de atividade mapeadas.								|
<a href="/Documentation/Jogo/README.md">Jogo</a> 						| Endpoints para manipulação de dados dos Jogos presentes do app.						|
<a href="/Documentation/Categoria/README.md">Categoria</a> 				| Endpoints para manipulação de dados das categorias que os jogos podem estar atrelados.|
<a href="/Documentation/Item/README.md">Item</a>	 					| Endpoints para manipulação de dados dos itens que os jogos podem possuir.				|
