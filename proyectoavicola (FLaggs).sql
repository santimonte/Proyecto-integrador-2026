-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-10-2026 a las 16:49:57
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoavicola`
--
CREATE DATABASE IF NOT EXISTS `proyectoavicola` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `proyectoavicola`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `actualizar`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar` (IN `p_tabla_destino` VARCHAR(50), IN `p_id` INT, IN `p_capacidad` INT, IN `p_estado_cadena_frio` VARCHAR(50), IN `p_id_usuario` INT, IN `p_telefono_cliente` VARCHAR(50), IN `p_id_lote` INT, IN `p_id_usuario_veterinario` INT, IN `p_fecha_control` DATETIME, IN `p_cantidad_bajas` INT, IN `p_observaciones_veterinarias` VARCHAR(255), IN `p_id_venta_detalle` INT, IN `p_id_producto_detalle` INT, IN `p_cantidad_detalle` INT, IN `p_precio_unitario` DECIMAL(10,2), IN `p_id_lote_faena` INT, IN `p_fecha_faena` DATETIME, IN `p_cantidad_aves_faenadas` INT, IN `p_peso_total_obtenido` DECIMAL(10,2), IN `p_nombre_galpon` VARCHAR(100), IN `p_capacidad_maxima` VARCHAR(50), IN `p_id_proveedor_insumo` INT, IN `p_descripcion_insumo` VARCHAR(200), IN `p_cantidad_insumo` INT, IN `p_fecha_compra` DATETIME, IN `p_id_galpon_lote` INT, IN `p_fecha_ingreso` DATETIME, IN `p_cantidad_inicial` INT, IN `p_raza_genetica` VARCHAR(100), IN `p_id_sensor_medicion` INT, IN `p_valor_registrado` DECIMAL(10,2), IN `p_fecha_medicion` DATETIME, IN `p_id_camara_prod` INT, IN `p_nombre_producto` VARCHAR(100), IN `p_stock_disponible` INT, IN `p_fecha_envasado` DATETIME, IN `p_telefono_proveedor` VARCHAR(50), IN `p_nombre_rol` VARCHAR(50), IN `p_tipo_sensor` VARCHAR(50), IN `p_ubicacion` VARCHAR(100), IN `p_email` VARCHAR(150), IN `p_contrasena` VARCHAR(255), IN `p_id_rol` INT, IN `p_id_cliente_venta` INT, IN `p_fecha_venta` DATETIME, IN `p_total_facturado` DECIMAL(12,2))   BEGIN

    IF p_tabla_destino = 'camaras_frigorificas' THEN
        UPDATE camaras_frigorificas 
        SET capacidad = p_capacidad, 
            estado_cadena_frio = p_estado_cadena_frio 
        WHERE id_camara = p_id;

    ELSEIF p_tabla_destino = 'clientes' THEN
        UPDATE clientes 
        SET id_usuario = p_id_usuario, 
            telefono = p_telefono_cliente 
        WHERE id_cliente = p_id;

    ELSEIF p_tabla_destino = 'controles_sanidad' THEN
        UPDATE controles_sanidad 
        SET id_lote = p_id_lote, 
            id_usuario_veterinario = p_id_usuario_veterinario, 
            fecha_control = p_fecha_control, 
            cantidad_bajas = p_cantidad_bajas, 
            observaciones_veterinarias = p_observaciones_veterinarias 
        WHERE id_control = p_id;

    ELSEIF p_tabla_destino = 'detalles_ventas' THEN
        UPDATE detalles_ventas 
        SET id_venta = p_id_venta_detalle, 
            id_producto = p_id_producto_detalle, 
            cantidad = p_cantidad_detalle, 
            precio_unitario = p_precio_unitario 
        WHERE id_detalle_venta = p_id;

    ELSEIF p_tabla_destino = 'faenas' THEN
        UPDATE faenas 
        SET id_lote = p_id_lote_faena, 
            fecha_faena = p_fecha_faena, 
            cantidad_aves_faenadas = p_cantidad_aves_faenadas, 
            peso_total_obtenido = p_peso_total_obtenido 
        WHERE id_faena = p_id;

    ELSEIF p_tabla_destino = 'galpones' THEN
        UPDATE galpones 
        SET nombre_galpon = p_nombre_galpon, 
            capacidad_maxima = p_capacidad_maxima 
        WHERE id_galpon = p_id;

    ELSEIF p_tabla_destino = 'insumos_compras' THEN
        UPDATE insumos_compras 
        SET id_proveedor = p_id_proveedor_insumo, 
            descripcion_insumo = p_descripcion_insumo, 
            cantidad = p_cantidad_insumo, 
            fecha_compra = p_fecha_compra 
        WHERE id_compra = p_id;

    ELSEIF p_tabla_destino = 'lotes_aves' THEN
        UPDATE lotes_aves 
        SET id_galpon = p_id_galpon_lote, 
            fecha_ingreso = p_fecha_ingreso, 
            cantidad_inicial = p_cantidad_inicial, 
            raza_genetica = p_raza_genetica 
        WHERE id_lote = p_id;

    ELSEIF p_tabla_destino = 'medicion_sensores' THEN
        UPDATE medicion_sensores 
        SET id_sensor = p_id_sensor_medicion, 
            valor_registrado = p_valor_registrado, 
            fecha_medicion = p_fecha_medicion 
        WHERE id_medicion = p_id;

    ELSEIF p_tabla_destino = 'productos_stock' THEN
        UPDATE productos_stock 
        SET id_camara = p_id_camara_prod, 
            nombre_producto = p_nombre_producto, 
            stock_disponible = p_stock_disponible, 
            fecha_envasado = p_fecha_envasado 
        WHERE id_producto = p_id;

    ELSEIF p_tabla_destino = 'proveedores' THEN
        UPDATE proveedores 
        SET telefono = p_telefono_proveedor 
        WHERE id_proveedor = p_id;

    ELSEIF p_tabla_destino = 'roles' THEN
        UPDATE roles 
        SET nombre_rol = p_nombre_rol 
        WHERE id_rol = p_id;

    ELSEIF p_tabla_destino = 'sensores' THEN
        UPDATE sensores 
        SET tipo_sensor = p_tipo_sensor, 
            ubicacion = p_ubicacion 
        WHERE id_sensor = p_id;

    ELSEIF p_tabla_destino = 'usuarios' THEN
        UPDATE usuarios 
        SET email = p_email, 
            contrasena = p_contrasena, 
            id_rol = p_id_rol 
        WHERE id_usuario = p_id;

    ELSEIF p_tabla_destino = 'ventas' THEN
        UPDATE ventas 
        SET id_cliente = p_id_cliente_venta, 
            fecha_venta = p_fecha_venta, 
            total_facturado = p_total_facturado 
        WHERE id_venta = p_id;
        
    ELSE
        SIGNAL SQLSTATE  '45000'  SET MESSAGE_TEXT =  'Error: El nombre de la tabla especificada no es válido.' ;
    END IF;

