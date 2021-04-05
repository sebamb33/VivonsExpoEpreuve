<?php
require_once '../modeles/dao/param.php';
require_once '../modeles/dao/utilisateurDAO.php';
require_once '../modeles/dao/dBConnex.php';


print(json_encode(utilisateurDAO::getCodeSecteur($_POST['libelleSecteur'])));