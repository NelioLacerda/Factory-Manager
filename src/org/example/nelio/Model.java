package org.example.nelio;

public class Model {
    private String modelName;
    private double modelPrice;
    private int amountProduce;

    private Boolean is_Ben_Produce;

    public Model(String modelName, double modelPrice){
        this.modelName = modelName;
        this.modelPrice = modelPrice;
        this.amountProduce = 0;
        is_Ben_Produce = false;
    }

    public String getModelName(){
        return modelName;
    }

    public double getModelPrice(){
        return modelPrice;
    }

    public void setModelName(String name){
        this.modelName = name;
    }

    public void setModelPrice(double price){
        this.modelPrice = price;
    }

    public int getAmountProduce(){
        return amountProduce;
    }

    public void setIs_Ben_Produce(boolean newState){
        this.is_Ben_Produce = newState;
    }

    public boolean getProductionSate(){
        return is_Ben_Produce;
    }
    public void addToAmountProduce(int amountProduce){
        this.amountProduce += amountProduce;
    }

    public void removeAmountProduce(int amountProduce){
        this.amountProduce -= amountProduce;
        if (amountProduce < 0){
            this.amountProduce = 0;
        }
    }

    public int compareTo(Model other){
        return this.is_Ben_Produce.compareTo(other.getProductionSate());
    }
}
