<?php

class exposantDAO
{
    public static function chargementExposant($univers)
    {
        try
        {
            $sql ="select raisonSociale,nom,prenom,telephone FROM utilisateur Where statut=\"exposant\" AND codeS in (SELECT codeS FROM secteur WHERE codeU in (Select codeU FROM univers WHERE libelleU=:univers))";
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
    public static function chercheExposantNonInscrit()
    {
        try {
            $sql = "SELECT raisonSociale,activite FROM utilisateur WHERE statut=\"exposant\" and etatInscription=0 ";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->execute();
            return $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
        }catch(Exception $e)
        {
            return null;
        }
    }
    public static function inscriptionExposant($rs)
    {
        try
        {
            $sql="UPDATE  utilisateur set etatInscription=1 WHERE raisonSociale=:rs";
            $requetePrepa=DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam(":rs",$rs);
            $requetePrepa->execute();
            $reponse=$requetePrepa->fetchAll(PDO::FETCH_ASSOC);

        }catch (Exception $e)
        {
            $reponse="";
        }
        return $reponse;
    }

}

