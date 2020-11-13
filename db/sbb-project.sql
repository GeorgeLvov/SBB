CREATE DATABASE  IF NOT EXISTS `sbb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sbb`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: sbb
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (1,'George','Lvov','1994-10-23'),(2,'Olga','Rubleva','1995-09-09'),(3,'Pavel','Mamaev','1978-11-01'),(57,'George','Smith','1994-10-23'),(59,'Anna','Smith','1992-09-19'),(60,'erty','erty','2020-10-27'),(61,'George','Lvov','1997-11-11'),(62,'George','Smith','1970-12-12'),(63,'George','Ivanov','1994-10-23'),(64,'George','Ivanov','1978-11-11'),(66,'George','Lvov','1995-10-23'),(67,'Gosha','Lvov','1994-10-23'),(68,'Alex','Popov','1992-11-11'),(70,'Gosha','Lvov','1992-02-22'),(72,'Anna','Ivanova','1997-08-19'),(74,'Anna ','Ivanova','1998-12-19'),(76,'Alexander','Ivanov','1992-01-23'),(77,'Anna','Lvova','1998-08-19'),(79,'Alexander','Ivanov','1994-10-23'),(80,'Oleg','Bogatov','1998-10-23'),(81,'Alexander','Ivanov','1998-10-23'),(82,'George','Lvov','1994-10-12');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trip_id` int NOT NULL,
  `station_index` int NOT NULL,
  `station_from` int NOT NULL,
  `station_to` int NOT NULL,
  `departure_time` timestamp NOT NULL,
  `arrival_time` timestamp NOT NULL,
  `departure_delay` int DEFAULT '0',
  `arrival_delay` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_schedule_to_station1_idx` (`station_from`),
  KEY `fk_schedule_to_stationto_idx` (`station_to`),
  KEY `fk_schedule_to_trip_idx` (`trip_id`),
  CONSTRAINT `fk_schedule_to_stationfrom` FOREIGN KEY (`station_from`) REFERENCES `station` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_schedule_to_stationto` FOREIGN KEY (`station_to`) REFERENCES `station` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_schedule_to_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,1,1,1,2,'2020-08-29 05:00:00','2020-08-29 15:50:00',0,0),(2,1,2,2,5,'2020-08-29 16:20:00','2020-08-30 07:40:00',0,0),(3,2,1,2,1,'2020-12-01 08:00:00','2020-12-01 15:00:00',0,0),(4,2,2,1,6,'2020-12-01 15:10:00','2020-12-01 20:50:00',0,0),(5,2,3,6,3,'2020-12-01 21:35:00','2020-12-02 08:25:00',0,0),(6,2,4,3,4,'2020-12-02 09:20:00','2020-12-02 22:05:00',0,0),(7,3,1,5,1,'2020-10-29 06:10:00','2020-10-30 16:25:00',0,0),(8,3,2,1,3,'2020-10-30 16:45:00','2020-10-31 09:15:00',0,0),(9,4,1,2,1,'2020-12-21 15:00:00','2020-12-22 04:00:00',0,0),(10,5,1,2,6,'2020-11-11 09:00:00','2020-11-11 21:35:00',0,0),(11,5,2,6,1,'2020-11-11 22:20:00','2020-11-13 14:40:00',0,0),(12,5,3,1,4,'2020-11-13 15:20:00','2020-11-13 22:20:00',0,0),(13,6,1,1,6,'2020-11-10 10:35:00','2020-11-10 14:00:00',0,0),(14,7,1,9,1,'2020-11-13 10:15:00','2020-11-13 18:30:00',5,5),(15,7,2,1,2,'2020-11-13 18:55:00','2020-11-13 22:50:00',5,5),(16,7,3,2,8,'2020-11-13 23:15:00','2020-11-14 14:15:00',5,5),(17,7,4,8,6,'2020-11-14 15:05:00','2020-11-15 05:38:00',5,5),(18,7,5,6,3,'2020-11-15 06:10:00','2020-11-15 17:15:00',5,5),(19,8,1,1,2,'2020-11-13 15:09:00','2020-11-13 20:15:00',0,0),(20,8,2,2,6,'2020-11-13 20:30:00','2020-11-13 22:42:00',0,0),(21,8,3,6,3,'2020-11-13 23:15:00','2020-11-14 09:30:00',0,0),(22,9,1,4,3,'2021-11-11 19:15:00','2021-11-12 15:55:00',5,5),(23,9,2,3,9,'2021-11-12 16:05:00','2021-11-12 22:49:00',5,5),(24,9,3,9,1,'2021-11-12 23:04:00','2021-11-13 15:55:00',5,5);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'St.Gallen'),(2,'Bern'),(3,'Basel Hbf'),(4,'Luzern'),(5,'Lausanne'),(6,'La Chaux-de-Fonds'),(7,'Zurich Hbf'),(8,'Montreux'),(9,'Geneva'),(36,'Lugano');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `train_id` int NOT NULL,
  `trip_id` int NOT NULL,
  `station_from_id` int NOT NULL,
  `station_to_id` int NOT NULL,
  `departure_time` timestamp NOT NULL,
  `arrival_time` timestamp NOT NULL,
  `passenger_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `valid` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_ticket_to_train_idx` (`train_id`),
  KEY `fk_ticket_to_station1_idx` (`station_from_id`),
  KEY `fk_ticket_to_station2_idx` (`station_to_id`),
  KEY `fk_ticket_to_passenger_idx` (`passenger_id`),
  KEY `fk_ticket_to_user_idx` (`user_id`),
  KEY `fk_ticket_to_trip_idx` (`trip_id`),
  CONSTRAINT `fk_ticket_to_passenger` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_to_station1` FOREIGN KEY (`station_from_id`) REFERENCES `station` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_to_station2` FOREIGN KEY (`station_to_id`) REFERENCES `station` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_to_train` FOREIGN KEY (`train_id`) REFERENCES `train` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_to_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,5,7,3,5,'2020-09-15 10:10:00','2020-09-16 10:45:00',1,2,0),(2,2,2,1,3,'2020-12-01 15:10:00','2020-12-02 08:25:00',66,2,1);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `capacity` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (1,'e200',3),(2,'d-1',2),(3,'w-14',2),(4,'h900',2),(5,'t300',20),(6,'Swiss1000',2),(7,'z-760ks',230);
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `id` int NOT NULL AUTO_INCREMENT,
  `train_id` int NOT NULL,
  `departure_station_id` int NOT NULL,
  `arrival_station_id` int NOT NULL,
  `departure_time` timestamp NOT NULL,
  `arrival_time` timestamp NOT NULL,
  `canceled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_trip_to_train_idx` (`train_id`),
  KEY `fk_trip_to_station1_idx` (`departure_station_id`),
  KEY `fk_trip_to_station2_idx` (`arrival_station_id`),
  CONSTRAINT `fk_trip_arrstation_to_station` FOREIGN KEY (`arrival_station_id`) REFERENCES `station` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trip_depstation_to_station` FOREIGN KEY (`departure_station_id`) REFERENCES `station` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trip_to_train` FOREIGN KEY (`train_id`) REFERENCES `train` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (1,1,1,5,'2020-08-29 05:00:00','2020-08-30 07:40:00',0),(2,2,2,4,'2020-12-01 08:00:00','2020-12-02 22:05:00',0),(3,3,5,3,'2020-10-29 06:10:00','2020-10-31 09:15:00',0),(4,4,2,1,'2020-12-21 15:00:00','2020-12-22 04:00:00',0),(5,1,2,4,'2020-11-11 09:00:00','2020-11-13 22:20:00',0),(6,4,1,6,'2020-11-10 10:35:00','2020-11-10 14:00:00',0),(7,5,9,3,'2020-11-13 10:15:00','2020-11-15 17:15:00',0),(8,3,1,6,'2020-11-13 15:09:00','2020-11-14 09:30:00',0),(9,4,4,1,'2021-11-11 19:15:00','2021-11-13 15:55:00',0);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_to_role_idx` (`role_id`),
  CONSTRAINT `fk_user_to_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user@gmail.com','$2y$12$2h8xF4Yi/wMDBivWquUgeeHb4TQBzKjUNmZJTooY39PtBn1SyQide',1),(2,'admin@gmail.com','$2y$12$UrhQVjjJ1rE/HRMXlfLsfOuag6iGmRJ84bQxCI41eUQEqyneCH0Pm',2),(3,'user2@gmail.com','$2a$12$K9A1IiuS1XI/H/Aj.IRTXO23iB0sUZr.icO3Qml74oI.uzFiyW24S',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sbb'
--

--
-- Dumping routines for database 'sbb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-13  5:03:44
