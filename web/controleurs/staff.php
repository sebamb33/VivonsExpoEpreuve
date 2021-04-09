<?php
require_once '../modeles/dao/param.php';
require_once '../modeles/dao/univerDAO.php';
require_once '../modeles/dao/dBConnex.php';


print(json_encode(UniverDAO::listeDesUnivers()));
