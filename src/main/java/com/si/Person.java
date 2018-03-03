package com.si;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person {
    private List <Integer> genotype;

    public Person(List genotype) {
        this.genotype = genotype;
    }

    public static ArrayList <Integer> getPossibleGenList(int PERSON_SIZE) {
        ArrayList <Integer> possibleGenns = new ArrayList();
        for (int i = 0; i < PERSON_SIZE; i++) {
            possibleGenns.add(i + 1);
        }
        return possibleGenns;
    }

    public List <Integer> getGenotype() {
        return genotype;
    }

    public int checkValue(int [][] matrixDist, int [][] matrixFlow){ //arr to sprawdzany wektor, a N to jego długość
        int result = 0;
        for (int i = 0; i < genotype.size(); i++){
            for (int j = 0; j < genotype.size(); j++){
                result += ((matrixDist[i][j]) * matrixFlow[genotype.get(i)-1][genotype.get(j)-1]);
            }
        }
        return result;
    }

    public void repairGenotypeIfWrong(){
        List<Integer> duplicates = new ArrayList<Integer>(findDuplicates(genotype));
        List<Integer> potential = new ArrayList<Integer>(findPotential(genotype));
        if(!duplicates.isEmpty()) {
            for (int i = 0; i < duplicates.size(); i++) {
                int indexOfDup = genotype.indexOf(duplicates.get(i));
                genotype.set(indexOfDup, potential.get(i));
            }
        }
    }


    private static Set<Integer> findDuplicates(List<Integer> listContainingDuplicates) {

        final Set<Integer> setToReturn = new HashSet<Integer>();
        final Set<Integer> set1 = new HashSet<Integer>();

        for (Integer yourInt : listContainingDuplicates) {
            if (!set1.add(yourInt)) {
                setToReturn.add(yourInt);
            }
        }
        return setToReturn;
    }

    private static Set<Integer> findPotential(List<Integer> listContainingDuplicates) {

        final Set<Integer> setToReturn = new HashSet<Integer>(getPossibleGenList(listContainingDuplicates.size()));
        final Set<Integer> set1 = new HashSet<Integer>();

        for (Integer yourInt : listContainingDuplicates) {
            setToReturn.remove(yourInt);
        }
        return setToReturn;
    }


    @Override
    public String toString() {
        return "Person{" + "genotype=" + genotype + '}';
    }
}
