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



	public static function selectionnerSecteur($univer){
		try{
			$requetePrepa = DBConnex::getInstance()->prepare("select libelleS 
			FROM secteur,univers
			WHERE univers.libelleU = :univers
			AND secteur.codeU = univers.codeU
			");
			$requetePrepa->bindParam("univers",$univer);
			$requetePrepa->execute();
			// $requetePrepa->debugDumpParams();
			$reponse = $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}
	

}

