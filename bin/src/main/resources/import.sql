
INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('administrador','$2a$10$MjAHK5yRLoJFeB3.HUQJYOJr/BTSad2GqoN/odx0vC49b2wp6pSwW','administrador', 'administrador', 'admin@proyecto.com', '2022-11-10', 1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('saulPrensa','$2a$10$MjAHK5yRLoJFeB3.HUQJYOJr/BTSad2GqoN/odx0vC49b2wp6pSwW','Saúl', 'Fernández', 'saul@proyecto.com', '2022-11-10', 1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('luciaMarketing','$2a$10$MjAHK5yRLoJFeB3.HUQJYOJr/BTSad2GqoN/odx0vC49b2wp6pSwW','Lucia', 'Bernabeu', 'lucia@proyecto.com', '2022-11-10', 1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('marianoDom','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Mariano', 'Dominguez', 'mar@proyecto.com', '2022-11-10', 1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('beatrizBer','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Beatriz', 'Bernabeu', 'bea@proyecto.com', '2022-11-11',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('lorenaSav','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Lorena', 'Savedra', 'lore@proyecto', '2022-11-11',1);

INSERT INTO usuarios (username,password, nombre, apellido, email,create_at, enabled ) VALUES('antonioMar','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Antonio', 'Martinez', 'anton@proyecto', '2022-11-11',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('mariaGuz','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','María', 'Guzman', 'maria@proyecto', '2022-11-12',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('adolfoLer','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Adolfo', 'de Lera', 'adolfo@proyecto','2022-11-12',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('veroGuz','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Veronica', 'Guzman', 'vero@proyecto', '2022-11-12',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('albaAlv','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Alba', 'Alvarez', 'alba@proyecto', '2022-11-12',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('andresBar','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Andres', 'Barbas', 'andresr@proyecto.com', '2022-11-13',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('vicentaFer','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Vicenta', 'Fernández', 'vicen@proyecto.com', '2022-11-13',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('quiquePoz','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Quique', 'Pozo', 'quin@proyecto', '2022-11-13',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('joseRio','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','José', 'Rios', 'jose@proyecto', '2022-11-14',1);

INSERT INTO usuarios (username,password, nombre, apellido, email , create_at, enabled) VALUES('joaquinaTol','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Joaquina', 'Toledanao', 'joa@proyecto', '2022-11-15',1);

INSERT INTO tareas (usuario_id, titulo ,contenido, create_at) VALUES (16,'Bienvenida Joaquina','Joaquina se incorpora a la empresa como junior developer, bienvenida Joaquina','2022-11-15');



INSERT INTO tareas (usuario_id, titulo ,contenido, create_at) VALUES (9,'Nuevo record de ventas mensual','nuestro comercial Adolfo de Lera finaliza el mes con 876 puntos, nuevo record mensual! ','2022-11-30');



INSERT INTO tareas (usuario_id, titulo ,contenido, create_at) VALUES (2,'Mañana no es día laboral','Se recuerda a los empleados que mañana 8 de Diciembre, la empresa cumple 50 años desde su inauguración, no se trabaja, disfrutar del día libre!!!','2022-12-08');



INSERT INTO roles (nombre, role) VALUES ('Usuario','ROLE_USER');
INSERT INTO roles (nombre, role) VALUES ('Usuario avanzado','ROLE_ADVANCED_USER');
INSERT INTO roles (nombre, role) VALUES ('Administrador','ROLE_ADMIN');



INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (1,2);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (1,3);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (2,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (3,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (3,2);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (4,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (5,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (6,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (7,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (8,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (9,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (10,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (11,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (12,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (13,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (14,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (15,1);
INSERT INTO usuarios_roles (usuario_id, roles_id) VALUES (16,1);



