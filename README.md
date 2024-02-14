# filmes-api
API RESTful para consulta e manutenção da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.
A API foi criada visando atender ao nível de maturidade 2 de Richardson.

## Requisitos
Para execução do projeto, é necessário instalação do Java 17 e Maven 3.9.4

## Configurações
- Por padrão a aplicação está configurada com o servlet.contextPath=/api para alterá-lo abra o arquivo application.properties e altere o valor da propriedade.
```sh
    # Context
    server.servlet.contextPath=/api
    springdoc.swagger-ui.path=/swagger
```
- Para alterar as configurações do banco de dados com URL, usuário, senha e url do console, e ativar/desativar o console, abra o arquivo application.properties. A opções do banco H2 e as propriedades do datasource são exibidas como abaixo:
```sh
    # H2
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2
    
    # Datasource
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.username=sa
    spring.datasource.password=
    spring.datasource.driver-class-name=org.h2.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.globally_quoted_identifiers=true
```

## Para executar o projeto
Para executar o projeto, nenhuma instalação externa é necessária. Ao ser iniciada, a aplicação cria o banco de dados e o popula com os dados do arquivo movielist.csv, que se encontra em *src/main/resources/*.
1. Clone o repositório ou faça download;
2. Na raiz do projeto, execute o comando:
```sh
        $ mvn install -Dmaven.test.skip=true
```
3. Para iniciar a aplicação, vá até a raiz do projeto e execute:
```sh
        $ mvn spring-boot:run
```

## EndPoints
Para ver a lista de chamadas REST disponíveis, seus parametros, códigos de resposta HTTP, e tipo de retorno, inicie a aplicação e acesse: http://localhost:8081/api/swagger

## Testes
Para executar os testes abra a classe AppTest.java, clique em Run -> Run As -> JUnit Test. Isso fará com que todos os testes de integração implementados sejam executados.
