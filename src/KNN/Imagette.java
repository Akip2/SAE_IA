package MNIST;

public class Imagette {
        int[][] pixels;
        int etiquette;

        public Imagette(int rows, int cols) {
            pixels = new int[rows][cols];
        }

        public void setPixel(int row, int col, int value) {
            pixels[row][col] = value;
        }

        public int getPixel(int row, int col) {
            return pixels[row][col];
        }

        public void setEtiquette(int etiquette) {
            this.etiquette = etiquette;
        }

        public int getLabel() {
            return etiquette;
        }
    }

