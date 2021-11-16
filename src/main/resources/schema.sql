
CREATE TABLE asignatura
(
    id integer NOT NULL,
    nombre varchar(100),
    cod_area integer,
    cod_plan integer,
    grupo integer,
    curso varchar(2),
    semestre varchar(2),
    PRIMARY KEY (id)
);