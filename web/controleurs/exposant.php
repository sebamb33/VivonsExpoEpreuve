<?php
require_once '../modeles/dao/param.php';
require_once '../modeles/dao/exposantDAO.php';
require_once '../modeles/dao/dBConnex.php';

print(json_encode(VisiteurDAO::chargementExposant($_POST['univers'])));
?>