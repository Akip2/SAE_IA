import KNN.AlgoMLP;
import KNN.Donnees;
import KNN.MNISTLoader;
import KNN.Statistique;
import MLP.Hyperbolic;
import MLP.Sigmoid;
import MLP.TransferFunction;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            // MNIST
            System.out.println("Chargement des données d'entraînements...");
            Donnees donneesTrain = MNISTLoader.loadData("images/MNIST/train-images.idx3-ubyte", "images/MNIST/train-labels.idx1-ubyte", 1000);

            System.out.println("Chargement des données de tests...");
            Donnees donneesTest = MNISTLoader.loadData("images/MNIST/t10k-images.idx3-ubyte", "images/MNIST/t10k-labels.idx1-ubyte", null);

            // Fashion MNIST
            Donnees donneesTrainFasion = MNISTLoader.loadData("images/Fashion_MNIST/train-images-idx3-ubyte", "images/Fashion_MNIST/train-labels-idx1-ubyte", null);
            Donnees donneesTestFasion = MNISTLoader.loadData("images/Fashion_MNIST/t10k-images-idx3-ubyte", "images/Fashion_MNIST/t10k-labels-idx1-ubyte", null);

            int[] layers = {784, 100, 50, 10};
            double tauxApprentissage = 0.01;
            TransferFunction fonctionActivation = new Sigmoid();
            TransferFunction fonctionActivation2 = new Hyperbolic();
            AlgoMLP algoMLP = new AlgoMLP(donneesTrain, layers, tauxApprentissage, fonctionActivation2);

            System.out.println("START : Précision train : "+Statistique.calculerPrecision(algoMLP, donneesTrain) + " ; Précision test : "+Statistique.calculerPrecision(algoMLP, donneesTest));

            double erreur_cible = 0.001;
            int max_iterations = 10;
            for (int i = 0; i < 10; i++) {
                double erreur = algoMLP.train(erreur_cible, max_iterations);
                System.out.println("Itération " + i + " Erreur : "+erreur+" Précision train : "+ Statistique.calculerPrecision(algoMLP, donneesTrain)+" ; Précision test : "+ Statistique.calculerPrecision(algoMLP, donneesTest));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
