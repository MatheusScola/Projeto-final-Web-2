# SchoolManagementApplication

## Descrição
SchoolManagementApplication é um projeto desenvolvido como parte do Curso Final do Módulo Programação Web II. O projeto implementa um sistema CRUD (Create, Read, Update, Delete) para gerenciamento de informações de alunos e professores.

## Tecnologias Utilizadas
- **Java 17**: Linguagem de programação.
- **Spring Boot**: Framework para desenvolvimento de aplicações web.
- **Banco de Dados H2**: Banco de dados em memória.
- **Lombok**: Biblioteca para redução de código boilerplate em Java.
- **Jakarta**: Especificações de APIs para Java.
- **OpenFeign**: Biblioteca para criação de clientes HTTP declarativos.

## Funcionalidades
Este projeto inclui as seguintes funcionalidades:
- **Gerenciamento de Alunos**: Adicionar, editar, visualizar e remover informações de alunos.
- **Gerenciamento de Professores**: Adicionar, editar, visualizar e remover informações de professores.

## Instalação e Configuração
### Pré-requisitos
- Java 17 instalado em sua máquina.
- Maven para gerenciamento e construção do projeto.

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/seu-projeto.git
cd seu-projeto

# Instale as dependências e construa o projeto
mvn install
```
## Como Usar
Iniciar a Aplicação
Para iniciar a aplicação, abra o terminal na pasta raiz do projeto e execute o seguinte comando:

```bash
./mvnw spring-boot:run
# Este comando irá iniciar o servidor Spring Boot. Preste atenção ao console, pois ele informará em qual porta o servidor está rodando, geralmente é a http://localhost:8080.
```

## Navegação Básica
Utilizando Swagger
A aplicação está configurada para usar o Swagger, que fornece uma interface gráfica para interação com a API. Para acessar o Swagger UI, visite:

```bash
http://localhost:8080/swagger-ui.html
# No Swagger UI, você poderá ver todos os endpoints disponíveis, assim como executar operações de CRUD (criar, ler, atualizar e deletar) diretamente através da interface web.
```

Utilizando Postman
Para uma experiência mais detalhada ou para integração em scripts de testes, você pode usar o Postman. Importe a coleção de endpoints da API disponível no diretório de documentação do projeto (se disponível) ou configure manualmente os requests usando as URLs fornecidas pela documentação do Swagger.
