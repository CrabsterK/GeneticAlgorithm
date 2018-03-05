package com.si;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        final int POP_SIZE = 100;
        final int GEN = 100;
        final double PM = 0.03;
        final double PX = 0.7;
        final int TOUR = 8;
        String filePath = "input/had20.dat.txt";

        long startTime = System.currentTimeMillis();

        InputData id = new InputData(filePath);
        int [][] matrixDist = id.getDistanceMatrix();
        int [][] matrixFlow = id.getFlowsMatrix();

        GenerationDataHolder data = null;
        try {
            data = new GenerationDataHolder();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final int PERSON_SIZE = id.getDimension();
        GeneticAlgorithm ga = new GeneticAlgorithm();
        Population population = new Population(POP_SIZE, PERSON_SIZE);
        population.initialize();


        for(int currentGeneration = 0; currentGeneration < GEN; currentGeneration++) {
            Population tempPopulation = new Population(POP_SIZE, PERSON_SIZE);
            for (int i = 0; i < POP_SIZE; i++) {
                Person tourSelection = ga.tourSelect(population.getPopulation(), TOUR, matrixDist, matrixFlow);
                tempPopulation.addPerson(tourSelection);
            }
            population = tempPopulation;

            tempPopulation = ga.crossoverPopulation(population, PX, POP_SIZE, PERSON_SIZE);
            population = tempPopulation;    //mam je po krosie

            tempPopulation = ga.mutatatePopulation(population, PM);
            population = tempPopulation;


            int bestVal = population.getBestValue(matrixDist, matrixFlow);
            int worstVal = population.getWorstValue(matrixDist, matrixFlow);
            int avgVal = population.getAverageValue(matrixDist, matrixFlow);
            data.addPopulationData(currentGeneration, bestVal, worstVal, avgVal);
        }


        if(data.generateFile()){
            System.out.println("DATA GENERATED SUCCESFULLY!");
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Execution time: " + elapsedTime + "ms");





        //TEST odczytu pliku
       /*
        System.out.println("Dimension is: " + id.getDimension());
        System.out.println("\nFLOW MATRIX:\n");
        id.printFlowsMatrix();
        System.out.println("\nDISTANCE MATRIX:\n");
        id.printDistanceMatrix();*/

        //TEST value from file
        /*
        List genotype = Arrays.asList(3,10,11,2,12,5,6,7,8,1,4,9);
        Person person = new Person(genotype);
        int cost = person.value(id.getFlowsMatrix(), id.getDistanceMatrix());
        System.out.println("COST: " + cost);
        */
    }
}