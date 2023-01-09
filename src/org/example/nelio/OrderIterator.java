package org.example.nelio;

public class OrderIterator {
    private Order[] orders;
    private int size, nextIndex;

    public OrderIterator(Order[] orders, int size){
        this.orders = orders;
        this.size = size;
        this.nextIndex = 0;
    }

    public boolean hasNext(){
        return nextIndex < size;
    }

    public Order next(){
        return orders[nextIndex++];
    }

}
