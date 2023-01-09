package org.example.nelio;

import java.time.LocalDate;

public class Factory {
    private Company company;
    private OrderSystem orderSystem;
    private Employee[] employees;
    private Job[] jobs;
    private Model[] models;
    private int modelsSize, employeesSize, jobsSize;

    public Factory(double netWork){
        this.modelsSize = 0;
        this.models = createInitialModels();
        this.company = new Company(netWork, getAmountOfCars(), getAmountByModel());
        this.orderSystem = new OrderSystem();
        this.employees = new Employee[5];
        this.employeesSize = 0;
        this.jobs = createInitialJobs();
        this.jobsSize = 0;
    }

    private Model[] createInitialModels(){
        Model[] auxModels = new Model[4];
        auxModels[modelsSize++] = new Model("Model S",1000);
        auxModels[modelsSize++] = new Model("Model 3", 100);
        auxModels[modelsSize++] = new Model("Model X", 10101);
        auxModels[modelsSize++] = new Model("Model Y",991);
        return auxModels;
    }

    private int getAmountOfCars(){
        int amount = 0;
        for (int p = 0; p< modelsSize; p++){
            amount += models[p].getAmountProduce();
        }
        return amount;
    }
    private int[] getAmountByModel(){
        int[] aux = new int[modelsSize];
        for (int p = 0; p< modelsSize; p++){
            aux[p] = models[p].getAmountProduce();
        }
        return aux;
    }

    private Job[] createInitialJobs(){
        Job[] auxJobs = new Job[1];
        auxJobs[jobsSize++] = new Job();
        return auxJobs;
    }

    public ModelOrderIterator modelOrderIterator(){
        return new ModelOrderIterator(models, modelsSize);
    }

    public ModelOrderIterator modelOrderIteratorByStatus(){
        return new ModelOrderIterator(sortModels(), modelsSize);
    }

    public EmployeeIterator employeeIteratorByName(){
        return new EmployeeIterator(sortEmployeesByName(), employeesSize);
    }

    public EmployeeIterator employeeIteratorBySalary(){
        return new EmployeeIterator(sortEmployeeBySalary(), employeesSize);
    }

    public OrderFilterIterator orderFilterIteratorByDestination(){
        return orderSystem.orderFilterIteratorByDestination();
    }

    public OrderFilterIterator orderFilterIteratorByProduct(){
        return orderSystem.orderFilterIteratorByProduct();
    }

    public OrderFilterIterator orderFilterIteratorByDate(){
        return orderSystem.orderFilterIteratorByDate();
    }

    public OrderIterator orderIterator(){
        return orderSystem.orderIterator();
    }

    public CompanyIterator companyIterator(){
        return company.companyIterator();
    }
    private Employee[] sortEmployeeBySalary(){
        Employee[] sortEmployee = new Employee[employeesSize];
        for (int p = 0; p < employeesSize; p++){
            sortEmployee[p] = employees[p];
        }
        for (int i = 0; i < employeesSize-1;i++){
            int minIndex = i;
            for (int j = i+1; j<employeesSize;j++){
                if (sortEmployee[j].compareToSalary(sortEmployee[minIndex]) < 0){
                    minIndex = j;
                }
            }
            Employee tmp = sortEmployee[i];
            sortEmployee[i] = sortEmployee[minIndex];
            sortEmployee[minIndex] = tmp;
        }
        return sortEmployee;
    }
    private Employee[] sortEmployeesByName(){
        Employee[] sortEmployee = new Employee[employeesSize];
        for (int p = 0; p < employeesSize; p++){
            sortEmployee[p] = employees[p];
        }
        for (int i = 0; i < employeesSize-1; i++){
            int minIndex = i;
            for (int j = i+1; j < employeesSize; j++){
                if (sortEmployee[j].getEmployeeName().compareTo(sortEmployee[minIndex].getEmployeeName()) < 0){
                    minIndex = j;
                }
            }
            Employee tmp = sortEmployee[i];
            sortEmployee[i] = sortEmployee[minIndex];
            sortEmployee[minIndex] = tmp;
        }
        return sortEmployee;
    }
    private Model[] sortModels(){
        Model[] sortModels = new Model[modelsSize];
        for (int p = 0; p<modelsSize; p++){
            sortModels[p] = models[p];
        }
        for (int i = 0; i<modelsSize-1; i++){
            int minIndex = i;
            for (int j = i+1; j < modelsSize; j++){
                if (sortModels[j].compareTo(sortModels[minIndex]) < 0){
                    minIndex = j;
                }
            }
            Model tmp = sortModels[i];
            sortModels[i] = sortModels[minIndex];
            sortModels[minIndex] = tmp;
        }
        return sortModels;
    }
    public boolean isAnExistentCarModel(String carModel){
        for (int p = 0; p<modelsSize; p++){
            if (models[p].getModelName().equals(carModel)){
                return true;
            }
        }
        return false;
    }

    public double getModelPrice(String carModel){
        return models[findModel(carModel)].getModelPrice();
    }

    public int getModelQuantity(String carModel){
        return models[findModel(carModel)].getAmountProduce();
    }
    public void addCarsToModel(String carModel, int quantity){
        models[findModel(carModel)].addToAmountProduce(quantity);
    }

    public void removeCarsFromModel(String carModel, int quantity){
        models[findModel(carModel)].removeAmountProduce(quantity);
    }

