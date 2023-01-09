package org.example.nelio;

public class OrderFilterIterator {
    private Order[] orders;
    private int size, nextIndex;

    public OrderFilterIterator(Order[] orders, int size){
        this.orders = orders;
        this.size = size;
        this.nextIndex = 0;
        advance();
    }

    public boolean hasNext(){
        return nextIndex < size;
    }

    public Order next(){
        Order order = orders[nextIndex++];
        advance();
        return order;
    }

    private void advance(){
        while (nextIndex<size && !criterion(orders[nextIndex])){
            nextIndex++;
        }
    }

    private boolean criterion(Order order){
        return order.getDeliveryState();
    }
}
