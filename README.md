Promoção 
====================
Exemplo que demonstra o uso tecnologia Java EE 7 (JPA 2, CDI, EJB 3.1 and JAX-RS) com wildfly swarm.

Docker Multi-stage Build, disponivel na versão 17.05 (não ficou pronto).  

Introdução
------------

Esse projeto é dividido em 2 modulos, Campanha e Socio Torcedor.


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

Monitoração
---------------------

 * [Campanha](http://localhost:8082/campanha/monitoring)
 
 * [Socio Torcedor](http://localhost:8080/socio-torcedor/monitoring)
  

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

1. Desenvolvimento utilizando Fedora 24.
2. Eclipse Neon 4.6.3.
3. WildflySwarm 2017.5.0 - http://wildfly-swarm.io/
4. Banco de dados Postgresql. 9.6
5. Docker Multi-stage Build, disponivel na versão 17.05 (não ficou pronto). (https://github.com/docker-library/docker)  
6. Docker Compose versão 1.13.0 (https://github.com/docker/compose/releases/tag/1.13.0)

Executando com maven
-------------------
#### Banco de dados.

1. Criar banco de dados campanha:

	su postgres
	psql template1
	template1=# CREATE USER campanha WITH PASSWORD 'campanha@1';
	template1=# CREATE DATABASE Campanhadb;
	template1=# GRANT ALL PRIVILEGES ON DATABASE campanhadb to campanha;
	template1=# \q
	
	su postgres
	bash-4.3$ psql campanhadb


2. Criar banco de dados socio-torcedor:

	su postgres
	psql template1
	template1=# CREATE USER sociotorcedor WITH PASSWORD 'socio@1';
	template1=# CREATE DATABASE sociodb;
	template1=# GRANT ALL PRIVILEGES ON DATABASE sociodb to sociotorcedor;
	template1=# \q
	bash-4.3$ psql sociodb


#### Maven

1. Modulo campanha:

			mvn clean package wildfly-swarm:run

2. Modulo socio-torcedor: 

			mvn clean package wildfly-swarm:run
           
### Executando fat jar.

1. java -jar ..... 

