<?php
class VisiteurDAO
{
    public static function chercheExposant($univers)
    {
        try
        {
            $sql="SELECT *
            FROM utilisateur 
            WHERE statut=\"exposant\" 
            AND codeS in (SELECT codeS 
                            FROM secteur WHERE codeU in (SELECT codeU FROM univers WHERE libelleU=:univ))";

            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("univ",$univers);
            $requetePrepa->execute();
            $reponse=$requetePrepa->fetchAll(PDO::FETCH_ASSOC);
        }catch(Exception $e)
        {
            return null;
        }
    
        return  $reponse;
    }

    public static function infoExposant($raisonSociale)
    {
        try
        {
            $sql="SELECT activite,anneeInscription,siteInternet FROM utilisateur WHERE raisonSociale=:rs";
            $requetePrepa=DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam(":rs",$raisonSociale);
            $requetePrepa->execute();
            $reponse=$requetePrepa->fetch(PDO::FETCH_ASSOC);
        }catch (Exception $e)
        {
            return null;
        }
        return $reponse;
    }


}
?>