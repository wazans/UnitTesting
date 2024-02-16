package org.example;

public class Calculator {
    private final DataService dataService;

    public Calculator(DataService dataService) {
        this.dataService = dataService;
    }

    public int performCalculation() {
        int data = dataService.getData();
        // Perform some calculation using the data
        //5
        return data * 2;
    }
}
