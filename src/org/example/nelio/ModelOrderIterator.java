package org.example.nelio;

public class ModelOrderIterator {
    private Model[] models;
    private int size, nextIndex;

    public ModelOrderIterator(Model[] models, int size){
        this.models = models;
        this.size = size;
        int nextIndex = 0;
    }

    public boolean hasNext(){
        return nextIndex < size;
    }

    public Model next(){
        return models[nextIndex++];
    }
}
