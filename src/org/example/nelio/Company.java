package org.example.nelio;

public class Company {
    private static final String COMPANY_NAME = "Tesla";

    private final String companyName;

    private double netWork;

    private int amountOfCars, movementsSize;

    private int[] amountOfCarsByModel;

    private Reserve reserve;

    private double[] movements;

    public Company(double netWork, int amountOfCars, int[] amountOfCarsByModel){
        this.companyName = COMPANY_NAME;
        this.netWork = netWork;
        this.amountOfCars = amountOfCars;
        this.amountOfCarsByModel = amountOfCarsByModel;
        this.reserve = new Reserve(10000);
        this.movements = new double[1];
        this.movementsSize = 0;
        this.movements[movementsSize++] = 10000;
    }

    public void addMoneyToCompanyFromReserve(double quantity){
        this.netWork += reserve.removeFromReserve(quantity);
        addMovement(quantity);
    }

    public void transferToReserve(double quantity){
        this.netWork -= quantity;
        reserve.addToReserve(quantity);
        addMovement(-1*quantity);
    }

    public int getAmountOfCars(){
        return amountOfCars;
    }

    public double getReserveAmount(){
        return reserve.getQuantity();
    }

    public void addMoney(double quantity){
        this.netWork += quantity;
        addMovement(quantity);
    }

    public double getNetWork(){
        return netWork;
    }

    public CompanyIterator companyIterator(){
        return new CompanyIterator(movements, movementsSize);
    }
    private void addMovement(double quantity){
        if(isFull()){
            grow();
        }
        movements[movementsSize++] = quantity;
    }

    private boolean isFull(){
        return movementsSize == movements.length;
    }

    private void grow(){
        double[] aux = new double[2 * movementsSize];
        for (int p = 0; p<movementsSize; p++){
            aux[p] = movements[p];
        }
        movements = aux;
    }
}
