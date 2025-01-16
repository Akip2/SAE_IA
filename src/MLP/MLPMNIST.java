package MLP;

public class MLPMNIST {
    protected double fLearningRate = 0.6;
    protected Layer[] fLayers;
    protected TransferFunction fTransferFunction;

    /**
     * @param layers       Nb neurones par couches
     * @param learningRate tx d'apprentissage
     * @param fun          Function de transfert
     */

    public MLPMNIST(int[] layers, double learningRate, TransferFunction fun) {
        fLearningRate = learningRate;
        fTransferFunction = fun;

        fLayers = new Layer[layers.length];
        for (int i = 0; i < layers.length; i++) {
            if (i != 0) {
                fLayers[i] = new Layer(layers[i], layers[i - 1]);
            } else {
                fLayers[i] = new Layer(layers[i], 0);
            }
        }
    }

    /**
     * Réponse à une entrée
     *
     * @param input l'entrée testée
     * @return résultat de l'exécution
     */
    public int execute(int[] input) {
        int i, j, k;
        double new_value;

        int output;

        // input en entrée du réseau
        for (i = 0; i < fLayers[0].Length; i++) {
            fLayers[0].Neurons[i].Value = input[i];
        }

        // calculs couches cachées et sortie
        for (k = 1; k < fLayers.length; k++) {
            for (i = 0; i < fLayers[k].Length; i++) {
                new_value = 0.0;
                for (j = 0; j < fLayers[k - 1].Length; j++) {
                    new_value += fLayers[k].Neurons[i].Weights[j] * fLayers[k - 1].Neurons[j].Value;
                }
                new_value -= fLayers[k].Neurons[i].Bias;
                fLayers[k].Neurons[i].Value = fTransferFunction.evaluate(new_value);
            }
        }

        // Renvoyer sortie
        //System.out.println(fLayers[fLayers.length - 1].Neurons[0].Value);
        output = (int) (fLayers[fLayers.length - 1].Neurons[0].Value * 10);

        return output;
    }

    /**
     * Rétropropagation
     *
     * @param input  L'entrée courante
     * @param output Sortie souhaitée (apprentissage supervisé !)
     * @return Error différence entre la sortie calculée et la sortie souhaitée
     */

    public double backPropagate(int[] input, int output) {
        int new_output = execute(input);
        double error;
        int i, j, k;

        // Erreur de sortie
        error = output - new_output;
        fLayers[fLayers.length - 1].Neurons[0].Delta = error * fTransferFunction.evaluateDer(new_output);

        for (k = fLayers.length - 2; k >= 0; k--) {
            // Calcul de l'erreur courante pour les couches cachées
            // et mise à jour des Delta de chaque neurone
            for (i = 0; i < fLayers[k].Length; i++) {
                error = 0.0;
                for (j = 0; j < fLayers[k + 1].Length; j++)
                    error += fLayers[k + 1].Neurons[j].Delta * fLayers[k + 1].Neurons[j].Weights[i];
                fLayers[k].Neurons[i].Delta = error * fTransferFunction.evaluateDer(fLayers[k].Neurons[i].Value);
            }
            // Mise à jour des poids de la couche suivante
            for (i = 0; i < fLayers[k + 1].Length; i++) {
                for (j = 0; j < fLayers[k].Length; j++)
                    fLayers[k + 1].Neurons[i].Weights[j] += fLearningRate * fLayers[k + 1].Neurons[i].Delta *
                            fLayers[k].Neurons[j].Value;
                fLayers[k + 1].Neurons[i].Bias -= fLearningRate * fLayers[k + 1].Neurons[i].Delta;
            }
        }

        // Calcul de l'erreur
        error = Math.abs(new_output - output);

        return error;
    }

    /**
     * @return LearningRate
     */
    public double getLearningRate() {
        return fLearningRate;
    }

    /**
     * maj LearningRate
     *
     * @param rate nouveau LearningRate
     */
    public void setLearningRate(double rate) {
        fLearningRate = rate;
    }

    /**
     * maj fonction de tranfert
     *
     * @param fun nouvelle fonction de tranfert
     */
    public void setTransferFunction(TransferFunction fun) {
        fTransferFunction = fun;
    }

    /**
     * @return Taille couche d'entrée
     */
    public int getInputLayerSize() {
        return fLayers[0].Length;
    }


    /**
     * @return Taille couche de sortie
     */
    public int getOutputLayerSize() {
        return fLayers[fLayers.length - 1].Length;
    }
}