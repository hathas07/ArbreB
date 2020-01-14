public class arbre {

    protected int M;
    protected noeud Racine;

    private noeud RechercherArbre(int Valeur, noeud Arbre) {
        int indice = 0;

        if (Arbre != null) {
            while (Valeur > Arbre.tabCles[indice]) {
                indice++;
            }
            if (Valeur == Arbre.tabCles[indice]) {
                return Arbre;
            } else RechercherArbre(Valeur, Arbre.tabPointeur[indice]);
        }

        return null;
    }


    public static void main(String[] args){

        System.out.println("dd");
    }

}