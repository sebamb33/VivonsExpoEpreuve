<?php
require_once '../modeles/dao/param.php';
require_once '../modeles/dao/univerDAO.php';
require_once '../modeles/dao/dBConnex.php';

$action = $_POST['action'];




if($action == "getSecteur"){
    print(json_encode(univerDAO::selectionnerSecteur($_POST['univers'])));
}

?>