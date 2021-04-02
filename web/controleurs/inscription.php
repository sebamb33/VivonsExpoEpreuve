<?php
require_once '../modeles/dao/param.php';
require_once '../modeles/dao/utilisateurDAO.php';
require_once '../modeles/dao/dBConnex.php';

$_POST['login'] = "login";
$_POST['mdp'] = "1234";
$_POST['nom'] = "nom";
$_POST['prenom'] = "prenom";
$_POST['telephone'] = "07";
$_POST['mail'] = "mail";
$_POST['statut'] = "Exposant";
$_POST['raisonSociale'] = "sebAndCo";
$_POST['activite'] = "Agriculture";
$_POST['anneeInscription'] = "2001";
$_POST['siteInternet'] = "google.com";
$_POST['dejaExpose'] = 0;
$_POST['etatInscription'] = 0;
$_POST['codeS'] = "17";

print(json_encode(utilisateurDAO::inscription($_POST['login'], $_POST['mdp'], $_POST['nom'], 
$_POST['prenom'], $_POST['telephone'],$_POST['mail'],$_POST['statut'],$_POST['raisonSociale'],$_POST['activite'],
$_POST['anneeInscription'], $_POST['siteInternet'],$_POST['dejaExpose'],$_POST['etatInscription'],$_POST['codeS'])));