END$$

DROP PROCEDURE IF EXISTS `Buscar_por_id`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Buscar_por_id` (IN `p_id_camara` INT, IN `p_id_cliente` INT, IN `p_id_control` INT, IN `p_id_detalle_venta` INT, IN `p_id_faena` INT, IN `p_id_galpon` INT, IN `p_id_insumos_compras` INT, IN `p_lote` INT, IN `p_id_medicion` INT, IN `p_id_productos` INT, IN `p_id_proveedor` INT, IN `p_id_rol` INT, IN `p_id_sensor` INT, IN `p_id_usuario` INT, IN `p_id_venta` INT)   BEGIN 

    -- Búsquedas Maestras: Si están ocultos (activo = 0), no va a devolver filas
    SELECT * FROM camaras_frigorificas WHERE id_camaras = p_id_camara AND activo = 1;
    
    SELECT * FROM clientes WHERE id_cliente = p_id_cliente AND activo = 1;
    
    SELECT * FROM galpones WHERE id_galpon = p_id_galpon AND activo = 1;
    
    SELECT * FROM productos_stock WHERE id_productos = p_id_productos AND activo = 1;
    
    SELECT * FROM proveedores WHERE id_proveedor = p_id_proveedor AND activo = 1;
    
    SELECT * FROM sensores WHERE id_sensor = p_id_sensor AND activo = 1;
    
    SELECT * FROM usuarios WHERE id_usuario = p_id_usuario AND activo = 1;


    -- Búsquedas Históricas / Transaccionales: Siguen igual que antes
    SELECT * FROM controles_sanidad WHERE id_control = p_id_control;
    
    SELECT * FROM detalles_ventas WHERE id_detalle_venta = p_id_detalle_venta;
    
    SELECT * FROM faenas WHERE id_faena = p_id_faena;
    
    SELECT * FROM insumos_compras WHERE id_insumos_compras = p_id_insumos_compras;
    
    SELECT * FROM lotes_aves WHERE id_lote = p_lote;
    
    SELECT * FROM medicion_sensores WHERE id_medicion = p_id_medicion;
    
    SELECT * FROM roles WHERE id_rol = p_id_rol;
    
    SELECT * FROM ventas WHERE id_venta = p_id_venta;

END$$

DROP PROCEDURE IF EXISTS `eliminar`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar` (IN `p_tabla_destino` VARCHAR(50), IN `p_id` INT)   BEGIN

    -- 1. TABLAS MAESTRAS: Borrado Lógico (Cambia activo a 0)
    IF p_tabla_destino = 'camaras_frigorificas' THEN
        UPDATE camaras_frigorificas SET activo = 0 WHERE id_camara = p_id;

    ELSEIF p_tabla_destino = 'clientes' THEN
        UPDATE clientes SET activo = 0 WHERE id_cliente = p_id;

    ELSEIF p_tabla_destino = 'galpones' THEN
        UPDATE galpones SET activo = 0 WHERE id_galpon = p_id;

    ELSEIF p_tabla_destino = 'productos_stock' THEN
        UPDATE productos_stock SET activo = 0 WHERE id_producto = p_id;

    ELSEIF p_tabla_destino = 'proveedores' THEN
        UPDATE proveedores SET activo = 0 WHERE id_proveedor = p_id;

    ELSEIF p_tabla_destino = 'sensores' THEN
        UPDATE sensores SET activo = 0 WHERE id_sensor = p_id;

    ELSEIF p_tabla_destino = 'usuarios' THEN
        UPDATE usuarios SET activo = 0 WHERE id_usuario = p_id;


    -- 2. TABLAS TRANSACCIONALES / HISTÓRICAS: Borrado Físico (Se mantiene DELETE)
    ELSEIF p_tabla_destino = 'controles_sanidad' THEN
        DELETE FROM controles_sanidad WHERE id_control = p_id;

    ELSEIF p_tabla_destino = 'detalles_ventas' THEN
        DELETE FROM detalles_ventas WHERE id_detalle_venta = p_id;

    ELSEIF p_tabla_destino = 'faenas' THEN
        DELETE FROM faenas WHERE id_faena = p_id;

    ELSEIF p_tabla_destino = 'insumos_compras' THEN
        DELETE FROM insumos_compras WHERE id_compra = p_id;

    ELSEIF p_tabla_destino = 'lotes_aves' THEN
        DELETE FROM lotes_aves WHERE id_lote = p_id;

    ELSEIF p_tabla_destino = 'medicion_sensores' THEN
        DELETE FROM medicion_sensores WHERE id_medicion = p_id;

    ELSEIF p_tabla_destino = 'ventas' THEN
        DELETE FROM ventas WHERE id_venta = p_id;
        
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre de la tabla especificada no es válido.';
    END IF;

