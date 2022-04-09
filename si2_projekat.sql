-- MariaDB dump 10.19  Distrib 10.7.3-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: si2_projekat
-- ------------------------------------------------------
-- Server version	10.7.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `knjige`
--

DROP TABLE IF EXISTS `knjige`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knjige` (
  `naziv` varchar(50) NOT NULL,
  `godina` int(11) NOT NULL,
  `izdavac` varchar(30) NOT NULL,
  `isbn` int(30) NOT NULL,
  `broj` int(11) NOT NULL,
  `slika` varchar(100) NOT NULL,
  `tip` enum('diplomski','doktorski','izdanje','knjiga','master','monografija','naucni','poglavlje','praktikum','udzbenik','zavrsni','zbirka') NOT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knjige`
--

LOCK TABLES `knjige` WRITE;
/*!40000 ALTER TABLE `knjige` DISABLE KEYS */;
INSERT INTO `knjige` VALUES
('OET praktikum',2016,'Zavod za udžbenike',168212437,25,'/slike/oet_praktikum.jpg','praktikum'),
('Neuronske mreze',2016,'',530145156,1,'/slike/neuronske_mreze.png','zavrsni'),
('Antropološka obeležja rukometaša',2018,'',828940409,0,'/slike/antropoloska_obelezja_rukometasa.png','master'),
('Zbirka zadataka iz matematike',1999,'Zavod za udžbenike',978972501,3,'/slike/zbirka_zadataka_iz_matematike.png','zbirka'),
('Digitalni svet ',2019,'Klett',979145821,15,'/slike/digitalni_svet.png','udzbenik');
/*!40000 ALTER TABLE `knjige` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `knjige_pisci`
--

DROP TABLE IF EXISTS `knjige_pisci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knjige_pisci` (
  `isbn` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`isbn`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `knjige_pisci_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pisci` (`id`),
  CONSTRAINT `knjige_pisci_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `knjige` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knjige_pisci`
--

LOCK TABLES `knjige_pisci` WRITE;
/*!40000 ALTER TABLE `knjige_pisci` DISABLE KEYS */;
INSERT INTO `knjige_pisci` VALUES
(979145821,1),
(979145821,2),
(978972501,3),
(168212437,4),
(168212437,5),
(168212437,6),
(168212437,7),
(168212437,8);
/*!40000 ALTER TABLE `knjige_pisci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(30) NOT NULL,
  `tip` varchar(30) DEFAULT 'student',
  `odobren` tinyint(4) DEFAULT 0,
  `blokiran` tinyint(4) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

LOCK TABLES `korisnici` WRITE;
/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;
INSERT INTO `korisnici` VALUES
(1,'Darko','Simić','darkos','darkosimic@gmail.com','lozinka123','student',1,0),
(2,'Marko','Perić','markop','markoperic@gmail.com','lozinka123','student',1,0),
(5,'Marko','Petrović','markop1','markopetrovic@gmail.com','lozinka123','student',1,0),
(6,'Lazar','Stević','lazars','lazars@gmail.com','lozinka123','student',1,1),
(7,'Lazar','Savić','lazarsa','lazarsa@gmail.com','lozinka123','student',1,0),
(8,'Milica','Vukobratović','milicaa','milicavukobratovic12345@gmail.com','lozinka123!','student',1,0),
(9,'Јелена','Перић','jelenap','јеленап@gmail.com','лозинка123','student',1,0),
(10,'Petar','Milić','petarm','petarm@hotmail.com','kozinka123','student',1,0),
(15,'Mihailo','Prodanović','miki123','mikikg@gmail.com','lozinka123','student',0,0),
(16,'Luka','Jović','lukal','lukajovic@gmail.com','lozinka123','bibliotekar',1,0),
(17,'Valentina','Stojković','vacaa','vaca@gmail.com','lozinka123','student',0,0),
(18,'Stefan','Radenković','stefan1','stefan1@gmail.com','lozinka123','admin',1,0),
(19,'Stefan','Gagić','stefan2','stefan2@gmail.com','stefan12345','admin',0,0);
/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pisci`
--

DROP TABLE IF EXISTS `pisci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pisci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `godina` int(11) NOT NULL,
  `tip` enum('autor','editor','mentor') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pisci`
--

LOCK TABLES `pisci` WRITE;
/*!40000 ALTER TABLE `pisci` DISABLE KEYS */;
INSERT INTO `pisci` VALUES
(1,'Dragan','Cvetković',1957,'autor'),
(2,'Isidora','Paunović',1981,'autor'),
(3,'Vene','Bogosavov',1932,'autor'),
(4,'Gordana','Mijatović',1976,'autor'),
(5,'Maja','Todorović',1975,'autor'),
(6,'Vela','Čoja',1986,'autor'),
(7,'Goran','Stojković',1966,'autor'),
(8,'Goran','Stanojević',1967,'autor');
/*!40000 ALTER TABLE `pisci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacije`
--

DROP TABLE IF EXISTS `rezervacije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezervacije` (
  `id` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `datum` datetime DEFAULT NULL,
  `istekla` tinyint(1) DEFAULT NULL,
  KEY `id` (`id`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `rezervacije_ibfk_1` FOREIGN KEY (`id`) REFERENCES `korisnici` (`id`),
  CONSTRAINT `rezervacije_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `knjige` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacije`
--

LOCK TABLES `rezervacije` WRITE;
/*!40000 ALTER TABLE `rezervacije` DISABLE KEYS */;
INSERT INTO `rezervacije` VALUES
(5,168212437,'2022-03-20 23:17:00',1),
(1,168212437,'2022-03-21 00:00:00',1),
(1,168212437,'2022-03-21 00:00:00',1),
(1,168212437,'2022-03-21 00:00:00',1),
(1,168212437,'2022-03-21 00:00:00',1),
(1,979145821,'2022-03-21 00:00:00',1),
(1,978972501,'2022-03-21 00:00:00',1),
(5,978972501,'2022-03-21 16:16:10',1),
(2,978972501,'2022-03-21 16:17:45',1),
(2,979145821,'2022-03-21 22:40:02',1),
(6,168212437,'2022-03-21 23:07:19',1),
(6,979145821,'2022-03-21 23:07:56',1),
(7,168212437,'2022-03-21 23:08:50',1),
(7,979145821,'2022-03-21 23:09:01',1),
(10,168212437,'2022-03-25 16:26:23',1),
(1,168212437,'2022-03-26 15:47:32',1),
(1,978972501,'2022-03-26 17:42:21',1),
(1,979145821,'2022-03-26 18:34:47',1),
(10,168212437,'2022-03-26 20:56:44',1),
(1,168212437,'2022-03-27 01:32:32',1),
(1,978972501,'2022-03-27 01:33:23',1),
(1,979145821,'2022-03-27 01:34:44',1),
(10,168212437,'2022-04-04 10:09:07',1),
(2,168212437,'2022-04-04 13:47:35',1),
(1,168212437,'2022-04-06 12:07:55',1),
(1,978972501,'2022-04-06 12:44:48',1),
(1,168212437,'2022-04-06 17:41:50',1),
(2,168212437,'2022-04-06 17:42:38',1),
(1,168212437,'2022-04-06 18:10:36',1),
(1,978972501,'2022-04-06 18:10:41',0),
(1,979145821,'2022-04-06 18:10:46',0),
(2,168212437,'2022-04-06 18:11:02',0),
(2,978972501,'2022-04-06 18:11:09',0),
(2,979145821,'2022-04-06 18:11:13',0),
(1,168212437,'2022-04-06 20:30:18',1);
/*!40000 ALTER TABLE `rezervacije` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-09 13:05:46
