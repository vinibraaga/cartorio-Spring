# cartorio-Spring
Sistema de cartorios e emisão de certidões.

## Uso
####IMPORTANTE
Abra o projeto no Intellij ou em uma IDE da sua prefêrencia <br/>
1. Caso tenha o Postgres instalado criar um BD chamado "cartorios" <br/>
1.1 Mudar as configurações no "application.properties" para seu ambiente local do postgres <br/>
2. Caso não tenha o Postgres ou queira fazer um teste rápido, retirar ou deixar comentado no "application.properties" a linha 9 em diante. <br/>
4. Executar o projeto com as teclas: Ctrl+Shift+F10 ou Shift+F10 e ser feliz.

## Tecnologia
- Java
- Spring Boot
- Thymeleaf
- H2DB
- PostgresSQL

## Planejamento
Analise completa do projeto <br/>
Logo após de ler a hitoria e os requisitos eu fiz o relacionamento das tabelas, <br/>
comecei o desenvolcimento com as entidades e RestControllers, fiz testes no postman para verificar se as relações estavam corretas. <br/>
Em seguida estudei o uso do Thymeleaf junto com as Controllers e comecei a executar conforme aprendia. <br/>
Por ultimo configurei o Postgres. Realizei testes manuais durante todo desenvolvimento.