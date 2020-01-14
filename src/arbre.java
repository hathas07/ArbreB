public class arbre {

    protected static int M;
    protected static noeud Racine;

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

    private static void AfficherArbre(noeud Arbre) {
        int indice = 0;
    while (Arbre.tabCles[indice] < 2*M){
        System.out.print(Arbre.tabCles[indice]);
    }
    }

    public static void main(String[] args){
        M = 2;
        noeud Arbre = new noeud(M);
        Arbre.tabCles = new int[]{3, 8, 12};
        AfficherArbre(Racine);
    }
}