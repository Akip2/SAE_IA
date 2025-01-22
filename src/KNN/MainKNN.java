package KNN;

import java.io.IOException;


public class MainKNN {
    public static void main(String[] args) {
        try {
            System.out.println("Premier chargement de données");
            Donnees donneesTrain = MNISTLoader.loadData("images/MNIST/train-images.idx3-ubyte", "images/MNIST/train-labels.idx1-ubyte", null);
            // Fashion
            Donnees donneesTrainFashion = MNISTLoader.loadData("images/FASHION_MNIST/train-images-idx3-ubyte", "images/FASHION_MNIST/train-labels-idx1-ubyte", null);

            //System.out.println("Création de l'image");
            //MNISTLoader.sauverImage(images[images.length-1], "images/test2.png");
            System.out.println("Recherche des labels");
            int[] labels = Etiquette.loadLabels("images/MNIST/train-labels.idx1-ubyte");
            int[] labelsFashion = Etiquette.loadLabels("images/FASHION_MNIST/train-labels-idx1-ubyte");
            // Vérifier les étiquettes de la première et de la dernière image
            System.out.println("Étiquette de la première image : " + labelsFashion[0]);
            System.out.println("Étiquette de la dernière image : " + labelsFashion[labelsFashion.length - 1]);
            Donnees donneesTest = MNISTLoader.loadData("images/MNIST/t10k-images.idx3-ubyte", "images/MNIST/t10k-labels.idx1-ubyte", null);
            Donnees donneesTestFashion = MNISTLoader.loadData("images/FASHION_MNIST/t10k-images-idx3-ubyte", "images/FASHION_MNIST/t10k-labels-idx1-ubyte", null);
            System.out.println("Deuxième chargement de données");
            kNN algo = new kNN(donneesTrainFashion,2);
            System.out.println("Calcul de la précision");
            double Res = Statistique.calculerPrecision(algo, donneesTestFashion);
            System.out.println("Precision : " + Res*100 + "%");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
