import KNN.AlgoMLP;
import KNN.Donnees;
import KNN.MNISTLoader;
import KNN.Statistique;
import MLP.Hyperbolic;
import MLP.MLPMNIST;
import MLP.Sigmoid;
import MLP.TransferFunction;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) {
        try {
            System.out.println("Chargement des données d'entraînements...");
            Donnees donneesTrain = MNISTLoader.loadData("images/train-images.idx3-ubyte", "images/train-labels.idx1-ubyte");

            int[] layers = {210, 70, 10, 1};
            double tauxApprentissage = 1;
            TransferFunction fonctionActivation = new Sigmoid();
            TransferFunction fonctionActivation2 = new Hyperbolic();
            double erreur_cible = 0.00001;
            int max_iterations = 1000;

            AlgoMLP algoMLP = new AlgoMLP(donneesTrain, layers, tauxApprentissage, fonctionActivation);
            algoMLP.mlp.setLearningRate(1);
            System.out.println("START : Etiquettes : "+ donneesTrain.getImagette(0).getLabel() + " "+ donneesTrain.getImagette(1).getLabel() +" Prédiction : "+algoMLP.mlp.execute(donneesTrain.getImagette(0).getFlatPixels())+" "+algoMLP.mlp.execute(donneesTrain.getImagette(1).getFlatPixels()));

            for (int i = 0; i < 10; i++) {
                double erreur = algoMLP.train(erreur_cible, max_iterations);
                //System.out.println("Itération " + i + " Erreur : "+erreur+" Précision : "+ Statistique.calculerPrecision(algoMLP, donneesTrain));
                System.out.println("Itération " + i + " Erreur : "+erreur+ " Etiquettes : "+ donneesTrain.getImagette(0).getLabel() + " "+ donneesTrain.getImagette(1).getLabel() +" Prédiction : "+algoMLP.mlp.execute(donneesTrain.getImagette(0).getFlatPixels())+" "+algoMLP.mlp.execute(donneesTrain.getImagette(1).getFlatPixels()));
                algoMLP.mlp.setLearningRate(Math.min(0.01, erreur));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
