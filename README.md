# covid-church

Foi criado um projeto para auxiliar as igrejas a organizarem os cultos durante a pandemia da COVID-19. Através da aplicação web é possível cadastar as igrejas, os cultos e criar um agendamento de pessoas no culto.

## Justificativas da composição do projeto

As linguagens escolhidas para o desenvolvimento foram: Javascript Angular(front-end) & Java (back-end).
A persistência dos dados está sendo feita em um banco de dados MongoDB Atlas.

## Justificativas da composição do projeto

## Frontend (pasta Frontend)



## Backend (pasta Backend)

### Inicialização

A inicialização é feita de maneira simplificada pelo uso do spring boot sendo necessário apenas executar a classe **ApiApplication**. Contudo, para que ocorra o correto funcionamento do microsserviço é necessário o preenchimento das propriedades no arquivo **application.yaml** neste arquivo temos diversas informações de credenciais e configurações do sistema, sendo elas (as principais):<br/>

jwt.expire: Tempo de expiração do Token. <br/>
jwt.secret: É a base para a geração do Token. <br/>
server.port: Porta que o serviço web da API irá utilizar. (Padrão da aplicação 3001) <br/>
spring.data.mongodb.database: Nome do banco que será criado no MongoDB. (Foi utilizada a database Church) <br/> 
spring.data.mongodb.uri: URI de conexão ao MongoDB, ela pode ser copiada da página de configuração do MongoDB. <br/> 
springfox.documentation.swagger-ui.enabled: Habilita a documentação do Swagger. (Por padrão true) <br/>

### Documentação
As informações referentes as rotas e funcionalidades do microsserviço podem ser acessadas através do swagger(http://localhost:3001/swagger-ui.html) configurado no projeto. Você pode fazer o download no link docs e importar no Postman ou no seu consumidor de APIs preferido :)
