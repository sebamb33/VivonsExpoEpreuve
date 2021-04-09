-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 09 avr. 2021 à 11:26
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `vivonexpo`
--

-- --------------------------------------------------------

--
-- Structure de la table `allee`
--

DROP TABLE IF EXISTS `allee`;
CREATE TABLE IF NOT EXISTS `allee` (
  `numH` varchar(1) NOT NULL,
  `codeA` varchar(2) NOT NULL,
  PRIMARY KEY (`numH`,`codeA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `allee`
--

INSERT INTO `allee` (`numH`, `codeA`) VALUES
('1', 'A'),
('1', 'B'),
('1', 'C'),
('1', 'D');

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

DROP TABLE IF EXISTS `demande`;
CREATE TABLE IF NOT EXISTS `demande` (
  `numD` varchar(4) NOT NULL,
  `dateD` date DEFAULT NULL,
  `motif` varchar(40) DEFAULT NULL,
  `statusDemande` tinyint(1) NOT NULL,
  `id` int(10) NOT NULL,
  PRIMARY KEY (`numD`),
  KEY `DEMANDE_UTILISATEUR_FK` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`numD`, `dateD`, `motif`, `statusDemande`, `id`) VALUES
('1', '2021-03-17', 'Exemple motif', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `hall`
--

DROP TABLE IF EXISTS `hall`;
CREATE TABLE IF NOT EXISTS `hall` (
  `numH` varchar(1) NOT NULL,
  PRIMARY KEY (`numH`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hall`
--

INSERT INTO `hall` (`numH`) VALUES
('1'),
('2'),
('3');

-- --------------------------------------------------------

--
-- Structure de la table `secteur`
--

DROP TABLE IF EXISTS `secteur`;
CREATE TABLE IF NOT EXISTS `secteur` (
  `codeS` varchar(2) NOT NULL,
  `libelleS` varchar(25) DEFAULT NULL,
  `codeU` varchar(3) NOT NULL,
  PRIMARY KEY (`codeS`),
  KEY `SECTEUR_UNIVERS_FK` (`codeU`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `secteur`
--

INSERT INTO `secteur` (`codeS`, `libelleS`, `codeU`) VALUES
('1', 'amelioration habitat', '1'),
('10', 'Presse', '1'),
('11', 'vivons bois', '1'),
('12', 'equipements sportifs', '2'),
('13', 'ligues et comites', '2'),
('14', 'neige', '2'),
('15', 'cadeaux', '1'),
('16', 'createurs', '3'),
('17', 'epicerie fine', '3'),
('18', 'producteurs', '3'),
('19', 'vins et spiritueux', '3'),
('2', 'ameublement', '1'),
('20', 'auto', '4'),
('21', 'roulez loisirs', '4'),
('22', 'camping cars', '5'),
('23', 'organisme de financement', '5'),
('24', 'vehicules occasions', '5'),
('3', 'cheminees', '1'),
('4', 'cuisines', '1'),
('5', 'decoration', '1'),
('6', 'equipement menager', '1'),
('7', 'immobilier', '1'),
('8', 'paysagiste', '1'),
('9', 'piscines', '1');

-- --------------------------------------------------------

--
-- Structure de la table `stand`
--

DROP TABLE IF EXISTS `stand`;
CREATE TABLE IF NOT EXISTS `stand` (
  `numH` varchar(1) NOT NULL,
  `codeA` varchar(2) NOT NULL,
  `numT` varchar(2) NOT NULL,
  `numS` varchar(3) NOT NULL,
  `id` int(10) DEFAULT NULL,
  PRIMARY KEY (`numH`,`codeA`,`numT`,`numS`),
  KEY `STAND_TRAVEE0_FK` (`numT`),
  KEY `STAND_UTILISATEUR1_FK` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `travee`
--

DROP TABLE IF EXISTS `travee`;
CREATE TABLE IF NOT EXISTS `travee` (
  `numT` varchar(2) NOT NULL,
  `numH` varchar(1) NOT NULL,
  `codeS` varchar(2) NOT NULL,
  PRIMARY KEY (`numT`),
  KEY `TRAVEE_HALL_FK` (`numH`),
  KEY `TRAVEE_SECTEUR0_FK` (`codeS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `travee`
--

INSERT INTO `travee` (`numT`, `numH`, `codeS`) VALUES
('01', '1', '1');

-- --------------------------------------------------------

--
-- Structure de la table `univers`
--

DROP TABLE IF EXISTS `univers`;
CREATE TABLE IF NOT EXISTS `univers` (
  `codeU` varchar(3) NOT NULL,
  `libelleU` varchar(25) DEFAULT NULL,
  `numH` varchar(1) NOT NULL,
  PRIMARY KEY (`codeU`),
  KEY `UNIVERS_HALL_FK` (`numH`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `univers`
--

INSERT INTO `univers` (`codeU`, `libelleU`, `numH`) VALUES
('1', 'Maison', '1'),
('2', 'Sport', '1'),
('3', 'Les fêtes', '1'),
('4', 'Auto', '1'),
('5', 'Evasion', '3');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `Login` varchar(25) NOT NULL,
  `Mdp` varchar(35) DEFAULT NULL,
  `nom` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `mail` varchar(40) DEFAULT NULL,
  `statut` varchar(20) NOT NULL,
  `raisonSociale` varchar(40) DEFAULT NULL,
  `activite` varchar(25) DEFAULT NULL,
  `anneeInscription` int(11) DEFAULT NULL,
  `siteInternet` varchar(100) DEFAULT NULL,
  `dejaExpose` tinyint(1) DEFAULT NULL,
  `etatInscription` tinyint(1) DEFAULT NULL,
  `codeS` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UTILISATEUR_SECTEUR0_FK` (`codeS`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `Login`, `Mdp`, `nom`, `prenom`, `telephone`, `mail`, `statut`, `raisonSociale`, `activite`, `anneeInscription`, `siteInternet`, `dejaExpose`, `etatInscription`, `codeS`) VALUES
(1, 'root', '81dc9bdb52d04dc20036dbd8313ed055', 'root', 'root', '0668191385', 'seb@gmail.com', 'exposant', 'sebAndCo', 'Agriculture', 2021, 'lol', 0, 0, '1'),
(2, 'staff', '81dc9bdb52d04dc20036dbd8313ed055', 'Descamps', 'Clementin', NULL, NULL, 'staff', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `allee`
--
ALTER TABLE `allee`
  ADD CONSTRAINT `ALLEE_HALL_FK` FOREIGN KEY (`numH`) REFERENCES `hall` (`numH`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `DEMANDE_UTILISATEUR_FK` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `secteur`
--
ALTER TABLE `secteur`
  ADD CONSTRAINT `SECTEUR_UNIVERS_FK` FOREIGN KEY (`codeU`) REFERENCES `univers` (`codeU`);

--
-- Contraintes pour la table `stand`
--
ALTER TABLE `stand`
  ADD CONSTRAINT `STAND_ALLEE_FK` FOREIGN KEY (`numH`,`codeA`) REFERENCES `allee` (`numH`, `codeA`),
  ADD CONSTRAINT `STAND_TRAVEE0_FK` FOREIGN KEY (`numT`) REFERENCES `travee` (`numT`),
  ADD CONSTRAINT `STAND_UTILISATEUR1_FK` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `travee`
--
ALTER TABLE `travee`
  ADD CONSTRAINT `TRAVEE_HALL_FK` FOREIGN KEY (`numH`) REFERENCES `hall` (`numH`),
  ADD CONSTRAINT `TRAVEE_SECTEUR0_FK` FOREIGN KEY (`codeS`) REFERENCES `secteur` (`codeS`);

--
-- Contraintes pour la table `univers`
--
ALTER TABLE `univers`
  ADD CONSTRAINT `UNIVERS_HALL_FK` FOREIGN KEY (`numH`) REFERENCES `hall` (`numH`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `UTILISATEUR_SECTEUR0_FK` FOREIGN KEY (`codeS`) REFERENCES `secteur` (`codeS`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
