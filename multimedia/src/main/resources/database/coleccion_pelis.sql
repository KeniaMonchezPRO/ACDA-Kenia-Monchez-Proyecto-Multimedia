-- Creacion del usuario
CREATE USER IF NOT EXISTS 'kenia'@'localhost' IDENTIFIED BY 'abc123.';
GRANT ALL ON huellas.* TO 'kenia'@'localhost';

-- Creacion y seleccion de la base de datos
CREATE DATABASE IF NOT EXISTS coleccion_pelis CHARACTER SET utf8;
SET default_storage_engine = InnoDB;
use coleccion_pelis;

-- Establecimiento del motor
set default_storage_engine = InnoDB;

-- Drops:
DROP TABLE IF EXISTS director;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS pelicula;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS puntuacion;
DROP TABLE IF EXISTS actor;


-- Creación de tablas:
CREATE TABLE categoria (
	id int NOT NULL auto_increment,
    nombre varchar(255) NOT NULL,
    
    primary key (id)
);

CREATE TABLE director (
	id int NOT NULL auto_increment,
    nombre varchar(255) NOT NULL,
    apellidos varchar(255) NOT NULL,
    nacionalidad varchar(255) NOT NULL,
    fecha_nac date NOT NULL,
    id_pelicula int NOT NULL,
    
    primary key (id),
    foreign key (id_pelicula) REFERENCES pelicula(id)
);

CREATE TABLE pelicula ( 
	id int auto_increment,
    titulo varchar(255) NOT NULL,
    estreno date NOT NULL,
    duracion int NOT NULL,
    fecha_nac date NOT NULL,
    
    id_director int NOT NULL, 
    
    primary key (id),
    foreign key (id_director) REFERENCES director(id) /*FALLA AQUI PORQUE REFERENCIA A UNA TABLA QUE AUN NO ESTA CREADA, POR ESO LO CAMBIAMOS A LA PARTE DEL PRINCIPIO, ANTES ESTABA AL FINAL CON EL MYISAM ENGINE*/
);

-- Inserciones
/*INSERT INTO empresa 
	(cif, nombre, direccion) si metemos todas las columnas esto no haria falta
VALUES 
	("A1234567", "Apple Store", "Rúa Milagrosa 123, Lugo");

-- Inserciones para tabla persona:
INSERT INTO persona 
	(dni, nombre, apellidos, fecha_nac, id_empresa)
VALUES 
	("7894561X", "Julián", "Ferreiro Brazas",'1968-12-17',1),
    ("1245875Y", "Amelia", "Castaneda Cervantes",'1988-02-02',1),
    ("9874125Z", "John", "Stone Smith",'1990-10-25',1);*/

