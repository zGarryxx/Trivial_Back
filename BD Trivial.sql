
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

-- Respuestas para las preguntas de "Lugares Visitados por San Ignacio"
INSERT INTO respuestas (pregunta_id, respuesta, es_correcta) VALUES
((SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad nació San Ignacio de Loyola?'), 'Loyola', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad nació San Ignacio de Loyola?'), 'Madrid', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad nació San Ignacio de Loyola?'), 'Barcelona', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad nació San Ignacio de Loyola?'), 'Sevilla', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿A qué ciudad fue Ignacio para estudiar?'), 'Paris', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿A qué ciudad fue Ignacio para estudiar?'), 'Roma', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿A qué ciudad fue Ignacio para estudiar?'), 'Lisboa', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿A qué ciudad fue Ignacio para estudiar?'), 'Granada', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?'), 'Jerusalén', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?'), 'Nápoles', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?'), 'Roma', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó San Ignacio para peregrinar a Tierra Santa?'), 'Venecia', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?'), 'Roma', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?'), 'Madrid', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?'), 'París', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué ciudad San Ignacio fundó la Compañía de Jesús?'), 'Barcelona', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?'), 'Roma', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?'), 'Milán', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?'), 'Lisboa', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio para tratar de convencer al Papa de su misión?'), 'Jerusalén', FALSE);

-- Respuestas para las preguntas de "Acontecimientos Clave en la Vida de Ignacio"
INSERT INTO respuestas (pregunta_id, respuesta, es_correcta) VALUES
((SELECT id FROM preguntas WHERE pregunta = '¿Qué evento cambió la vida de San Ignacio?'), 'Su herida en la pierna', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué evento cambió la vida de San Ignacio?'), 'Su encuentro con el Papa', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué evento cambió la vida de San Ignacio?'), 'Su viaje a Roma', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué evento cambió la vida de San Ignacio?'), 'Su viaje a Jerusalén', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿En qué batalla fue herido San Ignacio?'), 'En la batalla de Pamplona', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué batalla fue herido San Ignacio?'), 'En la batalla de Lepanto', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué batalla fue herido San Ignacio?'), 'En la batalla de Mühlberg', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué batalla fue herido San Ignacio?'), 'En la batalla de Trafalgar', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué importante decisión tomó Ignacio después de recuperarse de su herida?'), 'Se dedicó a servir a Dios', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué importante decisión tomó Ignacio después de recuperarse de su herida?'), 'Fue a vivir a una ciudad grande', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué importante decisión tomó Ignacio después de recuperarse de su herida?'), 'Se hizo comerciante', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué importante decisión tomó Ignacio después de recuperarse de su herida?'), 'Se dedicó a estudiar ciencias', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué experiencia espiritual vivió Ignacio en la cueva de Manresa?'), 'Tuvo una visión profunda de Dios', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué experiencia espiritual vivió Ignacio en la cueva de Manresa?'), 'Se dedicó a leer libros de historia', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué experiencia espiritual vivió Ignacio en la cueva de Manresa?'), 'Se dedicó a enseñar a los demás', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué experiencia espiritual vivió Ignacio en la cueva de Manresa?'), 'Fue a vivir en el desierto', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué escribió Ignacio para ayudar a las personas a rezar y pensar mejor?'), 'Los Ejercicios Espirituales', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué escribió Ignacio para ayudar a las personas a rezar y pensar mejor?'), 'El Diario de Manresa', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué escribió Ignacio para ayudar a las personas a rezar y pensar mejor?'), 'La Carta al Papa', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué escribió Ignacio para ayudar a las personas a rezar y pensar mejor?'), 'La Vida de Cristo', FALSE);

-- Respuestas para las preguntas de "Formación y Estudios de Ignacio de Loyola"
INSERT INTO respuestas (pregunta_id, respuesta, es_correcta) VALUES
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde estudió Ignacio antes de convertirse en religioso?'), 'En la Universidad de Alcalá', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde estudió Ignacio antes de convertirse en religioso?'), 'En la Universidad de Salamanca', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde estudió Ignacio antes de convertirse en religioso?'), 'En la Universidad de Barcelona', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde estudió Ignacio antes de convertirse en religioso?'), 'En la Universidad de Madrid', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de estudios realizaba Ignacio cuando vivió en París?'), 'Estudió Filosofía y Teología', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de estudios realizaba Ignacio cuando vivió en París?'), 'Estudió Medicina y Derecho', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de estudios realizaba Ignacio cuando vivió en París?'), 'Estudió Matemáticas y Física', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de estudios realizaba Ignacio cuando vivió en París?'), 'Estudió Literatura y Arte', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué idioma aprendió Ignacio para estudiar la Biblia?'), 'Latín', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué idioma aprendió Ignacio para estudiar la Biblia?'), 'Francés', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué idioma aprendió Ignacio para estudiar la Biblia?'), 'Griego', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué idioma aprendió Ignacio para estudiar la Biblia?'), 'Hebreo', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿En qué universidad estudió Ignacio?'), 'En la Universidad de París', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué universidad estudió Ignacio?'), 'En la Universidad de Roma', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué universidad estudió Ignacio?'), 'En la Universidad de Alcalá', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿En qué universidad estudió Ignacio?'), 'En la Universidad de Barcelona', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué hizo Ignacio para ayudar a los jóvenes a estudiar y aprender sobre Dios?'), 'Fundó la Compañía de Jesús', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué hizo Ignacio para ayudar a los jóvenes a estudiar y aprender sobre Dios?'), 'Se dedicó a enseñar en escuelas', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué hizo Ignacio para ayudar a los jóvenes a estudiar y aprender sobre Dios?'), 'Escribió libros de historia', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué hizo Ignacio para ayudar a los jóvenes a estudiar y aprender sobre Dios?'), 'Fundó monasterios', FALSE);

