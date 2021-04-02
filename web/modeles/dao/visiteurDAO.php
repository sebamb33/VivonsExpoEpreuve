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
            //Un commentaire parcque il faut commenter
        }
    
        return  $reponse
    }
}
?>