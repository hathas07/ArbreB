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
        if(Arbre.getNbCles() == 2*M){
            noeud fils = new noeud(2*M);
            Arbre.pere = Inserer(Arbre.tabCles[M],Arbre.pere);

            for(int i = M+1 ; i < 2*M ; i++){
                fils.tabCles[i-M] = Arbre.tabCles[i];
                fils.tabPointeur[i-M] = Arbre.tabPointeur[i];
            }
        }
        return Arbre;
    }

    private noeud Inserer(Object Cles, noeud Arbre){
        if(Arbre.feuille){
            Arbre = InsererDsFeuille(Cles, Arbre);
            if(Arbre.getNbCles() == 2*M){
                Arbre.pere = diviserRemonter(Arbre);
            }
        }
        return null;
    }
    private static void AfficherArbre(noeud Arbre) {
        int indice = 0;
        while (indice < Arbre.tabCles.length){
            System.out.print(Arbre.tabCles[indice]);
            indice++;
        }
    }

    public static void main(String[] args){
        M = 2;
        noeud Racine = new noeud(M);
        Racine.tabCles[0] = 3;
        Racine.tabCles[1] = 3;
        Racine.tabCles[2] = 3;
        Racine.tabCles[3] = 3;
        AfficherArbre(Racine);
        System.out.print(Racine.tabCles.length);
    }
}