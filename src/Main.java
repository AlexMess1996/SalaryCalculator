import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException{

        //Objects
        Scanner sc = new Scanner(System.in);
        List<Shift> shifts = new ArrayList<>();
        //variables

        //User input variables UI
        double UI_amountOrdersWeekday = 0;
        double UI_amountOrdersSaturday = 0;
        double UI_amountOrdersSunday = 0;
        double UI_seniority = 0;
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


        switch (menu_1) {
            case 1 -> {

                String storeDate;
                String storeTypeofWeek;
                double storeWorkedHours;
                double storeKm;
                double storeTips;
                double storeSeniority;

                System.out.println("***********************\n" +
                        "Shift Register\n" +
                        "***********************\n");
                Thread.sleep(1500);
                System.out.print("Today's date (dd.mm.yyyy): " +
                        "Your input: ");
                String date = "";
                date = sc.next();
                storeDate = date;

                System.out.print("\nChoose the following about your shift:(1,2 or 3) \n" +
                        "1: Weekday\n" +
                        "2: Saturday\n" +
                        "3: Sunday\n" +
                        "Your input: ");
                int typeOfWeek = sc.nextInt();
                String typeofWeek = "";
                if(typeOfWeek == 1){
                     typeofWeek = "Weekday";

                }else if (typeOfWeek == 2){
                     typeofWeek = "Saturday";
                }else if (typeOfWeek == 3){
                     typeofWeek = "Sunday";
                }
                storeTypeofWeek = typeofWeek;

                System.out.print("\nAmount of hours worked: " +
                        "\nYour input: ");
                        UI_workedHours = sc.nextDouble();
                        storeWorkedHours = UI_workedHours;
                System.out.println("\nDid you have a moment where you didn't have any orders? (Y/N): ");
                String guaranteeChoice = "";
                guaranteeChoice = sc.next();

                System.out.print("\nKm in total:\n" +
                        "Your Input: ");
                UI_totalKm = sc.nextDouble();
                storeKm = UI_totalKm;

                System.out.print("\nTips: ");
                UI_totalTips = sc.nextInt();
                storeTips = UI_totalTips;
                System.out.print("\nYears worked in the company: ");
                UI_seniority = sc.nextDouble();
                storeSeniority = UI_seniority;

                shifts.add(new Shift(storeDate,storeTypeofWeek,storeWorkedHours,storeKm,
                        storeTips,storeSeniority));

                 clearConsole();
                loadingScreen();
                Thread.sleep(1400);
                clearConsole();

                System.out.print("**********************************************\n" +
                        "Date: "+date+"\n" +
                         "\nType of the week: "+typeofWeek+"\n" +
                        "\nHours worked: "+UI_workedHours+"\n" +
                        "\nDid you have a moment where you didn't work?: "+guaranteeChoice+"\n" +
                        "\nKm in total: "+UI_totalKm+"\n" +
                        "\nTips: "+ UI_totalTips+"\n" +
                        "\nYears worked: "+UI_seniority+"\n" +
                        "**********************************************\n");

                System.out.print("\n\nSubmit? (Y/N) " +
                        "\nYour input: ");
                String choose = sc.next();

                if(Objects.equals(choose, "N") || Objects.equals(choose, "n")){
                    exit(0);
                }else if(Objects.equals(choose, "Y") || Objects.equals(choose, "y")){
                    saveShiftsDataToFile(shifts);
                }

            }
            case 2 ->{
                System.out.print("***********************\n" +
                        "Historic of shifts\n" +
                        "***********************");

                displayShiftsDataFromFile();
            }
        }

    }

    private static void displayShiftsDataFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader("shifts.txt"))){
            System.out.println("\nShifts datatable");
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-8s | %-8s |%n", "Date", "Type", "Hours", "Km","Tips","Seniority");
            System.out.println("--------------------------------------------------------------------------------------------------");

            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 6){
                    String date = data[0].trim();
                    String type = data[1].trim();
                    double hours = Double.parseDouble(data[2].trim());
                    double km = Double.parseDouble(data[3].trim());
                    double tips= Double.parseDouble(data[4].trim());
                    double seniority= Double.parseDouble(data[5].trim());

                    System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-8s | %-8s |%n", date, type, hours, km,tips,seniority);

                }
            }
            System.out.println("--------------------------------------------------------------------------------------------------");
        } catch (IOException e){
            System.out.println("An error occurred while reading the file.");
        }
    }

    private static void saveShiftsDataToFile(List<Shift> shifts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("shifts.txt",true))){
            for(Shift shift : shifts){
                writer.write(shift.toString());
                writer.newLine();
                System.out.println("The shift has been registered!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
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

    public static void loadingScreen() throws InterruptedException {
        System.out.print("Loading");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
    }

    public static int mainMenu(int menu_1){


        return 0;
    }
    private static class Shift{
        private String date;
        private String typeOfWeek;
        private double workedHours;
        private double km;
        private double tips;
        private double seniority;

        public Shift(String date,String typeOfWeek,double workedHours,double km, double tips,
        double seniority){
            this.date = date;
            this.typeOfWeek = typeOfWeek;
            this.workedHours = workedHours;
            this.km = km;
            this.tips = tips;
            this.seniority = seniority;
        }

        @Override
        public String toString() {return date + ","+typeOfWeek+","+workedHours+","+km+","+
        tips+","+seniority;}
    }
}

