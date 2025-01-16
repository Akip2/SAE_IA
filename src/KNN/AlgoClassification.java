package KNN;

public abstract class AlgoClassification {
    Donnees donneesEntrainement;

    public AlgoClassification(Donnees donneesEntrainement) {
        this.donneesEntrainement = donneesEntrainement;
    }

    public abstract int predireEtiquette(Imagette imagette);
}