END$$

DROP PROCEDURE IF EXISTS `insertar`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar` (IN `p_tabla_destino` VARCHAR(50), IN `p_id` INT, IN `p_capacidad` INT, IN `p_estado_cadena_frio` VARCHAR(50), IN `p_id_usuario` INT, IN `p_telefono_cliente` VARCHAR(50), IN `p_id_lote` INT, IN `p_id_usuario_veterinario` INT, IN `p_fecha_control` DATETIME, IN `p_cantidad_bajas` INT, IN `p_observaciones_veterinarias` VARCHAR(255), IN `p_id_venta_detalle` INT, IN `p_id_producto_detalle` INT, IN `p_cantidad_detalle` INT, IN `p_precio_unitario` DECIMAL(10,2), IN `p_id_lote_faena` INT, IN `p_fecha_faena` DATETIME, IN `p_cantidad_aves_faenadas` INT, IN `p_peso_total_obtenido` DECIMAL(10,2), IN `p_nombre_galpon` VARCHAR(100), IN `p_capacidad_maxima` VARCHAR(50), IN `p_id_proveedor_insumo` INT, IN `p_descripcion_insumo` VARCHAR(200), IN `p_cantidad_insumo` INT, IN `p_fecha_compra` DATETIME, IN `p_id_galpon_lote` INT, IN `p_fecha_ingreso` DATETIME, IN `p_cantidad_inicial` INT, IN `p_raza_genetica` VARCHAR(100), IN `p_id_sensor_medicion` INT, IN `p_valor_registrado` DECIMAL(10,2), IN `p_fecha_medicion` DATETIME, IN `p_id_camara_prod` INT, IN `p_nombre_producto` VARCHAR(100), IN `p_stock_disponible` INT, IN `p_fecha_envasado` DATETIME, IN `p_telefono_proveedor` VARCHAR(50), IN `p_nombre_rol` VARCHAR(50), IN `p_tipo_sensor` VARCHAR(50), IN `p_ubicacion` VARCHAR(100), IN `p_email` VARCHAR(150), IN `p_contrasena` VARCHAR(255), IN `p_id_rol` INT, IN `p_id_cliente_venta` INT, IN `p_fecha_venta` DATETIME, IN `p_total_facturado` DECIMAL(12,2))   BEGIN

        IF p_tabla_destino = 'camaras_frigorificas' THEN
        INSERT INTO camaras_frigorificas (capacidad, estado_cadena_frio) 
        VALUES (p_capacidad, p_estado_cadena_frio);

    ELSEIF p_tabla_destino = 'clientes' THEN
        INSERT INTO clientes (id_usuario, telefono) 
        VALUES (p_id_usuario, p_telefono_cliente);

    ELSEIF p_tabla_destino = 'controles_sanidad' THEN
        INSERT INTO controles_sanidad (id_lote, id_usuario_veterinario, fecha_control, cantidad_bajas, observaciones_veterinarias) 
        VALUES (p_id_lote, p_id_usuario_veterinario, p_fecha_control, p_cantidad_bajas, p_observaciones_veterinarias);

    ELSEIF p_tabla_destino = 'detalles_ventas' THEN
        INSERT INTO detalles_ventas (id_venta, id_producto, cantidad, precio_unitario) 
        VALUES (p_id_venta_detalle, p_id_producto_detalle, p_cantidad_detalle, p_precio_unitario);

    ELSEIF p_tabla_destino = 'faenas' THEN
        INSERT INTO faenas (id_lote, fecha_faena, cantidad_aves_faenadas, peso_total_obtenido) 
        VALUES (p_id_lote_faena, p_fecha_faena, p_cantidad_aves_faenadas, p_peso_total_obtenido);

    ELSEIF p_tabla_destino = 'galpones' THEN
        INSERT INTO galpones (nombre_galpon, capacidad_maxima) 
        VALUES (p_nombre_galpon, p_capacidad_maxima);

    ELSEIF p_tabla_destino = 'insumos_compras' THEN
        INSERT INTO insumos_compras (id_proveedor, descripcion_insumo, cantidad, fecha_compra) 
        VALUES (p_id_proveedor_insumo, p_descripcion_insumo, p_cantidad_insumo, p_fecha_compra);

    ELSEIF p_tabla_destino = 'lotes_aves' THEN
        INSERT INTO lotes_aves (id_galpon, fecha_ingreso, cantidad_inicial, raza_genetica) 
        VALUES (p_id_galpon_lote, p_fecha_ingreso, p_cantidad_inicial, p_raza_genetica);

    ELSEIF p_tabla_destino = 'medicion_sensores' THEN
        INSERT INTO medicion_sensores (id_sensor, valor_registrado, fecha_medicion) 
        VALUES (p_id_sensor_medicion, p_valor_registrado, p_fecha_medicion);

    ELSEIF p_tabla_destino = 'productos_stock' THEN
        INSERT INTO productos_stock (id_camara, nombre_producto, stock_disponible, fecha_envasado) 
        VALUES (p_id_camara_prod, p_nombre_producto, p_stock_disponible, p_fecha_envasado);

    ELSEIF p_tabla_destino = 'proveedores' THEN
        INSERT INTO proveedores (telefono) 
        VALUES (p_telefono_proveedor);

    ELSEIF p_tabla_destino = 'roles' THEN
        INSERT INTO roles (nombre_rol) 
        VALUES (p_nombre_rol);

    ELSEIF p_tabla_destino = 'sensores' THEN
        INSERT INTO sensores (tipo_sensor, ubicacion) 
        VALUES (p_tipo_sensor, p_ubicacion);

    ELSEIF p_tabla_destino = 'usuarios' THEN
        INSERT INTO usuarios (email, contrasena, id_rol) 
        VALUES (p_email, p_contrasena, p_id_rol);

    ELSEIF p_tabla_destino = 'ventas' THEN
        INSERT INTO ventas (id_cliente, fecha_venta, total_facturado) 
        VALUES (p_id_cliente_venta, p_fecha_venta, p_total_facturado);
        
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre de la tabla especificada no es válido.';
    END IF;

END$$

DROP PROCEDURE IF EXISTS `listar_tabla`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_tabla` (IN `p_tabla` VARCHAR(30))   BEGIN 

    -- Tablas Maestras (Muestra solo registros activos)
    IF p_tabla = 'camaras_frigorificas' THEN
        SELECT * FROM camaras_frigorificas WHERE activo = 1;

    ELSEIF p_tabla = 'clientes' THEN
        SELECT * FROM clientes WHERE activo = 1;

    ELSEIF p_tabla = 'galpones' THEN
        SELECT * FROM galpones WHERE activo = 1;

    ELSEIF p_tabla = 'productos_stock' THEN
        SELECT * FROM productos_stock WHERE activo = 1;

    ELSEIF p_tabla = 'proveedores' THEN
        SELECT * FROM proveedores WHERE activo = 1;

    ELSEIF p_tabla = 'sensores' THEN
        SELECT * FROM sensores WHERE activo = 1;

    ELSEIF p_tabla = 'usuarios' THEN
        SELECT * FROM usuarios WHERE activo = 1;

    -- Tablas Transaccionales / Históricas (Se muestran completas)
    ELSEIF p_tabla = 'controles_sanidad' THEN
        SELECT * FROM controles_sanidad;

    ELSEIF p_tabla = 'detalles_ventas' THEN
        SELECT * FROM detalles_ventas;

    ELSEIF p_tabla = 'faenas' THEN
        SELECT * FROM faenas;

    ELSEIF p_tabla = 'insumos_compras' THEN
        SELECT * FROM insumos_compras;

    ELSEIF p_tabla = 'lotes_aves' THEN
        SELECT * FROM lotes_aves;

    ELSEIF p_tabla = 'medicion_sensores' THEN
        SELECT * FROM medicion_sensores;

    ELSEIF p_tabla = 'ventas' THEN
        SELECT * FROM ventas;
        
    ELSE
        SELECT 'La tabla especificada no existe o no está soportada' AS Error;
    END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camaras_frigorificas`
--

DROP TABLE IF EXISTS `camaras_frigorificas`;
CREATE TABLE IF NOT EXISTS `camaras_frigorificas` (
  `id_camara` int(11) NOT NULL AUTO_INCREMENT,
  `capacidad` int(11) DEFAULT NULL,
  `estado_cadena_frio` varchar(50) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_camara`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_cliente`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controles_sanidad`
