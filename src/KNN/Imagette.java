package KNN;

import java.util.stream.Stream;

public class Imagette {
        double[][] pixels;
        int etiquette;

        public Imagette(int rows, int cols) {
            pixels = new double[rows][cols];
        }

        public void setPixel(int row, int col, double value) {
            pixels[row][col] = value;
        }

        public double getPixel(int row, int col) {
            return pixels[row][col];
        }

        public void setEtiquette(int etiquette) {
            this.etiquette = etiquette;
        }

        public double getLabel() {
            return etiquette;
        }

        public int getPixelCount() {
            return this.pixels[0].length*this.pixels.length;
        }

        public double[] getFlatPixels() {
            int lineSize = this.pixels[0].length;

            double[] flatPixels = new double[this.pixels.length * lineSize];

            for(int x=0; x<this.pixels.length; x++){
                double[] line = this.pixels[x];
                for(int y=0; y<line.length; y++){
                    flatPixels[x*lineSize+y]=line[y];
                }
            }

            return flatPixels;
        }
    }

