
CREATE TABLE IF NOT EXISTS asignatura
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

CREATE TABLE IF NOT EXISTS calendario (
  date VARCHAR(255) NOT NULL,
  type VARCHAR(255),
  day VARCHAR(255),
  week VARCHAR(255),
  comment VARCHAR(255),
  CONSTRAINT pk_calendario PRIMARY KEY (date)
);

CREATE TABLE IF NOT EXISTS usuario
(
    id integer NOT NULL,
    contrasena varchar(100),
    email varchar(100),
    admin boolean,
    PRIMARY KEY (id)
);