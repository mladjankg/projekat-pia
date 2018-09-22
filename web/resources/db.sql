/* 
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
/**
 * Author:  Mlađan
 * Created: Sep 12, 2018
 */
CREATE SCHEMA IF NOT EXISTS busoman;

USE `busoman`;

DROP TABLE IF EXISTS `karte`;
DROP TABLE IF EXISTS `medjugradske_polasci`;
DROP TABLE IF EXISTS `autobusi`;
DROP TABLE IF EXISTS `red_voznje`;
DROP TABLE IF EXISTS `vozaci`;
DROP TABLE IF EXISTS `linije_stanice`;
DROP TABLE IF EXISTS `gradske_linije`;
DROP TABLE IF EXISTS `medjugradske_linije`;
DROP TABLE IF EXISTS `linije`;
DROP TABLE IF EXISTS `poruke`;
DROP TABLE IF EXISTS `stanice`;
DROP TABLE IF EXISTS `prevoznici`;
DROP TABLE IF EXISTS `korisnici`;
DROP TABLE IF EXISTS `kategorije_zaposlenja`;



CREATE TABLE `kategorije_zaposlenja` (
  `naziv` varchar(255) NOT NULL,
  `cenaKarte` int(11) NOT NULL,
  `tip` bit(1) NOT NULL,
  PRIMARY KEY (`naziv`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `prevoznici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adresa` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `telefon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `stanice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `tip_stanice` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `poruke` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum_poruke` datetime DEFAULT NULL,
  `poruka` varchar(255) DEFAULT NULL,
  `korisnik_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoc21r4o7a89gmdo5bpiq8qrqn` (`korisnik_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `linije` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `odredisna_stanica_id` int(11) DEFAULT NULL,
  `polazna_stanica_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK18tu33g1r0rkuwf7rpqjj6gll` (`odredisna_stanica_id`),
  KEY `FKstnigd8d63gki989v6diie00p` (`polazna_stanica_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `medjugradske_linije` (
  `id` int(11) NOT NULL,
  `prevoznik_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbp207ktsidnxqm6otqd3rw8nl` (`prevoznik_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `gradske_linije` (
  `aktivna` bit(1) DEFAULT NULL,
  `broj_linije` int(11) DEFAULT NULL,
  `otkazana_do` datetime DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_arf3sx1d52byq90xw3nfud707` (`broj_linije`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `linije_stanice` (
  `linije_id` int(11) NOT NULL,
  `medjustanice_id` int(11) NOT NULL,
  `medjustanice_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`linije_id`,`medjustanice_ORDER`),
  KEY `FKm79w77dlkgq4px2r5t9gbd28u` (`medjustanice_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `red_voznje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `smer` bit(1) DEFAULT NULL,
  `vreme_polaska` datetime DEFAULT NULL,
  `gradska_linija_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKekiip7dqjk9gx27xk2l1nh6l` (`gradska_linija_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



--popunjavanje baze


INSERT INTO `kategorije_zaposlenja`
(`naziv`,
`cenaKarte`, `tip`)
VALUES
("student", 1200, 0),
("nezaposlen", 1200, 0),
("zaposlen", 2400, 0),
("lice_sa_invaliditetom", 1000, 1),
("penzioner", 1200, 1);

INSERT INTO `korisnici`
(`admin`,
`admin_potvrdio`,
`adresa`,
`datum_rodjenja`,
`ime`,
`kategorija_zaposlenja`,
`korisnicko_ime`,
`korisnik_validan`,
`lozinka`,
`mejl`,
`prezime`,
`telefon`)
VALUES
(true, true,'Gosina 3, Kragujevac','1996-03-08','Mladjan','student','mladjankg',true,'sifra123','mladjan.mihajlovic@hotmail.com','Mihajlovic','0605044654'),
(false, true, 'Bana Ivanisa 5, Beograd', '1987-12=31', 'Milos', 'zaposlen', 'milosbg', true, 'losmi11', 'losmi.bg@gmail.com', 'Radojkovic', '0641352665'),
(false, false, 'Nikole Pašića 22, Kragujevac', '1974-04-16', 'Ivana', 'nezaposlen', 'iki74', false, 'mafin95', 'ivana.karovic@yahoo.com', 'Karovic', '0638482164'),
(false, false, 'Internacionalnih brigada 23', '1996-10-14', 'Uroš', 'student', 'uros96', true, 'uros96', 'uros96@gmail.com', 'Marinković', '0654335654');
INSERT INTO `prevoznici`
(`adresa`,
`logo_url`,
`naziv`,
`telefon`)
VALUES
('Zorana Djindjica 27, Zemun', null, 'Unacop', '011425774'),
('Mihaila Gavrilovica 4, Kragujevac', 'janjusevic.jpg', 'Janjusevic', '034821552'),
('Autoput za Nis bb, Beograd', null, 'lasta.jpg', '011421850');

INSERT INTO `autobusi`
(`broj_mesta`,
`marka`,
`model`,
`slike`,
`prevoznik_id`,
`registracija`)
VALUES
(56, 'Mercedes Benz', 'TRAVEGO 17 SHD', 'travego1.jpg;travego2.jpg;travego3.jpg', 1, 'KG-123-HJ'),
(22, 'Mercedes Benz', 'I753', 'sprinter1.jpg;sprinter2.jpg;sprinter3.jpg', 2, 'BG-042-LF'),
(76, 'Neoplan', 'Cityliner 1116 Hl', 'neoplan1.jpg;neoplan2.jpg', 3, 'NS-078-MP'),
(42, 'Setra', '317 HDH', 'setrahdh1.jpg;setrahdh2.jpg', 1, 'KG-012-ER'),
(23, 'Setra', '431 DT', 'setradt1.jpg;setradt2.jpg', 2, 'BG-638-KZ'),
(33, 'Man', 'AO1', 'man1.jpg;man2.jpg;man3.jpg;man4.jpg;man5.jpg', 3, 'PB-026-IS');



INSERT INTO `stanice`
(`naziv`, `tip_stanice`)
VALUES
('Sumski Raj', '0'),
('Rosici', '0'),
('Petrovac', '0'),
('Jabucar', '0'),
('Susica', '0'),
('Bolnica', '0'),
('Ured', '0'),
('Sest topola', '0'),
('Erdec', '0'),
('Koricani', '0'),
('Stanovo', '0'),
('Dom starih', '0'),
('Sumarice', '0'),
('Vojska', '0'),
('Divostin', '0'),
('Okretnica Petrovac', '0'),
('Škola Petrovac', '0'),
('Bare', '0'),
('Samoposluga', '0'),
('Stovarište', '0'),
('Jabučar', '0'),
('Bogoslovija', '0'),
('Pivara', '0'),
('Stara Ćuprija', '0'),
('Ždraljica', '0');

INSERT INTO `stanice`
(`naziv`, `tip_stanice`, `latitude`, `longitude`)
VALUES
('Kragujevac', '1', 44.011787, 20.910962),
('Beograd', '1', 44.810796, 20.466320),
('Jagodina', '1', 43.979820, 21.257281),
('Novi Sad', '1', 45.256661, 19.834807),
('Nis', '1', 43.318426, 21.894754),         --30
('Mladenovac', '1', 44.438534, 20.694580),  
('Topola', '1', 44.252526, 20.688521),
('Novi Pazar', '1', 43.141101, 20.518478),
('Batocina', '1', 44.148589, 21.071541),
('Cuprija', '1', 43.928183, 21.381939),     --35
('Paracin', '1', 43.864151, 21.409312),
('Leskovac', '1', 42.992574, 21.948299),
('Kraljevo', '1', 43.725181, 21.948299),
('Čačak', '1', 43.893338, 20.349400),
('Šabac', '1', 44.751061, 19.687584),       --40
('Kosovska Mitrovica', '1', 42.893922, 20.865802),
('Sombor', '1', 45.768223, 19.121562);

INSERT INTO `linije`
(`odredisna_stanica_id`, `polazna_stanica_id`)
VALUES
(27, 26), --Beograd Kragujevac preko autoputa
(33, 27), --Novi Pazar Beograd preko Kraljeva i KGa i Topole
(26, 36), --Kragujevac PAracin
(29, 40); --Novi Sad sabac

/*
INSERT INTO `gradske_linije`
(`broj_linije`, `aktivna`, `id`)
VALUES
(25, 1, 1),
(15, 1, 2);
*/
INSERT INTO `vozaci`
(
`datum_rodjenja`,
`ime`,
`pocetak_voznje`,
`prezime`,
`tip`,
`gradska_linija_id`,
`prevoznik`)
VALUES
('1974-10-03', 'Mirko', '1999-03-29', 'Vasiljevic', 0, 1, null),
('1984-02-28', 'Vasilije', '2004-03-29', 'Dobrilovic', 0, null, null),
('1990-06-09', 'Dragan', '2015-08-17', 'Markovic', 1, null, 1),
('1963-10-03', 'Dragoljub', '1987-03-29', 'Simonovic',0, 2, null),
('1960-11-01', 'Zivorad', '1985-08-17', 'Mihajlovic', 1, null, 2),
('1972-03-17', 'Marko', '2091-12-02', 'Dragutinovic', 1, null, 3),
('1981-10-25', 'Radenko', '2004-01-29', 'Bajic', 1, null, 1);


INSERT INTO `medjugradske_linije`
(`id`,`prevoznik_id`)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 2);

INSERT INTO `linije_stanice`
(`linije_id`, `medjustanice_id`, `medjustanice_ORDER`)
VALUES
(1, 34, 0),
(2, 38, 0),
(2, 26, 1),
(2, 32, 2),
(2, 31, 3),
(3, 34, 0);
(3, 28, 1);
(3, 35, 2);
(3, 36, 3);

/*INSERT INTO `red_voznje`
(`smer`, `vreme_polaska`, `gradska_linija_id`)
VALUES
(true, '05:15', 1),
(true, '08:15', 1),
(true, '11:30', 1),
(true, '14:40', 1),
(true, '19:10', 1),
(false, '06:00', 1),
(false, '09:00', 1),
(false, '13:55', 1),
(false, '16:55', 1),
(false, '19:55', 1),
(true, '05:30', 2),
(true, '08:00', 2),
(true, '10:30', 2),
(true, '13:40', 2),
(true, '19:45', 2),
(false, '06:00', 2),
(false, '08:30', 2),
(false, '12:55', 2),
(false, '16:30', 2),
(false, '19:05', 2);

INSERT INTO `medjugradske_polasci`
(`preostalo_mesta`, `vreme_dolaska`, `vreme_polaska`, `autobus_id`, `medjugradska_linija_id`, `vozac_id`)
VALUES
(55, '2018-09-23 23:00', '2018-09-23 21:00', 1, 3, 3);


INSERT INTO `karte`
(
`admin_potvrdio`,
`datum_kupovine`,
`datum_vazenja`,
`odobrena`,
`tip`,
`korisnik_id`,
`linija_id`)
VALUES

(false, '2018-08-18', null, false, 0, 2, null),
(false, '2018-08-17', null, false, 0, 3, null);
*/
