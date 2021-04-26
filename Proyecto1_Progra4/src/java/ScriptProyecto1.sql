-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: cursos_db
-- ------------------------------------------------------
-- Server version	8.0.21


drop database if exists cursoslibres;
create database cursoslibres;

use cursoslibres;

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id` varchar(9)  NOT NULL,
  `nombre` varchar(50) NOT NULL ,
  `contrasenna` varchar(16) NOT NULL ,
  `telefono` varchar(8) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `rol` int NOT NULL  ,
  `especialidad` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;


DROP TABLE IF EXISTS `cursos`;


CREATE TABLE `cursos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL ,
  `tematica` varchar(1000),
  `estado` tinyint,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `grupos`;

CREATE TABLE `grupos` (
  `num_grupo` int NOT NULL  AUTO_INCREMENT,
  `id_curso` int DEFAULT NULL,
  `prof_titular` varchar(9) DEFAULT NULL,
  `dias` varchar(9) COLLATE utf8_bin NOT NULL,
  `horario` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`num_grupo`),
  CONSTRAINT `clase_fk` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id`),
  CONSTRAINT `profe_fk` FOREIGN KEY (`prof_titular`) REFERENCES `usuarios` (`id`)
);

DROP TABLE IF EXISTS `matriculas`;

CREATE TABLE `matriculas` (
  `id_grupo` int NOT NULL,
  `id_est` varchar(9) NOT NULL,
  `fec_matricula` date NOT NULL ,
  `calificacion` double DEFAULT NULL,
  PRIMARY KEY (`id_grupo`,`id_est`),
  CONSTRAINT `estmatriculado_fk` FOREIGN KEY (`id_est`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `grupo_fk` FOREIGN KEY (`id_grupo`) REFERENCES `grupos` (`num_grupo`)
);

insert into Usuarios (id,nombre,contrasenna,telefono,email,rol,especialidad) values('1234', 'Pedro Prueba','1234','232323','1234?@mail.com',3,null);
insert into Usuarios (id,nombre,contrasenna,telefono,email,rol,especialidad) values('123456', 'The Tiger','123456','232323','thetiger@mail.com',2,'Tecnologia');
insert into Usuarios (id,nombre,contrasenna,telefono,email,rol,especialidad) values('2222', 'Jose Prueba','2222','3233','1234@mail.com',2,'Artes');
insert into Usuarios (id,nombre,contrasenna,telefono,email,rol,especialidad) values('1111', 'Admin Prueba','1111','3233','1234@mail.com',1,null);
select * from Usuarios;

insert into Cursos(nombre,tematica,estado,precio)values('Fisiologia de Jose','Biologia',1,0);
select * from cursos;
insert into Grupos(id_curso,prof_titular,dias,horario)values(1,'2222','L-J','10:00-12:00');
select * from grupos;
insert into matriculas(id_grupo,id_est,fec_matricula,calificacion)values(1,'1234',SYSDATE(),null);
select * from matriculas;