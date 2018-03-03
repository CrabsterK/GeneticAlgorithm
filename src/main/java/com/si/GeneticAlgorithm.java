package com.si;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {


    public Person tourSelect(ArrayList <Person> pop, int TOUR, int[][] distanceMatrix, int[][] flowsMatrix) {
        Random rn = new Random();
        ArrayList <Person> selectedTour = new ArrayList <Person>();

        for (int i = 0; i <TOUR; i++) {
            int randomRow = rn.nextInt(pop.size());
            selectedTour.add(pop.get(randomRow));
        }

        int bestVal = selectedTour.get(0).checkValue(distanceMatrix, flowsMatrix);
        int indexOfBest = 0;
        for(int i = 1; i < selectedTour.size(); i++){
            int currentVal = selectedTour.get(i).checkValue(distanceMatrix, flowsMatrix);
            if (currentVal < bestVal){
                bestVal = currentVal;
                indexOfBest = i;
            }
        }
        return selectedTour.get(indexOfBest);
    }



    public Population crossoverPopulation(Population population, double PX, int POP_SIZE, int PERSON_SIZE){//ta populacja jest już w losowej kolejności
        ArrayList <Person> pop = population.getPopulation();
        Population newPopulation = new Population(POP_SIZE, PERSON_SIZE);
        Random rn = new Random();
        while ( pop.size() > 1){
            int currentIndex = 0;
            int nextIndex = 1;
            if (Math.random() < PX){
                int cutPoint = rn.nextInt(PERSON_SIZE - 1);
                List<Integer> genotype1 = new ArrayList<Integer>();
                List<Integer> genotype2 = new ArrayList<Integer>();
                for (int gen = 0; gen <= cutPoint; gen++) {
                    genotype1.add((Integer) pop.get(currentIndex).getGenotype().get(gen));
                    genotype2.add((Integer) pop.get(nextIndex).getGenotype().get(gen));
                }
                int goCut = cutPoint+1;
                for (int gen = goCut; gen < PERSON_SIZE; gen++) {
                    genotype1.add((Integer) pop.get(nextIndex).getGenotype().get(gen));
                    genotype2.add((Integer) pop.get(currentIndex).getGenotype().get(gen));
                }
                Person person1 = new Person(genotype1);
                Person person2 = new Person(genotype2);
                person1.repairGenotypeIfWrong();
                person2.repairGenotypeIfWrong();
                newPopulation.addPerson(person1);
                newPopulation.addPerson(person2);
                pop.remove(0);  //zawsze usuwam dwa z wierzchu. Po usunięciu tutaj i wielkości size 2
                pop.remove(0);  //tutaj juz zostaje tylko index 0.
            }
            else{
                newPopulation.addPerson(pop.get(currentIndex));
                pop.remove(currentIndex);
            }
        }
        if(pop.size() == 1){
            newPopulation.addPerson(pop.get(0));
        }
        return newPopulation;
    }


    public Population mutatatePopulation(Population population, double PM) {
        Random rn = new Random();
        int genotypeSize = population.getPersonSize();
        int populationSize = population.getPopulation().size();
        Population newPopulation = new Population(populationSize, genotypeSize);
        ArrayList<Person> pop = population.getPopulation();

        for (int i = 0; i < populationSize; i++) {
            Person person = pop.get(i);
            for (int gen = 0; gen < genotypeSize; gen++) {
                if (Math.random() < PM) {
                    int toSwapWith = rn.nextInt(genotypeSize);
                    while (gen == toSwapWith) {
                        toSwapWith = rn.nextInt(genotypeSize);
                    }
                    person = mutatePerson(person, gen, toSwapWith);
                }
            }
            newPopulation.addPerson(person);
        }
        return newPopulation;
    }


    private Person mutatePerson(Person person, int genIndex, int toSwapWith){
        int tpm = person.getGenotype().get(genIndex);
        person.getGenotype().set(genIndex, person.getGenotype().get(toSwapWith));
        person.getGenotype().set(toSwapWith, tpm);
        return person;
    }
}
