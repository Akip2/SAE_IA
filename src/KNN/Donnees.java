package KNN;

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
}
