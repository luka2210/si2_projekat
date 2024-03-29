-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2022 at 09:54 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `si2_projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `knjige`
--

CREATE TABLE `knjige` (
  `naziv` varchar(50) NOT NULL,
  `godina` int(11) NOT NULL,
  `izdavac` varchar(30) NOT NULL,
  `isbn` int(30) NOT NULL,
  `broj` int(11) NOT NULL,
  `slika` varchar(100) NOT NULL,
  `slika2` varchar(100) NOT NULL,
  `tip` enum('diplomski','doktorski','izdanje','knjiga','master','monografija','naucni','poglavlje','praktikum','udzbenik','zavrsni','zbirka') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `knjige`
--

INSERT INTO `knjige` (`naziv`, `godina`, `izdavac`, `isbn`, `broj`, `slika`, `slika2`, `tip`) VALUES
('OET praktikum', 2016, 'Zavod za udžbenike', 168212437, 25, '/slike/oet_praktikum.jpg', '/slike/korice.jpg', 'praktikum'),
('Neuronske mreze', 2016, '', 530145156, 1, '/slike/neuronske_mreze.png', '/slike/korice.jpg', 'zavrsni'),
('Antropološka obeležja rukometaša', 2018, '', 828940409, 0, '/slike/antropoloska_obelezja_rukometasa.png', '/slike/korice.jpg', 'master'),
('Zbirka zadataka iz matematike', 1999, 'Zavod za udžbenike', 978972501, 3, '/slike/zbirka_zadataka_iz_matematike.png', '/slike/korice.jpg', 'zbirka'),
('Digitalni svet ', 2019, 'Klett', 979145821, 15, '/slike/digitalni_svet.png', '/slile/korice.jpg', 'udzbenik');

-- --------------------------------------------------------

--
-- Table structure for table `knjige_pisci`
--

CREATE TABLE `knjige_pisci` (
  `isbn` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `knjige_pisci`
--

INSERT INTO `knjige_pisci` (`isbn`, `id`) VALUES
(979145821, 1),
(979145821, 2),
(978972501, 3),
(168212437, 4),
(168212437, 5),
(168212437, 6),
(168212437, 7),
(168212437, 8),
(530145156, 9),
(530145156, 10),
(828940409, 11),
(828940409, 13);

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(30) NOT NULL,
  `tip` varchar(30) DEFAULT 'student',
  `odobren` tinyint(4) DEFAULT 0,
  `blokiran` tinyint(4) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`id`, `ime`, `prezime`, `username`, `email`, `password`, `tip`, `odobren`, `blokiran`) VALUES
(1, 'Darko', 'Simić', 'darkos', 'darkosimic@gmail.com', 'lozinka123', 'student', 1, 1),
(2, 'Marko', 'Perić', 'markop', 'markoperic@gmail.com', 'lozinka123', 'student', 1, 0),
(5, 'Marko', 'Petrović', 'markop1', 'markopetrovic@gmail.com', 'lozinka123', 'student', 1, 0),
(6, 'Lazar', 'Stević', 'lazars', 'lazars@gmail.com', 'lozinka123', 'student', 1, 1),
(7, 'Lazar', 'Savić', 'lazarsa', 'lazarsa@gmail.com', 'lozinka123', 'student', 1, 0),
(8, 'Milica', 'Vukobratović', 'milicaa', 'milicavukobratovic12345@gmail.com', 'lozinka123!', 'student', 1, 0),
(9, 'Јелена', 'Перић', 'jelenap', 'јеленап@gmail.com', 'лозинка123', 'student', 1, 0),
(10, 'Petar', 'Milić', 'petarm', 'petarm@hotmail.com', 'kozinka123', 'student', 1, 0),
(15, 'Mihailo', 'Prodanović', 'miki123', 'mikikg@gmail.com', 'lozinka123', 'student', 0, 0),
(16, 'Luka', 'Jović', 'lukal', 'lukajovic@gmail.com', 'lozinka123', 'bibliotekar', 1, 0),
(17, 'Valentina', 'Stojković', 'vacaa', 'vaca@gmail.com', 'lozinka123', 'student', 1, 0),
(18, 'Stefan', 'Radenković', 'stefan1', 'stefan1@gmail.com', 'lozinka123', 'admin', 1, 0),
(19, 'Stefan', 'Gagić', 'stefan2', 'stefan2@gmail.com', 'stefan12345', 'admin', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pisci`
--

