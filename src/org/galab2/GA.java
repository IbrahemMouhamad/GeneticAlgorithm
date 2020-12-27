package org.galab2;

import java.util.ArrayList;

public class GA {

    private Population population;
    private int sizeOfPopulation;
    private final int MAX = 10000;
    private Function function;
    private boolean isMin;
    private double min;
    private double max;

    public GA(boolean isMin,int size,double min,double max,Function f){
        this.isMin = isMin;
        function = f;
        population = new Population(isMin,size,min,max,f);
        sizeOfPopulation = size;
        this.max = max;
        this.min = min;
    }


    public void run(){


        for (int i = 0; i < MAX; i++)
            crossover();
           
        if(isMin)
            System.out.print("the minimum of ");
        else
            System.out.print("the maximum of ");
        System.out.println("y : " + population.getTheBest().getFitness());
        System.out.println("x : " + Double.valueOf(population.getTheBest().getGenes()));

    }

    public void crossover(){


        if(population.getIndividuals().size() > 300)
        population.reducePopulation();

        ArrayList<Individual> individuals = new ArrayList<>();
        Population tmp = new Population(sizeOfPopulation,function);


        int psize = population.getIndividuals().size();
        for (int i = 0; i < psize; i++) {
            individuals.add(population.getTheBestAndRemoveIt());
            tmp.addToPopulation(individuals.get(i));
        }

        population = tmp;


        boolean flag;
        int index;


        while (individuals.size() > 1) {

            flag = false;
            index = 3;

            //choice two of them
            int rand1 = (int) (Math.random() * (individuals.size()));
            Individual one = individuals.get(rand1);
            individuals.remove(rand1);

            int rand2 = (int) (Math.random() * (individuals.size()));
            Individual two = individuals.get(rand2);
            individuals.remove(rand2);


            while (index < one.getpIndex() && index < two.getpIndex()) {
                if (index > one.getDotIndex() && index > two.getDotIndex()){
                    flag = true;
                    break;
                }
                index++;
            }

            if(flag){
                String str = one.getGenes().substring(0,index) + two.getGenes().substring(index);
                Individual new1 = new Individual(isMin,str,function);
                str = two.getGenes().substring(0,index) + one.getGenes().substring(index);
                Individual new2 = new Individual(isMin,str,function);

                new1.mutation();
                new2.mutation();

                if(Double.valueOf(new1.getGenes()) <= max && Double.valueOf(new1.getGenes()) >= min)
                    population.addToPopulation(new1);
                if(Double.valueOf(new2.getGenes()) <= max && Double.valueOf(new2.getGenes()) >= min)
                    population.addToPopulation(new2);
            }
        }


        population = tmp;
    }


    public static void main(String[] args) {

        // if you want the minimum give true else false!

        GA ga = new GA(true,100, 0, 1, new Function() {
            @Override
            public double fitness(double x) {
                return x*x+4;
            }
        });

        ga.run();
    }
}
