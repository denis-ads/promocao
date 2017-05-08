



postgres@developer ~] $  psql template1
psql (9.4.5)
Type "help" for help.

template1=# CREATE USER campanha WITH PASSWORD 'campanha@1';
CREATE ROLE
template1=# CREATE DATABASE CampanhaDB;
CREATE DATABASE
template1=# GRANT ALL PRIVILEGES ON DATABASE CampanhaDB to campanha
template1-# ;
GRANT
template1=# \q

su postgres
bash-4.3$ psql campanhadb
psql (9.4.5)
Type "help" for help.
