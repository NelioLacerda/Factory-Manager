package org.example.nelio;

public class Reserve {
    private double quantity;

    public Reserve(double quantity){
        this.quantity = quantity;
    }

    public double getQuantity(){
        return quantity;
    }

    public void addToReserve(double quantity){
        this.quantity += quantity;
    }

    public double removeFromReserve(double quantity){
        this.quantity -= quantity;
        return quantity;
    }
}
