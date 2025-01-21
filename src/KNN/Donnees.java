package KNN;

import java.util.*;

public class Donnees {

    Imagette[] images;

    public Donnees(Imagette[] images) {
        this.images = images;
    }

    public int getNombreImagettes() {
        return images.length;
    }

    public Imagette getImagette(int i) {
        return images[i];
    }

    public void mixImages() {
        // Conversion du tableau en liste
        List<Imagette> listeImagettes = new ArrayList<>();
        Collections.addAll(listeImagettes, images);

        // Mélange aléatoire
        Collections.shuffle(listeImagettes);

        // Conversion de la liste en tableau
        images = listeImagettes.toArray(new Imagette[0]);
    }
}
