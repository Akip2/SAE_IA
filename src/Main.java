package src;

public class Main {
    public static void main(String[] args) {
        int[] couches = {2, 1, 1};  //  nb entrées, nb neurones cachés, nb sortie
        double tauxApprentissage = 0.1;
        TransferFunction fonctionActivation = new Sigmoid();

        MLP reseau = new MLP(couches, tauxApprentissage, fonctionActivation);

        double[][] entreesApprentissage = {
                {0, 1},
                {0, 0},
                {1, 1},
                {1, 0}
        };
        double[][] sortiesAttendues = {
                {1},
                {0},
                {1},
                {1}
        };
        double erreur_cible = 0.01;
        int max_iterations = 10000;
        double erreur_courante = 1;
        int iteration = 0;
        while ((erreur_courante > erreur_cible) && iteration < max_iterations) {
            erreur_courante = 0;
            for (int i = 0; i < entreesApprentissage.length; i++) {
                double erreur = reseau.backPropagate(entreesApprentissage[i], sortiesAttendues[i]);
                erreur_courante += erreur;
            }
            erreur_courante /= entreesApprentissage.length;
            iteration++;
        }
        System.out.println("Tests");
        for(int i = 0; i < entreesApprentissage.length; i++) {
            double[] sortieCalculee = reseau.execute(entreesApprentissage[i]);
            // Afficher les résultats
            System.out.println("Entrée: " + entreesApprentissage[i][0] + ","
                    + entreesApprentissage[i][1]);
            System.out.println("Sortie attendue: " + sortiesAttendues[i][0]);
            System.out.println("Sortie calculée: " + sortieCalculee[0]);
        }
    }
}
