package com.si;

import java.util.ArrayList;
import java.util.Collections;


public class Population {

    private ArrayList <Person> population;
    private final int PERSON_SIZE;
    private final int POP_SIZE;

    public int getPersonSize() {
        return PERSON_SIZE;
    }



    public ArrayList getPopulation() {
        return population;
    }


    public Population(int POP_SIZE, int PERSON_SIZE) {
        this.POP_SIZE = POP_SIZE;
        this.PERSON_SIZE = PERSON_SIZE;
        population = new ArrayList<Person>();
    }

    public void initialize(){
        population = initializePopulation(POP_SIZE);
    }

    public void addPerson(Person person){
        population.add(person);
    }

    public ArrayList getPossibleGenList(int PERSON_SIZE) {
        final ArrayList possibleGenns = new ArrayList();
        for (int i = 0; i < PERSON_SIZE; i++) {
            possibleGenns.add(i + 1);
        }
        return possibleGenns;
    }

    private ArrayList initializePopulation(int POP_SIZE) {
        ArrayList <Person> populationLocal = new ArrayList<Person>();
        Person person;
        for (int row = 0; row < POP_SIZE; row++) {// make preson
            person = new Person(createRandomGenotype());
            populationLocal.add(person);
        }
        return populationLocal;
    }

    public ArrayList createRandomGenotype(){
        ArrayList genotype = new ArrayList();
        ArrayList possibleGennsList = getPossibleGenList(PERSON_SIZE);
        Collections.shuffle(possibleGennsList);
        for (int col = 0; col < PERSON_SIZE; col++) {//make gen array
            genotype.add((Integer) possibleGennsList.get(col));
        }
        return genotype;
    }

    public int getBestValue(int [][] matrixDist, int [][] matrixFlow){
        int best = population.get(0).checkValue(matrixDist, matrixFlow);
        int actualValue;
        for(int i = 1; i < POP_SIZE; i++){
            actualValue = population.get(i).checkValue(matrixDist, matrixFlow);
            if(best > actualValue){
                best = actualValue;
            }
        }
        return best;
    }

    public double getAvgStdDeviationin(int [][] matrixDist, int [][] matrixFlow, int avg){
        double sum = 0;
        double sumN = 0;
        double result = 0;
        for(int i = 1; i < POP_SIZE; i++){
            sum += (population.get(i).checkValue(matrixDist, matrixFlow)-avg) * (population.get(i).checkValue(matrixDist, matrixFlow)-avg);
        }
        sumN = sum/POP_SIZE;
        result = Math.sqrt(sumN);
        return result;
    }

    public int getWorstValue(int [][] matrixDist, int [][] matrixFlow){
        int worst = population.get(0).checkValue(matrixDist, matrixFlow);
        int actualValue;
        for(int i = 1; i < POP_SIZE; i++){
            actualValue = population.get(i).checkValue(matrixDist, matrixFlow);
            if(worst < actualValue){
                worst = actualValue;
            }
        }
        return worst;
    }

    public int getAverageValue(int [][] matrixDist, int [][] matrixFlow){
        int sum = 0;
        int avg;
        for(int i = 0; i < POP_SIZE; i++){
            sum += population.get(i).checkValue(matrixDist, matrixFlow);
        }
        avg = sum/POP_SIZE;
        return avg;
    }



    @Override
    public String toString() {
        String str="";
        for (int i = 0; i < population.size(); i ++) {
            str += population.get(i) + " -osobnik nr " + i + "\n";
        }
        return str;
    }
}
