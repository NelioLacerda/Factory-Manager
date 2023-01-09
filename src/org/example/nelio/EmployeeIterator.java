package org.example.nelio;

public class EmployeeIterator {
    private Employee[] employee;
    private int size, nextIndex;

    public EmployeeIterator(Employee[] employee, int size){
        this.employee = employee;
        this.size = size;
        this.nextIndex = 0;
    }

    public boolean hasNext(){
        return nextIndex < size;
    }

    public Employee next(){
        return employee[nextIndex++];
    }
}
