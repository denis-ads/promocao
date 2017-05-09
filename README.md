WildFly Swarm 
====================
Exemplo que demonstra o uso tecnologia Java EE 7 (JPA 2, CDI, EJB 3.1 and JAX-RS) com wildfly swarm.  

Introdução
------------
clean package wildfly-swarm:run

Esse projeto é dividido em 2 modulos, Campanha e Socio Torcedor.


* [Requisitos](#system-requirements): Lista de softwares necessarios para executar exemplo.

* [Configure Maven](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN.md): How to configure the Maven repository for use by the quickstarts.
* [Postgres](Roteiro para criação banco de dados em Orientacao/ProcedimentosBD).

Documentação API
---------------------------------
Utilizando Swagger Editor: http://editor.swagger.io

1. campanha/src/main/resources/swagger.json
2. socio-torcedor/src/main/resources/swagger.json

Uso do Jmeter para testes integrados
---------------------

* CampanhaEndpoint.jmx


Definição projeto
-------------------------------------

* Projeto foi divido em 2 microserviços, Campanha e SocioTorcedor.

* Cada módulo tem seu proprio banco de dados, porém a estrategia de compartilhamento das informações da entidade TimeCoração e Campanha deveria utilizar uma estratégia de cache distribuído usando jboss infinispan ou apache kafka.

* Processo de notifiação de atualização de Campanha deveria utilizar jms sendo acionado pelo CDI @Observer.

* Os projetos foram dividino nos pacotes:
	* br.com.developer.dao -> Persistência
	* br.com.developer.event -> Eventos
	* br.com.developer.exception -> Exceções
	* br.com.developer.jms -> Mensageria
	* br.com.developer.model -> Entidades
	* br.com.developer.rest -> Exposição recursos rest.
	* br.com.developer.services ->Negocio
	* br.com.developer.util -> Utilitarios


System Requirements
-------------------


Executando utilizando maven
-------------------

#### Maven

1. Modulo campanha:

			mvn clean package wildfly-swarm:run

2. Modulo socio-torcedor: 

			mvn clean package wildfly-swarm:run
           
### Executando jar.

1. java -jar ..... 



Use JBoss Developer Studio or Eclipse to Run the Quickstarts
------------------------------------------------------------

You can also deploy the quickstarts from Eclipse using JBoss tools. For more information on how to set up Maven and the JBoss tools, see the [Red Hat JBoss Enterprise Application Platform Documentation](https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/) _Getting Started Guide_ and _Development Guide_ or [Get Started with JBoss Developer Studio](http://www.jboss.org/products/devstudio/get-started/ "Get Started with JBoss Developer Studio").


Optional Components
-------------------
The following components are needed for only a small subset of the quickstarts. Do not install or configure them unless the quickstart requires it.

* [Create Users Required by the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CREATE_USERS.md#create-users-required-by-the-quickstarts): Add a Management or Application user for the quickstarts that run in a secured mode.

* [Configure the PostgreSQL Database for Use with the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_POSTGRESQL_EAP7.md#configure-the-postgresql-database-for-use-with-the-quickstarts): The PostgreSQL database is used for the distributed transaction quickstarts.

* [Configure Byteman for Use with the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_BYTEMAN.md#configure-byteman-for-use-with-the-quickstarts): This tool is used to demonstrate crash recovery for distributed transaction quickstarts.



