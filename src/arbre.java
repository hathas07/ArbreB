import java.awt.image.renderable.ParameterBlock;
import java.util.*;

public class arbre {

    protected static int M;
    protected static noeud Racine;

    /* ~~~~~~~~ FONCTIONS DE RECHERCHE ~~~~~~~~ */

    private static noeud RechercherArbre(Object Valeur, noeud Arbre) {
        int indice = 0;
        noeud resultat = Arbre;
        Comparator c = null;
        if (Arbre.getfeuille() != true) {

            while (indice < Arbre.NbCles && c.compare(Valeur,Arbre.tabCles[indice]) < 0) {
                indice++;

            }
            if(Arbre.tabPointeur[indice] != null){
                resultat = RechercherArbre(Valeur, Arbre.tabPointeur[indice]);
            }
            else System.out.print("pointeur null ligne 22");
        }
        return resultat;
    }

    private static int RechercherDansNoeud(Object Valeur, noeud Arbre){
        int i = 0;
        Comparator c = null;
        while(c.compare(Valeur,Arbre.tabCles[i]) > 0){
            i++;
        }
        return i;
    }

    /* ~~~~~~~~ FONCTIONS D'INSERTION ET DE MODIFICATION DE L'ARBRE ~~~~~~~~ */

    private static noeud Inserer(Object Cles, noeud Arbre){
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

    private static noeud InsererDsTab(Object Cles, noeud Arbre){
        Object Clestemp = 0;
        noeud Pointeurtemp1 = null;
        noeud Pointeurtemp2 = null;
        Comparator c = null;

        for(int i = 0 ;  i < 2*M+1 ; i++){
            // System.out.print("Cle : " + Arbre.tabCles[i]);
            // System.out.println("   Null " + i);
            if(Arbre.tabCles[i] != null && Cles != null){
                System.out.println("Valeur " + Arbre.tabCles[i]);
                System.out.println("Cles " + Cles);
                int r = c.compare(Cles,Arbre.tabCles[i]);
                System.out.println("r =" + r);
            }
            System.out.println("t");
            if(Arbre.NbCles <= i)
            {
                Clestemp = Arbre.tabCles[i];
                Arbre.tabCles[i] = Cles;
                Cles = Clestemp;

                Pointeurtemp1 = Arbre.tabPointeur[i+1];
                Arbre.tabPointeur[i+1] = Pointeurtemp2;
                Pointeurtemp2 = Pointeurtemp1;
            }
            else if(c.compare(Cles,Arbre.tabCles[i]) < 0){
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
            if(Arbre.pere == null){
                noeud pere = new noeud(M);
                Arbre.feuille = false;
                Arbre.pere = pere;
            }

            noeud fils = new noeud(M);        //Creation d'un fils en plus du pere et de l'arbre actuel
            fils.pere = Arbre.pere;
            Arbre.pere = Inserer(Arbre.tabCles[M],Arbre.pere);      //On insere la valeur médiane dans le père
            Arbre.tabCles[M] = null;

            for(int i = M+1 ; i <= 2*M+1 ; i++){           //Le nouveau fils accueille la partie droite de l'arbre divisé
                fils.tabCles[i-M] = Arbre.tabCles[i];
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
            Arbre.pere.NbCles++;

            Arbre.tabPointeur[RechercherDansNoeud(Arbre.tabCles[M], Arbre.pere)+1] = fils;
            Arbre.tabPointeur[RechercherDansNoeud(Arbre.tabCles[M], Arbre.pere)+1].feuille = false;
        }
        return Arbre.pere;
    }

    /* ~~~~~~~~ FONCTIONS D'AFFICHAGE ~~~~~~~~ */

    private static void AfficherArbre(noeud Arbre) {
        System.out.println("");
        for(int i = 0 ; i < Arbre.NbCles ; i++){
            System.out.print(Arbre.tabCles[i]+" ");
        }
        for(int i = 0 ; i < Arbre.NbCles ; i++){
            if(Arbre.tabPointeur[i] != null){
                AfficherArbre(Arbre.tabPointeur[i]);
            }
        }
    }

    /* ~~~~~~~~ MAIN ~~~~~~~~ */

    public static void main(String[] args){
        M = 2;
        noeud Racine = new noeud(M);
        Racine = Inserer(15,Racine);
        Racine = Inserer(21,Racine);
        //Racine = Inserer(2081,Racine);
        //Racine = Inserer(9,Racine);
        //Racine = Inserer(0.52,Racine);
        //Racine = Inserer(6,Racine);
        /*Racine.tabCles[0] = 3;
        Racine.tabCles[1] = 3;
        Racine.tabCles[2] = 3;
        Racine.tabCles[3] = 3;
        Racine.tabCles[4] = 3.14;
        Racine.NbCles = 5;*/
        AfficherArbre(Racine);
    }
}