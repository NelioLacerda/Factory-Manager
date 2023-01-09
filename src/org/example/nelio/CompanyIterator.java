package org.example.nelio;

public class CompanyIterator {
    private double[] movements;
    private int size, nextIndex;

    public CompanyIterator(double[] movements, int size){
        this.movements = movements;
        this.size = size;
        this.nextIndex = 0;
    }

    public boolean hasNext(){
        return nextIndex < size;
    }

    public double next(){
        return movements[nextIndex++];
    }
}
