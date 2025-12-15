-- phpMyAdmin SQL Dump
-- version 6.0.0-dev+20251107.c0c6f045d4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 15, 2025 at 05:43 PM
-- Server version: 8.4.3
-- PHP Version: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `joymarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `idUser` varchar(36) NOT NULL,
  `emergencyContact` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `idCustomer` varchar(36) NOT NULL,
  `idProduct` varchar(36) NOT NULL,
  `count` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `couriers`
--

CREATE TABLE `couriers` (
  `idUser` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `vehicleType` varchar(50) DEFAULT NULL,
  `vehiclePlate` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `idUser` varchar(36) NOT NULL,
  `balance` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `idOrder` varchar(36) NOT NULL,
  `idProduct` varchar(36) NOT NULL,
  `qty` int NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `order_headers`
--

CREATE TABLE `order_headers` (
  `idOrder` varchar(36) NOT NULL,
  `idCustomer` varchar(36) NOT NULL,
  `idPromo` varchar(36) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `orderedAt` date NOT NULL,
  `totalAmount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `idProduct` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `stock` int NOT NULL,
  `category` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promos`
--

CREATE TABLE `promos` (
  `idPromo` varchar(36) NOT NULL,
  `code` varchar(50) NOT NULL,
  `headline` varchar(255) DEFAULT NULL,
  `discountPercentage` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `idUser` varchar(36) NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `role` enum('CUSTOMER','ADMIN','COURIER') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`idUser`);

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`idCustomer`,`idProduct`),
  ADD KEY `fk_cart_product` (`idProduct`);

--
-- Indexes for table `couriers`
--
ALTER TABLE `couriers`
  ADD PRIMARY KEY (`idUser`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`idUser`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`idOrder`,`idProduct`),
  ADD KEY `fk_detail_product` (`idProduct`);

--
-- Indexes for table `order_headers`
--
ALTER TABLE `order_headers`
  ADD PRIMARY KEY (`idOrder`),
  ADD KEY `fk_order_customer` (`idCustomer`),
  ADD KEY `fk_order_promo` (`idPromo`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`idProduct`);

--
-- Indexes for table `promos`
--
ALTER TABLE `promos`
  ADD PRIMARY KEY (`idPromo`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`) ON DELETE CASCADE;

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `fk_cart_customer` FOREIGN KEY (`idCustomer`) REFERENCES `users` (`idUser`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_cart_product` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`) ON DELETE CASCADE;

--
-- Constraints for table `couriers`
--
ALTER TABLE `couriers`
  ADD CONSTRAINT `couriers_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`),
  ADD CONSTRAINT `fk_courier_user` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`) ON DELETE CASCADE;

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `fk_detail_order` FOREIGN KEY (`idOrder`) REFERENCES `order_headers` (`idOrder`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detail_product` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`);

--
-- Constraints for table `order_headers`
--
ALTER TABLE `order_headers`
  ADD CONSTRAINT `fk_order_customer` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idUser`),
  ADD CONSTRAINT `fk_order_promo` FOREIGN KEY (`idPromo`) REFERENCES `promos` (`idPromo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
