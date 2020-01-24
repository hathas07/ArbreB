import java.awt.image.renderable.ParameterBlock;
import java.util.*;

public class arbre extends noeud{

    public static int M;

    public arbre(int M){
        super(M);
        M = M;
    }


    /* ~~~~~~~~ FONCTIONS DE RECHERCHE ~~~~~~~~ */

    public static arbre.Cles Rechercher(int ClefId, noeud Arbre){
        noeud.Cles Clef = null;

        if(Arbre != null){
            for(int i = 0; i < Arbre.NbCles ; i++){
                if(ClefId == Arbre.tabCles[i].Id){
                    Clef = Arbre.tabCles[i];
                    break;
                }
                else if(ClefId < Arbre.tabCles[i].Id){
                    Clef = Rechercher(ClefId, Arbre.tabPointeur[i]);
                    break;
                }
            }
            if(Clef == null) Clef = Rechercher(ClefId, Arbre.tabPointeur[Arbre.NbCles]);
        }
        return Clef;
    }

    private static noeud RechercherArbre(int Valeur, noeud Arbre) {
        int indice = 0;
        noeud resultat = Arbre;
        if (!Arbre.getfeuille()) {

            while (indice < Arbre.NbCles && Valeur > Arbre.tabCles[indice].Id) {
                indice++;
            }
            if(Arbre.tabPointeur[indice] != null){
                resultat = RechercherArbre(Valeur, Arbre.tabPointeur[indice]);
            }
        }
        return resultat;
    }

    private static int RechercherDansNoeud(int Valeur, noeud Arbre){
        int i = 0;
        while( Valeur != Arbre.tabCles[i].Id){
            i++;
        }
        return i;
    }

    /* ~~~~~~~~ FONCTIONS D'INSERTION ET DE MODIFICATION DE L'ARBRE ~~~~~~~~ */

    public static noeud Inserer(noeud.Cles Cles, noeud Arbre){
        Arbre = RechercherArbre(Cles.Id, Arbre);
        InsererDsTab(Cles, Arbre);
        if(Arbre.NbCles == 2*M+1){
            Arbre = diviserRemonter(Arbre);
        }
        while(Arbre.pere !=null){
            Arbre = Arbre.pere;
        }

        return Arbre;
    }

    private static noeud InsererDsTab(noeud.Cles Cles, noeud Arbre){
        noeud.Cles Clestemp = null;
        noeud Pointeurtemp1, Pointeurtemp2 = null;

        for(int i = 0 ;  i < 2*M+1 ; i++){

            if(Arbre.NbCles <= i)
            {
                Clestemp = Arbre.tabCles[i];
                Arbre.tabCles[i] = Cles;
                Cles = Clestemp;

                Pointeurtemp1 = Arbre.tabPointeur[i+1];
                Arbre.tabPointeur[i+1] = Pointeurtemp2;
                Pointeurtemp2 = Pointeurtemp1;
            }
            else if(Cles.Id < Arbre.tabCles[i].Id){
                Clestemp = Arbre.tabCles[i];
                Arbre.tabCles[i] = Cles;
                Cles = Clestemp;

                Pointeurtemp1 = Arbre.tabPointeur[i+1];
                Arbre.tabPointeur[i+1] = Pointeurtemp2;
                Pointeurtemp2 = Pointeurtemp1;
            }
        }

        Arbre.NbCles++;
        return Arbre;
    }

    private static noeud diviserRemonter(noeud Arbre){
        if(Arbre.getNbCles() == 2*M+1){
            int IdM = Arbre.tabCles[M].Id;
            if(Arbre.pere == null){         //On cree un père si besoin
                noeud pere = new noeud(M);
                pere.feuille = false;
                pere.tabPointeur[0] = Arbre;
                Arbre.pere = pere;
            }

            noeud fils = new noeud(M);        //Creation d'un fils en plus du pere et de l'arbre actuel
            fils.pere = Arbre.pere;
            if(!Arbre.getfeuille()){
                fils.feuille = false;
            }


             //On insere la valeur médiane dans le père
            Arbre. pere = InsererDsTab(Arbre.tabCles[M], Arbre.pere);

            Arbre.tabCles[M] = null;

            //Le nouveau fils accueille la partie droite de l'arbre divisé

            for(int i = M+1 ; i < 2*M+1 ; i++){           //Partie Clef
                fils.tabCles[i-M-1] = Arbre.tabCles[i];
                Arbre.tabCles[i] = null;
            }

            for(int i = M + 1 ; i <= 2*M+1 ; i++){       //Partie Pointeur
                fils.tabPointeur[i-M-1] = Arbre.tabPointeur[i];
                Arbre.tabPointeur[i] = null;
            }
            fils.NbCles = M;
            Arbre.NbCles = M;

            Arbre.pere.tabPointeur[RechercherDansNoeud(IdM, Arbre.pere)+1] = fils;

            if(Arbre.pere.NbCles == 2*M+1){
                Arbre.pere = diviserRemonter(Arbre.pere);
            }
        }
        return Arbre.pere;
    }

