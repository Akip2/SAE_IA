package MNIST;

public class Statistique {

    // Méthode pour calculer la précision d'un algorithme de classification
    public static double calculerPrecision(AlgoClassification algo, Donnees donneesTest) {
        int nbCorrects = 0;
        int nbTotal = donneesTest.getNombreImagettes();

        // Parcourir toutes les imagettes de test
        for (int i = 0; i < nbTotal; i++) {

            Imagette imagetteTest = donneesTest.getImagette(i);

            // Prédire l'étiquette de l'imagette
            int etiquettePredite = algo.predireEtiquette(imagetteTest);
            System.out.println("Etiquette predite : " + etiquettePredite + " Etiquette Reelle : " + imagetteTest.getLabel());
            // Vérifier si la prédiction est correcte

            if (etiquettePredite == imagetteTest.getLabel()) {
                nbCorrects++;
            }
        }

        // Retourner la précision (nombre de prédictions correctes / nombre total)
        return (double) nbCorrects / nbTotal;
    }


}
