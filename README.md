# APis

## <i>ms-person - Microserviço que tem como finalidade o cadastro de pessoas</i>
<b>A api ms-person possui as seguintes caracteristicas e implementações:</b>
<ol>
  <li>Spring boot 3.0.5</li>
  <li>Utilização de REST</li>
  <li>Persistência de banco de dados relacional com Spring data JPA</li>
  <li>Migration com Flyway</li>
  <li>Conexão com banco de dados Mysql</li>
  <li>Biblioteca de produtividade para evitar códigos boilerplate em classes de domínio - Lombok </li>
  <li>Framework para cópia dados de uma classe para outra visando produtividade - Model Mapper</li>
  <li>Utilização da especificação bean validation, para validar campos das classes antes mesmo da realizaçao de persistência</li>
  <li>Utilização do projeto spring security para aplicar uma autenticaço básica de segurança para acessos aos endpoints contruidos</li>
  <li>Testes de integração com Junit 5 e banco de dados H2</li>
  <li>Implementação de DTOs de classes de Requests e de Responses</li>
  <li>Implementação de paginação e costumização do formato da paginação</li>
  <li>Implementação de exceptions costumizados com retorno padrão de mensagens</li>
  <li>Implementação de códigos de status http conforme os retornos dos endpoints</li>
  <li>Implementação dos endpoints com os verbos GET, POST, PUT e DELETE</li>
  <li>Implementação de profiles de dev, prod e test</li>
  <li>Implementaço de variáveis de ambiente para não deixar registrado no código fonte credenciais sensíveis de acesso</li>
</ol>

![image](https://user-images.githubusercontent.com/57464941/235391619-cfbe4a0c-2141-4143-826a-3ce476839154.png)

![image](https://user-images.githubusercontent.com/57464941/235391837-4b1d5ad3-c94e-42ed-bf28-8d07883e0b0d.png)

## <i>ms-person-photo - Microserviço que tem como finalidade o registro de fotos de pessoas</i>
<b>A api ms-person-photo possui as seguintes caracteristicas e implementações:</b>
<ol>
  <li>Spring boot 3.0.6</li>
  <li>Utilização de REST</li>
  <li>Persistência de banco de dados relacional com Spring data JPA</li>
  <li>Migration com Flyway</li>
  <li>Conexão com banco de dados Mysql</li>
  <li>Biblioteca de produtividade para evitar códigos boilerplate em classes de domínio - Lombok </li>
  <li>Implementação de logs utilizando slf4j</li>
  <li>Framework para cópia dados de uma classe para outra visando produtividade - Model Mapper</li>
  <li>Implementação de exceptions costumizados com retorno padrão de mensagens</li>
  <li>Implementação de códigos de status http conforme os retornos dos endpoints</li>
  <li>Implementação dos endpoints com os verbos GET, POST com MediaType MULTIPART FORM DATA</li>
</ol>

![image](https://user-images.githubusercontent.com/57464941/235392474-bc732431-9d9b-42ce-adbd-7b754530bf8c.png)

![image](https://user-images.githubusercontent.com/57464941/235392643-6415a243-c815-4326-a724-e5eceadea68e.png)

## <i>ms-product - Microserviço que tem como finalidade o cadastro de produtos</i>
<b>A api ms-product possui as seguintes caracteristicas e implementações:</b>
<ol>
  <li>Spring boot 2.7.11</li>
  <li>Utilização de REST</li>
  <li>Persistência de banco de dados relacional com Spring data JPA</li>
  <li>Migration com liquibase</li>
  <li>Conexão com banco de dados Mysql</li>
  <li>Biblioteca de produtividade para evitar códigos boilerplate em classes de domínio - Lombok </li>
  <li>Utilização do projeto spring data Rest que faz com que endpoints sejam criados automaticamente com Hoteoas</li>
  <li>Implementação de paginação</li>
  <li>Implementado o swagguer utilizando a biblioteca spring fox - http://localhost:8082/swagger-ui/index.html</li>
  <li>Implementação dos endpoints com os verbos GET, POST, PUT, PATCH e DELETE</li>
</ol>

![image](https://user-images.githubusercontent.com/57464941/235392993-6ee180c2-4b43-49c3-ab1a-943b302d4293.png)

![image](https://user-images.githubusercontent.com/57464941/235393656-ac0de2bd-b4da-4e9f-8d89-4a957a082d7d.png)

![image](https://user-images.githubusercontent.com/57464941/235393715-8e57d8ad-56ab-41ff-9356-55bdd60d340c.png)

![image](https://user-images.githubusercontent.com/57464941/235393909-c6eca116-d0b0-4b85-8aad-b84602ad4cc6.png)

## <i>ms-stock - Microserviço que tem como finalidade o cadastro de estoque de produtos</i>
<b>A api ms-stock possui as seguintes caracteristicas e implementações:</b>
<ol>
  <li>Spring boot 3.0.6</li>
  <li>Utilização de GRAPHQL</li>
  <li>Persistência de banco de dados relacional com Spring data JPA</li>
  <li>Conexão com banco de dados Mysql</li>
  <li>Biblioteca de produtividade para evitar códigos boilerplate em classes de domínio - Lombok </li>
  <li>Implementação dos endpoints com Querys e Mutations</li>
</ol>

## <i>ms-sales - Microserviço que tem como finalidade o cadastro de vendas</i>
<b>A api ms-sales possui as seguintes caracteristicas e implementações:</b>
<ol>
  <li>Spring boot 2.7.11</li>
  <li>Utilização de SOAP</li>
  <li>Persistência de banco de dados relacional com Spring data JPA</li>
  <li>Conexão com banco de dados Mysql</li>
  <li>Biblioteca de produtividade para evitar códigos boilerplate em classes de domínio - Lombok </li>
  <li>Implementação dos endpoints de busca de dados, inclusão, alteração e deleção </li>
</ol>

