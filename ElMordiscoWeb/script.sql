CREATE DATABASE el_mordisco;

USE el_mordisco;

CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    categoria VARCHAR(50),
    precio DECIMAL(10,2),
    stock INT
);

INSERT INTO producto (nombre, categoria, precio, stock) VALUES
('Hamburguesa Clásica', 'Hamburguesas', 18000, 20),
('Perro Especial', 'Perros Calientes', 15000, 15),
('Papas Fritas', 'Acompañamientos', 8000, 30);