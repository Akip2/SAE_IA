package KNN;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Etiquette {
    public static int[] loadLabels(String filePath) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath))) {
            // Lire l'identifiant magique pour vérifier le type de fichier
            int magicNumber = dataInputStream.readInt();
            if (magicNumber != 2049) {
                throw new IOException("Fichier invalide : identifiant incorrect " + magicNumber);
            }

            // Lire le nombre d'étiquettes
            int numberOfLabels = dataInputStream.readInt();

            // Créer un tableau pour stocker les étiquettes
            int[] labels = new int[numberOfLabels];

            // Lire chaque étiquette (un octet par étiquette)
            for (int i = 0; i < numberOfLabels; i++) {
                labels[i] = dataInputStream.readUnsignedByte();  // Lire l'octet représentant l'étiquette
            }

            return labels;
        }
    }
}
