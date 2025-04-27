CREATE DATABASE IF NOT EXISTS `sistema_pinturas` 
DEFAULT CHARACTER SET utf8mb4 
COLLATE utf8mb4_0900_ai_ci;

USE `sistema_pinturas`;

-- =============================================
-- SISTEMA DE USUARIOS ADMINISTRATIVOS
-- =============================================
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `documento` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `esta_activo` bit(1) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `token_contrasena` varchar(255) DEFAULT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nombre_usuario` (`nombre_usuario`),
  UNIQUE KEY `UK_correo` (`correo`)
);

CREATE TABLE `rol` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre_rol` enum('ADMINISTRADOR','VENDEDOR','GERENTE') NOT NULL,
  `esta_activo` boolean DEFAULT true,
  PRIMARY KEY (`id`)
);

CREATE TABLE `usuario_rol` (
  `usuario_id` bigint NOT NULL,
  `rol_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`)
);

-- =============================================
-- SISTEMA DE CLIENTES
-- =============================================
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `telefono` varchar(20),
  `documento_identidad` varchar(20),
  `direccion` text,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `esta_activo` boolean DEFAULT true,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cliente_correo` (`correo`)
);

-- =============================================
-- SISTEMA DE PRODUCTOS
-- =============================================
CREATE TABLE `marca` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tipo_pintura` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
);

CREATE TABLE `producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `marca_id` bigint NOT NULL,
  `tipo_pintura_id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `color` varchar(100) NOT NULL,
  `descripcion` text,
  `foto` BLOB,
  `precio_compra` decimal(10,2),
  `precio_venta_galon` decimal(10,2) NOT NULL,
  `permite_granel` boolean DEFAULT false,
  `precio_medio_galon` decimal(10,2),
  `precio_cuarto_galon` decimal(10,2),
  `precio_octavo_galon` decimal(10,2),
  `precio_dieciseisavo_galon` decimal(10,2),
  `precio_treintaidosavo_galon` decimal(10,2),
  `stock_total` int NOT NULL DEFAULT 0,
  `stock_minimo` int NOT NULL DEFAULT 5,
  `cantidad_cerrados` int NOT NULL DEFAULT 0,
  `cantidad_abiertos` int NOT NULL DEFAULT 0,
  `estante` varchar(50),
  `fila` varchar(50),
  `area` varchar(100),
  `esta_activo` boolean DEFAULT true,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`),
  FOREIGN KEY (`tipo_pintura_id`) REFERENCES `tipo_pintura` (`id`)
);

-- =============================================
-- SISTEMA DE PEDIDOS
-- =============================================
CREATE TABLE `pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_id` bigint NOT NULL,
  `vendedor_id` bigint,
  `fecha_pedido` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `subtotal` decimal(10,2) NOT NULL,
  `igv` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `estado` enum('PENDIENTE', 'EN_PROCESO', 'PREPARADO', 'ENTREGADO', 'CANCELADO') DEFAULT 'PENDIENTE',
  `observaciones` text,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  FOREIGN KEY (`vendedor_id`) REFERENCES `usuario` (`id`)
);

CREATE TABLE `pedido_detalle` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pedido_id` bigint NOT NULL,
  `producto_id` bigint NOT NULL,
  `cantidad` decimal(10,2) NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
);

-- =============================================
-- SISTEMA DE CHATBOT SIMPLE
-- =============================================
CREATE TABLE `chatbot_pregunta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pregunta` varchar(255) NOT NULL,
  `esta_activo` boolean DEFAULT true,
  PRIMARY KEY (`id`)
);

CREATE TABLE `chatbot_respuesta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pregunta_id` bigint NOT NULL,
  `plantilla_respuesta` text NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`pregunta_id`) REFERENCES `chatbot_pregunta` (`id`)
);

