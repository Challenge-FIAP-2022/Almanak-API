# Documentação AlmanaK API

## Disclaimer

Como o referido projeto está vinculado a uma base de dados de produção, os dados obtidos como respostas de cada requisição podem variar de acordo com a interação dos demais usuários.

<hr>
<br>


## Descriação do projeto
O projeto na criação de um app para Android em que os usuários possam consultar jogos prosenciais para jogar no seu tempo de lazer. 
Com a pandemia muitas crianças ficaram sem brincar com seus amigos, então o app poderá dar sugestões de brincadeiras, 
contudo a aplicação não se limite a crianças, podendo ser para adultos também.
<hr>
<br>

## Base de dados
Para entender melhor o layout da base de dados que está API está consultando recomandamos olhar o layout antes. Segue o link abaixo com a referida arquitetura:

Link: https://almanak-db-report.herokuapp.com/

<hr>
<br>

## API Endpoints

Como panorama geral da aplicação, vale a pena entender que nenhum dado é deltado, apenas flagado como não mais valido e registrado uma data de encerramento. Utilizamos esse mecanismo pra termos uma visão ampla do histórico da aplicação.

Para poder visualizar todos os testes que realizamos na API basta acessar o arquivo do <a href="Insomnia_Tests.json">Insomnia</a> .

Classe de Endpoint												| Descrição																				|
--- 															| ---																					|
301 															| Endpoints para manipulação de dados dos usuários.										|
301 															| Endpoints para manipulação de dados dos planos que o usuário pode estar atrelado.		|
<a href="/Documentation/Atividade/README.md">Atividade</a>		| Endpoints para rastreamento do usuário no uso do app.									|
301 															| Endpoints para listagem dos tipos de atividade mapeadas.								|
301 															| Endpoints para manipulação de dados dos Jogos presentes do app.						|
301 															| Endpoints para manipulação de dados das categorias que os jogos podem estar atrelados.|
301 															| Endpoints para manipulação de dados dos itens que os jogos podem possuir.				|
