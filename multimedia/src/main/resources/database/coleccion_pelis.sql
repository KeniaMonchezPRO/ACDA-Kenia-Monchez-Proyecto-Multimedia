-- Creacion del usuario
CREATE USER IF NOT EXISTS 'kenia'@'localhost' IDENTIFIED BY 'abc123.';
GRANT ALL ON coleccion_pelis.* TO 'kenia'@'localhost';

-- Creacion y seleccion de la base de datos
CREATE DATABASE IF NOT EXISTS coleccion_pelis CHARACTER SET utf8;
SET default_storage_engine = InnoDB;
use coleccion_pelis;

-- Establecimiento del motor
set default_storage_engine = InnoDB;

-- Drops:
DROP TABLE IF EXISTS puntuacion;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS pelicula;
DROP TABLE IF EXISTS categoria;

-- Creación de tablas:
CREATE TABLE usuario (
	id int NOT NULL auto_increment,
    usuario varchar(255) unique NOT NULL,
    contrasena varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    nombre varchar(255) NOT NULL,
    apellidos varchar(255) NOT NULL,
    fecha_nac date NOT NULL,
    
    primary key (id)
);

CREATE TABLE categoria (
	id int NOT NULL auto_increment,
    nombre varchar(255) NOT NULL,
    
    primary key (id)
);

CREATE TABLE pelicula ( 
	id int NOT NULL auto_increment,
    titulo varchar(255) NOT NULL,
    estreno date NOT NULL,
    duracion int NOT NULL,
    
    id_categoria int NOT NULL,
    
    primary key (id),
    foreign key (id_categoria) REFERENCES categoria(id)
);

CREATE TABLE puntuacion (
	id int NOT NULL auto_increment,
    puntuacion int NOT NULL,
    
    id_usuario int NOT NULL,
    id_pelicula int NOT NULL,
    
    primary key (id),
    foreign key (id_usuario) REFERENCES usuario(id),
    foreign key (id_pelicula) REFERENCES pelicula(id)
);

-- Inserciones para tabla usuarios:
INSERT INTO usuario
	(usuario, contrasena, email, nombre, apellidos, fecha_nac)
VALUES 
	("La pepita trambolica", "matrix10", "antonia@gmail.com", "Antonia", "Winesmith", '1968-12-17'),
    ("Humberto el valiente", "contrasena", "alexanderG@bellsouth.ue", "Alex", "White", '1999-03-08'),
    ("Baba Yaga", "yaga*10", "babayaga@yahoo.com", "Baba", "Yaga", '2005-10-31');

INSERT INTO categoria
	(nombre)
VALUES 
	("Acción"),
    ("Fantasía"),
    ("Ciencia Ficción");
    
INSERT INTO pelicula
	(titulo, estreno, duracion, id_categoria)
VALUES 
	("Los juegos del hambre: En llamas", '2014-12-17', "145",1),
    ("Harry Poter y la pieda filosofal", '2015-12-17', "145",2),
    ("2001: Una odisea en el espacio", '2016-12-17', "145",3),
    ("Senderos de gloria", '2000-01-20', "145",1);
    


