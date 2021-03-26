<?php

class UtilisateurDAO{
    public static function authentification($login , $mdp){
		try{
			$sql = "select id,Login,nom,prenom,telephone,mail,statut,raisonSociale,activite,anneeInscription,siteInternet,dejaExpose,etatInscription,codeS 
					FROM utilisateur
					WHERE Login = :login
					AND Mdp = :mdp" ;
			
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$mdp =  md5($mdp);
			$requetePrepa->bindParam("login", $login);
			$requetePrepa->bindParam("mdp", $mdp);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}
}
