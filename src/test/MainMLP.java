package test;

import MLP.MLP;
import MLP.Sigmoid;
import MLP.TransferFunction;

import java.util.Random;

public class MainMLP {
    public static void main(String[] args) {
        int[] couches = {2, 3, 1};  //  nb entrées, nb neurones cachés, nb sortie
        double tauxApprentissage = 0.7;
        TransferFunction fonctionActivation = new Sigmoid();

        MLP reseau = new MLP(couches, tauxApprentissage, fonctionActivation);

        double[][] entreesApprentissage = {
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        };
        double[][] sortiesAttendues = {
                {0},
                {1},
                {1},
                {0}
        };

        //Mélange aléatoire des données d'entrainement
        Random rd = new Random();
        for (int i = entreesApprentissage.length - 1; i > 0; i--) {
            int j = rd.nextInt(i + 1);
            // Permutation des entrées
            double[] tempEntree = entreesApprentissage[i];
            entreesApprentissage[i] = entreesApprentissage[j];
            entreesApprentissage[j] = tempEntree;
            // Permutation synchronisée des sorties
            double[] tempSortie = sortiesAttendues[i];
            sortiesAttendues[i] = sortiesAttendues[j];
            sortiesAttendues[j] = tempSortie;
        }

        //Apprentissage
        double erreur_cible = 0.01;
        int max_iterations = 1000000;
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
        for (int i = 0; i < entreesApprentissage.length; i++) {
            double[] sortieCalculee = reseau.execute(entreesApprentissage[i]);
            // Afficher les résultats
            System.out.println("Entrée: " + entreesApprentissage[i][0] + ","
                    + entreesApprentissage[i][1]);
            System.out.println("Sortie attendue: " + sortiesAttendues[i][0]);
            System.out.println("Sortie calculée: " + sortieCalculee[0]);
        }
    }
}
