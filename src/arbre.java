import java.awt.image.renderable.ParameterBlock;
import java.util.*;

public class arbre {

    protected static int M;
    protected static noeud Racine;

    /* ~~~~~~~~ FONCTIONS DE RECHERCHE ~~~~~~~~ */

    private static noeud RechercherArbre(noeud.Cles Valeur, noeud Arbre) {
        int indice = 0;
        noeud resultat = Arbre;
        if (Arbre.getfeuille() != true) {

            while (indice < Arbre.NbCles && Valeur.Id > Arbre.tabCles[indice].Id) {
                indice++;
            }
            if(Arbre.tabPointeur[indice] != null){
                resultat = RechercherArbre(Valeur, Arbre.tabPointeur[indice]);
            }
            else System.out.print("pointeur null ligne 22");
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

    private static noeud Inserer(noeud.Cles Cles, noeud Arbre){
        Arbre = RechercherArbre(Cles, Arbre);
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
            System.out.println("NbdeCle : " + Arbre.NbCles + "  i : " + i);
            if(Cles != null)System.out.println(Cles.Id);
            //System.out.println(Arbre.tabCles[i].Id);
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


            //Arbre.pere = Inserer(Arbre.tabCles[M],Arbre.pere);      //On insere la valeur médiane dans le père
            InsererDsTab(Arbre.tabCles[M], Arbre.pere);
            if(Arbre.pere.NbCles == 2*M+1){
                Arbre.pere = diviserRemonter(Arbre.pere);
            }

            Arbre.tabCles[M] = null;

            for(int i = M+1 ; i < 2*M+1 ; i++){           //Le nouveau fils accueille la partie droite de l'arbre divisé
                fils.tabCles[i-M-1] = Arbre.tabCles[i];
                Arbre.tabCles[i] = null;
    
                fils.tabPointeur[i-M] = Arbre.tabPointeur[i];
                Arbre.tabPointeur[i] = null;
            }



            /*for(int i = 1 ; i < M ; i++){ // La partie gauche du noeud divisé est accueillie en tant que fils du noeud de la valeur médiane insérée dans le père
                Arbre.pere.tabPointeur[RechercherDansNoeud(Arbre.tabCles[M], Arbre.pere)].tabCles[i] = Arbre.tabCles[i];
                Arbre.pere.tabPointeur[RechercherDansNoeud(Arbre.tabCles[M], Arbre.pere)].tabPointeur[i] = Arbre.tabPointeur[i];

            }*/

            fils.NbCles = M;
            Arbre.NbCles = M;

            Arbre.pere.tabPointeur[RechercherDansNoeud(IdM, Arbre.pere)+1] = fils;
        }
        return Arbre.pere;
    }

    /* ~~~~~~~~ FONCTIONS D'AFFICHAGE ~~~~~~~~ */

    private static void AfficherArbre(noeud Arbre) {
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
        Racine = Inserer(new noeud.Cles(9999,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(752,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(7152,"Chalut"),Racine);
        //OK

        Racine = Inserer(new noeud.Cles(3,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(2,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(4,"Chalut"),Racine);

        Racine = Inserer(new noeud.Cles(5,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(6,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(8,"Chalut"),Racine);
        Racine = Inserer(new noeud.Cles(10,"Chalut"),Racine);

        Racine = Inserer(new noeud.Cles(11,"Chalut"),Racine);


        AfficherArbre(Racine);

    }
}