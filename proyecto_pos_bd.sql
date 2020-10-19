CREATE DATABASE proyecto_pos;
USE proyecto_pos;

CREATE TABLE productos (
id_producto INT AUTO_INCREMENT,
nombre VARCHAR (70),
precio VARCHAR (70),
descripcion VARCHAR (70),
constraint pk_id_producto primary key (id_producto)
);