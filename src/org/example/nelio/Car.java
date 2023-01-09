package org.example.nelio;

public class Car {
    private String color;
    private double price;
    private final Model model;

    public Car(String color, Model model, double price){
        this.color = color;
        this.model = model;
        this.price = price;
    }

    public String getColor(){
        return this.color;
    }

    public Model getModel(){
        return this.model;
    }

    public double getPrice(){
        return this.price;
    }

    public void setColor(String newColor){
        this.color = newColor;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

}
