-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-11-2026 a las 22:27:53
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
DROP DATABASE IF EXISTS `proyectoavicola`;
CREATE DATABASE IF NOT EXISTS `proyectoavicola` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `proyectoavicola`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camaras_frigorificas`
--

DROP TABLE IF EXISTS `camaras_frigorificas`;
CREATE TABLE `camaras_frigorificas` (
  `id_camara` int(11) NOT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado_cadena_frio` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `camaras_frigorificas`
--

INSERT INTO `camaras_frigorificas` (`id_camara`, `capacidad`, `estado_cadena_frio`) VALUES
(1, 10000, 'Óptimo - 4°C'),
(2, 15000, 'Estable - 2°C');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `id_usuario`, `telefono`) VALUES
(1, 2, '555-0177'),
(2, 3, '555-0188');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controles_sanidad`
--

DROP TABLE IF EXISTS `controles_sanidad`;
CREATE TABLE `controles_sanidad` (
  `id_control` int(11) NOT NULL,
  `id_lote` int(11) DEFAULT NULL,
  `id_usuario_veterinario` int(11) DEFAULT NULL,
  `fecha_control` datetime DEFAULT NULL,
  `cantidad_bajas` int(11) DEFAULT NULL,
  `observaciones_veterinarias` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `controles_sanidad`
--

INSERT INTO `controles_sanidad` (`id_control`, `id_lote`, `id_usuario_veterinario`, `fecha_control`, `cantidad_bajas`, `observaciones_veterinarias`) VALUES
(1, 1, 3, '2026-10-20 10:00:00', 5, 'Lote en perfectas condiciones sanitarias. Peso óptimo.'),
(2, 2, 3, '2026-10-22 11:30:00', 12, 'Ligero cuadro respiratorio detectado.');

--
-- Disparadores `controles_sanidad`
--
DROP TRIGGER IF EXISTS `descontar_aves_muertas`;
DELIMITER $$
CREATE TRIGGER `descontar_aves_muertas` AFTER INSERT ON `controles_sanidad` FOR EACH ROW BEGIN
    UPDATE lotes_aves
    SET cantidad_inicial = cantidad_inicial - NEW.cantidad_bajas
    WHERE id_lote = NEW.id_lote;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_ventas`
--

