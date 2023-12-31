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
    public static void main(String[] args) throws InterruptedException {


        Scanner sc = new Scanner(System.in);
        int option;

        while (true) {
            option = mainMenu();
            clearConsole();

            switch (option) {
                case 1:
                    registerMenu();
                    break;
                case 2:
                    showShifts();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }


        }
    }

    private static void showShifts() {
        clearConsole();
        System.out.print("***********************\n" +
                         "Historic of shifts\n" +
                         "***********************");
        displayShiftsDataFromFile();
    }

    public static void registerMenu() throws InterruptedException {
        //User input variables UI
        double UI_amountOrdersWeekday = 0;
        double UI_amountOrdersSaturday = 0;
        double UI_amountOrdersSunday = 0;
        double UI_seniority = 0;
        double UI_totalKm = 0;
        double UI_totalTips = 0;
        double UI_workedHours = 0;

        List<Shift> shifts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

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

                Thread.sleep(1400);
                System.out.print("\n\nSubmit? (Y/N) " +
                                 "\nYour input: ");
                String choose = sc.next();

                if(Objects.equals(choose, "N") || Objects.equals(choose, "n")){
                    clearConsole();
                    registerMenu();
                }else if(Objects.equals(choose, "Y") || Objects.equals(choose, "y")){
                    Thread.sleep(1000);
                    saveShiftsDataToFile(shifts);
                }
                //clearConsole();
            }




    private static void displayShiftsDataFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader("shifts.txt"))){
            Scanner sc = new Scanner(System.in);
            Thread.sleep(1000);
            System.out.println("\nShifts datatable");
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-8s | %-8s |%n", "Date", "Type", "Hours", "Km","Tips","Seniority");
            System.out.println("--------------------------------------------------------------------------------------------------");
            String line;
            boolean choice = false;

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
            Thread.sleep(1000);
            waitForEnter();
            choice = true;
            if(choice){
                clearConsole();
            }
        } catch (IOException | InterruptedException e){
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

    public static int mainMenu() throws InterruptedException {
        clearConsole();
        Scanner sc = new Scanner(System.in);
        int result;

        System.out.println("***********************");
        System.out.println("Salary Calculator 2023");
        System.out.println("***********************");
        System.out.println("1: Register");
        System.out.println("2: Show shifts");
        System.out.println("3: Exit");
        System.out.print("Your input: ");
        result = sc.nextInt();

        return result;
    }

    public static void waitForEnter(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPress Enter to go back to the Main Menu...");
        sc.nextLine();

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

