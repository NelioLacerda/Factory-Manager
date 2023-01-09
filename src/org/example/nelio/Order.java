package org.example.nelio;

import java.time.*;

public class Order {
    private final String recipientName, address;
    private final Car car;
    private final LocalDate requestDate, deliveryLimitDate;
    private boolean has_ben_delivery;
    private final int orderNumber;

    public Order(String recipientName, String address, Car car, int orderNumber){
        this.recipientName = recipientName;
        this.address = address;
        this.car = car;
        this.requestDate = LocalDate.now();
        this.deliveryLimitDate = requestDate.plusDays(3);
        this.has_ben_delivery = false;
        this.orderNumber = orderNumber;
    }

    public String getCarModel(){
        return car.getModel().getModelName();
    }
    public double getOrderPrice(){
        return car.getPrice();
    }
    public boolean getDeliveryState(){
        return has_ben_delivery;
    }
    public int getOrderNumber(){
        return orderNumber;
    }

    public String getRecipientName(){
        return recipientName;
    }

    public String getAddress(){
        return address;
    }

    public Car getCar(){
        return car;
    }

    public LocalDate getRequestDate(){
        return requestDate;
    }

    public LocalDate getDeliveryLimitDate(){
        return deliveryLimitDate;
    }

    public void setDeliveryState(){
        this.has_ben_delivery = true;
    }
}
