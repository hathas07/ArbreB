import java.awt.geom.Area;
import java.util.*;

public class arbre {

    protected static int M;
    protected static noeud Racine;

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

    private noeud InsererDsFeuille(Object Cles, noeud Feuille){
        if(Feuille.feuille){
            Object Clestemp = 0;
            Comparator c = null;
            for(int i = 0;  i < Feuille.tabCles.length; i++){
                if(c.compare(Cles,Feuille.tabCles[i]) <= 0){
                    Clestemp = Feuille.tabCles[i];
                    Feuille.tabCles[i] = Cles;
                    Cles = Clestemp;
                }
            }
            return Feuille;
        }
        return null;
    }

    private noeud diviserRemonter(noeud Arbre){
        if(Arbre.getNbCles() == 2*M+1){
            noeud fils = new noeud(2*M);        //Creation d'un fils en plus du pere et de l'arbre actuel
            fils.pere = Arbre.pere;
            Arbre.pere = Inserer(Arbre.tabCles[M],Arbre.pere);      //On insere la valeur médiane dans le père

            for(int i = M+1 ; i < 2*M ; i++){           //Le nouveau fils acceuil la partie droite de l'arbre divisé
                fils.tabCles[i-M] = Arbre.tabCles[i];
                fils.tabPointeur[i-M] = Arbre.tabPointeur[i];
            }

            fils.NbCles = M;
            Arbre.NbCles = M;

            Arbre.tabPointeur[M+1] = fils;

        }
        return Arbre.pere;
    }

    private noeud Inserer(Object Cles, noeud Arbre){
        if(Arbre.feuille){
            Arbre = InsererDsFeuille(Cles, Arbre);
            if(Arbre.getNbCles() == 2*M+1){
                Arbre = diviserRemonter(Arbre);
            }
        }
        return null;
    }
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