-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 15-08-2022 a las 21:48:06
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.0.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_irca`
--
CREATE DATABASE IF NOT EXISTS `bd_irca` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bd_irca`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuerpos_de_agua`
--
-- Creación: 15-08-2022 a las 03:48:54
-- Última actualización: 16-08-2022 a las 02:45:41
--

CREATE TABLE `cuerpos_de_agua` (
  `id_cuerpo_agua` int(11) NOT NULL,
  `id_tipo_cuerpo_agua` int(11) NOT NULL,
  `id_tipo_agua` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `municipio` varchar(30) NOT NULL,
  `numero_clasificacion` varchar(5) NOT NULL,
  `clasificacion_IRCA` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `cuerpos_de_agua`:
--   `id_tipo_cuerpo_agua`
--       `tipos_cuerpo_agua` -> `id_tipo_cuerpo_agua`
--   `id_tipo_agua`
--       `tipos_de_agua` -> `id_tipo_agua`
--

--
-- Volcado de datos para la tabla `cuerpos_de_agua`
--

INSERT INTO `cuerpos_de_agua` (`id_cuerpo_agua`, `id_tipo_cuerpo_agua`, `id_tipo_agua`, `nombre`, `municipio`, `numero_clasificacion`, `clasificacion_IRCA`) VALUES
(1, 1, 1, 'Magdalena', 'Barranquilla', '40', 'ALTO'),
(2, 2, 2, 'Arbitrario', 'Barranquilla', '12.0', 'BAJO'),
(3, 1, 1, 'Amazona', 'Leticia', '80', 'ALTO'),
(4, 1, 1, 'Bogota', 'Bogota', '15', 'MEDIO'),
(5, 1, 1, 'Tota', 'tota', '80', 'ALTO'),
(6, 2, 1, 'asdasd', 'asdas', '50', 'ALTO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_cuerpo_agua`
--
-- Creación: 15-08-2022 a las 02:27:35
--

CREATE TABLE `tipos_cuerpo_agua` (
  `id_tipo_cuerpo_agua` int(11) NOT NULL,
  `descripcion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `tipos_cuerpo_agua`:
--

--
-- Volcado de datos para la tabla `tipos_cuerpo_agua`
--

INSERT INTO `tipos_cuerpo_agua` (`id_tipo_cuerpo_agua`, `descripcion`) VALUES
(1, 'Laguna'),
(2, 'Rio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_de_agua`
--
-- Creación: 15-08-2022 a las 02:31:34
--

CREATE TABLE `tipos_de_agua` (
  `id_tipo_agua` int(11) NOT NULL,
  `descripcion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `tipos_de_agua`:
--

--
-- Volcado de datos para la tabla `tipos_de_agua`
--

INSERT INTO `tipos_de_agua` (`id_tipo_agua`, `descripcion`) VALUES
(1, 'Dulce'),
(2, 'Salada');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuerpos_de_agua`
--
ALTER TABLE `cuerpos_de_agua`
  ADD PRIMARY KEY (`id_cuerpo_agua`),
  ADD KEY `FK_id_tipo_cuerpo_agua` (`id_tipo_cuerpo_agua`) USING BTREE,
  ADD KEY `FK_id_tipo_agua` (`id_tipo_agua`);

--
-- Indices de la tabla `tipos_cuerpo_agua`
--
ALTER TABLE `tipos_cuerpo_agua`
  ADD PRIMARY KEY (`id_tipo_cuerpo_agua`);

--
-- Indices de la tabla `tipos_de_agua`
--
ALTER TABLE `tipos_de_agua`
  ADD PRIMARY KEY (`id_tipo_agua`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuerpos_de_agua`
--
ALTER TABLE `cuerpos_de_agua`
  MODIFY `id_cuerpo_agua` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tipos_cuerpo_agua`
--
ALTER TABLE `tipos_cuerpo_agua`
  MODIFY `id_tipo_cuerpo_agua` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipos_de_agua`
--
ALTER TABLE `tipos_de_agua`
  MODIFY `id_tipo_agua` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuerpos_de_agua`
--
ALTER TABLE `cuerpos_de_agua`
  ADD CONSTRAINT `cuerpos_de_agua_ibfk_1` FOREIGN KEY (`id_tipo_cuerpo_agua`) REFERENCES `tipos_cuerpo_agua` (`id_tipo_cuerpo_agua`),
  ADD CONSTRAINT `cuerpos_de_agua_ibfk_2` FOREIGN KEY (`id_tipo_agua`) REFERENCES `tipos_de_agua` (`id_tipo_agua`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
