-- phpMyAdmin SQL Dump
-- version 6.0.0-dev+20251107.c0c6f045d4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 16, 2025 at 04:54 PM
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

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`idUser`, `emergencyContact`) VALUES
('U002', '08111111111');

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `idCustomer` varchar(36) NOT NULL,
  `idProduct` varchar(36) NOT NULL,
  `count` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cart_items`
--

INSERT INTO `cart_items` (`idCustomer`, `idProduct`, `count`) VALUES
('U001', 'P001', 2);

-- --------------------------------------------------------

--
-- Table structure for table `couriers`
--

CREATE TABLE `couriers` (
  `idUser` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `vehicleType` varchar(50) DEFAULT NULL,
  `vehiclePlate` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `couriers`
--

INSERT INTO `couriers` (`idUser`, `vehicleType`, `vehiclePlate`) VALUES
('U003', 'Motorcycle', 'B 1234 XYZ');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `idUser` varchar(36) NOT NULL,
  `balance` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`idUser`, `balance`) VALUES
('U001', 330000);

-- --------------------------------------------------------

--
-- Table structure for table `deliveries`
--

CREATE TABLE `deliveries` (
  `idOrder` varchar(36) NOT NULL,
  `idCourier` varchar(36) NOT NULL,
  `status` varchar(50) NOT NULL
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

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`idOrder`, `idProduct`, `qty`) VALUES
('1ce880d4-d046-4090-be18-a9cb86173a50', 'P002', 2),
('2bc0d6e9-d835-4164-b6ef-32f532b9268e', 'P001', 2),
('OR20251216145558', 'P004', 5),
('OR20251216162012', 'P001', 1);

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

--
-- Dumping data for table `order_headers`
--

INSERT INTO `order_headers` (`idOrder`, `idCustomer`, `idPromo`, `status`, `orderedAt`, `totalAmount`) VALUES
('1ce880d4-d046-4090-be18-a9cb86173a50', 'U001', 'PR002', 'PENDING', '2025-12-16', 120000),
('2bc0d6e9-d835-4164-b6ef-32f532b9268e', 'U001', NULL, 'PENDING', '2025-12-16', 70000),
('OR20251216145558', 'U001', 'PR001', 'PENDING', '2025-12-16', 45000),
('OR20251216162012', 'U001', NULL, 'PENDING', '2025-12-16', 35000);

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

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`idProduct`, `name`, `price`, `stock`, `category`) VALUES
('P001', 'Wiener Dog', 35000, 20, 'Doggo'),
('P002', 'Stevie Rebus', 75000, 13, 'Meat'),
('P003', 'Kucing Mentega', 12000, 50, 'Fruit'),
('P004', 'Pitbull Terbang', 10000, 2, 'Doggo'),
('P005', 'Susu Expired', 18000, 30, 'Dairy');

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

--
-- Dumping data for table `promos`
--

INSERT INTO `promos` (`idPromo`, `code`, `headline`, `discountPercentage`) VALUES
('PR001', 'PROMO10', 'Discount 10%', 10),
('PR002', 'PROMO20', 'Discount 20%', 20),
('PR003', 'PROMO5', 'Discount 5%', 5);

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
-- Dumping data for table `users`
--

INSERT INTO `users` (`idUser`, `fullName`, `email`, `password`, `phone`, `address`, `gender`, `role`) VALUES
('U001', 'Stevie Customer', 'stevie@gmail.com', 'password', '081234567890', 'Jakarta', 'Male', 'CUSTOMER'),
('U002', 'Charelle Admin', 'charelle@gmail.com', 'password', '081234567891', 'Bandung', 'Female', 'ADMIN'),
('U003', 'Rendy Courier', 'rendy@gmail.com', 'password', '081234567892', 'Surabaya', 'Male', 'COURIER');

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
-- Indexes for table `deliveries`
--
ALTER TABLE `deliveries`
  ADD PRIMARY KEY (`idOrder`),
  ADD KEY `fk_delivery_courier` (`idCourier`);

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
-- Constraints for table `deliveries`
--
ALTER TABLE `deliveries`
  ADD CONSTRAINT `fk_delivery_courier` FOREIGN KEY (`idCourier`) REFERENCES `couriers` (`idUser`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_delivery_order` FOREIGN KEY (`idOrder`) REFERENCES `order_headers` (`idOrder`) ON DELETE CASCADE;

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
