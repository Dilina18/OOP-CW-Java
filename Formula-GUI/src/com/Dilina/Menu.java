package com.Dilina;

import java.util.*;

public class Menu {
    Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();

    // this method validate user inputs and show details for a selected option.
    public void startMainMenu(){
        formula1ChampionshipManager.loadData("championshipData.ser");

        System.out.println("\n-----------WELCOME!------------");
        System.out.println("Formula1 Championship Manager");
        System.out.println("-------------------------------");
        System.out.println("1: Add New Driver to Championship");
        System.out.println("2: Remove Existing Driver");
        System.out.println("3: Change Driver Manufacturer");
        System.out.println("4: Display Statistics for a Selected Driver");
        System.out.println("5: Display the Formula1 Statistics Table");
        System.out.println("6: Update Race Details");
        System.out.println("7: Save & Exit");
        System.out.println("8: Open Formula1 GUI\n");

        // calling method to validate user option
        int selectedChoice = validateInputs();

        switch (selectedChoice) {

            case 1:
                optionAdd();
                break;
            case 2:
                optionDelete();
                break;
            case 3:
                optionChangeManufacturer();
                break;
            case 4:
                optionIndividualStatistics();
                break;
            case 5:
                optionDisplayFormulaTable();
                break;
            case 6:
                optionUpdateRaceDetails();
                break;
            case 7:
                formula1ChampionshipManager.loadData("championshipData.ser");
                formula1ChampionshipManager.saveData("championshipData.ser");
                System.out.println("Program Ended.Thank You!");
                System.exit(0);
                break;
            case 8:
                guiManager();
                break;
        }
    }


    // this method validate user input and returns a selected option in the start menu
    public static int validateInputs(){
        Scanner y = new Scanner(System.in);
        while (true){
            try {
                System.out.print("Select an Option to Continue: ");
                String input = y.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice < 9){
                    return choice;
                }
                else {
                    System.out.println("Invalid Number!\n");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Data Type!\n");
            }
        }
    }

