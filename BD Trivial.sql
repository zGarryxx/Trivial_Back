
/* CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE preguntas (
    id SERIAL PRIMARY KEY,
    categoria_id INT REFERENCES categorias(id) ON DELETE CASCADE,
    pregunta TEXT NOT NULL
);

CREATE TYPE estado AS ENUM ('CORRECTO', 'FALSO');

CREATE TABLE respuestas (
    id SERIAL PRIMARY KEY,
    pregunta_id INT REFERENCES preguntas(id) ON DELETE CASCADE,
    respuesta TEXT NOT NULL,
    estado estado NOT NULL
);

CREATE TABLE puntuacion (
    id SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL,
    categoria_id INT REFERENCES categorias(id) ON DELETE CASCADE,
    aciertos INT DEFAULT 0,
    fallos INT DEFAULT 0,
    puntuacion INT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
); */

-- Insertar categorías
INSERT INTO categorias (nombre) VALUES
                                    ('Lugares Visitados por San Ignacio'),
                                    ('Acontecimientos Clave en la Vida de Ignacio'),
                                    ('Formación y Estudios de Ignacio de Loyola'),
                                    ('La Fundación de la Compañía de Jesús'),
                                    ('Viajes Espirituales y Misioneros de Ignacio');

-- Preguntas de la categoría "Lugares Visitados por San Ignacio"
INSERT INTO preguntas (categoria_id, pregunta) VALUES
                                                   ((SELECT id FROM categorias WHERE nombre = 'Lugares Visitados por San Ignacio'), '¿En qué ciudad nació San Ignacio de Loyola?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Lugares Visitados por San Ignacio'), '¿A qué ciudad fue Ignacio para estudiar?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Lugares Visitados por San Ignacio'), '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Lugares Visitados por San Ignacio'), '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Lugares Visitados por San Ignacio'), '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?');

-- Preguntas de la categoría "Acontecimientos Clave en la Vida de Ignacio"
INSERT INTO preguntas (categoria_id, pregunta) VALUES
                                                   ((SELECT id FROM categorias WHERE nombre = 'Acontecimientos Clave en la Vida de Ignacio'), '¿Qué evento cambió la vida de San Ignacio?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Acontecimientos Clave en la Vida de Ignacio'), '¿En qué batalla fue herido San Ignacio?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Acontecimientos Clave en la Vida de Ignacio'), '¿Qué importante decisión tomó Ignacio después de recuperarse de su herida?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Acontecimientos Clave en la Vida de Ignacio'), '¿Qué experiencia espiritual vivió Ignacio en la cueva de Manresa?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Acontecimientos Clave en la Vida de Ignacio'), '¿Qué escribió Ignacio para ayudar a las personas a rezar y pensar mejor?');

-- Preguntas de la categoría "Formación y Estudios de Ignacio de Loyola"
INSERT INTO preguntas (categoria_id, pregunta) VALUES
                                                   ((SELECT id FROM categorias WHERE nombre = 'Formación y Estudios de Ignacio de Loyola'), '¿Dónde estudió Ignacio antes de convertirse en religioso?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Formación y Estudios de Ignacio de Loyola'), '¿Qué tipo de estudios realizaba Ignacio cuando vivió en París?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Formación y Estudios de Ignacio de Loyola'), '¿Qué idioma aprendió Ignacio para estudiar la Biblia?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Formación y Estudios de Ignacio de Loyola'), '¿En qué universidad estudió Ignacio?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Formación y Estudios de Ignacio de Loyola'), '¿Qué hizo Ignacio para ayudar a los jóvenes a estudiar y aprender sobre Dios?');

-- Preguntas de la categoría "La Fundación de la Compañía de Jesús"
INSERT INTO preguntas (categoria_id, pregunta) VALUES
                                                   ((SELECT id FROM categorias WHERE nombre = 'La Fundación de la Compañía de Jesús'), '¿Cuándo fue fundada la Compañía de Jesús?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'La Fundación de la Compañía de Jesús'), '¿Cuántas personas fundaron la Compañía de Jesús?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'La Fundación de la Compañía de Jesús'), '¿Qué evento importante ocurrió en 1540 relacionado con la Compañía de Jesús?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'La Fundación de la Compañía de Jesús'), '¿Dónde fue aprobada oficialmente la Compañía de Jesús?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'La Fundación de la Compañía de Jesús'), '¿Qué lema adoptó la Compañía de Jesús?');

