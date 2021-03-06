package org.galab2;

public class Individual implements Comparable<Individual>{

    private String genes;
    private double fitness;
    private int pIndex;
    private int dotIndex;
    private boolean isMin = true;

    public Individual(boolean isMin,double x,Function f){
        genes = Double.toHexString(x);
        fitness = f.fitness(x);
        pIndex = genes.length() -1;
        while (genes.charAt(pIndex) != 'p')
            pIndex--;
        dotIndex = 0;
        while (genes.charAt(dotIndex) != '.')
            dotIndex++;
    }

    public Individual(boolean isMin,String str,Function f){
        this.isMin = isMin;
        genes = str;
        fitness = f.fitness(Double.valueOf(str));
        pIndex = genes.length() -1;
        while (genes.charAt(pIndex) != 'p')
            pIndex--;
        dotIndex = 0;
        while (genes.charAt(dotIndex) != '.')
            dotIndex++;
    }

    public double getFitness() {
        return fitness;
    }

    public String getGenes() {
        return genes;
    }

    public boolean mutation(){
    
        int randomIndex = (int) ((Math.random() * (pIndex - dotIndex -1 )) + dotIndex + 1);
        boolean randomBool = Math.random() * 100 <= 2 ? true : false;

        if(randomIndex > (genes.length() - 3))
            randomIndex = genes.length() - 3;

        int randomNumber = (int) (Math.random() * (16));

        if(randomBool)
            genes = genes.substring(0,randomIndex) + Integer.toHexString(randomNumber) + genes.substring(randomIndex + 1);

        return randomBool;

    }

    @Override
    public int compareTo(Individual i){
        if (fitness < i.getFitness()) return isMin ? -1:1;
        if (fitness > i.getFitness()) return isMin ? 1:-1;
        return 0;
    }




    public int getDotIndex() {
        return dotIndex;
    }

    public int getpIndex() {
        return pIndex;
    }
}