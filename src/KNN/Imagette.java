package KNN;

import java.util.stream.Stream;

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

        public int getPixelCount() {
            return this.pixels[0].length*this.pixels.length;
        }

        public int[] getFlatPixels() {
            int lineSize = this.pixels[0].length;

            int[] flatPixels = new int[this.pixels.length * lineSize];

            for(int x=0; x<this.pixels.length; x++){
                int[] line = this.pixels[x];
                for(int y=0; y<line.length; y++){
                    flatPixels[x*lineSize+y]=line[y];
                }
            }

            return flatPixels;
        }
    }

