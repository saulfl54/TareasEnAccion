INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('administrador','$2a$10$MjAHK5yRLoJFeB3.HUQJYOJr/BTSad2GqoN/odx0vC49b2wp6pSwW','administrador', 'uno', 'admin@proyecto.com', '2024-12-01', 1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('antoniojefe','$2a$10$MjAHK5yRLoJFeB3.HUQJYOJr/BTSad2GqoN/odx0vC49b2wp6pSwW','Antonio', 'Pozo', 'saul@proyecto.com', '2022-12-02', 1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('luciajefe','$2a$10$MjAHK5yRLoJFeB3.HUQJYOJr/BTSad2GqoN/odx0vC49b2wp6pSwW','Lucia', 'Bernabeu', 'lucia@proyecto.com', '2022-12-03', 1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('marianocontable','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Mariano', 'Dominguez', 'mar@proyecto.com', '2022-11-10', 1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('beatrizcontable','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Beatriz', 'Bernabeu', 'bea@proyecto.com', '2022-11-11',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('lorenasecretaria','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Lorena', 'Savedra', 'lore@proyecto', '2022-11-11',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('antoniosecretario','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Antonio', 'Martinez', 'anton@proyecto', '2022-11-11',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('mariaadministrativa','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Maria', 'Guzman', 'maria@proyecto', '2022-11-12',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('adolfoadministrativo','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Adolfo', 'de Lera', 'adolfo@proyecto','2022-11-12',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('veroadministrativa','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Veronica', 'Guzman', 'vero@proyecto', '2022-11-12',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('albalimpieza','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Alba', 'Alvarez', 'alba@proyecto', '2022-11-12',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('andreslimpieza','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Andres', 'Barbas', 'andresr@proyecto.com', '2022-11-13',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled) VALUES('vicentacontratacion','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Vicenta', 'Fernandez', 'vicen@proyecto.com', '2022-11-13',1);

INSERT INTO usuarios (username,password, nombre, apellido, email, create_at, enabled ) VALUES('quiquemantenimiento','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','Quique', 'Pozo', 'quin@proyecto', '2022-11-13',1);

INSERT INTO tareas (usuario_id, titulo ,contenido, create_at) VALUES (1,'Crear usuarios','Crear los usuarios en el sistema para todos los empleados de la empresa','2024-12-01');

INSERT INTO tareas (usuario_id, titulo ,contenido, create_at) VALUES (2,'Crear tareas','Crear las tareas en el sistema para todos los empleados de la empresa','2024-12-01');
INSERT INTO tareas (usuario_id, titulo ,contenido, create_at) VALUES (2,'Proporcionar datos','Proporcionar los datos de los empleados al administrador','2024-12-01');

INSERT INTO tareas (usuario_id, titulo ,contenido, create_at) VALUES (3,'Crear tareas','Crear las tareas en el sistema para todos los empleados de la empresa','2024-12-01');
INSERT INTO tareas (usuario_id, titulo ,contenido, create_at) VALUES (3,'Proporcionar datos','Proporcionar los datos de los empleados al administrador','2024-12-01');

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









