<?php
require_once '../modeles/dao/param.php';
require_once '../modeles/dao/visiteurDAO.php';
require_once '../modeles/dao/dBConnex.php';


print(json_encode(visiteurDAO::chercheExposant($_POST["univers"])));