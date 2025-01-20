package MLP;

public class Sigmoid implements TransferFunction {

    @Override
    public double evaluate(double value) {
        return 1 / (1 + Math.exp(-value));
    }

    @Override
    public double evaluateDer(double value) {
        return value - Math.pow(value, 2);
    }
}
