Ciudad
INSERT INTO Ciudad (nombre) VALUES ('Ciudad A');
INSERT INTO Ciudad (nombre) VALUES ('Ciudad B');
INSERT INTO Ciudad (nombre) VALUES ('Ciudad C');


Refugio
INSERT INTO Refugio (nombre, id_ciudad) VALUES ('Refugio A', 1);
INSERT INTO Refugio (nombre, id_ciudad) VALUES ('Refugio B', 2);
INSERT INTO Refugio (nombre, id_ciudad) VALUES ('Refugio C', 1);

Persona
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Ana', 'Rodríguez', 'socio');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Carlos', 'Sánchez', 'voluntario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Sofía', 'Fernández', 'socio');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Diego', 'Gómez', 'voluntario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Elena', 'Díaz', 'socio');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Andrés', 'Vázquez', 'voluntario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Lucía', 'Alvarez', 'socio');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Mario', 'Hernández', 'voluntario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Sara', 'Muñoz', 'socio');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Javier', 'Romero', 'voluntario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Laura', 'Pérez', 'director');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Roberto', 'López', 'director');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Jefferson', 'López', 'socio');

tipo_cuota
INSERT INTO Tipo_cuota (nombre, cuota) VALUES ('Mínima', 10);
INSERT INTO Tipo_cuota (nombre, cuota) VALUES ('Media', 20);
INSERT INTO Tipo_cuota (nombre, cuota) VALUES ('Máxima', 30);

Sede
INSERT INTO Sede (direccion, id_director, id_ciudad) VALUES ('Dirección Sede 1', 11, 1);
INSERT INTO Sede (direccion, id_director, id_ciudad) VALUES ('Dirección Sede 2', 12, 2);


Socio
INSERT INTO Socio (cuenta, fecha_pago, id_sede, id_persona, id_tipo_cuota) VALUES ('Cuenta Socio 1', '2024-01-01', 1, 1, 1);
INSERT INTO Socio (cuenta, fecha_pago, id_sede, id_persona, id_tipo_cuota) VALUES ('Cuenta Socio 2', '2024-01-01', 2, 3, 2);
INSERT INTO Socio (cuenta, fecha_pago, id_sede, id_persona, id_tipo_cuota) VALUES ('Cuenta Socio 3', '2024-01-01', 1, 5, 3);
INSERT INTO Socio (cuenta, fecha_pago, id_sede, id_persona, id_tipo_cuota) VALUES ('Cuenta Socio 4', '2024-01-01', 2, 7, 1);
INSERT INTO Socio (cuenta, fecha_pago, id_sede, id_persona, id_tipo_cuota) VALUES ('Cuenta Socio 5', '2024-01-01', 1, 9, 2);


Profesion
INSERT INTO Profesion (nombre) VALUES ('Doctor');
INSERT INTO Profesion (nombre) VALUES ('Enfermero');
INSERT INTO Profesion (nombre) VALUES ('Ingeniero');
INSERT INTO Profesion (nombre) VALUES ('Maestro');
INSERT INTO Profesion (nombre) VALUES ('Abogado');


Voluntario
INSERT INTO Voluntario (tipo, disponibilidad, num_trabajos, id_sede, id_persona, id_profesion) VALUES ('SANITARIO', 'DISPONIBLE', 3, 1, 2, 1);
INSERT INTO Voluntario (tipo, disponibilidad, num_trabajos, id_sede, id_persona, id_profesion) VALUES ('SANITARIO ', 'DISPONIBLE', 2, 2, 4, 2);
INSERT INTO Voluntario (tipo, disponibilidad, num_trabajos, id_sede, id_persona, id_profesion) VALUES ('ADMINISTRATIVO', NULL, NULL, 1, 6, NULL);
INSERT INTO Voluntario (tipo, disponibilidad, num_trabajos, id_sede, id_persona, id_profesion) VALUES ('SANITARIO ', 'NO DISPONIBLE', 4, 2, 8, 1);
INSERT INTO Voluntario (tipo, disponibilidad, num_trabajos, id_sede, id_persona, id_profesion) VALUES ('ADMINISTRATIVO', NULL, NULL, 1, 10, NULL);

Envio
INSERT INTO Envio (fecha_salida, codigo, id_refugio) VALUES ('2024-04-01', 'COD001', 1);
INSERT INTO Envio (fecha_salida, codigo, id_refugio) VALUES ('2024-04-02', 'COD002', 2);
INSERT INTO Envio (fecha_salida, codigo, id_refugio) VALUES ('2024-04-03', 'COD003', 3);
INSERT INTO Envio (fecha_salida, codigo, id_refugio) VALUES ('2024-04-04', 'COD004', 1);
INSERT INTO Envio (fecha_salida, codigo, id_refugio) VALUES ('2024-04-05', 'COD005', 2);


Envio_x_Sede
INSERT INTO Envio_x_Sede (id_sede, id_envio) VALUES (1, 1);
INSERT INTO Envio_x_Sede (id_sede, id_envio) VALUES (2, 2);
INSERT INTO Envio_x_Sede (id_sede, id_envio) VALUES (1, 3);
INSERT INTO Envio_x_Sede (id_sede, id_envio) VALUES (2, 4);
INSERT INTO Envio_x_Sede (id_sede, id_envio) VALUES (1, 5);


Ayuda_Material
INSERT INTO Ayuda_Material (tipo, cantidad, descripcion, id_envio_sede) VALUES ('Medicamentos', '100 unidades', 'Analgésicos', 1);
INSERT INTO Ayuda_Material (tipo, cantidad, descripcion, id_envio_sede) VALUES ('Alimentos', '50 kg', 'Arroz', 2);
INSERT INTO Ayuda_Material (tipo, cantidad, descripcion, id_envio_sede) VALUES ('Alimentos', '30 kg', 'Frijoles', 2);
INSERT INTO Ayuda_Material (tipo, cantidad, descripcion, id_envio_sede) VALUES ('Medicamentos', '50 unidades', 'Antibióticos', 3);
INSERT INTO Ayuda_Material (tipo, cantidad, descripcion, id_envio_sede) VALUES ('Alimentos', '20 kg', 'Harina de maíz', 4);

Ayuda_Humanitaria
INSERT INTO Ayuda_Humanitaria (num_voluntarios, id_envio_sede, id_profesion) VALUES (10, 1, 1);
INSERT INTO Ayuda_Humanitaria (num_voluntarios, id_envio_sede, id_profesion) VALUES (15, 2, 2);
INSERT INTO Ayuda_Humanitaria (num_voluntarios, id_envio_sede, id_profesion) VALUES (20, 3, 3);
INSERT INTO Ayuda_Humanitaria (num_voluntarios, id_envio_sede, id_profesion) VALUES (8, 4, 2);
INSERT INTO Ayuda_Humanitaria (num_voluntarios, id_envio_sede, id_profesion) VALUES (12, 5, 1);


User
INSERT INTO users (email, pwd) VALUES ('account@debuggeandoieas.com', 'to_be_encoded');
INSERT INTO users (email, pwd) VALUES ('cards@debuggeandoieas.com', 'to_be_encoded');
INSERT INTO users (email, pwd) VALUES ('loans@debuggeandoieas.com', 'to_be_encoded');



Roles
INSERT INTO roles (role_name, description, id_user) VALUES ('ROLE_ADMIN', 'cant view account endpoint', 1);   
INSERT INTO roles (role_name, description, id_user) VALUES ('ROLE_DIRECTOR', 'cant view cards endpoint', 2); 
INSERT INTO roles (role_name, description, id_user) VALUES ('ROLE_AUXILIAR', 'cant view account endpoint', 3); 