    private static noeud SupprimerFeuille(int Id, noeud Arbre){

        try{
            int indice = RechercherDansNoeud(Id , Arbre);
            Arbre.tabCles[indice] = null;
            for(int i = indice ; i < 2*M ; i++){
                   Arbre.tabCles[i] = Arbre.tabCles[i+1];
            }
            Arbre.NbCles--;
        }
        catch(Exception e)  {

            System.out.println("\nLa feuille devant être supprimée n'existe pas :( ;( \n" + e);
        }

        return Arbre;
    }

    public static noeud Supprimer(int Id, noeud Arbre){
        Arbre = RechercherArbre(Id, Arbre);

        if(Arbre != null) {

            if (Arbre.getfeuille()) {
                //Cas 1 : La feuille comprend au moins M+1 élements
                if (Arbre.NbCles > M) {
                    Arbre = SupprimerFeuille(Id, Arbre);
                }                                                                  //pas oublier de gerer le cas de la racine

                //Cas 2 : La feuille comprend M élements
                else if (Arbre.NbCles == M) {
                    int indice = RechercherDansNoeud(Id, Arbre.pere);
                    int indice_gauche = indice-1;
                    int indice_droit = indice+1;

                    //On recherche à gauche le premier frère comprenant plus de M élements
                    while (Arbre.pere.tabPointeur[indice_gauche].NbCles <= M && indice_gauche >= 0) {
                        indice_gauche--;
                    }

                    //On recherche à droite le premier frère comprenant plus de M élements
                    while (Arbre.pere.tabPointeur[indice_droit].NbCles <= M && indice_droit <= 2*M){
                        indice_droit++;
                    }

                    //Cas où il existe un frère possédant au moins M+1 éléments
                    if(indice_gauche >= 0 && indice_droit <= 2*M){
                        Arbre = SupprimerFeuille(Id, Arbre);
                        if(indice - indice_gauche <= indice_droit - indice){
                            while(indice_gauche < indice){
                                //rotation à droite;
                                indice_gauche++;
                            }
                        }
                        else{
                            while(indice_droit > indice){
                                //rotation à gauche;
                                indice_droit--;
                            }
                        }
                    }

                    else if(indice_gauche >= 0){

                    }

                    else if(indice_droit <= 2*M){

                    }

                    //Cas où il n'existe pas un frère possédant au moins M+1 éléments
                    else{

                    }
                }

            }
        }
        else System.out.println("La clef à supprimer n'existe pas");
        return Arbre;
    }

    /* ~~~~~~~~ FONCTIONS D'AFFICHAGE ~~~~~~~~ */

    public static void AfficherArbre(noeud Arbre) {
        System.out.println("");
        for(int i = 0 ; i < Arbre.NbCles ; i++){
            System.out.print("|" + Arbre.tabCles[i].Id+" " + Arbre.tabCles[i].Contenue + "|  ");
        }
        for(int i = 0 ; i <= Arbre.NbCles ; i++){
            if(Arbre.tabPointeur[i] != null){
                AfficherArbre(Arbre.tabPointeur[i]);
            }
        }
    }

    /* ~~~~~~~~ MAIN ~~~~~~~~ */
    /*
    public static void main(String[] args){
        M = 2;
        noeud Racine = new noeud(M);

        Racine = Inserer(new noeud.Cles(15,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(21,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(1,"Chalut"),Racine);

        Racine = Inserer(new noeud.Cles(202,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(2501,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(9,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(7,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(9999,"Chalt"),Racine);
        Racine = Inserer(new noeud.Cles(752,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(7152,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(3,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(2,"Chalute"),Racine);
        Racine = Inserer(new noeud.Cles(4,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(5,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(6,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(8,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(10,"Chalut"),Racine);
        //OK
        Racine = Inserer(new noeud.Cles(11,"Chalut"),Racine);
        //OK
        Racine = Inserer(new noeud.Cles(42, 42), Racine);

        AfficherArbre(Racine);
        //noeud.Cles Recherche = Rechercher(9999,Racine);
        //System.out.println(Recherche.Contenue);

        //Racine = SupprimerFeuille(111, Racine);
        //AfficherArbre(Racine);
    }
    */
}