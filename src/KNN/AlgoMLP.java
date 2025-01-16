package KNN;

import MLP.MLPMNIST;
import MLP.TransferFunction;

public class AlgoMLP extends AlgoClassification {

    private MLP.MLPMNIST mlp;

    public AlgoMLP(Donnees donneesEntrainement, int[] layers, double tauxApprentissage, TransferFunction fonctionActivation) {
        super(donneesEntrainement);
        mlp = new MLP.MLPMNIST(layers, tauxApprentissage, fonctionActivation);
    }

    @Override
    public int predireEtiquette(Imagette imagette) {
        return mlp.execute(imagette.getFlatPixels());
    }

    public void train(double erreur_cible, int max_iterations) {
        double erreur_courante = 1;
        int iteration = 0;
        int count_percent = 1;
        while ((erreur_courante > erreur_cible) && iteration < max_iterations) {
            
            if (iteration % (max_iterations/100) == 0) {
                System.out.println("Apprentissage en cours : " + count_percent +"%");
                count_percent++;
            }

            erreur_courante = 0;
            for (int i = 0; i < donneesEntrainement.getNombreImagettes(); i++) {
                double erreur = mlp.backPropagate(donneesEntrainement.getImagette(i).getFlatPixels(), donneesEntrainement.getImagette(i).getLabel());
                erreur_courante += erreur;
            }
            erreur_courante /= donneesEntrainement.getNombreImagettes();
            iteration++;
        }
    }
}
