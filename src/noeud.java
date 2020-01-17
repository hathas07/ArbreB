import java.util.*;
public class noeud {

    public static class Cles{
        int Id;
        Object Contenue;

        public Cles(int Identifiant, Object objet){
            Id = Identifiant;
            Contenue = objet;
        }
    }
    int NbCles;
    Cles [] tabCles;
    noeud [] tabPointeur;
    boolean feuille;
    noeud pere;

    public noeud(int M){
        NbCles = 0;
        tabCles = new Cles [2*M+1];
        tabPointeur = new noeud[2*M+2];
        feuille = true;
        pere = null;
    }

    public int getNbCles(){ return NbCles; }

    public boolean getfeuille(){
        return feuille;
    }

    public Object [] gettabCles(){
        return tabCles;
    }

    public noeud [] gettabPointeur(){
        return tabPointeur;
    }
    
}
