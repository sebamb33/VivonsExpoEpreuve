<?php
require_once '../modeles/dao/param.php';
require_once '../modeles/dao/visiteurDAO.php';
require_once '../modeles/dao/dBConnex.php';


print (json_encode(visiteurDAO::infoExposant($_POST["rs"])));
