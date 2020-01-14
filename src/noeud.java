
public class noeud{

    int NbCles;
    int [] tabCles;
    noeud [] tabPointeur;
    boolean feuille;

    public noeud(int M){
        int NbCles = 0;
        int [] tabCles = new int [2*M];
        noeud [] tabPointeur = new noeud[2*M];
        boolean feuille = true;
    }

    public int getNbCles(){
        return NbCles;
    }

    public boolean getfeuille(){
        return feuille;
    }

    public int [] gettabCles(){
        return tabCles;
    }

    public noeud [] gettabPointeur(){
        return tabPointeur;
    }
}
