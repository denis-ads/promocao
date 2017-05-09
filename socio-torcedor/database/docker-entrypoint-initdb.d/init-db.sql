
-- CREATE DATABASE sociodb
--   WITH OWNER = sociotorcedor
--        ENCODING = 'UTF8'
--        TABLESPACE = pg_default
--        LC_COLLATE = 'en_US.utf8'
--        LC_CTYPE = 'en_US.utf8'
--        CONNECTION LIMIT = -1;
-- GRANT CONNECT, TEMPORARY ON DATABASE sociodb TO public;
-- GRANT ALL ON DATABASE sociodb TO sociotorcedor;


-- CONNECT `sociodb`;


create table public.CAMPANHA (
    ID int8 not null,
    FIM_VIGENCIA date,
    INICIO_VIGENCIA date,
    NOME varchar(100),
    version int4,
    timeCoracao int8,
    primary key (ID)
);

create table public.TIME_CORACAO (
    ID int8 not null,
    NOME varchar(100),
    version int4,
    primary key (ID)
);

create table public.USUARIO (
    ID int8 not null,
    EMAIL varchar(50),
    NASCIMENTO date,
    NOME varchar(100),
    version int4,
    timeCoracao int8,
    primary key (ID)
);

create sequence CAMPANHA_ID_SEQ start 1 increment 1;

create sequence TIME_CORACAO_ID_SEQ start 1 increment 1;

create sequence USUARIO_ID_SEQ start 1 increment 1;

alter table public.CAMPANHA 
    add constraint FKhcmlbvh1qhmi0lasl9ncvvvqt 
    foreign key (timeCoracao) 
    references public.TIME_CORACAO;

alter table public.USUARIO 
    add constraint FKsjx3fhjtarrm24t7b6uhbbi58 
    foreign key (timeCoracao) 
    references public.TIME_CORACAO;
