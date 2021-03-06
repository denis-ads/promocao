alter table public.CAMPANHA drop constraint FKhcmlbvh1qhmi0lasl9ncvvvqt;
alter table public.USUARIO drop constraint FKsjx3fhjtarrm24t7b6uhbbi58;
drop table if exists public.CAMPANHA cascade

drop table if exists public.TIME_CORACAO cascade;

drop table if exists public.USUARIO cascade;

drop sequence CAMPANHA_ID_SEQ;

drop sequence TIME_CORACAO_ID_SEQ;

drop sequence USUARIO_ID_SEQ;

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
