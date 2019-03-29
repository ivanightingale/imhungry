CREATE DATABASE  IF NOT EXISTS `imhungry` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `imhungry`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: imhungry
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recipe` (
  `recipID` int(11) NOT NULL AUTO_INCREMENT,
  `recipeIDapi` int(11) NOT NULL,
  `prepTime` int(11) NOT NULL,
  `cookTime` int(11) NOT NULL,
  `ingredient` varchar(50) NOT NULL,
  `instructions` varchar(50) NOT NULL,
  `imageURL` varchar(50) NOT NULL,
  PRIMARY KEY (`recipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipedonotshow`
--

DROP TABLE IF EXISTS `recipedonotshow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recipedonotshow` (
  `favID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `rID` int(11) NOT NULL,
  PRIMARY KEY (`favID`),
  KEY `fk7` (`userID`),
  KEY `fk8` (`rID`),
  CONSTRAINT `fk7` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `fk8` FOREIGN KEY (`rID`) REFERENCES `recipe` (`recipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipedonotshow`
--

LOCK TABLES `recipedonotshow` WRITE;
/*!40000 ALTER TABLE `recipedonotshow` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipedonotshow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipefavorites`
--

DROP TABLE IF EXISTS `recipefavorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recipefavorites` (
  `favID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `rID` int(11) NOT NULL,
  PRIMARY KEY (`favID`),
  KEY `fk3` (`userID`),
  KEY `fk4` (`rID`),
  CONSTRAINT `fk3` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `fk4` FOREIGN KEY (`rID`) REFERENCES `recipe` (`recipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipefavorites`
--

LOCK TABLES `recipefavorites` WRITE;
/*!40000 ALTER TABLE `recipefavorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipefavorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipetoexplore`
--

DROP TABLE IF EXISTS `recipetoexplore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recipetoexplore` (
  `favID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `rID` int(11) NOT NULL,
  PRIMARY KEY (`favID`),
  KEY `fk11` (`userID`),
  KEY `fk12` (`rID`),
  CONSTRAINT `fk11` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `fk12` FOREIGN KEY (`rID`) REFERENCES `recipe` (`recipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipetoexplore`
--

LOCK TABLES `recipetoexplore` WRITE;
/*!40000 ALTER TABLE `recipetoexplore` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipetoexplore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `restaurant` (
  `restaurantID` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `priceL` varchar(50) NOT NULL,
  `driveTimeT` varchar(50) NOT NULL,
  `driveTimeV` int(11) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `url` varchar(50) NOT NULL,
  PRIMARY KEY (`restaurantID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restdonotshow`
--

DROP TABLE IF EXISTS `restdonotshow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `restdonotshow` (
  `favID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `rID` int(11) NOT NULL,
  PRIMARY KEY (`favID`),
  KEY `fk5` (`userID`),
  KEY `fk6` (`rID`),
  CONSTRAINT `fk5` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `fk6` FOREIGN KEY (`rID`) REFERENCES `restaurant` (`restaurantID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restdonotshow`
--

LOCK TABLES `restdonotshow` WRITE;
/*!40000 ALTER TABLE `restdonotshow` DISABLE KEYS */;
/*!40000 ALTER TABLE `restdonotshow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restfavorites`
--

DROP TABLE IF EXISTS `restfavorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `restfavorites` (
  `favID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `rID` int(11) NOT NULL,
  PRIMARY KEY (`favID`),
  KEY `fk1` (`userID`),
  KEY `fk2` (`rID`),
  CONSTRAINT `fk1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `fk2` FOREIGN KEY (`rID`) REFERENCES `restaurant` (`restaurantID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restfavorites`
--

LOCK TABLES `restfavorites` WRITE;
/*!40000 ALTER TABLE `restfavorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `restfavorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resttoexplore`
--

DROP TABLE IF EXISTS `resttoexplore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `resttoexplore` (
  `favID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `rID` int(11) NOT NULL,
  PRIMARY KEY (`favID`),
  KEY `fk9` (`userID`),
  KEY `fk10` (`rID`),
  CONSTRAINT `fk10` FOREIGN KEY (`rID`) REFERENCES `restaurant` (`restaurantID`),
  CONSTRAINT `fk9` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resttoexplore`
--

LOCK TABLES `resttoexplore` WRITE;
/*!40000 ALTER TABLE `resttoexplore` DISABLE KEYS */;
/*!40000 ALTER TABLE `resttoexplore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `pw` varchar(50) NOT NULL,
  `salt` varchar(50) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'testuser','hashvalue','abcdefgh');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-28 17:46:28
