package KNN;

public class Imagette {
    double[][] pixels;
    int etiquette;

    public Imagette(int rows, int cols) {
        pixels = new double[rows][cols];
    }

    public void setPixel(double[][] pixel) {
        pixels=pixel;
    }

    public double getPixel(int row, int col) {
        return pixels[row][col];
    }

    public void setEtiquette(int etiquette) {
        this.etiquette = etiquette;
    }

    public int getLabel() {
        return etiquette;
    }


    public double[][] normalizeInput(double[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = input[i][j] / 255;
            }
        }
        return input;
    }

    public int getPixelCount() {
        return this.pixels[0].length * this.pixels.length;
    }

    public int[] getFlatPixels() {
        int lineSize = this.pixels[0].length;

        int[] flatPixels = new int[this.pixels.length * lineSize];

        for (int x = 0; x < this.pixels.length; x++) {
            double[] line = this.pixels[x];
            for (int y = 0; y < line.length; y++) {
                flatPixels[x * lineSize + y] = (int) line[y];
            }
        }

        return flatPixels;
    }
}