-- Respuestas para las preguntas de "La Fundación de la Compañía de Jesús"
INSERT INTO respuestas (pregunta_id, respuesta, es_correcta) VALUES
((SELECT id FROM preguntas WHERE pregunta = '¿Cuándo fue fundada la Compañía de Jesús?'), '1534', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Cuándo fue fundada la Compañía de Jesús?'), '1500', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Cuándo fue fundada la Compañía de Jesús?'), '1521', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Cuándo fue fundada la Compañía de Jesús?'), '1545', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Cuántas personas fundaron la Compañía de Jesús?'), 'Siete', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Cuántas personas fundaron la Compañía de Jesús?'), 'Tres', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Cuántas personas fundaron la Compañía de Jesús?'), 'Doce', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Cuántas personas fundaron la Compañía de Jesús?'), 'Cinco', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué evento importante ocurrió en 1540 relacionado con la Compañía de Jesús?'), 'Fue aprobada por el Papa Paulo III', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué evento importante ocurrió en 1540 relacionado con la Compañía de Jesús?'), 'Ignacio fue canonizado', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué evento importante ocurrió en 1540 relacionado con la Compañía de Jesús?'), 'Se fundó la Universidad de Lovaina', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué evento importante ocurrió en 1540 relacionado con la Compañía de Jesús?'), 'Se realizaron los Ejercicios Espirituales', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Dónde fue aprobada oficialmente la Compañía de Jesús?'), 'En Roma', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde fue aprobada oficialmente la Compañía de Jesús?'), 'En París', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde fue aprobada oficialmente la Compañía de Jesús?'), 'En Lisboa', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde fue aprobada oficialmente la Compañía de Jesús?'), 'En Madrid', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué lema adoptó la Compañía de Jesús?'), 'Ad Maiorem Dei Gloriam', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué lema adoptó la Compañía de Jesús?'), 'Veni, Vidi, Vici', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué lema adoptó la Compañía de Jesús?'), 'Libertad para todos', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué lema adoptó la Compañía de Jesús?'), 'Cresce y Multiplicaos', FALSE);

-- Respuestas para las preguntas de "Viajes Espirituales y Misioneros de Ignacio"
INSERT INTO respuestas (pregunta_id, respuesta, es_correcta) VALUES
((SELECT id FROM preguntas WHERE pregunta = '¿A qué país viajó Ignacio para realizar su misión en el comienzo de su vida religiosa?'), 'Francia', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿A qué país viajó Ignacio para realizar su misión en el comienzo de su vida religiosa?'), 'Italia', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿A qué país viajó Ignacio para realizar su misión en el comienzo de su vida religiosa?'), 'Portugal', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿A qué país viajó Ignacio para realizar su misión en el comienzo de su vida religiosa?'), 'España', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Dónde pasó San Ignacio un tiempo de retiro y oración?'), 'En la cueva de Manresa', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde pasó San Ignacio un tiempo de retiro y oración?'), 'En el monasterio de Montserrat', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde pasó San Ignacio un tiempo de retiro y oración?'), 'En la iglesia de San Pedro', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Dónde pasó San Ignacio un tiempo de retiro y oración?'), 'En el convento de San Juan', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de trabajo realizó Ignacio durante sus viajes misioneros?'), 'Predicó y formó nuevos seguidores', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de trabajo realizó Ignacio durante sus viajes misioneros?'), 'Repartió riquezas a los pobres', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de trabajo realizó Ignacio durante sus viajes misioneros?'), 'Estudió ciencia y astronomía', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué tipo de trabajo realizó Ignacio durante sus viajes misioneros?'), 'Construyó iglesias', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué país visitó Ignacio para hablar con los reyes y tratar su misión?'), 'Italia', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué país visitó Ignacio para hablar con los reyes y tratar su misión?'), 'Francia', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué país visitó Ignacio para hablar con los reyes y tratar su misión?'), 'España', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué país visitó Ignacio para hablar con los reyes y tratar su misión?'), 'Portugal', FALSE),

((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio en su viaje hacia Roma?'), 'Florencia', TRUE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio en su viaje hacia Roma?'), 'Milán', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio en su viaje hacia Roma?'), 'Venecia', FALSE),
((SELECT id FROM preguntas WHERE pregunta = '¿Qué ciudad visitó Ignacio en su viaje hacia Roma?'), 'Génova', FALSE);

