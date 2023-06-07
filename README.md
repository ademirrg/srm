# SRM
Necessário ter o mysql server instalado com configuração de root padrão sem senha e na porta 3306, ou você poderá modificar as credenciais no arquivo application.properties.
Estou utilizando um versionador de banco de dados chamado flyway, já possui scripts de criação da tabela de Pessoa.

Para subir a aplicação, é necessário importar o projeto como Maven Project e deixar baixar as dependências, em seguida basta executar a classe principal SrmApp.

O Projeto foi feito em Java 11 com Spring Boot.

Segue abaixo os exemplos de requisições com as rotas e corpo de request e response:

=======================================================
Pessoa
=======================================================

URI /person/{document} (retorna uma pessoa pesquisada por documento)
Metodo GET
Ex.: de resposta:
{
    "id": 1,
    "name": "Fulano Srm da Silva",
    "document": "33333333333",
    "documentType": "CPF"
}

URI /person/all (retorna todas as pessoas cadastradas na base)
Metodo GET
Ex.: de resposta:
[
    {
    "id": 1,
    "name": "Fulano Srm da Silva",
    "document": "33333333333",
    "documentType": "CPF"
    },
    {
    "id": 2,
    "name": "Ciclano Srm da Silva",
    "document": "11111111000111",
    "documentType": "CNPJ"
    }
]

URI /person/create (grava uma pessoa no banco de dados)
Metodo POST
Ex.: de resposta:
{
"id": 1,
"name": "Fulano Srm da Silva",
"document": "33333333333",
"documentType": "CPF"
}

URI /person/update/{id} (atualiza uma pessoa no banco de dados)
Metodo PUT
Ex.: de resposta:
{
"id": 1,
"name": "Fulano Srm da Silva",
"document": "33333333333",
"documentType": "CPF"
}

URI /person/delete/{id} (deleta uma pessoa no banco de dados)
Metodo POST
Ex.: de resposta:
{
    "Registro deletado"
}
