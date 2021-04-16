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


<<<<<<< HEAD

	public static function getLibelleSecteurs(){
        $listeLibelleSecteurs = array();
        $requetePrepa = DBConnex::getInstance()->prepare("select libelleS from secteur");
    
        $requetePrepa->execute();
        $listeLibelleSecteurs = $requetePrepa->fetchAll(PDO::FETCH_ASSOC); 

        return $listeLibelleSecteurs;
        
    }

	public static function getCodeSecteur($unLibelleSecteur){
		try{
			$sql = "SELECT codeS FROM `secteur` WHERE libelleS = :libelleSecteur;" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->bindParam("libelleSecteur", $unLibelleSecteur);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}


	public static function inscription($unLogin, $unMdp, $unNom, $unPrenom, $unTelephone, $unMail, $unStatut, $uneRaisonSociale, $uneActivite, $uneAnneeInscription, $unSiteInternet, $dejaExpose, $unEtatInscription, $unlibelleSecteur){
		try{
			$sql = "INSERT INTO `utilisateur` (`id`, `Login`, `Mdp`, `nom`, `prenom`, `telephone`, `mail`, `statut`, `raisonSociale`, `activite`, `anneeInscription`, `siteInternet`, `dejaExpose`, `etatInscription`, `codeS`) 
			VALUES (NULL, :login, :mdp, :nom, :prenom, :telephone, :mail, :statut, :raisonSociale, :activite, :anneeInscription, :siteInternet, :dejaExpose, :etatInscription, (SELECT codeS FROM secteur WHERE libelleS = :libelleSecteur));" ;

			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$mdp =  md5($unMdp);
			$requetePrepa->bindParam("login", $unLogin);
			$requetePrepa->bindParam("mdp", $mdp);
			$requetePrepa->bindParam("nom", $unNom);
			$requetePrepa->bindParam("prenom", $unPrenom);
			$requetePrepa->bindParam("telephone", $unTelephone);
			$requetePrepa->bindParam("mail", $unMail);
			$requetePrepa->bindParam("statut", $unStatut);
			$requetePrepa->bindParam("raisonSociale", $uneRaisonSociale);
			$requetePrepa->bindParam("activite", $uneActivite);
			$requetePrepa->bindParam("anneeInscription", $uneAnneeInscription);
			$requetePrepa->bindParam("siteInternet", $unSiteInternet);
			$requetePrepa->bindParam("dejaExpose", $dejaExpose);
			$requetePrepa->bindParam("etatInscription", $unEtatInscription);
			$requetePrepa->bindParam("libelleSecteur", $unlibelleSecteur);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}
=======
// 	public static function inscription($, $mdp){
// 		try{
// 			$sql = "INSERT INTO " ;
			
// 			$requetePrepa = DBConnex::getInstance()->prepare($sql);
// 			$mdp =  md5($mdp);
// 			$requetePrepa->bindParam("login", $login);
// 			$requetePrepa->bindParam("mdp", $mdp);
// 			$requetePrepa->execute();
// 			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
// 		}catch(Exception $e){
// 			$reponse = "";
// 		}
// 		return $reponse;
// 	}
// }
>>>>>>> cb01adafa52557b079f70c07dac5ebd2573a5533
}
