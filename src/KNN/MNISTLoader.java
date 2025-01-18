package KNN;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MNISTLoader {
    public static Donnees loadData(String imageFilePath, String labelFilePath) throws IOException {
        // Charger les images
        Imagette[] images = loadImages(imageFilePath);

        // Charger les étiquettes
        int[] labels = Etiquette.loadLabels(labelFilePath);

        // Associer chaque image à son étiquette
        for (int i = 0; i < images.length; i++) {
            images[i].setEtiquette(labels[i]);  // Associer l'étiquette à l'image
        }

        // Retourner les données associées sous forme d'un objet Donnees
        return new Donnees(images);
    }

    public static Imagette[] loadImages(String filePath) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath))) {
            int id = dataInputStream.readInt();
            int nbImages = dataInputStream.readInt();
            nbImages = 1000;
            int nbLignes = dataInputStream.readInt();
            int nbColonnes = dataInputStream.readInt();
            Imagette[] images = new Imagette[nbImages];

            for (int k = 0; k < nbImages; k++) {
                images[k] = new Imagette(nbLignes, nbColonnes);
                double[][] pixels = images[k].pixels;

                for (int i = 0; i < nbLignes; i++) {
                    for (int j = 0; j < nbColonnes; j++) {
                        pixels[i][j] = dataInputStream.readUnsignedByte();
                        pixels[i][j] /= 255;
                    }
                }
            }
            return images;
        }
    }

    public static void sauverImage(Imagette image, String filePath) throws IOException {
        int nbLignes = image.pixels.length;
        int nbColonnes = image.pixels[0].length;
        BufferedImage bufferedImage = new BufferedImage(nbColonnes, nbLignes, BufferedImage.TYPE_BYTE_GRAY);

        int[] pixelMap = new int[256];
        for (int i = 0; i < 256; i++) {
            pixelMap[i] = (i << 16) | (i << 8) | i;
        }

        double[][] pixels = image.pixels;
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                bufferedImage.setRGB(j, i, pixelMap[(int) pixels[i][j]]);
            }
        }

        ImageIO.write(bufferedImage, "png", new java.io.File(filePath));
    }



}