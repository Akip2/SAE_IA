import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Donnees donneesTrain = MNISTLoader.loadData("images/train-images.idx3-ubyte", "images/train-labels.idx1-ubyte");
            System.out.println("Premier chargement de données");
            //System.out.println("Création de l'image");
            //MNISTLoader.sauverImage(images[images.length-1], "images/test2.png");
           System.out.println("Recherche des labels");
            int[] labels = Etiquette.loadLabels("images/train-labels.idx1-ubyte");
            // Vérifier les étiquettes de la première et de la dernière image
             System.out.println("Étiquette de la première image : " + labels[0]);
            System.out.println("Étiquette de la dernière image : " + labels[labels.length - 1]);
            Donnees donneesTest = MNISTLoader.loadData("images/t10k-images.idx3-ubyte", "images/t10k-labels.idx1-ubyte");
            System.out.println("Deuxième chargement de données");
           // PlusProche algo = new PlusProche(donneesTrain);
            kNN algo = new kNN(donneesTrain,2);
            System.out.println("Calcul de la précision");
            double Res = Statistique.calculerPrecision(algo, donneesTest);
            System.out.println("Precision : " + Res*100 + "%");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
