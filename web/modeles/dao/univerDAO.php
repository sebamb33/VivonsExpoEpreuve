<?php

class UniverDAO{
    public static function listeDesUnivers(){
		try{
			$sql = "select * 
					FROM univers
					";
			
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}


	

}
