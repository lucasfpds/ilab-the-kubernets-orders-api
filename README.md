![Logo](https://www.notion.so/image/https%3A%2F%2Fbutecotecnologico.com.br%2Fkubernetes-explicado%2Fk8s-logo.png?table=block&id=4bdd526d-997b-4b7a-aff3-60f6d830cb8e&spaceId=b5bde1f6-a69d-4d6a-ba92-e85e080fe677&width=150&height=150&userId=8a4a1998-bbe3-4ecd-8da4-81ad9f899b9c&cache=v2)

# The Kubernetes: API de Orders

</br>

## Tópicos

   * [Sobre o projeto](#-sobre-o-projeto)
   * [Informações Gerais](#-informações-gerais)
   * [Endpoints da API](#-endpoints-da-api)
      * [Create Order](#-create-order)
      * [List Orders](#-list-orders)
   * [Pré-requisitos](#-pré-requisitos)
   * [Executar a aplicação](#-executar-a-aplicação)
   * [Tecnologias](#-tecnologias)
   * [Equipe responsável](#-equipe-responsável)

<br/>
<hr/>

## 📌 Sobre o projeto

<p>
O projeto tem como objetivo implementar um sistema de realização de pedidos, gerenciado por um administrador. Para o funcionamento correto da aplicação, o administrador precisa estar logado em sua conta para que o pedido seja criado. O processamento deste deve ocorrer através de um sistema de mensageria (em background), com o envio de e-mail ao usuário ao término da operação, informando que o pedido foi realizado com sucesso.
Por fim, os pedidos devem ser armazenados em um banco de dados, possibilitando que posteriormente estes possam ser consultados.
</p> 

Em resumo, o fluxo do processo consiste em:

- Administrador se conecta à aplicação.
- Seleciona qual a opção desejada (usuários ou pedidos).
  - Caso usuários seja escolhido:
    - Cadastrar um novo usuário.
    - Editar um usuário existente.
    - Excluir um usuário.
  - Caso pedidos seja escolhido:
    - Criar um novo pedido.
      - O pedido é enviado para o sistema de mensageria.
      - O sistema de mensageria processa o pedido.
      - O sistema de mensageria envia um e-mail ao usuário, informado que o pedido foi realizado.
- Independente da escolha, usuários e pedidos sempre estão sendo salvos no banco de dados.  

</br>

<em>
    link para o repositório de Front: 
</em>

<br/>
<hr/>

## 📝 Informações Gerais
- A persistência dos dados foi feita no Sistema Gerenciador de Banco de Dados Postgres.
- O sistema de mensageria foi realizado utilizando Simple Queue Service (SQS) da Amazon Web Service (AWS).
- O sistema de envio de e-mails foi realizado utilizando Simple Email Service (SES) da AWS.
- As imagens docker de cada aplicação estão publicadas no [DockerHub](https://hub.docker.com/).
- As aplicações foram deployadas utilizando Kubernetes.

</br>

O que foi implementado durante o projeto:
1) endpoint cadastrar (nome, email e senha);
2) endpoint login administrador (email e senha);
3) endpoint cadastrar usuário;
4) endpoint editar usuário;
5) endpoint excluir usuário;
6) endpoint listarusuários;
7) endpoint cadastrar pedido;
8) endpoint listar pedidos;

<br/>
<hr/>

## ☞ Endpoints da API

### Create Order
* `POST` (/create-order) <br/>
{<br/>
idOrder: idOrder,           <br/>
idAdmin: idAdmin,           <br/>
idUser: idUser,             <br/>
description: description,   <br/>
totalValue: totalValue      <br/>
} <br/>

<br/>

* Retorno: <br/>
{<br/>
idOrder: idOrder,           <br/>
idAdmin: idAdmin,           <br/>
idUser: idUser,             <br/>
description: description,   <br/>
totalValue: totalValue,      <br/>
status: status,      <br/>
statusEmail: statusEmail      <br/>
} <br/> <br/>
ou {"mensagem": "Bad Request"} <br/>

</br>

### List Orders
`GET` (/orders) <br/>

* Retorno: <br/>
status 200 (ok) <br/> <br/>
ou {"mensagem": "Bad Request"} <br/>

<br/>
<hr/>

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
Git, Java 17, Spring Boot, PostgreSQL.

<br/>
<hr/>

## Executar a aplicação

```bash

# Clone este repositório em sua máquina  
$ git clone https://gitlab.com/ilab-the-kubernets/orders-api.git

```

<br/>
<hr/>

## 🛠 Tecnologias

As seguintes linguagens/tecnologias foram usadas na construção do projeto:

<div>
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" target="_blank">

  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" target="_blank">

  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" target="_blank">

  <img src="https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white" target="_blank">
</div>

<br/>
<hr/>

## 🛠 Ferramentas

As seguintes ferrramentas foram utilizadas no projeto (clique nos ícones para acessar a documentação):

<div>
    <a href=https://donovan-tarsis.atlassian.net/jira/software/projects/KG4/boards/2>
        <img src="https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=Jira&logoColor=white" target="_blank">
    </a>
    <a href=https://www.notion.so/d797dd90bf404eb889490c76731514e7?v=fd976418c0704b7c9205a5dc80db1826>
        <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white" target="_blank">
    </a>
</div>

<br/>
<hr/>

## 👩‍💻🧑‍💻 Equipe responsável

**Antônia Pamela Yhaohannah de Lima** - [GitLab](https://gitlab.com/yhaohannah.lima) [Linkedin](https://www.linkedin.com/in/yhaohannah-lima-954690216/)

**Donovan Társis Bicalho Silva** - [GitLab](https://gitlab.com/donovan.tarsis) [Linkedin](https://www.linkedin.com/in/donovan-tarsis/)

**Guilherme Felipe Campos** - [GitLab](https://gitlab.com/GuilhermeFelipeCampos) [Linkedin](https://www.linkedin.com/in/guilhermefelipecampos/)

**Lucas Fernandes Paixão dos Santos** - [GitLab](https://gitlab.com/lucasfpds) [Linkedin](https://www.linkedin.com/in/lfpds/)

**Rebeca Victoria dos Santos Ferreira** - [GitLab](https://gitlab.com/rvsfrebeca1) [Linkedin](https://www.linkedin.com/in/rebecaferreirajs/)

---