CREATE TABLE `pisci` (
  `id` int(11) NOT NULL,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `godina` int(11) NOT NULL,
  `tip` enum('autor','editor','mentor') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pisci`
--

INSERT INTO `pisci` (`id`, `ime`, `prezime`, `godina`, `tip`) VALUES
(1, 'Dragan', 'Cvetković', 1957, 'autor'),
(2, 'Isidora', 'Paunović', 1981, 'autor'),
(3, 'Vene', 'Bogosavov', 1932, 'autor'),
(4, 'Gordana', 'Mijatović', 1976, 'autor'),
(5, 'Maja', 'Todorović', 1975, 'autor'),
(6, 'Vela', 'Čoja', 1986, 'autor'),
(7, 'Goran', 'Stojković', 1966, 'autor'),
(8, 'Goran', 'Stanojević', 1967, 'autor'),
(9, 'Branko', 'Latinović', 1955, 'mentor'),
(10, 'Zoran', 'Malić', 1998, 'autor'),
(11, 'Mehmed', 'Dolovac', 1995, 'autor'),
(13, 'Fahrudin', 'Mavrić', 1966, 'mentor');

-- --------------------------------------------------------

--
-- Table structure for table `rezervacije`
--

CREATE TABLE `rezervacije` (
  `id` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `datum` datetime DEFAULT NULL,
  `istekla` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `rezervacije`
--

INSERT INTO `rezervacije` (`id`, `isbn`, `datum`, `istekla`) VALUES
(5, 168212437, '2022-03-20 23:17:00', 1),
(1, 168212437, '2022-03-21 00:00:00', 1),
(1, 168212437, '2022-03-21 00:00:00', 1),
(1, 168212437, '2022-03-21 00:00:00', 1),
(1, 168212437, '2022-03-21 00:00:00', 1),
(1, 979145821, '2022-03-21 00:00:00', 1),
(1, 978972501, '2022-03-21 00:00:00', 1),
(5, 978972501, '2022-03-21 16:16:10', 1),
(2, 978972501, '2022-03-21 16:17:45', 1),
(2, 979145821, '2022-03-21 22:40:02', 1),
(6, 168212437, '2022-03-21 23:07:19', 1),
(6, 979145821, '2022-03-21 23:07:56', 1),
(7, 168212437, '2022-03-21 23:08:50', 1),
(7, 979145821, '2022-03-21 23:09:01', 1),
(10, 168212437, '2022-03-25 16:26:23', 1),
(1, 168212437, '2022-03-26 15:47:32', 1),
(1, 978972501, '2022-03-26 17:42:21', 1),
(1, 979145821, '2022-03-26 18:34:47', 1),
(10, 168212437, '2022-03-26 20:56:44', 1),
(1, 168212437, '2022-03-27 01:32:32', 1),
(1, 978972501, '2022-03-27 01:33:23', 1),
(1, 979145821, '2022-03-27 01:34:44', 1),
(10, 168212437, '2022-04-04 10:09:07', 1),
(2, 168212437, '2022-04-04 13:47:35', 1),
(1, 168212437, '2022-04-06 12:07:55', 1),
(1, 978972501, '2022-04-06 12:44:48', 1),
(1, 168212437, '2022-04-06 17:41:50', 1),
(2, 168212437, '2022-04-06 17:42:38', 1),
(1, 168212437, '2022-04-06 18:10:36', 1),
(1, 978972501, '2022-04-06 18:10:41', 0),
(1, 979145821, '2022-04-06 18:10:46', 0),
(2, 168212437, '2022-04-06 18:11:02', 1),
(2, 978972501, '2022-04-06 18:11:09', 0),
(2, 979145821, '2022-04-06 18:11:13', 0),
(1, 168212437, '2022-04-06 20:30:18', 1),
(2, 530145156, '2022-04-27 23:34:36', 0),
(2, 168212437, '2022-04-28 09:26:39', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `knjige`
--
ALTER TABLE `knjige`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `knjige_pisci`
--
ALTER TABLE `knjige_pisci`
  ADD PRIMARY KEY (`id`,`isbn`),
  ADD KEY `isbn` (`isbn`);

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `pisci`
--
ALTER TABLE `pisci`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rezervacije`
--
ALTER TABLE `rezervacije`
  ADD KEY `id` (`id`),
  ADD KEY `isbn` (`isbn`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnici`
--
ALTER TABLE `korisnici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `pisci`
--
ALTER TABLE `pisci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `knjige_pisci`
--
ALTER TABLE `knjige_pisci`
  ADD CONSTRAINT `knjige_pisci_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pisci` (`id`),
  ADD CONSTRAINT `knjige_pisci_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `knjige` (`isbn`);

--
-- Constraints for table `rezervacije`
--
ALTER TABLE `rezervacije`
  ADD CONSTRAINT `rezervacije_ibfk_1` FOREIGN KEY (`id`) REFERENCES `korisnici` (`id`),
  ADD CONSTRAINT `rezervacije_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `knjige` (`isbn`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `proveri_rezervacije` ON SCHEDULE EVERY 1 HOUR STARTS '2022-03-26 18:15:14' ON COMPLETION NOT PRESERVE ENABLE DO update rezervacije set rezervacije.istekla = 1 where datediff(sysdate(), rezervacije.datum) >= 1$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
