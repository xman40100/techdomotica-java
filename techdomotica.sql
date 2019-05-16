-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-05-2019 a las 14:48:46
-- Versión del servidor: 10.1.39-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `techdomotica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acondicionado`
--

CREATE TABLE `acondicionado` (
  `id_Acondicionado` int(11) NOT NULL,
  `id_componente` int(11) NOT NULL,
  `temperatura` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camara`
--

CREATE TABLE `camara` (
  `id_camara` int(11) NOT NULL,
  `id_componente` int(11) NOT NULL,
  `resolucion` varchar(45) NOT NULL,
  `ubicacion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `componente`
--

CREATE TABLE `componente` (
  `id_componente` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `nom_componente` varchar(30) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `uso` double NOT NULL,
  `gasto_energetico` double NOT NULL,
  `componente_on` tinyint(4) NOT NULL,
  `habilitado` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `luz`
--

CREATE TABLE `luz` (
  `id_luz` int(11) NOT NULL,
  `id_componente` int(11) NOT NULL,
  `encendido` tinyint(4) NOT NULL,
  `intensidad` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `id_perfil` int(11) NOT NULL,
  `id_Acondicionado` int(11) NOT NULL,
  `id_tv` int(11) NOT NULL,
  `id_camara` int(11) NOT NULL,
  `id_sensor` int(11) NOT NULL,
  `id_luz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reporte`
--

CREATE TABLE `reporte` (
  `id_reporte` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_tr` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `texto` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reporte`
--

INSERT INTO `reporte` (`id_reporte`, `id_usuario`, `id_tr`, `fecha`, `hora`, `texto`) VALUES
(1, 1, 7, '2019-05-16', '07:40:36', 'Intento de inicio de sesión en la versión de Java en Linux'),
(2, 1, 7, '2019-05-16', '07:41:49', 'Intento de inicio de sesión en la versión de Java en Linux'),
(3, 1, 9, '2019-05-16', '07:42:06', 'Se ha realizado un cambio de contraseña exitoso para este usuario desde la versión de Java en Linux.'),
(4, 1, 1, '2019-05-16', '07:42:12', 'Este usuario ha iniciado sesión en la versión de Java desde Linux');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `tipo_rol` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `tipo_rol`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sensor`
--

CREATE TABLE `sensor` (
  `id_sensor` int(11) NOT NULL,
  `id_componente` int(11) NOT NULL,
  `tiposensor` varchar(45) NOT NULL,
  `ubicacion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_reporte`
--

CREATE TABLE `tipo_reporte` (
  `id_tr` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_reporte`
--

INSERT INTO `tipo_reporte` (`id_tr`, `nombre`) VALUES
(1, 'Inicio de sesión'),
(2, 'Registro de usuario'),
(3, 'Dispositivos - agregado'),
(4, 'Dispositivos - modificado'),
(5, 'Dispositivos - reparado'),
(6, 'Cierre de sesión'),
(7, 'Intento de inicio de sesión'),
(8, 'Personalizado'),
(9, 'Cambio de contraseña'),
(10, 'Usuario deshabilitado'),
(11, 'Dispositivo deshabilitado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tv`
--

CREATE TABLE `tv` (
  `id_tv` int(11) NOT NULL,
  `id_componente` int(11) NOT NULL,
  `calidadtv` varchar(45) NOT NULL,
  `resolucion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `nom1` varchar(30) NOT NULL,
  `nom2` varchar(30) NOT NULL,
  `apellido1` varchar(30) NOT NULL,
  `apellido2` varchar(30) NOT NULL,
  `dni` int(30) NOT NULL,
  `habilitado` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `id_rol`, `correo`, `password`, `nom1`, `nom2`, `apellido1`, `apellido2`, `dni`, `habilitado`) VALUES
(1, 1, 'admin@admin.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Andrés', 'Felipe', 'Estupiñán', 'Peláez', 123456, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `acondicionado`
--
ALTER TABLE `acondicionado`
  ADD PRIMARY KEY (`id_Acondicionado`),
  ADD KEY `id_componente` (`id_componente`);

--
-- Indices de la tabla `camara`
--
ALTER TABLE `camara`
  ADD PRIMARY KEY (`id_camara`),
  ADD KEY `id_componente` (`id_componente`);

--
-- Indices de la tabla `componente`
--
ALTER TABLE `componente`
  ADD PRIMARY KEY (`id_componente`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `luz`
--
ALTER TABLE `luz`
  ADD PRIMARY KEY (`id_luz`),
  ADD KEY `id_componente` (`id_componente`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id_perfil`),
  ADD KEY `id_Acondicionado` (`id_Acondicionado`),
  ADD KEY `id_tv` (`id_tv`),
  ADD KEY `id_camara` (`id_camara`),
  ADD KEY `id_sensor` (`id_sensor`),
  ADD KEY `id_luz` (`id_luz`);

--
-- Indices de la tabla `reporte`
--
ALTER TABLE `reporte`
  ADD PRIMARY KEY (`id_reporte`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_tr` (`id_tr`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `sensor`
--
ALTER TABLE `sensor`
  ADD PRIMARY KEY (`id_sensor`),
  ADD KEY `id_componente` (`id_componente`);

--
-- Indices de la tabla `tipo_reporte`
--
ALTER TABLE `tipo_reporte`
  ADD PRIMARY KEY (`id_tr`);

--
-- Indices de la tabla `tv`
--
ALTER TABLE `tv`
  ADD PRIMARY KEY (`id_tv`),
  ADD KEY `id_componente` (`id_componente`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `id_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `acondicionado`
--
ALTER TABLE `acondicionado`
  MODIFY `id_Acondicionado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `camara`
--
ALTER TABLE `camara`
  MODIFY `id_camara` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `componente`
--
ALTER TABLE `componente`
  MODIFY `id_componente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `luz`
--
ALTER TABLE `luz`
  MODIFY `id_luz` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id_perfil` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reporte`
--
ALTER TABLE `reporte`
  MODIFY `id_reporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `sensor`
--
ALTER TABLE `sensor`
  MODIFY `id_sensor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_reporte`
--
ALTER TABLE `tipo_reporte`
  MODIFY `id_tr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `tv`
--
ALTER TABLE `tv`
  MODIFY `id_tv` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `acondicionado`
--
ALTER TABLE `acondicionado`
  ADD CONSTRAINT `acondicionado_ibfk_1` FOREIGN KEY (`id_componente`) REFERENCES `componente` (`id_componente`);

--
-- Filtros para la tabla `camara`
--
ALTER TABLE `camara`
  ADD CONSTRAINT `camara_ibfk_1` FOREIGN KEY (`id_componente`) REFERENCES `componente` (`id_componente`);

--
-- Filtros para la tabla `componente`
--
ALTER TABLE `componente`
  ADD CONSTRAINT `componente_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `luz`
--
ALTER TABLE `luz`
  ADD CONSTRAINT `luz_ibfk_1` FOREIGN KEY (`id_componente`) REFERENCES `componente` (`id_componente`);

--
-- Filtros para la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD CONSTRAINT `perfil_ibfk_1` FOREIGN KEY (`id_Acondicionado`) REFERENCES `acondicionado` (`id_Acondicionado`),
  ADD CONSTRAINT `perfil_ibfk_2` FOREIGN KEY (`id_tv`) REFERENCES `tv` (`id_tv`),
  ADD CONSTRAINT `perfil_ibfk_3` FOREIGN KEY (`id_camara`) REFERENCES `camara` (`id_camara`),
  ADD CONSTRAINT `perfil_ibfk_4` FOREIGN KEY (`id_sensor`) REFERENCES `sensor` (`id_sensor`),
  ADD CONSTRAINT `perfil_ibfk_5` FOREIGN KEY (`id_luz`) REFERENCES `luz` (`id_luz`);

--
-- Filtros para la tabla `reporte`
--
ALTER TABLE `reporte`
  ADD CONSTRAINT `reporte_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `reporte_ibfk_2` FOREIGN KEY (`id_tr`) REFERENCES `tipo_reporte` (`id_tr`);

--
-- Filtros para la tabla `sensor`
--
ALTER TABLE `sensor`
  ADD CONSTRAINT `sensor_ibfk_1` FOREIGN KEY (`id_componente`) REFERENCES `componente` (`id_componente`);

--
-- Filtros para la tabla `tv`
--
ALTER TABLE `tv`
  ADD CONSTRAINT `tv_ibfk_1` FOREIGN KEY (`id_componente`) REFERENCES `componente` (`id_componente`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