DROP TABLE IF EXISTS `detalles_ventas`;
CREATE TABLE `detalles_ventas` (
  `id_detalle_venta` int(11) NOT NULL,
  `id_venta` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalles_ventas`
--

INSERT INTO `detalles_ventas` (`id_detalle_venta`, `id_venta`, `id_producto`, `cantidad`, `precio_unitario`) VALUES
(1, 1, 1, 100, 45.00),
(2, 2, 2, 200, 50.00);

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
CREATE TABLE `faenas` (
  `id_faena` int(11) NOT NULL,
  `id_lote` int(11) DEFAULT NULL,
  `fecha_faena` datetime DEFAULT NULL,
  `cantidad_aves_faenadas` int(11) DEFAULT NULL,
  `peso_total_obtenido` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `faenas`
--

INSERT INTO `faenas` (`id_faena`, `id_lote`, `fecha_faena`, `cantidad_aves_faenadas`, `peso_total_obtenido`) VALUES
(1, 1, '2026-10-26 06:00:00', 1200, 2750.50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `galpones`
--

DROP TABLE IF EXISTS `galpones`;
CREATE TABLE `galpones` (
  `id_galpon` int(11) NOT NULL,
  `nombre_galpon` varchar(100) NOT NULL,
  `capacidad_maxima` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `galpones`
--

INSERT INTO `galpones` (`id_galpon`, `nombre_galpon`, `capacidad_maxima`) VALUES
(1, 'Galpón Norte de Engorde', '5000'),
(2, 'Galpón Sur de Postura', '3500');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos_compras`
--

DROP TABLE IF EXISTS `insumos_compras`;
CREATE TABLE `insumos_compras` (
  `id_compra` int(11) NOT NULL,
  `id_proveedor` int(11) DEFAULT NULL,
  `descripcion_insumo` varchar(200) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fecha_compra` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `insumos_compras`
--

INSERT INTO `insumos_compras` (`id_compra`, `id_proveedor`, `descripcion_insumo`, `cantidad`, `fecha_compra`) VALUES
(1, 1, 'Alimento Balanceado Iniciador (Sacos 40kg)', 100, '2026-10-10 14:00:00'),
(2, 2, 'Vacunas NewCastle (Dosis)', 5000, '2026-10-12 11:15:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lotes_aves`
--

DROP TABLE IF EXISTS `lotes_aves`;
CREATE TABLE `lotes_aves` (
  `id_lote` int(11) NOT NULL,
  `id_galpon` int(11) DEFAULT NULL,
  `fecha_ingreso` datetime DEFAULT NULL,
  `cantidad_inicial` int(11) DEFAULT NULL,
  `raza_genetica` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `lotes_aves`
--

INSERT INTO `lotes_aves` (`id_lote`, `id_galpon`, `fecha_ingreso`, `cantidad_inicial`, `raza_genetica`) VALUES
(1, 1, '2026-09-01 08:00:00', 2500, 'Cobb 500'),
(2, 2, '2026-09-15 08:30:00', 1800, 'Ross 308');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicion_sensores`
--

DROP TABLE IF EXISTS `medicion_sensores`;
CREATE TABLE `medicion_sensores` (
  `id_medicion` int(11) NOT NULL,
  `id_sensor` int(11) DEFAULT NULL,
  `valor_registrado` decimal(10,2) DEFAULT NULL,
  `fecha_medicion` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_stock`
--

DROP TABLE IF EXISTS `productos_stock`;
CREATE TABLE `productos_stock` (
  `id_producto` int(11) NOT NULL,
  `id_camara` int(11) DEFAULT NULL,
  `nombre_producto` varchar(100) DEFAULT NULL,
  `stock_disponible` int(11) DEFAULT NULL,
  `fecha_envasado` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos_stock`
--

INSERT INTO `productos_stock` (`id_producto`, `id_camara`, `nombre_producto`, `stock_disponible`, `fecha_envasado`) VALUES
(1, 1, 'Pollo Entero Grado A', 350, '2026-10-25 08:00:00'),
(2, 1, 'Pechuga deshuesada bandeja x 1kg', 1000, '2026-10-25 09:30:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE `proveedores` (
  `id_proveedor` int(11) NOT NULL,
  `telefono` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id_proveedor`, `telefono`) VALUES
(1, '555-0192'),
(2, '555-0143');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
CREATE TABLE `sensores` (
  `id_sensor` int(11) NOT NULL,
  `tipo_sensor` varchar(50) DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sensores`
--

INSERT INTO `sensores` (`id_sensor`, `tipo_sensor`, `ubicacion`) VALUES
(1, 'Temperatura', 'Sector 1'),
(2, 'Humedad', 'Sector 2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `email` varchar(150) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `id_rol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `email`, `contrasena`, `id_rol`) VALUES
(1, 'carlos@proyecto.com', 'admin2026', 2),
(2, 'juan@proyecto.com', 'op1234', 1),
(3, 'ana@proyecto.com', 'calidad987', 2),
(8, 'gianpancho@gmail.com', '1234', 1),
(9, 'mica@gmail.com', '123345', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `fecha_venta` datetime DEFAULT NULL,
  `total_facturado` decimal(12,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id_venta`, `id_cliente`, `fecha_venta`, `total_facturado`) VALUES
(1, 1, '2026-10-26 09:00:00', 4500.00),
(2, 2, '2026-10-26 09:15:00', 12500.50);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_lotes_con_galpon`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `vista_lotes_con_galpon`;
CREATE TABLE `vista_lotes_con_galpon` (
`id_lote` int(11)
,`raza_genetica` varchar(100)
,`cantidad_inicial` int(11)
,`nombre_galpon` varchar(100)
,`capacidad_maxima` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_lotes_con_galpon`
--
DROP TABLE IF EXISTS `vista_lotes_con_galpon`;

DROP VIEW IF EXISTS `vista_lotes_con_galpon`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_lotes_con_galpon`  AS SELECT `lotes_aves`.`id_lote` AS `id_lote`, `lotes_aves`.`raza_genetica` AS `raza_genetica`, `lotes_aves`.`cantidad_inicial` AS `cantidad_inicial`, `galpones`.`nombre_galpon` AS `nombre_galpon`, `galpones`.`capacidad_maxima` AS `capacidad_maxima` FROM (`lotes_aves` join `galpones` on(`lotes_aves`.`id_galpon` = `galpones`.`id_galpon`)) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `camaras_frigorificas`
--
ALTER TABLE `camaras_frigorificas`
  ADD PRIMARY KEY (`id_camara`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `controles_sanidad`
--
ALTER TABLE `controles_sanidad`
  ADD PRIMARY KEY (`id_control`),
  ADD KEY `id_lote` (`id_lote`),
  ADD KEY `id_usuario_veterinario` (`id_usuario_veterinario`);

--
-- Indices de la tabla `detalles_ventas`
--
ALTER TABLE `detalles_ventas`
  ADD PRIMARY KEY (`id_detalle_venta`),
  ADD KEY `id_venta` (`id_venta`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `faenas`
--
ALTER TABLE `faenas`
  ADD PRIMARY KEY (`id_faena`),
  ADD KEY `id_lote` (`id_lote`);

--
-- Indices de la tabla `galpones`
--
ALTER TABLE `galpones`
  ADD PRIMARY KEY (`id_galpon`);

--
-- Indices de la tabla `insumos_compras`
--
ALTER TABLE `insumos_compras`
  ADD PRIMARY KEY (`id_compra`),
  ADD KEY `id_proveedor` (`id_proveedor`);

--
-- Indices de la tabla `lotes_aves`
--
ALTER TABLE `lotes_aves`
  ADD PRIMARY KEY (`id_lote`),
  ADD KEY `id_galpon` (`id_galpon`);

--
-- Indices de la tabla `medicion_sensores`
--
ALTER TABLE `medicion_sensores`
  ADD PRIMARY KEY (`id_medicion`),
  ADD KEY `id_sensor` (`id_sensor`);

--
-- Indices de la tabla `productos_stock`
--
ALTER TABLE `productos_stock`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `id_camara` (`id_camara`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `sensores`
--
ALTER TABLE `sensores`
  ADD PRIMARY KEY (`id_sensor`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `camaras_frigorificas`
--
ALTER TABLE `camaras_frigorificas`
  MODIFY `id_camara` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `controles_sanidad`
--
ALTER TABLE `controles_sanidad`
  MODIFY `id_control` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detalles_ventas`
--
ALTER TABLE `detalles_ventas`
  MODIFY `id_detalle_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `faenas`
--
ALTER TABLE `faenas`
  MODIFY `id_faena` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `galpones`
--
ALTER TABLE `galpones`
  MODIFY `id_galpon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `insumos_compras`
--
ALTER TABLE `insumos_compras`
  MODIFY `id_compra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `lotes_aves`
--
ALTER TABLE `lotes_aves`
  MODIFY `id_lote` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `medicion_sensores`
--
ALTER TABLE `medicion_sensores`
  MODIFY `id_medicion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos_stock`
--
ALTER TABLE `productos_stock`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `sensores`
--
ALTER TABLE `sensores`
  MODIFY `id_sensor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
