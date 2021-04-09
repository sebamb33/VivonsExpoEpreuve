<?php

class VisiteurDAO
{
    public static function chargementExposant($univers)
    {
        try
        {
            $sql ="select raisonSociale,nom,prenom,telephone FROM utilisateur Where statut= \"exposant\" AND codeS in (SELECT codeS FROM secteur WHERE codeU in (Select codeU FROM univers WHERE libeleU=:univers))";
            $requetePrepa=DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("univers",$univers);
            $requetePrepa->execute();
            $reponse=$requetePrepa->fetchAll(PDO::FETCH_ASSOC);
        }catch(Exception $e)
        {
            $reponse="";
        }

        return $reponse;
    }
}

?>