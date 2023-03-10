import parallel.ParallelMatrixMultiplication;
import sequential.MatrixMultiplication;
import streamParallel.StreamParallelMatrixMultiplication;
import util.MatrixUtil;

import java.util.Date;

public class Main {

    public static final String SEQUENTIAL = "sequential";
    public static final String PARALLEL = "parallel";
    public static final String STREAM_PARALLEL = "streamParallel";

    public static void main(String[] args) {
        printResults();
    }

    private static void printResults() {
        double[][] matrixA = MatrixUtil.generateMatrix(2000, 2000);
        double[][] matrixB = MatrixUtil.generateMatrix(2000, 2000);
        printMatrixMultiplicationResults(matrixA, matrixB, SEQUENTIAL);
        printMatrixMultiplicationResults(matrixA, matrixB, PARALLEL);
        printMatrixMultiplicationResults(matrixA, matrixB, STREAM_PARALLEL);
    }

    private static void printMatrixMultiplicationResults(double[][] matrixA, double[][] matrixB, String type) {
        if (matrixA.length < 100 && matrixB.length < 100) {
            System.out.println("Matrix A : ");
            MatrixUtil.print(matrixA);

            System.out.println("\nMatrix B : ");
            MatrixUtil.print(matrixB);
        }

        Date start = new Date();

        double[][] result = new double[matrixA.length][matrixB[0].length];

        if (type.equals(SEQUENTIAL)) {
            result = MatrixMultiplication.multiply(matrixA, matrixB);
        } else if (type.equals(PARALLEL)) {
            ParallelMatrixMultiplication.multiply(matrixA, matrixB, result);
        } else if(type.equals(STREAM_PARALLEL)) {
            result = StreamParallelMatrixMultiplication.multiply(matrixA, matrixB);
        }

        Date end = new Date();

        if (result.length < 100) {
            System.out.println("\nOutput Matrix : ");
            MatrixUtil.print(result);
        }

        System.out.println("\nTime taken in milli seconds for " + type + " algorithm: " + (end.getTime() - start.getTime()));
    }
}