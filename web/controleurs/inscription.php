<?php
require_once '../modeles/dao/param.php';
require_once '../modeles/dao/utilisateurDAO.php';
require_once '../modeles/dao/dBConnex.php';


print(json_encode(utilisateurDAO::inscription($_POST['login'], $_POST['mdp'], $_POST['nom'], 
$_POST['prenom'], $_POST['telephone'],$_POST['mail'],"exposant",$_POST['raisonSociale'],$_POST['activite'],
date("Y"), $_POST['siteInternet'],$_POST['dejaExpose'],"0",$_POST['libelleSecteur'])));