import java.awt.geom.Area;
import java.util.*;

public class arbre {

    protected static int M;
    protected static noeud Racine;

    /* ~~~~~~~~ FONCTIONS DE RECHERCHE ~~~~~~~~ */

    private noeud RechercherArbre(Object Valeur, noeud Arbre) {
        int indice = 0;
        Comparator c = null;
        if (Arbre != null) {
            while (c.compare(Valeur,Arbre.tabCles[indice]) > 0) {
                indice++;
            }
            if (c.compare(Valeur,Arbre.tabCles[indice]) == 0) {
                return Arbre;
            } else RechercherArbre(Valeur, Arbre.tabPointeur[indice]);
        }
        return null;
    }

    private int RechercherDansNoeud(Object Valeur, noeud Arbre){
        int i = 0;
        Comparator c = null;
        while(c.compare(Valeur,Arbre.tabCles[i]) > 0){
            i++;
        }
        return i;
    }

    /* ~~~~~~~~ FONCTIONS D'INSERTION ET DE MODIFICATION DE L'ARBRE ~~~~~~~~ */

    private noeud Inserer(Object Cles, noeud Arbre){
        if(Arbre.feuille){
            Arbre = InsererDsTab(Cles, Arbre);
            if(Arbre.getNbCles() == 2*M+1){
                Arbre = diviserRemonter(Arbre);
            }
        }
        return null;
    }

    private noeud InsererDsTab(Object Cles, noeud Arbre){
        Object Clestemp = 0;
        noeud Pointeurtemp1 = null;
        noeud Pointeurtemp2 = null;
        Comparator c = null;
        for(int i = 0 ;  i < Arbre.tabCles.length ; i++){
            if(c.compare(Cles,Arbre.tabCles[i]) <= 0){
                Clestemp = Arbre.tabCles[i];
                Arbre.tabCles[i] = Cles;
                Cles = Clestemp;

                Pointeurtemp1 = Arbre.tabPointeur[i+1];
                Arbre.tabPointeur[i+1] = Pointeurtemp2;
                Pointeurtemp2 = Pointeurtemp1;
            }
        }
        return Arbre;
    }

    private noeud diviserRemonter(noeud Arbre){
        if(Arbre.getNbCles() == 2*M+1){
            noeud fils = new noeud(M);        //Creation d'un fils en plus du pere et de l'arbre actuel
            fils.pere = Arbre.pere;
            Arbre.pere = Inserer(Arbre.tabCles[M],Arbre.pere);      //On insere la valeur médiane dans le père


            for(int i = M+1 ; i < 2*M ; i++){           //Le nouveau fils accueille la partie droite de l'arbre divisé
                fils.tabCles[i-M] = Arbre.tabCles[i];
                fils.tabPointeur[i-M] = Arbre.tabPointeur[i];
            }

            for(int i = 1 ; i < M ; i++){ // La partie gauche du noeud divisé est accueillie en tant que fils du noeud de la valeur médiane insérée dans le père
                Arbre.pere.tabPointeur[RechercherDansNoeud(Arbre.tabCles[M], Arbre.pere)].tabCles[i] = Arbre.tabCles[i];
                Arbre.pere.tabPointeur[RechercherDansNoeud(Arbre.tabCles[M], Arbre.pere)].tabPointeur[i] = Arbre.tabPointeur[i];
            }

            fils.NbCles = M;
            Arbre.NbCles = M;
            Arbre.tabPointeur[M+1] = fils;
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
        Racine.tabCles[0] = 3;
        Racine.tabCles[1] = 3;
        Racine.tabCles[2] = 3;
        Racine.tabCles[3] = 3;
        Racine.tabCles[4] = 3.14;
        Racine.NbCles = 5;
        AfficherArbre(Racine);
        System.out.print(Racine.tabCles.length);
    }
}