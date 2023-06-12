import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.System.exit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException{

        //Objects
        Scanner sc = new Scanner(System.in);
        //variables

        //User input variables UI
        double UI_amountOrdersWeekday = 0;
        double UI_amountOrdersSaturday = 0;
        double UI_amountOrdersSunday = 0;
        int UI_seniority = 0;
        double UI_totalKm = 0;
        double UI_totalTips = 0;
        double UI_workedHours = 0;
        int menu_1 = 0;

        //if the user decides to register data
        char registerMenu = ' '; // Y or N (y or n)
        int chooseRegister = 0; // 1 or 2. 1 to register new shift or 2 to go back to the main menu.

        //if the user decides to read data from the .txt file
        int type = 0; //1: daily , 2: weekly, 3: monthly
        int chooseSum = 0; // 1 or 2. 1 to go back into choosing the timeframe or 2 to go back to the main menu


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
        double seniority = 0;
        double kmPerOrder = 0;
        double tipsPerOrder = 0;
        double yearsOfService = UI_seniority;

        //sum variables
        double sumTips = 0;
        double priceKmPerOrder = 0;

        //calculations
        priceOrderWeekday *=amountOrdersWeekday;
        priceOrderSaturday *=amountOrdersSaturday;
        priceOrderSunday *=amountOrdersSunday;
        sumTips +=tipsPerOrder;
        seniority +=yearsOfService;
        priceKmPerOrder = pricePerKm * kmPerOrder;

        //for weekdays
        //title
        clearConsole();
        System.out.print(
                "***********************\n" +
                "Salary Calculator 2023\n" +
                "***********************\n");
                Thread.sleep(1200);
        //main menu
        System.out.print(
                "1: Register\n" +
                        "2: Show sum\n" +
                        "3: Exit\n\n" +
                        "Your input: ");
        menu_1 = sc.nextInt();
        clearConsole();
        //System.out.println(menu_1);

        switch (menu_1) {
            case 1 -> {
                System.out.println("***********************\n" +
                        "Shift Register\n" +
                        "***********************\n");
                Thread.sleep(1500);
                System.out.print("Today's date (dd.mm.yyyy): " +
                        "Your input: ");
                String date = "";
                date = sc.next();
                System.out.print("\nChoose the following about your shift:(1,2 or 3) \n" +
                        "1: Weekday\n" +
                        "2: Saturday\n" +
                        "3: Sunday\n" +
                        "Your input: ");
                int typeOfWeek = sc.nextInt();

                System.out.print("\nAmount of hours worked: " +
                        "\nYour input: ");
                        UI_workedHours = sc.nextDouble();
                System.out.println("\nDid you have a moment where you didn't have any orders? (Y/N): ");
                String guaranteeChoice = "";
                guaranteeChoice = sc.next();

                System.out.print("\nKm in total:\n" +
                        "Your Input: ");
                UI_totalKm = sc.nextDouble();

                System.out.print("\nTips: ");
                UI_totalTips = sc.nextInt();

                System.out.print("\nYears worked in the company: ");
                UI_seniority = sc.nextInt();
                 clearConsole();
                System.out.print("Loading");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1400);
                clearConsole();

                System.out.print("**********************************************\n" +
                        "Date: "+date+"" +
                        "\nType of the week: "+typeOfWeek+"\n" +
                        "\nHours worked: "+UI_workedHours+"\n" +
                        "\nDid you have a moment where you didn't work?: "+guaranteeChoice+"\n" +
                        "\nKm in total: "+UI_totalKm+"\n" +
                        "\nTips: "+ UI_totalTips+"\n" +
                        "\nYears worked: "+UI_seniority+"\n" +
                        "**********************************************\n");

                System.out.print("\n\nSubmit? (Y/N) " +
                        "\nYour input: ");
                String choose = sc.next();

                if(choose == "N" || choose =="n"){
                    exit(0);
                }





            }
        }



    }
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }
}