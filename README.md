# crudSys
Projeto realizado com o intuito de entender como funciona as operações CRUD em conjunto com o postgreSQL, sistema de gerenciamento de banco de dados objeto-relacional. Utilizando Maven para gerenciar as dependencias.


# Funcionamento do projeto

Existem dois tipos de requisições do tipo 'GET' definido por "acao" para executar no browser que retornam uma camada de visão:<br> 
/crudSys/usucontroller.do?acao=lis<br>
![](https://i.imgur.com/j5hfXc0.png)<br>
Em "acao=lis", a camada de visão irá apresentar uma tabela com todos os Usuários, seus nomes e seus IDs, tendo como duas opções 
[Excluir] e [Alterar], em caso de escolha de uma das ambas opções, o ID do usuário escolhido será passado por request de parâmetro 'GET', e processado
pelo servlet UsuController.

<br>acao=cad<br>
![](https://i.imgur.com/HF4owDg.png)<br>

Para efetuar o cadastro de um novo usuario, terá que ser passado pelo parâmetro 'GET' a "acao=cad". Irá carregar a camada de visão responsável
por consumir os dados do lado do Cliente, para ser processado no lado do Servidor pelo servlet UsuController, e a partir disso, tratar os dados
recebidos atráves de um DataAccessObject (DAO), fazendo a persistência no banco de dados.
