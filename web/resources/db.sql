/* 
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
/**
 * Author:  Mlađan
 * Created: Sep 12, 2018
 */


USE `prodaja_karata`;

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
(false, false, 'Nikole Pasica 22, Kragujevac', '1974-04-16', 'Ivana', 'nezaposlen', 'iki74', false, 'mafin95', 'ivana.karovic@yahoo.com', 'Karovic', '0638482164');

INSERT INTO `autobusi`
(`broj_mesta`,
`marka`,
`model`,
`slike`)
VALUES
(57, 'Mercedes', 'InterBus 234', null),
(97, 'Ikarbus', 'I753', null),
(76, 'Man', 'D523', null),
(42, 'Mercedes', 'InterBus 428', null),
(23, 'Vulovic', 'V9', null),
(33, 'Man', 'D772', null);

INSERT INTO `prevoznici`
(`adresa`,
`logo_url`,
`naziv`,
`telefon`)
VALUES
('Zorana Djindjica 27, Zemun', null, 'Unacop', '011425774'),
('Mihaila Gavrilovica 4, Kragujevac', null, 'Janjusevic', '034821552'),
('Autoput za Nis bb, Beograd', null, 'Lasta', '011421850');

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
('Draca', '0');

INSERT INTO `stanice`
(`naziv`, `tip_stanice`, `latitude`, `longitude`)
VALUES
('Kragujevac', '1', 44.011787, 20.910962),
('Beograd', '1', 44.810796, 20.466320),
('Jagodina', '1', 43.979820, 21.257281),
('Novi Sad', '1', 45.256661, 19.834807),
('Nis', '1', 43.318426, 21.894754),
('Mladenovac', '1', 44.438534, 20.694580),
('Topola', '1', 44.252526, 20.688521),
('Novi Pazar', '1', 43.141101, 20.518478),
('Batocina', '1', 44.148589, 21.071541),
('Cuprija', '1', 43.928183, 21.381939),
('Paracin', '1', 43.864151, 21.409312),
('Leskovac', '1', 42.992574, 21.948299),
('Sombor', '1', 45.768223, 19.121562);

INSERT INTO `linije`
(`odredisna_stanica_id`, `polazna_stanica_id`)
VALUES
(9,1),
(10, 16),
(18, 17);

INSERT INTO `gradske_linije`
(`broj_linije`, `aktivna`, `id`)
VALUES
(25, 1, 1),
(15, 1, 2);

INSERT INTO `vozaci`
(
`datum_rodjenja`,
`ime`,
`pocetak_voznje`,
`prezime`,
`tip`,
`gradska_linija_id`)
VALUES
('1974-10-03', 'Mirko', '1999-03-29', 'Vasiljevic', 0, 1),
('1984-02-28', 'Vasilije', '2004-03-29', 'Dobrilovic', 0, null),
('1990-06-09', 'Dragan', '2015-08-17', 'Markovic', 1, null),
('1963-10-03', 'Dragoljub', '1987-03-29', 'Simonovic',0, 2);


INSERT INTO `medjugradske_linije`
(`id`,`prevoznik_id`)
VALUES
(3, 1);

INSERT INTO `linije_stanice`
(`linije_id`, `medjustanice_id`, `medjustanice_ORDER`)
VALUES
(3, 25, 0);

INSERT INTO `red_voznje`
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

INSERT INTO `prodaja_karata`.`medjugradske_polasci`
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