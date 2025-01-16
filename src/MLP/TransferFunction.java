package MLP;

public interface TransferFunction {
    /**
     * Function de transfert
     *
     * @param value entrée
     * @return sortie de la fonction sur l'entrée
     */
    double evaluate(double value);

    /**
     * Dérivée de la fonction de tranfert
     *
     * @param value entrée
     * @return sortie de la fonction dérivée sur l'entrée
     */
    double evaluateDer(double value);
}