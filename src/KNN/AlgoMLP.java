package KNN;

import MLP.MLP;
import MLP.TransferFunction;

public class AlgoMLP extends AlgoClassification {

    private MLP mlp;

    public AlgoMLP(Donnees donneesEntrainement, int[] layers, double tauxApprentissage, TransferFunction fonctionActivation) {
        super(donneesEntrainement);
        mlp = new MLP(layers, tauxApprentissage, fonctionActivation);
    }

    @Override
    public int predireEtiquette(Imagette imagette) {
        return (int) Math.round(mlp.execute(imagette.getFlatPixels())[0]);
    }

    public void train(double erreur_cible, int max_iterations) {
        double erreur_courante = 1;
        int iteration = 0;
        while ((erreur_courante > erreur_cible) && iteration < max_iterations) {
            if (iteration % max_iterations/100 == 0) {
                System.out.println("Apprentissage en cours : "+iteration/(max_iterations/100) +"%");
            }

            erreur_courante = 0;
            for (int i = 0; i < donneesEntrainement.getNombreImagettes(); i++) {
                double erreur = mlp.backPropagate(donneesEntrainement.getImagette(i).getFlatPixels(), new double[]{donneesEntrainement.getImagette(i).getLabel()});
                erreur_courante += erreur;
            }
            erreur_courante /= donneesEntrainement.getNombreImagettes();
            iteration++;
        }
    }
}