    // this method returns a validated user input.
    public static String userDetailsValidator(String question){
        Scanner y = new Scanner(System.in);
        while (true){
            try {
                System.out.print(question);
                String input = y.nextLine().trim();
                if (input.length() > 0){
                    return input;
                }
                else {
                    System.out.println("Invalid Entry!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Data Type!");
            }
        }
    }

    // this method check if the given driver team already exist
    public static boolean isManufacturerExist(Formula1ChampionshipManager formula1ChampionshipManager,String manufacturer) {
        for (Formula1Driver driver : formula1ChampionshipManager.getFormula1Drivers()) {
            if (driver.getDriverManufactureTeam().equalsIgnoreCase(manufacturer)) {
                return true;
            }
        }
        return false;
    }

    // method to validate user input and add a new driver
    public void optionAdd(){
        formula1ChampionshipManager.loadData("championshipData.ser");
        System.out.println("\n-------ADDING NEW DRIVER--------");
        String manufacturer = userDetailsValidator("Enter Driver Manufacturer Team: ");

        // calling method to check if driver team already exists
        if (isManufacturerExist(formula1ChampionshipManager,manufacturer)){
            System.out.println("Driver Manufacturer already exist!");
            optionAdd();
        }
        else {
            // calling this method returns a lengthwise validated user input
            String name = userDetailsValidator("Enter Driver Name: ");
            String location = userDetailsValidator("Enter Driver Location: ");

            formula1ChampionshipManager.addNewDriver(name,location,manufacturer); // adding new driver
            System.out.println("\nNew Driver " + name + " From " + location + " has been added to Team " +
                    manufacturer + " Successfully!");
        }
        formula1ChampionshipManager.saveData("championshipData.ser"); // save program data
        startMainMenu();
    }

    // method to validate user input and delete an existing driver
    public void optionDelete(){
        formula1ChampionshipManager.loadData("championshipData.ser");
        if (formula1ChampionshipManager.getFormula1Drivers().isEmpty()){
            System.out.println("\n-----REMOVE EXISTING DRIVER-----\n");
            System.out.println("There Are No Existing Drivers In The Competition!");
            startMainMenu();
        }
        else {
            formula1ChampionshipManager.loadData("championshipData.ser"); // load program data
            System.out.println("\n-----REMOVE EXISTING DRIVER-----\n");
            formula1ChampionshipManager.displayDriverList();
            System.out.println();

            // calling this method returns a lengthwise validated user input
            String name = userDetailsValidator("Enter Driver Name: ");
            String manufacturer = userDetailsValidator("Enter Driver Manufacturer Team: ");

            // calling this method check if selected driver found and deleted
            boolean result = formula1ChampionshipManager.deleteDriver(name,manufacturer);
            if (result){
                System.out.println("\nDriver " + name + " From Team " + manufacturer + " Successfully Deleted!");
                startMainMenu();
            }
            else {
                System.out.println("\nDriver Did Not Find!\n");
                optionDelete();
            }
            formula1ChampionshipManager.saveData("championshipData.ser"); // save program data
        }
    }

    // method to validate user input and change the constructor team of a driver
    public void optionChangeManufacturer(){
        formula1ChampionshipManager.loadData("championshipData.ser");
        if (formula1ChampionshipManager.getFormula1Drivers().isEmpty()){
            System.out.println("\n-------CHANGE CONSTRUCTOR-------");
            System.out.println("There Are No Existing Drivers In The Competition!");
            startMainMenu();
        }
        else if (formula1ChampionshipManager.getFormula1Drivers().size() < 2){
            System.out.println("\n-------CHANGE CONSTRUCTOR-------");
            System.out.println("There Is Only One Driver In The Competition!");
            startMainMenu();
        }
        else {
            formula1ChampionshipManager.loadData("championshipData.ser");
            System.out.println("\n-------CHANGE CONSTRUCTOR-------\n");
            formula1ChampionshipManager.displayDriverList();
            System.out.println();

            // calling this method returns a lengthwise validated user input
            String name = userDetailsValidator("Enter Driver Name: ");
            String manufacturer = userDetailsValidator("Enter Driver Current Manufacturer Team: ");
            String newManufacturer = userDetailsValidator("Enter Driver New Manufacturer Team: ");

            // calling method to check if driver team already exists
            if (!isManufacturerExist(formula1ChampionshipManager,newManufacturer)){
                System.out.println("\nDriver Manufacturer Not exist!");
                optionChangeManufacturer();
            }
            else {
                // calling this method check if selected driver found and team successfully changed
                boolean result = formula1ChampionshipManager.changeDriverManufacturerTeam(name,manufacturer,newManufacturer);

                if (result){
                    System.out.println("\nDriver Manufacturer Successfully Changed to "+ newManufacturer + "!\n");
                    formula1ChampionshipManager.saveData("championshipData.ser");
                    startMainMenu();
                }
                else {
                    System.out.println("\nDriver Did Nof Find Please/Invalid Manufacturer Details!");
                    optionChangeManufacturer();
                }
            }
        }
    }

    // method to validate user input and display statistics for a selected driver
    public void optionIndividualStatistics(){
        formula1ChampionshipManager.loadData("championshipData.ser");
        if (formula1ChampionshipManager.getFormula1Drivers().isEmpty()){
            System.out.println("\n-------VIEW DRIVER STATS-------");
            System.out.println("There Are No Existing Drivers In The Competition!");
            startMainMenu();
        }
        else {
            System.out.println("\n-------VIEW DRIVER STATS-------\n");
            formula1ChampionshipManager.displayDriverList();
            System.out.println();

            // calling this method returns a lengthwise validated user input
            String name = userDetailsValidator("Enter Driver Name: ");
            String manufacturer = userDetailsValidator("Enter Driver Manufacturer Team: ");

            // get selected driver
            Formula1Driver driver = formula1ChampionshipManager.getDriverByNameAndTeam(name,manufacturer);

            // check if driver is founded
            if (null != driver){
                System.out.println(formula1ChampionshipManager.displayDriverStatistics(driver.getDriverName(),driver.getDriverManufactureTeam()));
                startMainMenu();
            }
            else {
                System.out.println();
                System.out.println(formula1ChampionshipManager.displayDriverStatistics(name,manufacturer));
                optionIndividualStatistics();
            }
        }
    }

    // method to validate user input and display the formula points table
    public void optionDisplayFormulaTable(){
        formula1ChampionshipManager.loadData("championshipData.ser");
        if (formula1ChampionshipManager.getFormula1Drivers().isEmpty()){
            System.out.println("\n-------VIEW FORMULA TABLE-------");
            System.out.println("There Are No Existing Drivers In The Competition!");
        }
        else {
            System.out.println(formula1ChampionshipManager.displayFormulaTable());
        }
        startMainMenu();
    }

    // method to validate user input and update all the details of a race
    public void optionUpdateRaceDetails(){
        formula1ChampionshipManager.loadData("championshipData.ser");
        System.out.println("\n-------UPDATE RACE DETAILS-------\n");
        formula1ChampionshipManager.displayDriverList();
        List<Formula1Driver> participatedDrivers = new ArrayList<>();
        List<Integer> driverPositions = new ArrayList<>();

        // check drivers are in the competition
        if (formula1ChampionshipManager.getFormula1Drivers().isEmpty()){
            System.out.println("There Are No Existing Drivers In The Competition!");
            startMainMenu();
        }
        // make sure need least 2 drivers to get a race
        else if (formula1ChampionshipManager.getFormula1Drivers().size() < 2){
            System.out.println("There Should be Lest Two Drivers to Entertained a Race!");
            startMainMenu();
        }
        else {
            System.out.println();
            // calling this method returns a validated date entered by the user
            Date raceDate = dateInputValidator();
            System.out.println();
            for (Formula1Driver driver:formula1ChampionshipManager.getFormula1Drivers()){
                String answer = yesNoValidator("Did " + driver.getDriverName() + " Participate(Y/N):");
                if (answer.equalsIgnoreCase("y") ){
                    System.out.println(driver.getDriverName() + " has Participated.\n");
                    int position = positionValidator(); // calling this method returns a validated user input for position
                    participatedDrivers.add(driver);
                    driverPositions.add(position);
                }
                else if (answer.equalsIgnoreCase("n")){
                    System.out.println(driver.getDriverName() + " has not Participated.\n");
                }
            }
            // calling this method check all the positions entered by the user are in a correct order
            boolean isSequential = sequentialValidator(driverPositions,participatedDrivers);
            if (isSequential){
                // adding race
                formula1ChampionshipManager.updateRaceDetails(raceDate,participatedDrivers,driverPositions);
                System.out.println("Race Details Successfully Updated!");
                System.out.println();
            }
            else {
                System.out.println("Entered Positions Are Not In A Sequential Order!");
                optionUpdateRaceDetails();
            }
        }
        formula1ChampionshipManager.saveData("championshipData.ser");
        startMainMenu();
    }

    // method to validate user input and returns selected option
    public String yesNoValidator(String question){
        Scanner y = new Scanner(System.in);
        while (true){
            try {
                System.out.print(question);
                String input = y.nextLine().trim();
                if (input.length() > 0){
                    if (input.equalsIgnoreCase("y")){
                        return input;
                    }
                    else if (input.equalsIgnoreCase("n")){
                        return input;
                    }
                    else {
                        System.out.println("Invalid Entry!");
                    }
                }
                else {
                    System.out.println("Field Cannot Be Empty!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Data Type!");
            }
        }
    }

    // method to check that all entered race positions are in a valid order.
    public boolean sequentialValidator(List<Integer> positions, List<Formula1Driver> drivers){
        int x = 0;
        List<Integer> sortedPositions = new ArrayList<>(positions);
        Collections.sort(sortedPositions);
        if (positions.contains(1) && Collections.max(positions)==drivers.size()){
            for (int num1: sortedPositions){
                for (int i = 1; i<sortedPositions.size();i++){
                    if (num1-sortedPositions.get(i)==-1){
                        x++;
                    }
                }
            }
        }
        return x == sortedPositions.size() - 1;
    }

    // method to validate user input and set the race Date
    public static Date dateInputValidator() {
        // note - all the validations regarding date handled inside Date class
        Date date = new Date();
        String inputMismatchErrorMessage = "Invalid input. Please enter an integer.";

        while (true) {
            try {
                int year = Integer.parseInt(userDetailsValidator("Year: "));
                date.setYear(year);
                break;
            } catch (InputMismatchException e) {
                System.out.println(inputMismatchErrorMessage);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid year.");
            }
        }

        while (true) {
            try {
                int month = Integer.parseInt(userDetailsValidator("Month: "));
                date.setMonth(month);
                break;
            } catch (InputMismatchException e) {
                System.out.println(inputMismatchErrorMessage);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }

        while (true) {
            try {
                int day = Integer.parseInt(userDetailsValidator("Day: "));
                date.setDay(day);
                break;
            } catch (InputMismatchException e) {
                System.out.println(inputMismatchErrorMessage);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }

        return date;
    }

    // method to check if the entered race position is valid
    public static int positionValidator(){
        Scanner input = new Scanner(System.in);
        while (true){
            try {
                System.out.print("Please Enter his Position: ");
                String choice = input.nextLine().trim();
                int position = Integer.parseInt(choice);
                if (position > 0){
                    return position;
                }
                else {
                    System.out.println("Invalid Number!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Data Type!");
            }
        }
    }

    // method to authenticate user and run formula GUI.
    // for the testing purposes please use [User Name-user || Password-1234].
    public void guiManager(){
        System.out.println("\n-------ACCESSING FORMULA GUI-------");
        System.out.println("Note: User-user || Password-1234");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter User Name: ");
        String user = input.nextLine();
        System.out.print("Enter User Password: ");
        String password = input.nextLine();

        // calling this method check if entered user details are verified
        boolean isVerified = formula1ChampionshipManager.userValidator(user,password);

        if (isVerified){
            MyFrame frame = new MyFrame();
        }
        else {
            System.out.println("\nUnauthorised User!");
            startMainMenu();
        }
    }
}
