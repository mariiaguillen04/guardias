INSERT INTO anexo (id_usuario, comentario, fecha_ausencia, justificante) VALUES
(1, 'Ausencia por consulta médica', '2025-04-15', 'https://i.imgur.com/ejemplo1.jpg'),
(2, 'Asuntos personales', '2025-04-16', 'https://i.imgur.com/ejemplo2.jpg'),
(3, 'Asistencia a formación externa', '2025-04-17', 'https://i.imgur.com/ejemplo3.jpg'),
(4, 'Fallecimiento de familiar', '2025-04-18', 'https://i.imgur.com/ejemplo4.jpg'),
(5, 'No especificado aún', '2025-04-19', 'https://i.imgur.com/ejemplo5.jpg');

INSERT INTO falta (id_usuario, fecha, tarea) VALUES
(1, '2025-04-15', 'Sustituir clase de Matemáticas 3ºB'),
(2, '2025-04-16', 'Cubrir guardia en recreo'),
(3, '2025-04-17', 'Atención en sala de profesores por incidencia'),
(4, '2025-04-18', 'Vigilar examen de Historia 4ºC'),
(5, '2025-04-19', 'Sustitución de tutoría en aula 1.3');

INSERT INTO rol (rol, id_usuario) VALUES
('DIRECTORA', 1),
('SUBDIRECTORA', 2),
('JEFAESTUDIOS', 3),
('PROFESOR', 4),
('PROFESOR', 5);

INSERT INTO tramo (id_usuario, hora, fecha, curso, aula, id_falta) VALUES
(1, 'PRIMERA', '2025-04-21', '3º ESO A', 'Aula 11', 1),
(2, 'SEGUNDA', '2025-04-21', '2º ESO B', 'Aula 6', 1),
(3, 'TERCERA', '2025-04-22', '4º ESO C', 'Aula 14', 2),
(4, 'CUARTA', '2025-04-22', '1º Bach A', 'Aula 3', 2),
(5, 'QUINTA', '2025-04-23', '1º ESO D', 'Aula 1', 3),
(1, 'SEXTA', '2025-04-23', '2º Bach B', 'Aula 5', 3);

INSERT INTO usuario (id, nombre_usuario, password, email) VALUES
(1, 'carmen.directora', 'hashedpass1', 'carmen@iesalcores.com'),
(2, 'juan.profesor', 'hashedpass2', 'juan@iesalcores.com'),
(3, 'marta.subdirectora', 'hashedpass3', 'marta@iesalcores.com'),
(4, 'luis.jefatura', 'hashedpass4', 'luis@iesalcores.com'),
(5, 'ana.profesor', 'hashedpass5', 'ana@iesalcores.com');
