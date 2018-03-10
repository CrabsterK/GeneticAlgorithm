package com.si;
import java.util.ArrayList;
import java.util.Collections;

public class GreedyAlgorithm {

    public static void returnBest(int popSize, int genotypeSize, int[][] distanceMatrix, int[][] flowsMatrix){
        int bestVal = 0;
        int avgVal = 0;
        int sum = 0;
        int numberOfPerson = 0;

        Population population = new Population(popSize, genotypeSize);
        Person person = new Person(population.createRandomGenotype());//osoba
        Person tmpPerson = new Person();//kopia osoby
        for(int i = 0; i < person.getGenotype().size()-1; i++) {
            sum += person.checkValue(distanceMatrix, flowsMatrix);
            ArrayList emptyList = new ArrayList();
            ArrayList copyGenotyp = new ArrayList();

            for(int j=0; j < person.getGenotype().size(); j++) {
                copyGenotyp.add(person.getGenotype().get(j));
            }
            tmpPerson.setGenotype(copyGenotyp);
            Collections.swap(tmpPerson.getGenotype(), i, i+1);

            if(tmpPerson.checkValue(distanceMatrix, flowsMatrix) < person.checkValue(distanceMatrix, flowsMatrix)) {	//JEŚLI POPRAWIŁEM SYTUACJĘ SWAPEM
                person.getGenotype().clear();				//WYRZUCAM GENOTYP RANDOMA
                person.setGenotype(tmpPerson.getGenotype());	//WRZUCAM DO NIEGO TEN LEPSZY
                i=0;	//OD NOWA SPRAWDZAM
            }
            tmpPerson.setGenotype(emptyList);
            numberOfPerson++;
            population.addPerson(person);
        }
        bestVal = person.checkValue(distanceMatrix, flowsMatrix);
        avgVal = sum/numberOfPerson;
       // System.out.println("Best: " + bestVal);
        System.out.println("AVG: " + avgVal);
       // System.out.println("numberOfPerson: " + numberOfPerson);
        System.out.println("OS: " + population.getAvgStdDeviationin(numberOfPerson, distanceMatrix, flowsMatrix, avgVal));
    }
}
