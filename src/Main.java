import KNN.*;
import MLP.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Chargement des données d'entraînements...");
            Donnees donneesTrain = MNISTLoader.loadData("images/train-images.idx3-ubyte", "images/train-labels.idx1-ubyte");
            //Nombre de couches de la première couche = taille des images
            int nbPixelsImagette = donneesTrain.getImagette(0).getFlatPixels().length; //784 ; 28x28

            int[] layers = {200, 50, 10, 1};
            double tauxApprentissage = 0.01;
            TransferFunction fonctionActivation = new Sigmoid();
            TransferFunction fonctionActivation2 = new Hyperbolic();
            AlgoMLP algoMLP = new AlgoMLP(donneesTrain, layers, tauxApprentissage, fonctionActivation);

            System.out.println("Entraîenement du MLP...");
            double erreur_cible = 0.0001;
            int max_iterations = 100;

            for (int i = 0; i < 10; i++) {
                double erreur = algoMLP.train(erreur_cible, max_iterations);
                System.out.println("Itération " + i + " Erreur : "+erreur+" Précision : "+ Statistique.calculerPrecision(algoMLP, donneesTrain));
            }


//            System.out.println("Chargement des données de tests...");
//            Donnees donneesTest = MNISTLoader.loadData("images/t10k-images.idx3-ubyte", "images/t10k-labels.idx1-ubyte");
//
//            System.out.println("Calcul de la précision...");
//            double precision = Statistique.calculerPrecision(algoMLP, donneesTrain);
//            System.out.println("Precision MLP : " + precision*100 + "% sur les données d'entraînements");
//            double precision2 = Statistique.calculerPrecision(algoMLP, donneesTest);
//            System.out.println("Precision MLP : " + precision2*100 + "% sur les données de test");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
