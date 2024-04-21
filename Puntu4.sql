-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS PUNTU4;

-- Usar la base de datos creada
USE PUNTU4;

-- Crear la tabla NOTAS
CREATE TABLE IF NOT EXISTS NOTAS (
    codigo_matricula INT PRIMARY KEY,
    nombre_asignatura VARCHAR(50),
    nota1 FLOAT,
    nota2 FLOAT
);
