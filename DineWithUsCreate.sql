CREATE DATABASE  IF NOT EXISTS `dinewithusdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dinewithusdb`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: dinewithusdb
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments` (
  `idAppointments` int(11) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `DATE` datetime DEFAULT NULL,
  `START` int(11) DEFAULT NULL,
  `END` int(11) DEFAULT NULL,
  `idStore` int(11) DEFAULT NULL,
  `idUSER_A` int(11) DEFAULT NULL,
  `idUSER_B` int(11) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAppointments`),
  UNIQUE KEY `idAppointments_UNIQUE` (`idAppointments`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dislikes`
--

DROP TABLE IF EXISTS `dislikes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dislikes` (
  `idLikes` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `Dislike` varchar(45) NOT NULL,
  PRIMARY KEY (`idLikes`),
  UNIQUE KEY `idLikes_UNIQUE` (`idLikes`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dislikes`
--

LOCK TABLES `dislikes` WRITE;
/*!40000 ALTER TABLE `dislikes` DISABLE KEYS */;
INSERT INTO `dislikes` VALUES (1,1,'Smoke'),(2,1,'Highpitched Noises'),(3,1,'Beansprouts');
/*!40000 ALTER TABLE `dislikes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `idLikes` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) DEFAULT NULL,
  `Like` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idLikes`),
  UNIQUE KEY `idLikes_UNIQUE` (`idLikes`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,1,'Curry'),(2,1,'Dogs'),(3,1,'Seafood'),(4,2,'Tags'),(5,3,'Cats'),(6,3,'Lasers');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mapappointments`
--

DROP TABLE IF EXISTS `mapappointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapappointments` (
  `idMap` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) DEFAULT NULL,
  `idAppointments` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMap`),
  UNIQUE KEY `idMap_UNIQUE` (`idMap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mapappointments`
--

LOCK TABLES `mapappointments` WRITE;
/*!40000 ALTER TABLE `mapappointments` DISABLE KEYS */;
/*!40000 ALTER TABLE `mapappointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mapprofiles`
--

DROP TABLE IF EXISTS `mapprofiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapprofiles` (
  `idMap` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) DEFAULT NULL,
  `idProfile` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMap`),
  UNIQUE KEY `idMap_UNIQUE` (`idMap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mapprofiles`
--

LOCK TABLES `mapprofiles` WRITE;
/*!40000 ALTER TABLE `mapprofiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `mapprofiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mapschedule`
--

DROP TABLE IF EXISTS `mapschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapschedule` (
  `idMap` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) DEFAULT NULL,
  `idSchedule` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMap`),
  UNIQUE KEY `idMap_UNIQUE` (`idMap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mapschedule`
--

LOCK TABLES `mapschedule` WRITE;
/*!40000 ALTER TABLE `mapschedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `mapschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profiles` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(45) DEFAULT NULL,
  `LASTNAME` varchar(45) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `IMAGEPATH` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idProfiles_UNIQUE` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (1,'HUIJUN','TAY',24,'none','412-265-8369','htay@andrew.cmu.edu');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

DROP TABLE IF EXISTS `schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedules` (
  `idSchedule` int(11) NOT NULL,
  `DATE` datetime DEFAULT NULL,
  `START` int(11) DEFAULT NULL,
  `END` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSchedule`),
  UNIQUE KEY `idUser_UNIQUE` (`idSchedule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stores` (
  `idStore` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `LOCATION` varchar(100) DEFAULT NULL,
  `START` int(11) DEFAULT NULL,
  `END` int(11) DEFAULT NULL,
  `IMAGEURL` varchar(200) DEFAULT NULL,
  `WEBSITE` varchar(200) DEFAULT NULL,
  `MENUURL` varchar(200) DEFAULT NULL,
  `LATITUDE` float DEFAULT NULL,
  `LONGITUDE` float DEFAULT NULL,
  `CUISINE` varchar(45) DEFAULT NULL,
  `PRICERANGE` int(11) DEFAULT NULL,
  `RATING` int(11) DEFAULT NULL,
  `DESCRIPTION` mediumtext,
  PRIMARY KEY (`idStore`),
  UNIQUE KEY `idStores_UNIQUE` (`idStore`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1,'Oriental Express','Craig Street',1000,2200,'http://s3.amazonaws.com/foodspotting-ec2/reviews/3384387/thumb_600.jpg?1365285933','www.oe.com','www.oe.com',100.5,100,'CHINESE',2,1,'Oriental Express, home for the fried chicken fast'),(2,'EatUnique','Craig Street',1100,2000,'http://s3.amazonaws.com/foodspotting-ec2/reviews/3384387/thumb_600.jpg?1365285933','www.eatunique.com','www.eatunique.com',200,200.9,'SANDWICHES',3,3,'eat unique soups are good!'),(3,'Little Asia','Craig Street',1100,2200,'http://s3.amazonaws.com/foodspotting-ec2/reviews/3384387/thumb_600.jpg?1365285933','www.littleasiapittsburgh.com','www.littleasiapittsburgh.com',300.3,100.65,'CHINESE',3,3,'Little asia has nice set meals'),(4,'Five Guys','Oakland',1200,2100,'http://s3.amazonaws.com/foodspotting-ec2/reviews/3384387/thumb_600.jpg?1365285933','www.fiveguysburgersandfries.com','www.fiveguysburgersandfries.com',177.65,56.1,'FAST FOOD',3,3,'Burgerbugersurgerspeanutsurgers');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'htay','test');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-23 17:10:34
