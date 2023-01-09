package org.example.nelio;

import java.time.LocalDate;

public class OrderSystem {
    private Order[] orders;

    private int numberOfOrder, ordersSize;

    public OrderSystem(){
        this.orders = new Order[5];
        this.numberOfOrder = 1;
        this.ordersSize = 0;
    }

    public void addOrder(String recipientName, String address, Car car){
        if (isFull()){
            grow();
        }
        orders[ordersSize++] = new Order(recipientName,address,car,numberOfOrder++);
    }

    public OrderFilterIterator orderFilterIteratorByDestination(){
        return new OrderFilterIterator(sortOrdersByDestination(), ordersSize);
    }

    public OrderFilterIterator orderFilterIteratorByProduct(){
        return new OrderFilterIterator(sortOrdersByProduct(), ordersSize);
    }

    public OrderFilterIterator orderFilterIteratorByDate(){
        return new OrderFilterIterator(sortOrdersByDate(), ordersSize);
    }

    public OrderIterator orderIterator(){
        return new OrderIterator(sortOrdersByDestination(), ordersSize);
    }

    private Order[] sortOrdersByDate(){
        Order[] sortOrders = new Order[ordersSize];
        for (int p = 0; p<ordersSize;p++){
            sortOrders[p] = orders[p];
        }
        for (int i = 0; i < ordersSize-1;i++){
            int minIndex = i;
            for (int j = i+1; j<ordersSize;j++){
                if (sortOrders[j].getDeliveryLimitDate().compareTo(sortOrders[minIndex].getDeliveryLimitDate()) <0){
                    minIndex = j;
                }
            }
            Order tmp = sortOrders[i];
            sortOrders[i] = sortOrders[minIndex];
            sortOrders[minIndex] = tmp;
        }
        return sortOrders;
    }

    private Order[] sortOrdersByProduct(){
        Order[] sortOrders = new Order[ordersSize];
        for (int p = 0; p<ordersSize;p++){
            sortOrders[p] = orders[p];
        }
        for (int i = 0; i < ordersSize-1;i++){
            int minIndex = i;
            for (int j = i+1; j<ordersSize;j++){
                if (sortOrders[j].getCarModel().compareTo(sortOrders[minIndex].getCarModel()) < 0){
                    minIndex = j;
                }
            }
            Order tmp = sortOrders[i];
            sortOrders[i] = sortOrders[minIndex];
            sortOrders[minIndex] = tmp;
        }
        return sortOrders;
    }
    private Order[] sortOrdersByDestination(){
        Order[] sortOrders = new Order[ordersSize];
        for (int p = 0; p<ordersSize;p++){
            sortOrders[p] = orders[p];
        }
        for (int i = 0; i < ordersSize-1;i++){
            int minIndex = i;
            for (int j = i+1; j<ordersSize;j++){
                if (sortOrders[j].getAddress().compareTo(sortOrders[minIndex].getAddress()) < 0){
                    minIndex = j;
                }
            }
            Order tmp = sortOrders[i];
            sortOrders[i] = sortOrders[minIndex];
            sortOrders[minIndex] = tmp;
        }
        return sortOrders;
    }
    public String getOrderRecipient(int p){
        return orders[p].getRecipientName();
    }

    public String getOrderAddress(int p){
        return orders[p].getAddress();
    }

    public Car getModel(int p){
        return orders[p].getCar();
    }

    public LocalDate getRequestDate(int p){
        return orders[p].getRequestDate();
    }

    public double getOrderPrice(int numberOfOrder){
        return orders[findOrderByNumber(numberOfOrder)].getOrderPrice();
    }
    public LocalDate getDeliveryLimitDate(int p){
        return orders[p].getDeliveryLimitDate();
    }

    public boolean getDeliveryState(int p){
        return orders[p].getDeliveryState();
    }

    public void deliveryOrder(int numberOfOrder){
        orders[findOrderByNumber(numberOfOrder)].setDeliveryState();
    }

    public void removeOrder(int numberOfOrder){
        for (int p = findOrderByNumber(numberOfOrder); p < ordersSize -1; p++){
            orders[p] = orders[p + 1];
        }
        ordersSize--;
    }

    private int findOrderByNumber(int numberOfOrder){
        int p = 0;
        while (p< ordersSize && orders[p].getOrderNumber() != numberOfOrder){
            p++;
        }

        return p;
    }
    private boolean isFull(){
        return ordersSize == orders.length;
    }

    private void grow(){
        Order[] auxOrders = new Order[2 * ordersSize];
        for (int p = 0; p < ordersSize; p++){
            auxOrders[p] = orders[p];
        }
        orders = auxOrders;
    }

    public int findLastOrder(){
        return ordersSize - 1;
    }

    public int getOrderNumber(int orderPosition){
        return orders[orderPosition].getOrderNumber();
    }

    public boolean isAnExistentOrder(int numberOfOrder){
        for (int p = 0; p < ordersSize; p++){
            if (orders[p].getOrderNumber() == numberOfOrder){
                return true;
            }
        }
        return false;
    }
}
