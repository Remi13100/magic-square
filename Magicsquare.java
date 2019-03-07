/**
 *
 * @author remiboissise
 */
public class Magicsquare {

    // Dimension du carré magique (à renseigner) 
    public static int dimSquare = 4;

    public static int n;
    public static int somme;
    public static int[][] sol;
    public static boolean[] dejaPris;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        n = dimSquare * dimSquare;
        int sommeN2 = (n * (n + 1)) / 2;
        somme = sommeN2 / dimSquare;
        dejaPris = new boolean[n + 1];
        sol = new int[dimSquare][dimSquare];

        if (solve(0, 0, 0)) {
            show(sol);
        }
    }

    // Solver
    public static boolean solve(int i, int j, int sommeLigneEnCours) {
        //Condition d'arrêt
        if (j >= dimSquare) {
            j = 0;
            i++;
            sommeLigneEnCours = 0;
        }

        if (i >= dimSquare) {
            return true;
        }

        // trouver une valeur pour la case i,j
        int end = Math.min(n, somme - sommeLigneEnCours);
        for (int v = 1; v <= end; v++) {
            if (!dejaPris[v]) {
                sol[i][j] = v;
                if (i == dimSquare - 1 && j == 0 && !checkDiag2())
                    continue;
                if (i == dimSquare - 1 && j == dimSquare - 1 && !checkDiag1())
                    continue;
                if (i == dimSquare - 1 && !checkCol(j)) {
                    continue;
                }
                dejaPris[v] = true;
                if (solve(i, j + 1, sommeLigneEnCours + v)) {
                    return true;
                }
                dejaPris[v] = false;
            }
        }
        return false;
    }

    // Vérification de la somme des colonnes
    public static boolean checkCol(int j) {
        int s = 0;
        for (int i = 0; i < dimSquare; i++) {
            s += sol[i][j];
        }
        return (s == somme);
    }

    // Vérification de la somme de la première diagonale (de gauche à droite)
    private static boolean checkDiag1() {
        int s = 0;
        for (int i = 0; i < dimSquare; i++) {
            s += sol[i][i];
        }
        return (s == somme);
    }

    // Vérification de la somme de la deuxième diagonale (de droite à gauche)
    private static boolean checkDiag2() {
        int s = 0;
        for (int i = 0; i < dimSquare; i++) {
            s += sol[i][dimSquare-i-1];
        }
        return (s == somme);
    }

    // Affichage du résultat
    public static void show(int[][] sol) {
        System.out.println("--- sol ---");
        for (int i = 0; i < dimSquare; i++) {
            for (int j = 0; j < dimSquare; j++) {
                System.out.printf("%4d", sol[i][j]);
            }
            System.out.println();
        }
    }

}