--

DROP TABLE IF EXISTS `controles_sanidad`;
CREATE TABLE IF NOT EXISTS `controles_sanidad` (
  `id_control` int(11) NOT NULL AUTO_INCREMENT,
  `id_lote` int(11) DEFAULT NULL,
  `id_usuario_veterinario` int(11) DEFAULT NULL,
  `fecha_control` datetime DEFAULT NULL,
  `cantidad_bajas` int(11) DEFAULT NULL,
  `observaciones_veterinarias` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_control`),
  KEY `id_lote` (`id_lote`),
  KEY `id_usuario_veterinario` (`id_usuario_veterinario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_ventas`
--

DROP TABLE IF EXISTS `detalles_ventas`;
CREATE TABLE IF NOT EXISTS `detalles_ventas` (
  `id_detalle_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_venta` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_venta`),
  KEY `id_venta` (`id_venta`),
  KEY `id_producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `detalles_ventas`
--
DROP TRIGGER IF EXISTS `trg_descontar_stock_venta`;
DELIMITER $$
CREATE TRIGGER `trg_descontar_stock_venta` AFTER INSERT ON `detalles_ventas` FOR EACH ROW BEGIN
-- Se actualiza la tabla de stock restando la cantidad que acaba de comprar el cliente
UPDATE productos_stock
SET stock_disponible = stock_disponible - NEW.cantidad
WHERE id_producto = NEW.id_producto;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `faenas`
--

DROP TABLE IF EXISTS `faenas`;
CREATE TABLE IF NOT EXISTS `faenas` (
  `id_faena` int(11) NOT NULL AUTO_INCREMENT,
  `id_lote` int(11) DEFAULT NULL,
  `fecha_faena` datetime DEFAULT NULL,
  `cantidad_aves_faenadas` int(11) DEFAULT NULL,
  `peso_total_obtenido` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_faena`),
  KEY `id_lote` (`id_lote`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `galpones`
--

DROP TABLE IF EXISTS `galpones`;
CREATE TABLE IF NOT EXISTS `galpones` (
  `id_galpon` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_galpon` varchar(100) NOT NULL,
  `capacidad_maxima` varchar(50) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_galpon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos_compras`
--

DROP TABLE IF EXISTS `insumos_compras`;
CREATE TABLE IF NOT EXISTS `insumos_compras` (
  `id_compra` int(11) NOT NULL AUTO_INCREMENT,
  `id_proveedor` int(11) DEFAULT NULL,
  `descripcion_insumo` varchar(200) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fecha_compra` datetime DEFAULT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `id_proveedor` (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lotes_aves`
--

DROP TABLE IF EXISTS `lotes_aves`;
CREATE TABLE IF NOT EXISTS `lotes_aves` (
  `id_lote` int(11) NOT NULL AUTO_INCREMENT,
  `id_galpon` int(11) DEFAULT NULL,
  `fecha_ingreso` datetime DEFAULT NULL,
  `cantidad_inicial` int(11) DEFAULT NULL,
  `raza_genetica` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_lote`),
  KEY `id_galpon` (`id_galpon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicion_sensores`
--

DROP TABLE IF EXISTS `medicion_sensores`;
CREATE TABLE IF NOT EXISTS `medicion_sensores` (
  `id_medicion` int(11) NOT NULL AUTO_INCREMENT,
  `id_sensor` int(11) DEFAULT NULL,
  `valor_registrado` decimal(10,2) DEFAULT NULL,
  `fecha_medicion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_medicion`),
  KEY `id_sensor` (`id_sensor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_stock`
--

DROP TABLE IF EXISTS `productos_stock`;
CREATE TABLE IF NOT EXISTS `productos_stock` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `id_camara` int(11) DEFAULT NULL,
  `nombre_producto` varchar(100) DEFAULT NULL,
  `stock_disponible` int(11) DEFAULT NULL,
  `fecha_envasado` datetime DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_producto`),
  KEY `id_camara` (`id_camara`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE IF NOT EXISTS `proveedores` (
  `id_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `telefono` varchar(50) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre_rol`) VALUES
(1, 'Cliente'),
(2, 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sensores`
--

DROP TABLE IF EXISTS `sensores`;
CREATE TABLE IF NOT EXISTS `sensores` (
  `id_sensor` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_sensor` varchar(50) DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_sensor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(150) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email` (`email`),
  KEY `id_rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `email`, `contrasena`, `id_rol`, `activo`) VALUES
(4, 'messi@mail', '123', 1, 1),
(5, 'micho@mail.com', '12345', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `id_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  `fecha_venta` datetime DEFAULT NULL,
  `total_facturado` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `controles_sanidad`
--
ALTER TABLE `controles_sanidad`
  ADD CONSTRAINT `controles_sanidad_ibfk_1` FOREIGN KEY (`id_lote`) REFERENCES `lotes_aves` (`id_lote`),
  ADD CONSTRAINT `controles_sanidad_ibfk_2` FOREIGN KEY (`id_usuario_veterinario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `detalles_ventas`
--
ALTER TABLE `detalles_ventas`
  ADD CONSTRAINT `detalles_ventas_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`),
  ADD CONSTRAINT `detalles_ventas_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos_stock` (`id_producto`);

--
-- Filtros para la tabla `faenas`
--
ALTER TABLE `faenas`
  ADD CONSTRAINT `faenas_ibfk_1` FOREIGN KEY (`id_lote`) REFERENCES `lotes_aves` (`id_lote`);

--
-- Filtros para la tabla `insumos_compras`
--
ALTER TABLE `insumos_compras`
  ADD CONSTRAINT `insumos_compras_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`);

--
-- Filtros para la tabla `lotes_aves`
--
ALTER TABLE `lotes_aves`
  ADD CONSTRAINT `lotes_aves_ibfk_1` FOREIGN KEY (`id_galpon`) REFERENCES `galpones` (`id_galpon`);

--
-- Filtros para la tabla `medicion_sensores`
--
ALTER TABLE `medicion_sensores`
  ADD CONSTRAINT `medicion_sensores_ibfk_1` FOREIGN KEY (`id_sensor`) REFERENCES `sensores` (`id_sensor`);

--
-- Filtros para la tabla `productos_stock`
--
ALTER TABLE `productos_stock`
  ADD CONSTRAINT `productos_stock_ibfk_1` FOREIGN KEY (`id_camara`) REFERENCES `camaras_frigorificas` (`id_camara`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
