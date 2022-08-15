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

/* Populate tabla usuarios */
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Wilmar', '$2a$10$pJ69JCQn.Lzk2r4zX9Az5uZFv7Fiu/igJZmpdisuDUEh6RJT1JQC6', 1, 'Wilmar', 'Zapata', 'wilmar@wmail.wom')
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$HWeiHhblE8Y4Cb.uCz4cNOPl2uL75SFyiXpvpiWxLZ56GxWWuV8aG', 1, 'Sandra', 'Gil', 'sandra@smail.som')

/* Populate tabla roles */
INSERT INTO roles (nombre) VALUES ('ROLE_USER')
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN')

/* Populate tabla intermedia usuarios_roles */
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 1)
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 2)
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 1)