-- Preguntas de la categoría "Viajes Espirituales y Misioneros de Ignacio"
INSERT INTO preguntas (categoria_id, pregunta) VALUES
                                                   ((SELECT id FROM categorias WHERE nombre = 'Viajes Espirituales y Misioneros de Ignacio'), '¿A qué país viajó Ignacio para realizar su misión en el comienzo de su vida religiosa?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Viajes Espirituales y Misioneros de Ignacio'), '¿Dónde pasó San Ignacio un tiempo de retiro y oración?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Viajes Espirituales y Misioneros de Ignacio'), '¿Qué tipo de trabajo realizó Ignacio durante sus viajes misioneros?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Viajes Espirituales y Misioneros de Ignacio'), '¿Qué país visitó Ignacio para hablar con los reyes y tratar su misión?'),
                                                   ((SELECT id FROM categorias WHERE nombre = 'Viajes Espirituales y Misioneros de Ignacio'), '¿Qué ciudad visitó Ignacio en su viaje hacia Roma?');

-- Respuestas para la categoría "Lugares Visitados por San Ignacio"
INSERT INTO respuestas (id, estado, respuesta, pregunta_id) VALUES
                                                                (1, 1, 'Loyola', (SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad nació San Ignacio de Loyola?')),
                                                                (2, 0, 'Madrid', (SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad nació San Ignacio de Loyola?')),
                                                                (3, 0, 'Barcelona', (SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad nació San Ignacio de Loyola?')),
                                                                (4, 0, 'Sevilla', (SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad nació San Ignacio de Loyola?')),

                                                                (5, 1, 'París', (SELECT id FROM preguntas WHERE pregunta = '¿A qué ciudad fue Ignacio para estudiar?')),
                                                                (6, 0, 'Roma', (SELECT id FROM preguntas WHERE pregunta = '¿A qué ciudad fue Ignacio para estudiar?')),
                                                                (7, 0, 'Lisboa', (SELECT id FROM preguntas WHERE pregunta = '¿A qué ciudad fue Ignacio para estudiar?')),
                                                                (8, 0, 'Granada', (SELECT id FROM preguntas WHERE pregunta = '¿A qué ciudad fue Ignacio para estudiar?')),

                                                                (9, 1, 'Jerusalén', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?')),
                                                                (10, 0, 'Nápoles', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?')),
                                                                (11, 0, 'Roma', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?')),
                                                                (12, 0, 'Venecia', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?')),

                                                                (13, 1, 'Roma', (SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?')),
                                                                (14, 0, 'Madrid', (SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?')),
                                                                (15, 0, 'París', (SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?')),
                                                                (16, 0, 'Barcelona', (SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?')),

                                                                (17, 1, 'Roma', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?')),
                                                                (18, 0, 'Milán', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?')),
                                                                (19, 0, 'Lisboa', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?')),
                                                                (20, 0, 'Jerusalén', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?')),

-- Respuestas para la categoría "Acontecimientos Clave en la Vida de Ignacio"
                                                                (21, 1, 'La herida en Pamplona', (SELECT id FROM preguntas WHERE pregunta = '¿Qué evento cambió la vida de San Ignacio?')),
                                                                (22, 0, 'La batalla de Lepanto', (SELECT id FROM preguntas WHERE pregunta = '¿Qué evento cambió la vida de San Ignacio?')),
                                                                (23, 0, 'La fundación de la Compañía de Jesús', (SELECT id FROM preguntas WHERE pregunta = '¿Qué evento cambió la vida de San Ignacio?')),

                                                                (24, 1, 'La batalla de Pamplona', (SELECT id FROM preguntas WHERE pregunta = '¿En qué batalla fue herido San Ignacio?')),
                                                                (25, 0, 'La batalla de Pavía', (SELECT id FROM preguntas WHERE pregunta = '¿En qué batalla fue herido San Ignacio?')),
                                                                (26, 0, 'La batalla de San Quintín', (SELECT id FROM preguntas WHERE pregunta = '¿En qué batalla fue herido San Ignacio?')),

                                                                (27, 1, 'Se dedicó a la vida religiosa', (SELECT id FROM preguntas WHERE pregunta = '¿Qué importante decisión tomó Ignacio después de recuperarse de su herida?')),
                                                                (28, 0, 'Se retiró a su casa familiar', (SELECT id FROM preguntas WHERE pregunta = '¿Qué importante decisión tomó Ignacio después de recuperarse de su herida?')),
                                                                (29, 0, 'Se dedicó a la política', (SELECT id FROM preguntas WHERE pregunta = '¿Qué importante decisión tomó Ignacio después de recuperarse de su herida?')),

                                                                (30, 1, 'Vivió una profunda experiencia espiritual', (SELECT id FROM preguntas WHERE pregunta = '¿Qué experiencia espiritual vivió Ignacio en la cueva de Manresa?')),
                                                                (31, 0, 'Estudió teología en Manresa', (SELECT id FROM preguntas WHERE pregunta = '¿Qué experiencia espiritual vivió Ignacio en la cueva de Manresa?')),
                                                                (32, 0, 'Escribió su autobiografía', (SELECT id FROM preguntas WHERE pregunta = '¿Qué experiencia espiritual vivió Ignacio en la cueva de Manresa?')),

                                                                (33, 1, 'Los Ejercicios Espirituales', (SELECT id FROM preguntas WHERE pregunta = '¿Qué escribió Ignacio para ayudar a las personas a rezar y pensar mejor?')),
                                                                (34, 0, 'El Catecismo', (SELECT id FROM preguntas WHERE pregunta = '¿Qué escribió Ignacio para ayudar a las personas a rezar y pensar mejor?')),
                                                                (35, 0, 'La autobiografía', (SELECT id FROM preguntas WHERE pregunta = '¿Qué escribió Ignacio para ayudar a las personas a rezar y pensar mejor?')),

-- Respuestas para la categoría "Formación y Estudios de Ignacio de Loyola"
                                                                (36, 1, 'En París', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde estudió Ignacio antes de convertirse en religioso?')),
                                                                (37, 0, 'En Salamanca', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde estudió Ignacio antes de convertirse en religioso?')),
                                                                (38, 0, 'En Valladolid', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde estudió Ignacio antes de convertirse en religioso?')),

                                                                (39, 1, 'Teología y filosofía', (SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de estudios realizaba Ignacio cuando vivió en París?')),
                                                                (40, 0, 'Lenguas clásicas', (SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de estudios realizaba Ignacio cuando vivió en París?')),
                                                                (41, 0, 'Matemáticas', (SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de estudios realizaba Ignacio cuando vivió en París?')),

                                                                (42, 1, 'Latín y griego', (SELECT id FROM preguntas WHERE pregunta = '¿Qué idioma aprendió Ignacio para estudiar la Biblia?')),
                                                                (43, 0, 'Hebreo', (SELECT id FROM preguntas WHERE pregunta = '¿Qué idioma aprendió Ignacio para estudiar la Biblia?')),
                                                                (44, 0, 'Francés', (SELECT id FROM preguntas WHERE pregunta = '¿Qué idioma aprendió Ignacio para estudiar la Biblia?')),

                                                                (45, 1, 'En la Universidad de París', (SELECT id FROM preguntas WHERE pregunta = '¿En qué universidad estudió Ignacio?')),
                                                                (46, 0, 'En la Universidad de Salamanca', (SELECT id FROM preguntas WHERE pregunta = '¿En qué universidad estudió Ignacio?')),
                                                                (47, 0, 'En la Universidad de Bolonia', (SELECT id FROM preguntas WHERE pregunta = '¿En qué universidad estudió Ignacio?')),

                                                                (48, 1, 'Fundó la Compañía de Jesús para ayudar a los jóvenes', (SELECT id FROM preguntas WHERE pregunta = '¿Qué hizo Ignacio para ayudar a los jóvenes a estudiar y aprender sobre Dios?')),
                                                                (49, 0, 'Escribió un libro para educar a los jóvenes', (SELECT id FROM preguntas WHERE pregunta = '¿Qué hizo Ignacio para ayudar a los jóvenes a estudiar y aprender sobre Dios?')),
                                                                (50, 0, 'Organizó retiros espirituales', (SELECT id FROM preguntas WHERE pregunta = '¿Qué hizo Ignacio para ayudar a los jóvenes a estudiar y aprender sobre Dios?')),

-- Respuestas para la categoría "La Fundación de la Compañía de Jesús"
                                                                (51, 1, '1534', (SELECT id FROM preguntas WHERE pregunta = '¿Cuándo fue fundada la Compañía de Jesús?')),
                                                                (52, 0, '1540', (SELECT id FROM preguntas WHERE pregunta = '¿Cuándo fue fundada la Compañía de Jesús?')),
                                                                (53, 0, '1521', (SELECT id FROM preguntas WHERE pregunta = '¿Cuándo fue fundada la Compañía de Jesús?')),

                                                                (54, 1, 'Seis', (SELECT id FROM preguntas WHERE pregunta = '¿Cuántas personas fundaron la Compañía de Jesús?')),
                                                                (55, 0, 'Cuatro', (SELECT id FROM preguntas WHERE pregunta = '¿Cuántas personas fundaron la Compañía de Jesús?')),
                                                                (56, 0, 'Ocho', (SELECT id FROM preguntas WHERE pregunta = '¿Cuántas personas fundaron la Compañía de Jesús?')),

                                                                (57, 1, 'La aprobación papal', (SELECT id FROM preguntas WHERE pregunta = '¿Qué evento importante ocurrió en 1540 relacionado con la Compañía de Jesús?')),
                                                                (58, 0, 'La creación de la primera escuela', (SELECT id FROM preguntas WHERE pregunta = '¿Qué evento importante ocurrió en 1540 relacionado con la Compañía de Jesús?')),
                                                                (59, 0, 'La construcción del primer convento', (SELECT id FROM preguntas WHERE pregunta = '¿Qué evento importante ocurrió en 1540 relacionado con la Compañía de Jesús?')),

                                                                (60, 1, 'En Roma', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde fue aprobada oficialmente la Compañía de Jesús?')),
                                                                (61, 0, 'En París', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde fue aprobada oficialmente la Compañía de Jesús?')),
                                                                (62, 0, 'En Madrid', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde fue aprobada oficialmente la Compañía de Jesús?')),

                                                                (63, 1, 'Ad majorem Dei gloriam (AMDG)', (SELECT id FROM preguntas WHERE pregunta = '¿Qué lema adoptó la Compañía de Jesús?')),
                                                                (64, 0, 'In nomine Dei', (SELECT id FROM preguntas WHERE pregunta = '¿Qué lema adoptó la Compañía de Jesús?')),
                                                                (65, 0, 'Viva Cristo Rey', (SELECT id FROM preguntas WHERE pregunta = '¿Qué lema adoptó la Compañía de Jesús?')),

-- Respuestas para la categoría "Viajes Espirituales y Misioneros de Ignacio"
                                                                (66, 1, 'España', (SELECT id FROM preguntas WHERE pregunta = '¿A qué país viajó Ignacio para realizar su misión en el comienzo de su vida religiosa?')),
                                                                (67, 0, 'Francia', (SELECT id FROM preguntas WHERE pregunta = '¿A qué país viajó Ignacio para realizar su misión en el comienzo de su vida religiosa?')),
                                                                (68, 0, 'Italia', (SELECT id FROM preguntas WHERE pregunta = '¿A qué país viajó Ignacio para realizar su misión en el comienzo de su vida religiosa?')),

                                                                (69, 1, 'En Manresa', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde pasó San Ignacio un tiempo de retiro y oración?')),
                                                                (70, 0, 'En el monasterio de Montserrat', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde pasó San Ignacio un tiempo de retiro y oración?')),
                                                                (71, 0, 'En la cueva de San Pedro', (SELECT id FROM preguntas WHERE pregunta = '¿Dónde pasó San Ignacio un tiempo de retiro y oración?')),

                                                                (72, 1, 'Predicó, enseñó y fundó comunidades religiosas', (SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de trabajo realizó Ignacio durante sus viajes misioneros?')),
                                                                (73, 0, 'Organizó eventos sociales y culturales', (SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de trabajo realizó Ignacio durante sus viajes misioneros?')),
                                                                (74, 0, 'Luchó en batallas religiosas', (SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de trabajo realizó Ignacio durante sus viajes misioneros?')),

                                                                (75, 1, 'Italia', (SELECT id FROM preguntas WHERE pregunta = '¿Qué país visitó Ignacio para hablar con los reyes y tratar su misión?')),
                                                                (76, 0, 'Francia', (SELECT id FROM preguntas WHERE pregunta = '¿Qué país visitó Ignacio para hablar con los reyes y tratar su misión?')),
                                                                (77, 0, 'España', (SELECT id FROM preguntas WHERE pregunta = '¿Qué país visitó Ignacio para hablar con los reyes y tratar su misión?')),

                                                                (78, 1, 'Génova', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio en su viaje hacia Roma?')),
                                                                (79, 0, 'Venecia', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio en su viaje hacia Roma?')),
                                                                (80, 0, 'Milán', (SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio en su viaje hacia Roma?'));

