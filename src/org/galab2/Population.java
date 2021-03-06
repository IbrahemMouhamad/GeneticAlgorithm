package org.galab2;

import java.util.PriorityQueue;

public class Population {


    private PriorityQueue<Individual> individuals;
    private int size;


    public PriorityQueue<Individual> getIndividuals() {
        return individuals;
    }

    public Population(boolean isMin,int s, double min, double max,Function f){

        size = s;
        individuals = new PriorityQueue<>();
        for (int i = 0; i < s; i++) {

            double rand = Math.random() * (max - min) + min;
            Individual individual = new Individual(isMin,rand,f);
            individuals.add(individual);

        }

    }


    public Population(int s,Function f){
        size = s;
    }

    public void reducePopulation(){
        if(individuals.size() > size){
            PriorityQueue<Individual> tmp = new PriorityQueue<>();
            for (int i = 0; i < size; i++) {
                tmp.add(individuals.poll());
            }
            individuals = tmp;
        }
    }


    public void addToPopulation(Individual individual){
        if(individuals == null)
            individuals = new PriorityQueue<>();
        individuals.add(individual);
    }


    public Individual getTheBest(){
        return individuals.peek();
    }

    public Individual getTheBestAndRemoveIt(){
        return individuals.poll();
    }

}
