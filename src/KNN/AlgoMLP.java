package KNN;

import MLP.MLPMNIST;
import MLP.TransferFunction;

public class AlgoMLP extends AlgoClassification {

    public MLP.MLPMNIST mlp;

    public AlgoMLP(Donnees donneesEntrainement, int[] layers, double tauxApprentissage, TransferFunction fonctionActivation) {
        super(donneesEntrainement);
        mlp = new MLP.MLPMNIST(layers, tauxApprentissage, fonctionActivation);
    }

    @Override
    public int predireEtiquette(Imagette imagette) {
        double[] output = mlp.execute(imagette.getFlatPixels());
        int maxIndex = 0;
        for (int i = 1; i < output.length; i++) {
            if (output[i] > output[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public double train(double erreur_cible, int max_iterations) {
        double erreur_courante = Double.MAX_VALUE;
        int iteration = 0;
        int totalImagettes = donneesEntrainement.getNombreImagettes();

        while ((erreur_courante > erreur_cible) && iteration < max_iterations) {
            erreur_courante = 0;

            for (int indice = 0; indice < totalImagettes; indice++) {
                double[] labelOneHot = new double[mlp.getOutputLayerSize()];
                int label = donneesEntrainement.getImagette(indice).getLabel(); // Returns an int


                labelOneHot[label] = 1.0;

                double erreur = mlp.backPropagate(
                        donneesEntrainement.getImagette(indice).getFlatPixels(),
                        labelOneHot
                );
                erreur_courante += erreur;
            }

            // Moyenne de l'erreur
            erreur_courante /= totalImagettes;
            iteration++;

            // Affichage de progression toutes les 10%
            if (iteration % (max_iterations / 10) == 0) {
                System.out.println("Apprentissage en cours : " + (iteration * 100 / max_iterations) + "%");
            }
        }

        System.out.println("Apprentissage terminé après " + iteration + " itérations avec erreur finale : " + erreur_courante);
        return erreur_courante;
    }
}
