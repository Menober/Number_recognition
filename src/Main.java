import com.sun.deploy.ui.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[]args) throws IOException {

        ArrayList<double [][]> det=new ArrayList<double [][]>();
        int countOfNumbers=10; //-1 ilosc folderow z danymi

        double [][] image=loadImageToMatrix("res\\image.bmp");
        image=deleteUselessColumnsAndRows(image);

        for(int i=0;i<countOfNumbers;i++)
        det.add(designModelMatrix(10,10,10,"res\\treningData\\"+i+"\\"));


        ArrayList<Diffrence> differences=new ArrayList<>();

        for(int i=0;i<countOfNumbers;i++)
            differences.add(new Diffrence(String.valueOf(i),compareMatrices(image,det.get(i))));
        sort(differences);

        //The fewer the better
        System.out.println("The best match:");
        showArray(differences);



         saveMatrixAsImage(image,"res\\out\\!");
        for(int i=0;i<countOfNumbers;i++)
            saveMatrixAsImage(det.get(i),"res\\out\\"+i+"");

        }

        public static void showArray(ArrayList<Diffrence> diffrences){
        for(Diffrence d:diffrences)
            System.out.println(d);
        }
        public static void sort(ArrayList<Diffrence> diffrences){
            Collections.sort(diffrences, new Comparator<Diffrence>() {
                @Override
                public int compare(final Diffrence o1, final Diffrence o2) {
                    if (o1.value > o2.value) {
                        return 1;
                    } else if (o1.value < o2.value) {
                        return -1;
                    }
                    return 0;
                }
            });
        }
        public static double compareMatrices(double[][] matrix1,double[][] matrix2){
            double det=0;

            for(int y=0;y<matrix1.length;y++)
            {
                for(int x=0;x<matrix1[0].length;x++){
                    det+=Math.abs(matrix1[y][x]-matrix2[y][x]);
                }
            }

            return det;
        }
        public static double[][] deleteUselessColumnsAndRows(double[][] matrixIn){
            double[][] matrixOut=new double[matrixIn.length][matrixIn[0].length];
            boolean isNotZero=false;
            int offset=0;//ilosc lewych kolumn do usuniecia
            for(int x=0;x<matrixIn[0].length&&!isNotZero;x++) {
                for (int y = 0; y < matrixIn.length&&!isNotZero; y++){
                    if(matrixIn[y][x]!=0)
                        isNotZero=true;
                }
                offset++;
            }offset--;

            for(int x=0;x<matrixIn[0].length-offset;x++){
                for(int y=0;y<matrixIn.length;y++){
                    matrixOut[y][x]=matrixIn[y][x+offset];
                }
            }
            //usuwanie wierszy
            isNotZero=false;
            offset=0;
            for(int y=0;y<matrixOut.length&&!isNotZero;y++){
                for(int x=0;x<matrixOut[0].length&&!isNotZero;x++){
                    if(matrixOut[y][x]!=0){
                        isNotZero=true;
                    }

                }
                offset++;
            }offset--;

            for(int x=0;x<matrixOut[0].length;x++){
                for(int y=0;y<matrixOut.length;y++){
                    if(y<matrixOut.length-offset)
                        matrixOut[y][x]=matrixOut[y+offset][x];
                    else
                        matrixOut[y][x]=0;
                }
            }

            return matrixOut;
        }
        public static double[][] designModelMatrix(int width, int height, int imagesCount,String path) throws IOException {
           double[][]matrix=new double[width][height];
           double [][]tmp;
            for(int i=1;i<=imagesCount;i++) {
                tmp=deleteUselessColumnsAndRows(loadImageToMatrix(path+i+".bmp"));

                for(int y=0;y<width;y++)
                    for(int x=0;x<height;x++)
                        matrix[y][x]+=tmp[y][x];
            }
            for(int y=0;y<width;y++)
                for(int x=0;x<height;x++)
                    matrix[y][x]/=imagesCount;
        return matrix;
        }
        public static void viewMatrix(double [][] matrix){
        for(int x=0;x<matrix[0].length;x++){
            for(int y=0;y<matrix.length;y++)
                System.out.print(matrix[x][y]);
            System.out.println();
        }

    }
        public static double[][] loadImageToMatrix(String res) throws IOException {
            File input = new File(res);
            BufferedImage image;
            image=ImageIO.read(input);
            double[][] matrix=new double[image.getHeight()][image.getWidth()];
            for(int y=0;y<image.getHeight();y++)
                for(int x=0;x<image.getWidth();x++)
                    if(image.getRGB(x,y)==-16777216)
                        matrix[y][x]=1;
                    else
                        matrix[y][x]=0;
        return matrix;
        }
        public static void saveMatrixAsImage(double [][]matrix,String res) throws IOException {
            File input = new File(res+"output.bmp");
            BufferedImage image=new BufferedImage(matrix[0].length,matrix.length,1);
            for(int y=0;y<matrix.length;y++)
                for(int x=0;x<matrix[0].length;x++)
                    image.setRGB(x,y,(int)(200*matrix[y][x]));
            ImageIO.write(image,"bmp",input);
        }
}
