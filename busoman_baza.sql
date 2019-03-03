CREATE DATABASE  IF NOT EXISTS `busoman` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `busoman`;
-- MySQL dump 10.16  Distrib 10.1.34-MariaDB, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: busoman
-- ------------------------------------------------------
-- Server version	10.1.34-MariaDB

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
-- Table structure for table `autobusi`
--

DROP TABLE IF EXISTS `autobusi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autobusi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broj_mesta` int(11) DEFAULT NULL,
  `marka` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `registracija` varchar(255) DEFAULT NULL,
  `slike` varchar(255) DEFAULT NULL,
  `prevoznik_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7ijo404m9l99bp60n1du180qi` (`prevoznik_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autobusi`
--

/*!40000 ALTER TABLE `autobusi` DISABLE KEYS */;
INSERT INTO `autobusi` VALUES (1,56,'Mercedes Benz','TRAVEGO 17 SHD','KG-123-HJ','travego1.jpg;travego2.jpg;travego3.jpg',1);
INSERT INTO `autobusi` VALUES (2,22,'Mercedes Benz','I753','BG-042-LF','sprinter1.jpg;sprinter2.jpg;sprinter3.jpg',2);
INSERT INTO `autobusi` VALUES (3,76,'Neoplan','Cityliner 1116 Hl','NS-078-MP','neoplan1.jpg;neoplan2.jpg',3);
INSERT INTO `autobusi` VALUES (4,42,'Setra','317 HDH','KG-012-ER','setrahdh1.jpg;setrahdh2.jpg',1);
INSERT INTO `autobusi` VALUES (5,23,'Setra','431 DT','BG-638-KZ','setradt1.jpg;setradt2.jpg',2);
INSERT INTO `autobusi` VALUES (6,33,'Man','AO1','PB-026-IS','man1.jpg;man2.jpg;man3.jpg;man4.jpg;man5.jpg',3);
INSERT INTO `autobusi` VALUES (7,43,'wdr','ihoh','jpijpojo','0d7be9cd-d83f-49b7-86ea-e45434273567.jpg;04574d55-a390-425b-a12a-0e115369c49b.jpg',1);
/*!40000 ALTER TABLE `autobusi` ENABLE KEYS */;

--
-- Table structure for table `gradske_linije`
--

DROP TABLE IF EXISTS `gradske_linije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gradske_linije` (
  `aktivna` bit(1) DEFAULT NULL,
  `broj_linije` int(11) DEFAULT NULL,
  `otkazana_do` datetime DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_arf3sx1d52byq90xw3nfud707` (`broj_linije`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradske_linije`
--

/*!40000 ALTER TABLE `gradske_linije` DISABLE KEYS */;
INSERT INTO `gradske_linije` VALUES ('',25,NULL,9);
INSERT INTO `gradske_linije` VALUES ('',15,NULL,10);
INSERT INTO `gradske_linije` VALUES ('',2,NULL,11);
INSERT INTO `gradske_linije` VALUES ('\0',43,'2018-09-26 00:00:00',12);
INSERT INTO `gradske_linije` VALUES ('',3,NULL,13);
INSERT INTO `gradske_linije` VALUES ('',12,NULL,14);
/*!40000 ALTER TABLE `gradske_linije` ENABLE KEYS */;

--
-- Table structure for table `karte`
--

DROP TABLE IF EXISTS `karte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `karte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_potvrdio` bit(1) DEFAULT NULL,
  `cena` int(11) DEFAULT NULL,
  `datum_kupovine` datetime DEFAULT NULL,
  `datum_vazenja` datetime DEFAULT NULL,
  `odobrena` bit(1) DEFAULT NULL,
  `tip` bit(1) DEFAULT NULL,
  `korisnik_id` int(11) DEFAULT NULL,
  `polazak_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk29vw0pfeqh9g76seht73xg5g` (`korisnik_id`),
  KEY `FK533wwh1nyxe9nvo5bx4yi2xgi` (`polazak_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `karte`
--

/*!40000 ALTER TABLE `karte` DISABLE KEYS */;
INSERT INTO `karte` VALUES (1,'',0,'2018-09-23 13:51:57',NULL,'','',4,2);
INSERT INTO `karte` VALUES (2,'\0',0,'2018-09-23 13:52:03',NULL,NULL,'',4,5);
INSERT INTO `karte` VALUES (3,'',0,'2018-09-23 13:52:03',NULL,'','',4,7);
INSERT INTO `karte` VALUES (5,'\0',0,'2018-09-23 14:11:12',NULL,NULL,'',4,15);
INSERT INTO `karte` VALUES (6,'',0,'2018-09-23 14:25:02',NULL,'','',4,3);
/*!40000 ALTER TABLE `karte` ENABLE KEYS */;

--
-- Table structure for table `kategorije_zaposlenja`
--

DROP TABLE IF EXISTS `kategorije_zaposlenja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategorije_zaposlenja` (
  `naziv` varchar(255) NOT NULL,
  `cenaKarte` int(11) NOT NULL,
  `tip` bit(1) NOT NULL,
  PRIMARY KEY (`naziv`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorije_zaposlenja`
--

/*!40000 ALTER TABLE `kategorije_zaposlenja` DISABLE KEYS */;
INSERT INTO `kategorije_zaposlenja` VALUES ('student',1200,'\0');
INSERT INTO `kategorije_zaposlenja` VALUES ('nezaposlen',1200,'\0');
INSERT INTO `kategorije_zaposlenja` VALUES ('zaposlen',2400,'\0');
INSERT INTO `kategorije_zaposlenja` VALUES ('lice_sa_invaliditetom',1000,'');
INSERT INTO `kategorije_zaposlenja` VALUES ('penzioner',1200,'');
/*!40000 ALTER TABLE `kategorije_zaposlenja` ENABLE KEYS */;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin` bit(1) NOT NULL,
  `admin_potvrdio` bit(1) DEFAULT NULL,
  `adresa` varchar(255) DEFAULT NULL,
  `datum_rodjenja` datetime DEFAULT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `korisnicko_ime` varchar(255) DEFAULT NULL,
  `korisnik_validan` bit(1) DEFAULT NULL,
  `lozinka` varchar(255) DEFAULT NULL,
  `mejl` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `telefon` varchar(255) DEFAULT NULL,
  `kategorija_zaposlenja` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_riqypi0ne6b27lldd6dn51d55` (`korisnicko_ime`),
  KEY `FKli7hvt2bmjgqk12jbcix5m6dt` (`kategorija_zaposlenja`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;
INSERT INTO `korisnici` VALUES (1,'','','Gosina 3, Kragujevac','1996-03-08 00:00:00','Mladjan','mladjankg','','oSs/VoKgmnkMBfU/8V4ytBBYT6Eubadc231tBlC8Cuk=','mladjan.mihajlovic@hotmail.com','Mihajlovic','0605044654','student');
INSERT INTO `korisnici` VALUES (2,'\0','','Bana Ivanisa 5, Beograd','1987-12-31 00:00:00','Milos','milosbg','','oSs/VoKgmnkMBfU/8V4ytBBYT6Eubadc231tBlC8Cuk=','losmi.bg@gmail.com','Radojkovic','0641352665','zaposlen');
INSERT INTO `korisnici` VALUES (3,'\0','\0','Nikole Pašića 22, Kragujevac','1974-04-16 00:00:00','Ivana','iki74','\0','oSs/VoKgmnkMBfU/8V4ytBBYT6Eubadc231tBlC8Cuk=','ivana.karovic@yahoo.com','Karovic','0638482164','nezaposlen');
INSERT INTO `korisnici` VALUES (4,'\0','','Internacionalnih brigada 23','1996-10-14 00:00:00','Uroš','uros96','','oSs/VoKgmnkMBfU/8V4ytBBYT6Eubadc231tBlC8Cuk=','uros96@gmail.com','Marinkovic','0654335654','student');
/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;

--
-- Table structure for table `linije`
--

DROP TABLE IF EXISTS `linije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linije` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `odredisna_stanica_id` int(11) DEFAULT NULL,
  `polazna_stanica_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK18tu33g1r0rkuwf7rpqjj6gll` (`odredisna_stanica_id`),
  KEY `FKstnigd8d63gki989v6diie00p` (`polazna_stanica_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linije`
--

/*!40000 ALTER TABLE `linije` DISABLE KEYS */;
INSERT INTO `linije` VALUES (1,27,26);
INSERT INTO `linije` VALUES (2,33,27);
INSERT INTO `linije` VALUES (3,26,36);
INSERT INTO `linije` VALUES (4,29,40);
INSERT INTO `linije` VALUES (5,27,33);
INSERT INTO `linije` VALUES (6,39,26);
INSERT INTO `linije` VALUES (7,40,29);
INSERT INTO `linije` VALUES (8,40,29);
INSERT INTO `linije` VALUES (9,8,2);
INSERT INTO `linije` VALUES (10,14,11);
INSERT INTO `linije` VALUES (11,24,17);
INSERT INTO `linije` VALUES (12,13,6);
INSERT INTO `linije` VALUES (13,21,13);
INSERT INTO `linije` VALUES (14,16,2);
/*!40000 ALTER TABLE `linije` ENABLE KEYS */;

--
-- Table structure for table `linije_stanice`
--

DROP TABLE IF EXISTS `linije_stanice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linije_stanice` (
  `linije_id` int(11) NOT NULL,
  `medjustanice_id` int(11) NOT NULL,
  `medjustanice_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`linije_id`,`medjustanice_ORDER`),
  KEY `FKm79w77dlkgq4px2r5t9gbd28u` (`medjustanice_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linije_stanice`
--

/*!40000 ALTER TABLE `linije_stanice` DISABLE KEYS */;
INSERT INTO `linije_stanice` VALUES (1,34,0);
INSERT INTO `linije_stanice` VALUES (2,38,0);
INSERT INTO `linije_stanice` VALUES (2,26,1);
INSERT INTO `linije_stanice` VALUES (2,32,2);
INSERT INTO `linije_stanice` VALUES (2,31,3);
INSERT INTO `linije_stanice` VALUES (3,34,2);
INSERT INTO `linije_stanice` VALUES (3,28,1);
INSERT INTO `linije_stanice` VALUES (3,35,0);
INSERT INTO `linije_stanice` VALUES (5,38,0);
INSERT INTO `linije_stanice` VALUES (5,26,1);
INSERT INTO `linije_stanice` VALUES (5,32,2);
INSERT INTO `linije_stanice` VALUES (5,31,3);
INSERT INTO `linije_stanice` VALUES (9,2,0);
INSERT INTO `linije_stanice` VALUES (9,3,1);
INSERT INTO `linije_stanice` VALUES (9,4,2);
INSERT INTO `linije_stanice` VALUES (9,5,3);
INSERT INTO `linije_stanice` VALUES (9,6,4);
INSERT INTO `linije_stanice` VALUES (9,7,5);
INSERT INTO `linije_stanice` VALUES (9,8,6);
INSERT INTO `linije_stanice` VALUES (10,11,0);
INSERT INTO `linije_stanice` VALUES (10,8,1);
INSERT INTO `linije_stanice` VALUES (10,7,2);
INSERT INTO `linije_stanice` VALUES (10,6,3);
INSERT INTO `linije_stanice` VALUES (10,12,4);
INSERT INTO `linije_stanice` VALUES (10,13,5);
INSERT INTO `linije_stanice` VALUES (10,14,6);
INSERT INTO `linije_stanice` VALUES (11,17,0);
INSERT INTO `linije_stanice` VALUES (11,18,1);
INSERT INTO `linije_stanice` VALUES (11,19,2);
INSERT INTO `linije_stanice` VALUES (11,20,3);
INSERT INTO `linije_stanice` VALUES (11,21,4);
INSERT INTO `linije_stanice` VALUES (11,22,5);
INSERT INTO `linije_stanice` VALUES (11,23,6);
INSERT INTO `linije_stanice` VALUES (11,24,7);
INSERT INTO `linije_stanice` VALUES (12,6,0);
INSERT INTO `linije_stanice` VALUES (12,7,1);
INSERT INTO `linije_stanice` VALUES (12,8,2);
INSERT INTO `linije_stanice` VALUES (12,9,3);
INSERT INTO `linije_stanice` VALUES (12,10,4);
INSERT INTO `linije_stanice` VALUES (12,11,5);
INSERT INTO `linije_stanice` VALUES (12,12,6);
INSERT INTO `linije_stanice` VALUES (12,13,7);
INSERT INTO `linije_stanice` VALUES (13,13,0);
INSERT INTO `linije_stanice` VALUES (13,14,1);
INSERT INTO `linije_stanice` VALUES (13,15,2);
INSERT INTO `linije_stanice` VALUES (13,16,3);
INSERT INTO `linije_stanice` VALUES (13,17,4);
INSERT INTO `linije_stanice` VALUES (13,18,5);
INSERT INTO `linije_stanice` VALUES (13,19,6);
INSERT INTO `linije_stanice` VALUES (13,20,7);
INSERT INTO `linije_stanice` VALUES (13,21,8);
INSERT INTO `linije_stanice` VALUES (14,2,0);
INSERT INTO `linije_stanice` VALUES (14,3,1);
INSERT INTO `linije_stanice` VALUES (14,8,2);
INSERT INTO `linije_stanice` VALUES (14,9,3);
INSERT INTO `linije_stanice` VALUES (14,10,4);
INSERT INTO `linije_stanice` VALUES (14,12,5);
INSERT INTO `linije_stanice` VALUES (14,14,6);
INSERT INTO `linije_stanice` VALUES (14,16,7);
/*!40000 ALTER TABLE `linije_stanice` ENABLE KEYS */;

--
-- Table structure for table `medjugradske_linije`
--

DROP TABLE IF EXISTS `medjugradske_linije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medjugradske_linije` (
  `id` int(11) NOT NULL,
  `prevoznik_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbp207ktsidnxqm6otqd3rw8nl` (`prevoznik_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medjugradske_linije`
--

/*!40000 ALTER TABLE `medjugradske_linije` DISABLE KEYS */;
INSERT INTO `medjugradske_linije` VALUES (1,1);
INSERT INTO `medjugradske_linije` VALUES (2,2);
INSERT INTO `medjugradske_linije` VALUES (3,3);
INSERT INTO `medjugradske_linije` VALUES (4,2);
INSERT INTO `medjugradske_linije` VALUES (5,2);
INSERT INTO `medjugradske_linije` VALUES (6,3);
INSERT INTO `medjugradske_linije` VALUES (7,1);
INSERT INTO `medjugradske_linije` VALUES (8,2);
/*!40000 ALTER TABLE `medjugradske_linije` ENABLE KEYS */;

--
-- Table structure for table `medjugradske_polasci`
--

DROP TABLE IF EXISTS `medjugradske_polasci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medjugradske_polasci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `preostalo_mesta` int(11) DEFAULT NULL,
  `vreme_dolaska` datetime DEFAULT NULL,
  `vreme_polaska` datetime DEFAULT NULL,
  `autobus_id` int(11) DEFAULT NULL,
  `medjugradska_linija_id` int(11) NOT NULL,
  `vozac_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjaqulbvkmwwhk35yc2ch8mb2h` (`autobus_id`),
  KEY `FKeogfh1ocetqryhroe4ftj77pf` (`medjugradska_linija_id`),
  KEY `FKe8nfg4d01a99kyxd1wfgdir6r` (`vozac_id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medjugradske_polasci`
--

/*!40000 ALTER TABLE `medjugradske_polasci` DISABLE KEYS */;
INSERT INTO `medjugradske_polasci` VALUES (1,56,'2018-09-23 16:00:00','2018-09-23 14:00:00',1,1,3);
INSERT INTO `medjugradske_polasci` VALUES (2,41,'2018-09-23 20:00:00','2018-09-23 18:00:00',4,1,7);
INSERT INTO `medjugradske_polasci` VALUES (3,21,'2018-09-24 11:00:00','2018-09-24 06:00:00',2,2,5);
INSERT INTO `medjugradske_polasci` VALUES (4,22,'2018-09-24 21:30:00','2018-09-24 16:30:00',2,5,5);
INSERT INTO `medjugradske_polasci` VALUES (5,32,'2018-09-24 09:45:00','2018-09-24 08:00:00',6,3,6);
INSERT INTO `medjugradske_polasci` VALUES (6,23,'2018-09-24 10:00:00','2018-09-24 08:00:00',5,4,8);
INSERT INTO `medjugradske_polasci` VALUES (7,32,'2018-09-24 16:25:00','2018-09-24 15:00:00',6,6,10);
INSERT INTO `medjugradske_polasci` VALUES (8,76,'2018-09-24 09:30:00','2018-09-24 08:00:00',3,6,10);
INSERT INTO `medjugradske_polasci` VALUES (9,23,'2018-09-24 13:15:00','2018-09-24 11:15:00',5,8,8);
INSERT INTO `medjugradske_polasci` VALUES (10,56,'2018-09-24 16:00:00','2018-09-24 14:00:00',1,1,3);
INSERT INTO `medjugradske_polasci` VALUES (11,41,'2018-09-24 20:00:00','2018-09-24 18:00:00',4,1,7);
INSERT INTO `medjugradske_polasci` VALUES (12,22,'2018-09-25 11:00:00','2018-09-25 06:00:00',2,2,5);
INSERT INTO `medjugradske_polasci` VALUES (13,22,'2018-09-25 21:30:00','2018-09-25 16:30:00',2,5,5);
INSERT INTO `medjugradske_polasci` VALUES (14,32,'2018-09-25 09:45:00','2018-09-25 08:00:00',6,3,6);
INSERT INTO `medjugradske_polasci` VALUES (15,22,'2018-09-25 10:00:00','2018-09-25 08:00:00',5,4,8);
INSERT INTO `medjugradske_polasci` VALUES (16,32,'2018-09-25 16:25:00','2018-09-25 15:00:00',6,6,10);
INSERT INTO `medjugradske_polasci` VALUES (17,76,'2018-09-25 09:30:00','2018-09-25 08:00:00',3,6,10);
INSERT INTO `medjugradske_polasci` VALUES (18,23,'2018-09-25 13:15:00','2018-09-25 11:15:00',5,8,8);
INSERT INTO `medjugradske_polasci` VALUES (19,56,'2018-09-25 16:00:00','2018-09-25 14:00:00',1,1,3);
INSERT INTO `medjugradske_polasci` VALUES (20,41,'2018-09-25 20:00:00','2018-09-25 18:00:00',4,1,7);
INSERT INTO `medjugradske_polasci` VALUES (21,22,'2018-09-26 11:00:00','2018-09-26 06:00:00',2,2,5);
INSERT INTO `medjugradske_polasci` VALUES (22,22,'2018-09-26 21:30:00','2018-09-26 16:30:00',2,5,5);
INSERT INTO `medjugradske_polasci` VALUES (23,32,'2018-09-26 09:45:00','2018-09-26 08:00:00',6,3,6);
INSERT INTO `medjugradske_polasci` VALUES (24,23,'2018-09-26 10:00:00','2018-09-26 08:00:00',5,4,8);
INSERT INTO `medjugradske_polasci` VALUES (25,32,'2018-09-26 16:25:00','2018-09-26 15:00:00',6,6,10);
INSERT INTO `medjugradske_polasci` VALUES (26,76,'2018-09-26 09:30:00','2018-09-26 08:00:00',3,6,10);
INSERT INTO `medjugradske_polasci` VALUES (27,23,'2018-09-26 13:15:00','2018-09-26 11:15:00',5,8,8);
/*!40000 ALTER TABLE `medjugradske_polasci` ENABLE KEYS */;

--
-- Table structure for table `poruke`
--

DROP TABLE IF EXISTS `poruke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poruke` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum_poruke` datetime DEFAULT NULL,
  `poruka` varchar(255) DEFAULT NULL,
  `korisnik_id` int(11) NOT NULL,
  `tip` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoc21r4o7a89gmdo5bpiq8qrqn` (`korisnik_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poruke`
--

/*!40000 ALTER TABLE `poruke` DISABLE KEYS */;
INSERT INTO `poruke` VALUES (1,'2018-09-23 13:23:14','Administrator je odobrio vaš zahtev za registraciju.',4,'\0');
INSERT INTO `poruke` VALUES (2,'2018-09-23 16:33:54','Vaš zahtev za kupovinu karte je odobren.\nPrevoznik: Unacop\nPolaziste: Kragujevac\nOdrediste: Beograd\nVreme polaska: 23-09-2018 18:00',4,'\0');
INSERT INTO `poruke` VALUES (3,'2018-09-23 16:33:54','Vaš zahtev za kupovinu karte je odobren.\nPrevoznik: Lasta\nPolaziste: Kragujevac\nOdrediste: Cacak\nVreme polaska: 24-09-2018 15:00',4,'\0');
INSERT INTO `poruke` VALUES (4,'2018-09-23 16:33:56','Vaš zahtev za kupovinu karte je odbijen.\nPrevoznik: Unacop\nPolaziste: Kragujevac\nOdrediste: Beograd\nVreme polaska: 25-09-2018 14:00',4,'\0');
INSERT INTO `poruke` VALUES (5,'2018-09-23 16:33:57','Vaš zahtev za kupovinu karte je odobren.\nPrevoznik: Janjusevic\nPolaziste: Beograd\nOdrediste: Novi Pazar\nVreme polaska: 24-09-2018 06:00',4,'\0');
INSERT INTO `poruke` VALUES (6,'2018-09-23 16:34:11','Linija 12 je otkazana do 26-09-2018 00:00.',1,'');
/*!40000 ALTER TABLE `poruke` ENABLE KEYS */;

--
-- Table structure for table `prevoznici`
--

DROP TABLE IF EXISTS `prevoznici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prevoznici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adresa` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `telefon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prevoznici`
--

/*!40000 ALTER TABLE `prevoznici` DISABLE KEYS */;
INSERT INTO `prevoznici` VALUES (1,'Zorana Djindjica 27, Zemun',NULL,'Unacop','011425774');
INSERT INTO `prevoznici` VALUES (2,'Mihaila Gavrilovica 4, Kragujevac','janjusevic.jpg','Janjusevic','034821552');
INSERT INTO `prevoznici` VALUES (3,'Autoput za Nis bb, Beograd','lasta.jpg','Lasta','011421850');
/*!40000 ALTER TABLE `prevoznici` ENABLE KEYS */;

--
-- Table structure for table `red_voznje`
--

DROP TABLE IF EXISTS `red_voznje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `red_voznje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `smer` bit(1) DEFAULT NULL,
  `vreme_polaska` datetime DEFAULT NULL,
  `gradska_linija_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKekiip7dqjk9gx27xk2l1nh6l` (`gradska_linija_id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `red_voznje`
--

/*!40000 ALTER TABLE `red_voznje` DISABLE KEYS */;
INSERT INTO `red_voznje` VALUES (1,NULL,'1970-01-01 07:00:00',9);
INSERT INTO `red_voznje` VALUES (2,NULL,'1970-01-01 08:00:00',9);
INSERT INTO `red_voznje` VALUES (3,NULL,'1970-01-01 09:00:00',9);
INSERT INTO `red_voznje` VALUES (4,NULL,'1970-01-01 10:00:00',9);
INSERT INTO `red_voznje` VALUES (5,NULL,'1970-01-01 11:00:00',9);
INSERT INTO `red_voznje` VALUES (6,NULL,'1970-01-01 12:00:00',9);
INSERT INTO `red_voznje` VALUES (7,NULL,'1970-01-01 13:00:00',9);
INSERT INTO `red_voznje` VALUES (8,NULL,'1970-01-01 14:00:00',9);
INSERT INTO `red_voznje` VALUES (9,NULL,'1970-01-01 15:00:00',9);
INSERT INTO `red_voznje` VALUES (10,NULL,'1970-01-01 17:00:00',9);
INSERT INTO `red_voznje` VALUES (11,NULL,'1970-01-01 06:00:00',10);
INSERT INTO `red_voznje` VALUES (12,NULL,'1970-01-01 07:00:00',10);
INSERT INTO `red_voznje` VALUES (13,NULL,'1970-01-01 08:00:00',10);
INSERT INTO `red_voznje` VALUES (14,NULL,'1970-01-01 09:00:00',10);
INSERT INTO `red_voznje` VALUES (15,NULL,'1970-01-01 10:30:00',10);
INSERT INTO `red_voznje` VALUES (16,NULL,'1970-01-01 11:00:00',10);
INSERT INTO `red_voznje` VALUES (17,NULL,'1970-01-01 12:30:00',10);
INSERT INTO `red_voznje` VALUES (18,NULL,'1970-01-01 13:45:00',10);
INSERT INTO `red_voznje` VALUES (19,NULL,'1970-01-01 14:45:00',10);
INSERT INTO `red_voznje` VALUES (20,NULL,'1970-01-01 17:45:00',10);
INSERT INTO `red_voznje` VALUES (21,NULL,'1970-01-01 07:45:00',11);
INSERT INTO `red_voznje` VALUES (22,NULL,'1970-01-01 09:45:00',11);
INSERT INTO `red_voznje` VALUES (23,NULL,'1970-01-01 11:45:00',11);
INSERT INTO `red_voznje` VALUES (24,NULL,'1970-01-01 13:45:00',11);
INSERT INTO `red_voznje` VALUES (25,NULL,'1970-01-01 15:45:00',11);
INSERT INTO `red_voznje` VALUES (26,NULL,'1970-01-01 17:45:00',11);
INSERT INTO `red_voznje` VALUES (27,NULL,'1970-01-01 06:45:00',12);
INSERT INTO `red_voznje` VALUES (28,NULL,'1970-01-01 10:45:00',12);
INSERT INTO `red_voznje` VALUES (29,NULL,'1970-01-01 11:45:00',12);
INSERT INTO `red_voznje` VALUES (30,NULL,'1970-01-01 13:45:00',12);
INSERT INTO `red_voznje` VALUES (31,NULL,'1970-01-01 16:45:00',12);
INSERT INTO `red_voznje` VALUES (32,NULL,'1970-01-01 18:13:00',12);
INSERT INTO `red_voznje` VALUES (33,NULL,'1970-01-01 05:50:00',13);
INSERT INTO `red_voznje` VALUES (34,NULL,'1970-01-01 07:40:00',13);
INSERT INTO `red_voznje` VALUES (35,NULL,'1970-01-01 08:35:00',13);
INSERT INTO `red_voznje` VALUES (36,NULL,'1970-01-01 09:55:00',13);
INSERT INTO `red_voznje` VALUES (37,NULL,'1970-01-01 14:29:00',13);
INSERT INTO `red_voznje` VALUES (38,NULL,'1970-01-01 08:29:00',14);
INSERT INTO `red_voznje` VALUES (39,NULL,'1970-01-01 10:29:00',14);
INSERT INTO `red_voznje` VALUES (40,NULL,'1970-01-01 13:29:00',14);
INSERT INTO `red_voznje` VALUES (41,NULL,'1970-01-01 17:29:00',14);
/*!40000 ALTER TABLE `red_voznje` ENABLE KEYS */;

--
-- Table structure for table `stanice`
--

DROP TABLE IF EXISTS `stanice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stanice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `tip_stanice` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stanice`
--

/*!40000 ALTER TABLE `stanice` DISABLE KEYS */;
INSERT INTO `stanice` VALUES (1,NULL,NULL,'Sumski Raj',0);
INSERT INTO `stanice` VALUES (2,NULL,NULL,'Rosici',0);
INSERT INTO `stanice` VALUES (3,NULL,NULL,'Petrovac',0);
INSERT INTO `stanice` VALUES (4,NULL,NULL,'Jabucar',0);
INSERT INTO `stanice` VALUES (5,NULL,NULL,'Susica',0);
INSERT INTO `stanice` VALUES (6,NULL,NULL,'Bolnica',0);
INSERT INTO `stanice` VALUES (7,NULL,NULL,'Ured',0);
INSERT INTO `stanice` VALUES (8,NULL,NULL,'Sest topola',0);
INSERT INTO `stanice` VALUES (9,NULL,NULL,'Erdec',0);
INSERT INTO `stanice` VALUES (10,NULL,NULL,'Koricani',0);
INSERT INTO `stanice` VALUES (11,NULL,NULL,'Stanovo',0);
INSERT INTO `stanice` VALUES (12,NULL,NULL,'Dom starih',0);
INSERT INTO `stanice` VALUES (13,NULL,NULL,'Sumarice',0);
INSERT INTO `stanice` VALUES (14,NULL,NULL,'Vojska',0);
INSERT INTO `stanice` VALUES (15,NULL,NULL,'Divostin',0);
INSERT INTO `stanice` VALUES (16,NULL,NULL,'Okretnica Petrovac',0);
INSERT INTO `stanice` VALUES (17,NULL,NULL,'Škola Petrovac',0);
INSERT INTO `stanice` VALUES (18,NULL,NULL,'Bare',0);
INSERT INTO `stanice` VALUES (19,NULL,NULL,'Samoposluga',0);
INSERT INTO `stanice` VALUES (20,NULL,NULL,'Stovarište',0);
INSERT INTO `stanice` VALUES (21,NULL,NULL,'Jabucar',0);
INSERT INTO `stanice` VALUES (22,NULL,NULL,'Bogoslovija',0);
INSERT INTO `stanice` VALUES (23,NULL,NULL,'Pivara',0);
INSERT INTO `stanice` VALUES (24,NULL,NULL,'Stara Cuprija',0);
INSERT INTO `stanice` VALUES (25,NULL,NULL,'Ždraljica',0);
INSERT INTO `stanice` VALUES (26,44.011787,20.910962,'Kragujevac',1);
INSERT INTO `stanice` VALUES (27,44.810796,20.46632,'Beograd',1);
INSERT INTO `stanice` VALUES (28,43.97982,21.257281,'Jagodina',1);
INSERT INTO `stanice` VALUES (29,45.256661,19.834807,'Novi Sad',1);
INSERT INTO `stanice` VALUES (30,43.318426,21.894754,'Nis',1);
INSERT INTO `stanice` VALUES (31,44.438534,20.69458,'Mladenovac',1);
INSERT INTO `stanice` VALUES (32,44.252526,20.688521,'Topola',1);
INSERT INTO `stanice` VALUES (33,43.141101,20.518478,'Novi Pazar',1);
INSERT INTO `stanice` VALUES (34,44.148589,21.071541,'Batocina',1);
INSERT INTO `stanice` VALUES (35,43.928183,21.381939,'Cuprija',1);
INSERT INTO `stanice` VALUES (36,43.856964,21.412148,'Paracin',1);
INSERT INTO `stanice` VALUES (37,42.992574,21.948299,'Leskovac',1);
INSERT INTO `stanice` VALUES (38,43.726455,20.685558,'Kraljevo',1);
INSERT INTO `stanice` VALUES (39,43.893338,20.3494,'Cacak',1);
INSERT INTO `stanice` VALUES (40,44.751061,19.687584,'Šabac',1);
INSERT INTO `stanice` VALUES (41,42.893922,20.865802,'Kosovska Mitrovica',1);
INSERT INTO `stanice` VALUES (42,45.768223,19.121562,'Sombor',1);
/*!40000 ALTER TABLE `stanice` ENABLE KEYS */;

--
-- Table structure for table `vozaci`
--

DROP TABLE IF EXISTS `vozaci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vozaci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum_rodjenja` datetime DEFAULT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `pocetak_voznje` datetime DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `tip` int(11) DEFAULT NULL,
  `gradska_linija_id` int(11) DEFAULT NULL,
  `prevoznik` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5mvn6g5qcarjw9bpsbmc45uw5` (`gradska_linija_id`),
  KEY `FKimx0a2sa8cs9g5olsy8cv08tp` (`prevoznik`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vozaci`
--

/*!40000 ALTER TABLE `vozaci` DISABLE KEYS */;
INSERT INTO `vozaci` VALUES (1,'1974-10-03 00:00:00','Mirko','1999-03-29 00:00:00','Vasiljevic',0,1,NULL);
INSERT INTO `vozaci` VALUES (2,'1984-02-28 00:00:00','Vasilije','2004-03-29 00:00:00','Dobrilovic',0,9,NULL);
INSERT INTO `vozaci` VALUES (3,'1990-06-09 00:00:00','Dragan','2015-08-17 00:00:00','Markovic',1,NULL,1);
INSERT INTO `vozaci` VALUES (4,'1963-10-03 00:00:00','Dragoljub','1987-03-29 00:00:00','Simonovic',0,2,NULL);
INSERT INTO `vozaci` VALUES (5,'1960-11-01 00:00:00','Zivorad','1985-08-17 00:00:00','Mihajlovic',1,NULL,2);
INSERT INTO `vozaci` VALUES (6,'1972-03-17 00:00:00','Marko','2091-12-02 00:00:00','Dragutinovic',1,NULL,3);
INSERT INTO `vozaci` VALUES (7,'1981-10-25 00:00:00','Radenko','2004-01-29 00:00:00','Bajic',1,NULL,1);
INSERT INTO `vozaci` VALUES (8,'1989-04-11 00:00:00','Milos','2013-11-05 00:00:00','Delic',1,NULL,2);
INSERT INTO `vozaci` VALUES (9,'1979-02-21 00:00:00','Janko','2005-09-04 00:00:00','Bojic',1,NULL,1);
INSERT INTO `vozaci` VALUES (10,'1984-05-02 00:00:00','Branko','2010-08-12 00:00:00','Velickovic',1,NULL,3);
INSERT INTO `vozaci` VALUES (11,'1986-04-04 00:00:00','Nikola','2013-01-23 00:00:00','Gudzulic',0,10,NULL);
INSERT INTO `vozaci` VALUES (12,'1994-08-31 00:00:00','Strahinja','2018-09-11 00:00:00','Milutinovic',0,11,NULL);
INSERT INTO `vozaci` VALUES (13,'1977-05-16 00:00:00','Bogoljub','2003-09-30 00:00:00','Jevremovic',0,9,NULL);
INSERT INTO `vozaci` VALUES (14,'1986-10-09 00:00:00','Milan','2012-09-18 00:00:00','Miletic',0,13,NULL);
INSERT INTO `vozaci` VALUES (15,'1983-02-17 00:00:00','Stevan','2015-09-14 00:00:00','Radivojevic',0,12,NULL);
INSERT INTO `vozaci` VALUES (16,'1985-12-18 00:00:00','Stefan','2010-09-15 00:00:00','Dragutinovic',0,14,NULL);
/*!40000 ALTER TABLE `vozaci` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-23 16:35:26
