import java.util.*;

public class kNN extends AlgoClassification{

    private int k;

    public kNN(Donnees donnees, int k){
        super(donnees);
        this.k = k;
    }

    @Override
    public int predireEtiquette(Imagette imagette) {List<ImagetteDistance> distances = new ArrayList<>();

        // Parcourir toutes les imagettes d'entraînement
        for (int i = 0; i < donneesEntrainement.getNombreImagettes(); i++) {
            Imagette imagetteEntrainement = donneesEntrainement.getImagette(i);

            // Calculer la distance euclidienne entre imagetteTest et imagetteEntrainement
            double distance = calculerDistance(imagette, imagetteEntrainement);

            // Ajouter la distance et l'étiquette dans la liste
            distances.add(new ImagetteDistance(imagetteEntrainement.getLabel(), distance));
        }

        // Trier la liste des distances par ordre croissant
        Collections.sort(distances, Comparator.comparingDouble(id -> id.distance));

        // Sélectionner les k plus proches voisins
        List<ImagetteDistance> kPlusProches = distances.subList(0, k);

        // Trouver l'étiquette majoritaire parmi les k voisins
        return trouverEtiquetteMajoritaire(kPlusProches);
    }

    // Calculer la distance euclidienne entre deux imagettes
    private double calculerDistance(Imagette img1, Imagette img2) {
        double somme = 0.0;
        int nbLignes = img1.pixels.length;
        int nbColonnes = img1.pixels[0].length;

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                int diff = img1.getPixel(i, j) - img2.getPixel(i, j);
                somme += diff * diff;
            }
        }

        return Math.sqrt(somme);
    }

    // Trouver l'étiquette majoritaire parmi les k voisins
    private int trouverEtiquetteMajoritaire(List<ImagetteDistance> voisins) {
        // On utilise une HashMap pour compter les occurrences de chaque étiquette
        Map<Integer, Integer> frequences = new HashMap<>();

        for (ImagetteDistance voisin : voisins) {
            frequences.put(voisin.label, frequences.getOrDefault(voisin.label, 0) + 1);
        }

        // Trouver l'étiquette qui apparaît le plus souvent
        return frequences.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    // Classe interne pour stocker une imagette avec sa distance
    private static class ImagetteDistance {
        int label;
        double distance;

        public ImagetteDistance(int label, double distance) {
            this.label = label;
            this.distance = distance;
        }
    }
}

