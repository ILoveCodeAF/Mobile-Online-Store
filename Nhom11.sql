-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: nhom11
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `googleId` varchar(45) DEFAULT NULL,
  `fbId` varchar(45) DEFAULT NULL,
  `role` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `googleId_UNIQUE` (`googleId`),
  UNIQUE KEY `fbId_UNIQUE` (`fbId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (31,'admin','admin',NULL,NULL,'ADMIN'),(33,'tuan','tuan',NULL,NULL,'CUSTOMER');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Vương Anh Tuấn','Trung Tú','tuanva@gmail.com','1998-06-14','0123456789',31);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (15,'Vương Anh Tuấn','Trung Tú','tuanva@gmail.com',NULL,NULL,33);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `manufacturer` varchar(20) NOT NULL,
  `rom` int(11) NOT NULL,
  `ram` int(11) NOT NULL,
  `cpu` varchar(45) NOT NULL,
  `frontCamera` float DEFAULT NULL,
  `behindCamera` float DEFAULT NULL,
  `os` varchar(45) NOT NULL,
  `battery` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `screen_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,'Xiaomi Redmi Note 4X','Xiaomi',32,3,'SnapDragon 625',5,13,'Android 6',4100,'/static/image/phone/1591367271811.jpg',173,1),(2,'Xiaomi Redmi Note 8','Xiaomi',32,3,'SnapDragon 665',13,48,'Android 9',4000,'/static/image/phone/1591373142572.png',192,2),(3,'OPPO A92','Oppo',128,6,'SnapDragon 665',16,12,'Android 10',5000,'/static/image/phone/1591507960206.jpg',299,3),(5,'Samsung Galaxy A21s (6GB/64GB)','Samsung',64,6,'Exynos 850 8 nhân',13,48,'Android 10',5000,'/static/image/phone/1591508353254.jpg',282,5),(7,'Samsung Galaxy A21s (3GB/32GB)','Samsung',32,3,'Exynos 850 8 nhân',13,48,'Android 10',5000,'/static/image/phone/1591508458147.png',232,7),(8,'OPPO Reno2 F','Oppo',128,8,'MediaTek Helio P70 8 nhân',16,48,'Android 9',4000,'/static/image/phone/1591508981219.png',319,8),(10,'OPPO Reno3','Oppo',128,8,'MediaTek Helio P90 8 nhân',44,48,'Android 10',4025,'/static/image/phone/1591509470082.png',331,10),(13,'Xiaomi Redmi Note 9S','Xiaomi',128,6,'Snapdragon 720G 8 nhân',16,48,'Android 10',5020,'/static/image/phone/1591509711303.jpg',222,13),(14,'Huawei Y9s','Huawei',128,6,'Kirin 710F 8 nhân',16,48,'Android 9.1',4000,'/static/image/phone/1591510174763.png',204,14),(15,'Vivo V19 Neo','Vivo',128,8,'Snapdragon 675 8 nhân',32,48,'Android 10',4500,'/static/image/phone/1591510437832.jpg',232,15),(16,'iPhone 7 Plus 32GB','Apple',32,3,'Apple A10 Fusion 4 nhân',7,12,'iOS 12',2900,'/static/image/phone/1591510816463.png',393,16),(17,'iPhone 8 Plus 64GB','Apple',64,3,'Apple A11 Bionic 6 nhân',7,12,'iOS 12',2691,'/static/image/phone/1591512786897.jpg',686,17),(18,'Samsung Galaxy A31','Samsung',128,6,'MediaTek MT6768 8 nhân (Helio P65)',20,48,'Android 10',5000,'/static/image/phone/1591524533380.jpg',299,18),(19,'Vivo V15 128GB','Vivo',128,6,'MediaTek Helio P70 8 nhân',32,12,'Android 9',4000,'/static/image/phone/1591525017046.jpg',186,19);
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `screen`
--

DROP TABLE IF EXISTS `screen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `screen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `size` float NOT NULL,
  `resolution` varchar(45) NOT NULL,
  `technology` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `screen`
--

LOCK TABLES `screen` WRITE;
/*!40000 ALTER TABLE `screen` DISABLE KEYS */;
INSERT INTO `screen` VALUES (1,5.5,'FHD','IPS'),(2,6.3,'FHD+','IPS, LCD'),(3,6.5,'FHD+','TFT LCD'),(5,6.5,'HD+','TFT LCD'),(7,6.5,'HD+','TFT LCD'),(8,6.5,'FHD+','AMOLED'),(10,6.4,'FHD+','AMOLED'),(13,6.67,'FHD+','IPS LCD'),(14,6.59,'FHD+','TFT'),(15,6.44,'FHD+','Super AMOLED'),(16,5.5,'Retina HD','LED-backlit IPS LCD'),(17,5.5,'Retina HD','LED-backlit IPS LCD'),(18,6.4,'FHD+','Super AMOLED'),(19,6.53,'FHD+','IPS LCD');
/*!40000 ALTER TABLE `screen` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-19 21:51:24
