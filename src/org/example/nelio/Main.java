package org.example.nelio;

import java.util.Scanner;

public class Main {
    private static final String EXIT = "EXIT";
    private static final String MC = "MC";
    private static final String ME = "ME";
    private static final String MO = "MO";
    private static final String MP = "MP";
    private static final int ADD_CARS_OPTION = 1, REMOVE_CARS_OPTION = 2, CHANGE_VALUE_CARS_OPTION = 3, ADD_MODEL_OPTION = 4,
                                START_CAR_PRODUCTION = 5,STOP_CAR_PRODUCTION = 6,LIST_BY_ORDER = 7,LIST_RECENTLY = 8,FIND_MODEL = 9
                                ,EXIT_MANAGE_CARS = 10;

    private static final int ADD_EMPLOYER = 1, REMOVE_EMPLOYER = 2, PUT_EMPLOYER_IN_JOB = 3, CHANGE_SALARY = 4,
                                CHANGE_JOB_SALARY = 5, CHANGE_EMPLOYER_RANKING = 6,ADD_JOB = 7,
                                REMOVE_JOB = 8, LIST_BY_JOB = 9, LIST_BY_SALARY = 10, EXIT_MANAGE_EMPLOYERS = 11;

    private static final int ADD_ORDER = 1, REMOVE_ORDER = 2,ORDER_DELIVERY=3,LIST_BY_DESTINATION = 4, LIST_BY_PRODUCT = 5,
                                LIST_BY_TIME = 6, LIST_ALL_ORDERS = 7, EXIT_MANAGE_ORDERS = 8;

    private static final int CONSULT_PROFITS = 1, CONSULT_MOVEMENTS = 2, PUT_MONEY_IN_RESERVE = 3, DRAW_MONEY_FROM_RESERVE = 5,
                                EXIT_MANAGE_PROFITS = 6;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Factory factory = createFactory(scanner);

        factorySystem(scanner, factory);