    public void setNewValueModel(String carModel, int price){
        models[findModel(carModel)].setModelPrice(price);
    }

    public void addModel(String modelName, double modelPrice){
        if (modelsIsFull()){
            growModels();
        }
        models[modelsSize++] = new Model(modelName, modelPrice);
    }

    private boolean modelsIsFull(){
        return modelsSize == models.length;
    }

    private void growModels(){
        Model[] auxModels = new Model[2 * modelsSize];
        for (int p = 0; p<modelsSize; p++){
            auxModels[p] = models[p];
        }
        models = auxModels;
    }

    public void startModelProduction(String carModel){
        models[findModel(carModel)].setIs_Ben_Produce(true);
    }

    public void stopModelProduction(String carModel){
        models[findModel(carModel)].setIs_Ben_Produce(false);
    }
    public boolean getProductionState(String carModel){
        return models[findModel(carModel)].getProductionSate();
    }
    private int findModel(String carModel){
        int p = 0;
        while (p<modelsSize && !models[p].getModelName().equals(carModel)){
            p++;
        }
        return p;
    }

    public boolean isAnExistentEmployee(String employeeName){
        for (int p = 0; p<employeesSize; p++){
            if (employees[p].getEmployeeName().equals(employeeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnExistentJob(String jobName){
        for (int p = 0; p <  jobsSize; p++){
            if (jobs[p].getJobName().equals(jobName)){
                return true;
            }
        }
        return false;
    }

    public void addEmployee(String employeeName, String jobName, int rakingPosition){
        //TODO: ordenar por nomes
        employees[employeesSize++] = new Employee(employeeName, jobs[findJob(jobName)], rakingPosition);
        jobs[findJob(jobName)].addPeopleToJob();
    }

    public void removeEmployee(String employeeName){
        int employeeIndex = findEmployee(employeeName);
        for (int p = employeeIndex; p < employeesSize-1; p++){
            employees[p] = employees[p + 1];
        }
        employeesSize--;
    }

    public void addEmployeeToJob(String employeeName, String jobName, int rakingPosition){
        employees[findEmployee(employeeName)].setNewJob(jobs[findJob(jobName)], rakingPosition);
    }
    public boolean isAnExistentRaking(int rakingPosition, String jobName){
        return jobs[findJob(jobName)].isAnExistentRakingPosition(rakingPosition);
    }

    public void setNewSalary(String employeeName, double newSalary){
        employees[findEmployee(employeeName)].setNewSalary(newSalary);
    }

    public void changeSalaryJob(String jobName, double newSalary, int rakingPosition){
        jobs[findJob(jobName)].setRankingSalary(newSalary, rakingPosition);
    }

    public String findJobName(String employeeName){
        return employees[findEmployee(employeeName)].getJobName();
    }

    public void setNewEmployeeRaking(String employeeName, int newRakingPosition){
        employees[findEmployee(employeeName)].setNewRaking(newRakingPosition);
    }

    public void addNewJob(String jobName, String[] rakingNames, double[] rakingSalary){
        if (jobsIsFull()){
            growJobs();
        }
        jobs[jobsSize++] = new Job(jobName, rakingNames, rakingSalary);
    }

    private boolean jobsIsFull(){
        return jobsSize == jobs.length;
    }

    private void growJobs(){
        Job[] auxJobs = new Job[2 * jobsSize];
        for (int p = 0; p < jobsSize; p++){
            auxJobs[p] = jobs[p];
        }
        jobs = auxJobs;
    }

    public void removeJob(String jobName){
        //TODO:por as pessoas desse trabanho para trabanho undefine
        int jobIndex = findJob(jobName);
        for (int p = jobIndex; p < jobsSize-1; p++){
            jobs[p] = jobs[p+1];
        }
        jobsSize--;
    }
    private int findEmployee(String employeeName){
        int p = 0;
        while(p < employeesSize && !employees[p].getEmployeeName().equals(employeeName)){
            p++;
        }
        return p;
    }
    private int findJob(String jobName){
        int p = 0;
        while (p < jobsSize && !jobs[p].getJobName().equals(jobName) ){
            p++;
        }
        return p;
    }

    public void createNewOrder(String recipientName, String address, String modelName, String color){
        orderSystem.addOrder(recipientName, address, new Car(color, models[findModel(modelName)],
                                                        models[findModel(modelName)].getModelPrice()));
    }

    public LocalDate getLimitDate(int p){
        return orderSystem.getDeliveryLimitDate(p);
    }

    public int findLastOrder(){
        return orderSystem.findLastOrder();
    }

    public int getOrderNumber(int order){
        return orderSystem.getOrderNumber(order);
    }

    public boolean isAnExistentOrder(int orderNumber){
        return orderSystem.isAnExistentOrder(orderNumber);
    }

    public void removeOrder(int orderNumber){
        orderSystem.removeOrder(orderNumber);
    }

    public void deliveryOrder(int orderNumber){
        orderSystem.deliveryOrder(orderNumber);
        company.addMoney(orderSystem.getOrderPrice(orderNumber));
    }

    public double getCompanyNetWork(){
        return company.getNetWork();
    }

    public void addMoneyToReserve(double quantity){
        company.transferToReserve(quantity);
    }

    public double getReserveAmount(){
        return company.getReserveAmount();
    }

    public void drawFromReserve(double quantity){
        company.addMoneyToCompanyFromReserve(quantity);
    }
}
