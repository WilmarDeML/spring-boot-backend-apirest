/* Populate tabla regiones */
INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica')
INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica')
INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica')
INSERT INTO regiones (id, nombre) VALUES (4, 'Europa')
INSERT INTO regiones (id, nombre) VALUES (5, 'Asia')
INSERT INTO regiones (id, nombre) VALUES (6, 'Africa')
INSERT INTO regiones (id, nombre) VALUES (7, 'Oceanía')
INSERT INTO regiones (id, nombre) VALUES (8, 'Antártida')

/* Populate tabla clientes */
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Wilmar', 'Zapata', 'wilmar@wmail.wom', '2022-05-21', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Migue', 'Zapata', 'migue@wmail.wom', '2022-06-25', 2);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Isaac', 'Zapata', 'isaac@wmail.wom', '2022-06-11', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Helen', 'Villa', 'helen@wmail.wom', '2022-06-05', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Antonio', 'Zapata', 'antonio@wmail.wom', '2022-06-17', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Manuela', 'Zapata', 'manuela@wmail.wom', '2022-07-13', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Luciana', 'Tobón', 'luciana@wmail.wom', '2022-07-08', 5);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Sandra', 'Gil', 'sandra@wmail.wom', '2022-07-22', 6);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Liliana', 'Londoño', 'liliana@wmail.wom', '2022-04-25', 8);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Michael', 'Zapata', 'michael@wmail.wom', '2022-04-10', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES('Camila', 'Bohórquez', 'camila@wmail.wom', '2022-06-05', 7);