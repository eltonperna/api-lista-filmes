# filmes-api
API RESTful para consulta dos produtores com maior e menor prazo entre os prêmios.
A API foi criada visando atender ao nível de maturidade 2 de Richardson.

## Requisitos
Para execução do projeto, é necessário instalação do Java 17 e Maven 3.9.4

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
