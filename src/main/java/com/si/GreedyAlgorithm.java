package com.si;
import java.util.ArrayList;
import java.util.Collections;

public class GreedyAlgorithm {

    public static Person returnBest(int popSize, int genotypeSize, int[][] distanceMatrix, int[][] flowsMatrix){
        Population population = new Population(popSize, genotypeSize);
        Person person = new Person(population.createRandomGenotype());//osoba
        Person tmpPerson = new Person();//kopia osoby
        for(int i = 0; i < person.getGenotype().size()-1; i++) {
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
        }
        return person;
    }
}
