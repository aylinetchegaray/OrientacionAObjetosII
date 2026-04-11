--Base de Datos para el Restaurante
CREATE DATABASE IF NOT EXISTS restaurante;
USE restaurante;

CREATE TABLE IF NOT EXISTS ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha VARCHAR(15),
    monto DOUBLE
    );


-- 2. Base de Datos para el Concurso
CREATE DATABASE IF NOT EXISTS concursos;
USE concursos;

CREATE TABLE IF NOT EXISTS inscripciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora VARCHAR(15),
    id_participante INT,
    id_concurso INT
    );