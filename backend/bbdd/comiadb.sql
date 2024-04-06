-- Creación de tabla Usuarios con fechas de creación y baja
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_baja TIMESTAMP NULL
);

-- Creación de tabla Autoridades para gestionar roles de usuarios
CREATE TABLE Autoridades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES Usuarios(username)
);

-- Creación de tabla Perfiles para almacenar preferencias y alergias
CREATE TABLE Perfiles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    preferencias TEXT,
    alergias TEXT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

-- Creación de tabla Ingredientes
CREATE TABLE Ingredientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100)
);

-- Creación de tabla NeveraVirtual para gestionar ingredientes de usuarios
CREATE TABLE NeveraVirtual (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    ingrediente_id INT NOT NULL,
    cantidad DECIMAL(10,2),
    unidad_medida VARCHAR(50),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (ingrediente_id) REFERENCES Ingredientes(id)
);

-- Creación de tabla Recetas
CREATE TABLE Recetas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    instrucciones TEXT,
    tiempo_preparacion INT,
    dificultad VARCHAR(50)
);

-- Creación de tabla IngredientesReceta para relacionar ingredientes con recetas
CREATE TABLE IngredientesReceta (
    receta_id INT NOT NULL,
    ingrediente_id INT NOT NULL,
    cantidad DECIMAL(10,2),
    unidad_medida VARCHAR(50),
    PRIMARY KEY (receta_id, ingrediente_id),
    FOREIGN KEY (receta_id) REFERENCES Recetas(id),
    FOREIGN KEY (ingrediente_id) REFERENCES Ingredientes(id)
);

-- Creación de tabla HistorialRecetas para registrar recetas generadas por usuarios
CREATE TABLE HistorialRecetas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    receta_id INT NOT NULL,
    fecha_generacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (receta_id) REFERENCES Recetas(id)
);