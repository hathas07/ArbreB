public class Test {

    public static void main(String[] args){
        arbre.M = 2;
        noeud Racine = new noeud(arbre.M);

        Racine = arbre.Inserer(new noeud.Cles(15,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(21,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(1,"Chalut"),Racine);

        Racine = arbre.Inserer(new noeud.Cles(202,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(2501,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(9,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(7,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(9999,"Chalt"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(752,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(7152,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(3,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(2,"Chalute"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(4,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(5,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(6,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(8,"Chalut"),Racine);
        Racine = arbre.Inserer(new noeud.Cles(10,"Chalut"),Racine);
        //OK
        Racine = arbre.Inserer(new noeud.Cles(11,"Chalut"),Racine);
        //OK
        Racine = arbre.Inserer(new noeud.Cles(42, 42), Racine);

        arbre.AfficherArbre(Racine);
        //noeud.Cles Recherche = Rechercher(9999,Racine);
        //System.out.println(Recherche.Contenue);

        //Racine = SupprimerFeuille(111, Racine);
        //AfficherArbre(Racine);
    }

}