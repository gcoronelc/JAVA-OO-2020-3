Create database db_fisca_exp;

use db_fisca_exp
create table declaraciones (
n_doc_declarante varchar(11) not null foreign key references declarante(n_doc_declarante) ,
n_orden int identity(1000,1) not null primary key,
anio int,
fecha date,
cas107 float,
cas507 float,
cas508 float,
cas111 float,
cas510 float,
cas511 float,
cas512 float,
cas513 float,
cas517 float,
cas120 float,
cas146 float
)

create table declarante
(
n_doc_declarante varchar(11) primary key,
nom_declarante varchar (255),
cell_declarante varchar (9)

)




