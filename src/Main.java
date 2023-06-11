import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //Objects
        Scanner sc = new Scanner(System.in);
        //variables

        //constant variables
        double baseHourlySalary = 134.39;
        double priceOrderWeekday = 17.25;
        double priceOrderSaturday = 22.51;
        double priceOrderSunday = 27.77;
        double pricePerKm = 2.067;
        double guaranteePayWeekday = 152.28;
        double guaranteePaySaturday = 162.8;
        double guaranteePaySunday = 173.32;

        //dynamic variables
        double amountOrdersWeekday = 0;
        double amountOrdersSaturday = 0;
        double amountOrdersSunday = 0;
        double sumTips = 0;
        double seniority = 0;
        double priceKmPerOrder = 0;
        double kmPerOrder = 0;
        double tipsPerOrder = 0;
        double yearsOfService = 0;

        //calculations
        priceOrderWeekday *=amountOrdersWeekday;
        priceOrderSaturday *=amountOrdersSaturday;
        priceOrderSunday *=amountOrdersSunday;
        sumTips +=tipsPerOrder;
        seniority +=yearsOfService;
        priceKmPerOrder = pricePerKm * kmPerOrder;

        //for weekdays
        System.out.print("***********************\n" +
                "Salary Calculator 2023\n" +
                "***********************");


    }
}