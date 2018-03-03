package com.si;

import java.io.*;
import java.util.*;


public class InputData {
    int[][] distanceMatrix;
    int[][] flowsMatrix;
    int dimension;


    public InputData(String filePath) {
        setDistanceMatrix(readDistanceMatrix(filePath));
        setFlowsMatrix(readFlowsMatrix(filePath));
        setDimension(readDimension(filePath));
    }


    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public int[][] getFlowsMatrix() {// pierwsza w pliku
        return flowsMatrix;
    }

    public void setFlowsMatrix(int[][] flowsMatrix) {
        this.flowsMatrix = flowsMatrix;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }




    private int[][] readDistanceMatrix(String filePath) {
        int dimension = readDimension(filePath);
        int[][] distanceMatrix = new int[dimension][dimension];
        Scanner input = null;
        try {
            input = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input.nextLine();   //skip line with dimension
        for ( int i = 0; i <= dimension; i++){ //skip Flows Matrix
            input.nextLine();
        }

        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(input.hasNextInt()){
                    distanceMatrix[i][j] = input.nextInt();
                }
            }
        }
        input.close();
        return distanceMatrix;
    }


    private int[][] readFlowsMatrix(String filePath) {// pierwsza w pliku
        int dimension = readDimension(filePath);
        int[][] flowsMatrix = new int[dimension][dimension];
        Scanner input = null;
        try {
            input = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input.nextLine();   //skip line with dimension
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(input.hasNextInt()){
                    flowsMatrix[i][j] = input.nextInt();
                }
            }
        }
        input.close();
        return flowsMatrix;
    }


    public static int readDimension(String filePath) {
        int dimension = 0;
        try {
            BufferedReader input = new BufferedReader(new FileReader(filePath));
            dimension = Integer.parseInt(input.readLine().replaceAll("\\s+", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dimension;
    }









    public void printFlowsMatrix(){
        int dimension = getDimension();
        for (int row = 0; row < dimension; row ++) {
            for (int col = 0; col < dimension; col++) {
                System.out.print(flowsMatrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void printDistanceMatrix(){
        int dimension = getDimension();
        for (int row = 0; row < dimension; row ++) {
            for (int col = 0; col < dimension; col++) {
                System.out.print(distanceMatrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