        scanner.close();
    }

    private static Factory createFactory(Scanner scanner){
        System.out.println("Initial company netWork:");
        double netWork = scanner.nextDouble();
        return new Factory(netWork);
    }

    private static void factorySystem(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            showInitialInstructions();
            String option = scanner.nextLine();
            switch (option){
                case MC:
                    manageCars(scanner, factory);
                    break;
                case ME:
                    manageEmployers(scanner, factory);
                    break;
                case MO:
                    manageOrders(scanner, factory);
                    break;
                case MP:
                    manageProfits(scanner, factory);
                    break;
                case EXIT:
                    has_finish = true;
            }
        }while (!has_finish);
    }

    private static void manageCars(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            showManageCarsOptions();
            int option = scanner.nextInt();
            switch (option){
                case ADD_CARS_OPTION:
                    addCarsOption(scanner, factory);
                    break;
                case REMOVE_CARS_OPTION:
                    removeCarsOption(scanner, factory);
                    break;
                case CHANGE_VALUE_CARS_OPTION:
                    changeValueOption(scanner, factory);
                    break;
                case ADD_MODEL_OPTION:
                    addModelOption(scanner, factory);
                    break;
                case START_CAR_PRODUCTION:
                    startCarOption(scanner, factory);
                    break;
                case STOP_CAR_PRODUCTION:
                    stopCarOption(scanner, factory);
                    break;
                case LIST_BY_ORDER:
                    listByOrderOption(factory);
                    break;
                case LIST_RECENTLY:
                    listByProductionStatusOption(factory);
                    break;
                case FIND_MODEL:
                    findModelOption(scanner, factory);
                    break;
                case EXIT_MANAGE_CARS:
                    has_finish = true;
            }
        }while(!has_finish);
    }

    private static void addCarsOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do {
            scanner.nextLine();
            System.out.println("Car model:");
            String carModel = scanner.nextLine();
            if (factory.isAnExistentCarModel(carModel)) {
                if (factory.getProductionState(carModel)) {
                    System.out.println("Quantity: ");
                    int quantity = scanner.nextInt();
                    if (quantity > 0) {
                        factory.addCarsToModel(carModel, quantity);
                    } else {
                        System.out.println("Invalid quantity");
                    }
                }else{
                    System.out.println("Car model isn't in production");
                }
            } else {
                System.out.println("Car model doesnt exit");
            }
            System.out.println("Add another car?(Yes)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void removeCarsOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Car model:");
            String carModel = scanner.nextLine();
            if (factory.isAnExistentCarModel(carModel)){
                if (factory.getProductionState(carModel)) {
                    System.out.println("Quantity:");
                    int quantity = scanner.nextInt();
                    if (quantity > 0) {
                        factory.removeCarsFromModel(carModel, quantity);
                    } else {
                        System.out.println("Invalid quantity");
                    }
                }else{
                    System.out.println("Car model isn't in production");
                }
            }else{
                System.out.println("Car model doesnt exit");
            }
            System.out.println("Remove another car?(Yes)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void changeValueOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Car model:");
            String carModel = scanner.nextLine();
            if (factory.isAnExistentCarModel(carModel)){
                System.out.println("New value: ");
                int value = scanner.nextInt();
                if (value > 0){
                    factory.setNewValueModel(carModel, value);
                }else{
                    System.out.println("Invalid value");
                }
            }else{
                System.out.println("Car model doesnt exit");
            }
            System.out.println("change another car value?(Yes)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void addModelOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do {
            scanner.nextLine();
            System.out.println("Car model to add: ");
            String carModel = scanner.nextLine();
            if (!factory.isAnExistentCarModel(carModel)) {
                System.out.println("Car model price:");
                double price = scanner.nextDouble();
                factory.addModel(carModel, price);
            }else{
                System.out.println("Car model already exist");
            }
            System.out.println("Add another car model?(Yes)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while (!has_finish);
    }

    private static void startCarOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Car model to start production:");
            String carModel = scanner.nextLine();
            if (factory.isAnExistentCarModel(carModel)){
                if (!factory.getProductionState(carModel)) {
                    factory.startModelProduction(carModel);
                }else {
                    System.out.println("Car model already in production");
                }
            }else{
                System.out.println("Car model doesnt exit");
            }
            System.out.println("Start production of another car model?(Yes)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void stopCarOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Car model to stop production:");
            String carModel = scanner.nextLine();
            if (factory.isAnExistentCarModel(carModel)){
                if (factory.getProductionState(carModel)){
                    factory.stopModelProduction(carModel);
                }else{
                    System.out.println("Car model is already not been produce");
                }
            }else{
                System.out.println("Car model doesnt exit");
            }
            System.out.println("Stop production of another car model?(Yes)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while (!has_finish);
    }

    private static void listByOrderOption(Factory factory){
        ModelOrderIterator it = factory.modelOrderIterator();
        int generation = 1;
        while (it.hasNext()){
            System.out.println(generation++ + "ª generation: " + it.next().getModelName());
        }
    }

    private static void listByProductionStatusOption(Factory factory){
        ModelOrderIterator it = factory.modelOrderIteratorByStatus();
        while (it.hasNext()){
            Model model = it.next();
            String text = " production stop";
            if (model.getProductionSate()){
                text = " in production";
            }
            System.out.println(model.getModelName() + ": Production state:" + text );
        }
    }

    private static void findModelOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Model name:");
            String modelName = scanner.nextLine();
            if (factory.isAnExistentCarModel(modelName)){
                String text = " production stop";
                if (factory.getProductionState(modelName)){
                    text = " in production";
                }
                System.out.println("Model price: " + factory.getModelPrice(modelName) + "\n" +
                        " Model quantity: " + factory.getModelQuantity(modelName) + "\n" +
                        "Production sate: " + text);
            }else{
                System.out.println("Car model doesnt exist");
            }

            System.out.println("Find another car?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while (!has_finish);
    }
    private static void manageEmployers(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            showManageEmployersOptions();
            int option = scanner.nextInt();
            switch (option){
                case ADD_EMPLOYER:
                    addEmployersOption(scanner, factory);
                    break;
                case REMOVE_EMPLOYER:
                    removeEmployersOption(scanner, factory);
                    break;
                case PUT_EMPLOYER_IN_JOB:
                    putEmployersInJobOption(scanner, factory);
                    break;
                case CHANGE_SALARY:
                    changeEmployerSalaryOption(scanner, factory);
                    break;
                case CHANGE_JOB_SALARY:
                    changeJobSalaryOption(scanner, factory);
                    break;
                case CHANGE_EMPLOYER_RANKING:
                    changeEmployerRankingOption(scanner, factory);
                    break;
                case ADD_JOB:
                    addNewJobOption(scanner, factory);
                    break;
                case REMOVE_JOB:
                    removeJobOption(scanner, factory);
                    break;
                    //ERRADO:
                case LIST_BY_JOB:
                    listByJobOption(factory);
                    break;
                case LIST_BY_SALARY:
                    listBySalaryOption(factory);
                    break;
                case EXIT_MANAGE_EMPLOYERS:
                    has_finish = true;
            }
        }while(!has_finish);
    }

    private static void listByJobOption(Factory factory){
        EmployeeIterator it = factory.employeeIteratorByName();
        while (it.hasNext()){
            Employee employee = it.next();
            System.out.println(employee.getEmployeeName() + "; Job: " + employee.getJobName() + "; Salary: " + employee.getSalary() + "$");
        }
    }

    private static void listBySalaryOption(Factory factory){
        EmployeeIterator it = factory.employeeIteratorBySalary();
        while (it.hasNext()){
            Employee employee = it.next();
            System.out.println(employee.getEmployeeName() + "; Job: " + employee.getJobName() + "; Salary: " + employee.getSalary() + "$");
        }
    }
    private static void addEmployersOption(Scanner scanner, Factory factory){
       boolean has_finish = false;
       do{
           scanner.nextLine();
           System.out.println("Employee name: ");
           String employeeName = scanner.nextLine();
           System.out.println("Employee job:");
           String job = scanner.nextLine();
           System.out.println("Initial raking position:");
           int rankingPosition = scanner.nextInt();
           if (factory.isAnExistentRaking(rankingPosition, job)) {
               if (factory.isAnExistentJob(job)) {
                   factory.addEmployee(employeeName, job, rankingPosition);
               } else {
                   System.out.println("Job doesnt exit");
               }
           }else{
               System.out.println("Raking position doesnt exist");
           }

           System.out.println("Add another employer?(YES)");
           String option = scanner.next();
           if (!option.equals("YES")){
               has_finish = true;
           }
       }while (!has_finish);
    }

    private static void removeEmployersOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do {
            scanner.nextLine();
            System.out.println("Employer name:");
            String employerName = scanner.nextLine();
            if (factory.isAnExistentEmployee(employerName)) {
                factory.removeEmployee(employerName);
            } else {
                System.out.println("Employer name doesnt exist");
            }
            System.out.println("Remove another employer?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while (!has_finish);
    }
    private static void putEmployersInJobOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Employer name:");
            String employerName = scanner.nextLine();
            if (factory.isAnExistentEmployee(employerName)) {
                System.out.println("New job name:");
                String jobName = scanner.nextLine();
                if (factory.isAnExistentJob(jobName)){
                    System.out.println("Raking position:");
                    int rakingPosition = scanner.nextInt();
                    if (factory.isAnExistentRaking(rakingPosition, jobName)) {
                        factory.addEmployeeToJob(employerName, jobName, rakingPosition);
                    }else{
                        System.out.println("Raking position doesnt exist");
                    }
                }else {
                    System.out.println("Job doesnt exist");
                }
            } else {
                System.out.println("Employer name doesnt exist");
            }
            System.out.println("Put another employer in job?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void changeEmployerSalaryOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Employer name:");
            String employerName = scanner.nextLine();
            if (factory.isAnExistentEmployee(employerName)){
                System.out.println("New salary:");
                int salary = scanner.nextInt();
                if (salary > 0){
                    factory.setNewSalary(employerName, salary);
                }else{
                    System.out.println("Invalid salary");
                }
            }else{
                System.out.println("Employer doesnt exit");
            }
            System.out.println("Change another employer salary?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while (!has_finish);
    }

    private static void changeJobSalaryOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Job name: ");
            String jobName = scanner.nextLine();
            if (factory.isAnExistentJob(jobName)){
                System.out.println("Select raking position: ");
                int rakingPosition = scanner.nextInt();
                if (factory.isAnExistentRaking(rakingPosition, jobName)){
                    System.out.println("New salary:");
                    double newSalary = scanner.nextDouble();
                    if (newSalary > 0){
                        factory.changeSalaryJob(jobName,newSalary, rakingPosition);
                    }else{
                        System.out.println("Invalid salary");
                    }
                }else{
                    System.out.println("Raking position doesnt exist");
                }
            }else{
                System.out.println("Job doesnt exist");
            }
            System.out.println("Change another job salary?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void changeEmployerRankingOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Employer name: ");
            String employerName = scanner.nextLine();
            if (factory.isAnExistentEmployee(employerName)){
                System.out.println("New raking position");
                int rakingPosition = scanner.nextInt();
                if (factory.isAnExistentRaking(rakingPosition, factory.findJobName(employerName))){
                   factory.setNewEmployeeRaking(employerName, rakingPosition);
                }else{
                    System.out.println("Raking position doesnt exist");
                }
            }else{
                System.out.println("Employer doesnt exist");
            }
            System.out.println("Change another employer ranking?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void addNewJobOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("New job name:");
            String newJob = scanner.nextLine();
            if (!factory.isAnExistentJob(newJob)){
                createNewJob(scanner, factory, newJob);
            }else{
                System.out.println("Job already exist");
            }
            System.out.println("Add another job?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void createNewJob(Scanner scanner, Factory factory, String newJob){
        System.out.println("Number of positions:");
        int numOfPositions = scanner.nextInt();
        scanner.nextLine();
        String[] rakingNames = new String[numOfPositions];
        double[] rakingSalary = new double[numOfPositions];
        for (int p = 0; p < numOfPositions; p++){
            System.out.println("Raking name:");
            String rakingName = scanner.nextLine();
            rakingNames[p] = rakingName;
            System.out.println("Raking salary:");
            double salary = scanner.nextDouble();
            scanner.nextLine();
            rakingSalary[p] = salary;
        }
        factory.addNewJob(newJob, rakingNames, rakingSalary);
    }
    private static void removeJobOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Job name:");
            String jobName = scanner.nextLine();
            if (factory.isAnExistentJob(jobName)){
                factory.removeJob(jobName);
            }else{
                System.out.println("Job doesnt exist");
            }
            System.out.println("Remove another job?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while(!has_finish);
    }

    private static void manageOrders(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            showManegeOrdersOptions();
            int option = scanner.nextInt();
            switch (option){
                case ADD_ORDER:
                    addOrdersOption(scanner,factory);
                    break;
                case REMOVE_ORDER:
                    removeOrderOption(scanner, factory);
                    break;
                case ORDER_DELIVERY:
                    orderDeliveryOption(scanner, factory);
                    break;
                case LIST_BY_DESTINATION:
                    listByDestinationOrder(factory);
                    break;
                case LIST_BY_PRODUCT:
                    listByProductOption(factory);
                    break;
                case LIST_BY_TIME:
                    listByTimeOption(factory);
                    break;
                case LIST_ALL_ORDERS:
                    listAllOrdersOption(factory);
                    break;
                case EXIT_MANAGE_ORDERS:
                    has_finish = true;
            }
        }while(!has_finish);
    }

    private static void listByDestinationOrder(Factory factory){
        OrderFilterIterator it = factory.orderFilterIteratorByDestination();
        while (it.hasNext()){
            Order order = it.next();
            System.out.println("Order model: " + order.getCarModel() + "\n" +
                    " Recipient: " + order.getRecipientName() + "; Address: " + order.getAddress() + "\n" +
                    "Limit delivery date: " + order.getDeliveryLimitDate());
        }
    }

    private static void listByProductOption(Factory factory){
        OrderFilterIterator it = factory.orderFilterIteratorByProduct();
        while (it.hasNext()){
            Order order = it.next();
            System.out.println("Order model: " + order.getCarModel() + "\n" +
                    " Recipient: " + order.getRecipientName() + "; Address: " + order.getAddress() + "\n" +
                    "Limit delivery date: " + order.getDeliveryLimitDate());
        }
    }

    private static void listByTimeOption(Factory factory){
        OrderFilterIterator it = factory.orderFilterIteratorByDate();
        while (it.hasNext()){
            Order order = it.next();
            System.out.println("Order model: " + order.getCarModel() + "\n" +
                    " Recipient: " + order.getRecipientName() + "; Address: " + order.getAddress() + "\n" +
                    "Limit delivery date: " + order.getDeliveryLimitDate());
        }
    }

    private static void listAllOrdersOption(Factory factory){
        OrderIterator it = factory.orderIterator();
        while (it.hasNext()){
            Order order = it.next();
            String text = " order not delivery yet";
            if (order.getDeliveryState()){
                text = " order already delivery";
            }
            System.out.println("Order model: " + order.getCarModel() + "\n" +
                    " Recipient: " + order.getRecipientName() + "; Address: " + order.getAddress() + "\n" +
                    "Limit delivery date: " + order.getDeliveryLimitDate() + "\n" +
                    "Order delivery state: " + text);
        }
    }
    private static void addOrdersOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Recipient:");
            String recipientName = scanner.nextLine();
            System.out.println("Address:");
            String address = scanner.nextLine();
            System.out.println("Model to be delivery:");
            String model = scanner.nextLine();
            if (factory.isAnExistentCarModel(model)){
                /*
                PENSAR EM COISAS EXTRAS PARA O CARRO
                 */
                String color = scanner.nextLine();
                factory.createNewOrder(recipientName,address,model,color);
                System.out.println("Order added!" + "\n" +
                        "Limit delivery date: " + factory.getLimitDate(factory.findLastOrder()) + "\n" +
                        "Order number: " + factory.getOrderNumber(factory.findLastOrder()));
            }else{
                System.out.println("Model doesnt exist");
            }
            System.out.println("Add another order?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while (!has_finish);
    }

    private static void removeOrderOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            scanner.nextLine();
            System.out.println("Order number:");
            int nOrder = scanner.nextInt();
            if (factory.isAnExistentOrder(nOrder) && nOrder > 0){
                factory.removeOrder(nOrder);
            }else{
                System.out.println("Order doesnt exist");
            }
            System.out.println("Remove another order?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")){
                has_finish = true;
            }
        }while (!has_finish);
    }

    private static void orderDeliveryOption(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do {
            scanner.nextLine();
            System.out.println("Delivery order number:");
            int orderDelivery = scanner.nextInt();
            if (orderDelivery > 0 && factory.isAnExistentOrder(orderDelivery)) {
                factory.deliveryOrder(orderDelivery);
            } else {
                System.out.println("Invalid order number");
            }
            System.out.println("Delivery another order?(YES)");
            String option = scanner.next();
            if (!option.equals("YES")) {
                has_finish = true;
            }
        }while (!has_finish);
    }
    private static void manageProfits(Scanner scanner, Factory factory){
        boolean has_finish = false;
        do{
            showManageProfitsOptions();
            int option = scanner.nextInt();
            switch (option){
                case CONSULT_PROFITS:
                    consultProfitsOption(factory);
                    break;
                case CONSULT_MOVEMENTS:
                    consultMovementsOption(factory);
                    break;
                case PUT_MONEY_IN_RESERVE:
                    putMoneyInReserve(scanner, factory);
                    break;
                case DRAW_MONEY_FROM_RESERVE:
                    drawMoneyFromReserve(scanner, factory);
                    break;
                case EXIT_MANAGE_PROFITS:
                    has_finish = true;
            }
        }while(!has_finish);
    }
    private static void consultProfitsOption(Factory factory){
        System.out.println("Company network: " + factory.getCompanyNetWork());
    }

    private static void consultMovementsOption(Factory factory){
        CompanyIterator it = factory.companyIterator();
        System.out.println("Movements:");
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
    private static void putMoneyInReserve(Scanner scanner, Factory factory){
        scanner.nextLine();
        System.out.println("Money do add: ");
        double money = scanner.nextDouble();
        if (money > 0.0) {
            if (money < factory.getCompanyNetWork()) {
                factory.addMoneyToReserve(money);
            } else {
                System.out.println("The company doesnt have enough money");
            }
        }else{
            System.out.println("Invalid amount of money");
        }
    }

    private static void drawMoneyFromReserve(Scanner scanner, Factory factory){
        scanner.nextLine();
        System.out.println("Money to draw:");
        double money = scanner.nextDouble();
        if (money > 0.0){
            if (money < factory.getReserveAmount()){
                factory.drawFromReserve(money);
            }else{
                System.out.println("The reserve doesnt have that quantity of money");
            }
        }else{
            System.out.println("Invalid amount of money");
        }
    }
    private static void showManageProfitsOptions(){
        System.out.println("""
                1-Consult profits
                2-Consult movements
                3-Put money in reserve
                4-Draw money from reserve
                6-Exit""");
    }
    private static void showManegeOrdersOptions(){
        System.out.println("""
                1-Add order
                2-Remove order
                3-Delivery order
                4-List orders by destination name
                5-List orders by product
                6-List order by time limit(from the oldest to the youngest)
                7-List all order by destination name
                8-Exit""");
    }
    private static void showManageEmployersOptions(){
        System.out.println("""
                1-Add employer
                2-Remove employer
                3-Put employers in function
                4-Change employer salary
                5-Change salary of specific function
                6-Change employer ranking
                7-Add job
                8-Remove job
                9-List employers by job
                10-List employers by salary
                11-List employers by ranking
                12-Exit
                """);
    }
    private static void showManageCarsOptions(){
        System.out.println("""
                1-Add cars to stock
                2-Remove cars from stock
                3-Change car model value
                4-Add model to produce
                5-Start car model production
                6-Stop car model production
                7-List model by production order
                8-List model by produced status
                9-Find model
                10-Exit""");
    }
    private static void showInitialInstructions(){
        System.out.println("""
                MC-manage cars
                ME - manage employers
                MP- manage factory profit
                MO- manage orders
                EXIT-exit""");
    }
}
