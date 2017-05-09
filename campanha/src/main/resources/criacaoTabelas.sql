 alter table public.CAMPANHA drop constraint FKhcmlbvh1qhmi0lasl9ncvvvqt;
 drop table if exists public.CAMPANHA cascade;
 drop table if exists public.TIME_CORACAO cascade;
 drop sequence CAMPANHA_ID_SEQ;
 drop sequence TIME_CORACAO_ID_SEQ;

 create table public.CAMPANHA (
     ID int8 not null,
     FIM_VIGENCIA date not null,
     INICIO_VIGENCIA date not null,
     NOME varchar(100) not null,
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

 create sequence CAMPANHA_ID_SEQ start 1 increment 1;
 create sequence TIME_CORACAO_ID_SEQ start 1 increment 1;

 alter table public.CAMPANHA add constraint FKhcmlbvh1qhmi0lasl9ncvvvqt 
 foreign key (timeCoracao) references public.TIME_CORACAO;