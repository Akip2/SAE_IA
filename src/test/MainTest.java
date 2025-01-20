package test;

import KNN.*;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) {
        try {
            System.out.println("Premier chargement de données");
            Donnees donneesTrain = MNISTLoader.loadData("images/train-images.idx3-ubyte", "images/train-labels.idx1-ubyte");

            System.out.println("Recherche des labels");
            int[] labels = Etiquette.loadLabels("images/train-labels.idx1-ubyte");

            // Vérifier les étiquettes de la première et de la dernière image
            System.out.println("Étiquette de la première image : " + labels[0]);
            System.out.println("Étiquette de la dernière image : " + labels[labels.length - 1]);

            System.out.println("Deuxième chargement de données");
            Donnees donneesTest = MNISTLoader.loadData("images/t10k-images.idx3-ubyte", "images/t10k-labels.idx1-ubyte");

            // algos
            kNN algo = new kNN(donneesTrain,2);

            // params MLP
            int[] layers = {784, 100, 50, 10};
            double tauxApprentissage = 0.01;
            TransferFunction fonctionActivation = new Sigmoid();
            TransferFunction fonctionActivation2 = new Hyperbolic();

            MLP MLP = new MLP(donneesTrain, layers, tauxApprentissage, fonctionActivation2);

            System.out.println("Calcul de la précision");
            double Res = Statistique.calculerPrecision(MLP, donneesTest);
            System.out.println("Precision : " + Res*100 + "%");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
