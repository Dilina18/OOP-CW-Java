package com.Dilina;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Formula1ChampionshipManager implements ChampionshipManager, Serializable {

    List<Formula1Driver> formula1Drivers = new ArrayList<>();
    List<Race> races = new ArrayList<>();

    // method to validate and handle adding new driver
    @Override
    public void addNewDriver(String name, String location, String manufacturer) {
        Formula1Driver newDriver = new Formula1Driver(name,location,manufacturer);
        formula1Drivers.add(newDriver);
        formula1Drivers.sort(Collections.reverseOrder());
    }

    // method to display a simple list of all existing drivers
    @Override
    public void displayDriverList() {
        int i = 1;
        for (Formula1Driver driver:formula1Drivers){
            System.out.println("Pos: " + i + " Name: "+ driver + " Manufacturer: " + driver.getDriverManufactureTeam() );
            i++;
        }
    }

    // method to validate and handle deleting existing driver
    @Override
    public boolean deleteDriver(String name, String manufacturer) {
        for (Formula1Driver driver: formula1Drivers)  {
            if (driver.getDriverName().equalsIgnoreCase(name) &&
                    driver.getDriverManufactureTeam().equalsIgnoreCase(manufacturer)) {
                formula1Drivers.remove(driver);
                this.saveData("championshipData.ser");
                return true;
            }
        }
        return false;
    }

    // method to validate and handle changing driver constructor
    @Override
    public boolean changeDriverManufacturerTeam(String name, String currentManufacturer, String newManufacturer) {
        for (Formula1Driver driver: formula1Drivers) {
            if (driver.getDriverName().equalsIgnoreCase(name) &&
                    driver.getDriverManufactureTeam().equalsIgnoreCase(currentManufacturer) && !newManufacturer.equals(currentManufacturer)) {
                driver.setDriverManufactureTeam(newManufacturer);
                return true;
            }
        }
        return false;
    }

    // method to validate and return all the statistics for a selected driver
    @Override
    public String displayDriverStatistics(String name, String manufacturer) {
        int i = 1;
        for (Formula1Driver driver : formula1Drivers) {
            if (driver.getDriverName().equalsIgnoreCase(name) &&
                    driver.getDriverManufactureTeam().equalsIgnoreCase(manufacturer)) {
                return "\n" + driver.getDriverName().toUpperCase() + "\n----------------------\n" +
                        "Position          :  " + i + "\n" +
                        "Location          :  " + driver.getDriverLocation() + "\n" +
                        "Manufacturer Team :  " + driver.getDriverManufactureTeam() + "\n" +
                        "Total Races       :  " + driver.getDriverTotalRaces() + "\n" +
                        "First Places      :  " + driver.getDriverFirstPlaces() + "\n" +
                        "Second Places     :  " + driver.getDriverSecondPlaces() + "\n" +
                        "Third Places      :  " + driver.getDriverThirdPlaces() + "\n" +
                        "Fourth Places     :  " + driver.getOtherPlaces().getFourthPlaces() + "\n" +
                        "Fifth Places      :  " + driver.getOtherPlaces().getFifthPlaces() + "\n" +
                        "Sixth Places      :  " + driver.getOtherPlaces().getSixthPlaces() + "\n" +
                        "Seventh Places    :  " + driver.getOtherPlaces().getSeventhPlaces() + "\n" +
                        "Eighth Places     :  " + driver.getOtherPlaces().getEighthPlaces() + "\n" +
                        "Ninth Places      :  " + driver.getOtherPlaces().getNinthPlaces() + "\n" +
                        "Tenth Places      :  " + driver.getOtherPlaces().getTenthPlace() + "\n" +
                        "Above Tenth       :  " + driver.getOtherPlaces().getAboveTenth() + "\n" +
                        "Total Points      :  " + driver.getDriverTotalPoints() + "\n";
            }
            i++;
        }
        return "Driver did not find Please try Again!";
    }

    // this method returns a statistics table of all the formula drivers
    @Override
    public String displayFormulaTable() {
        StringBuilder output = new StringBuilder();
        output.append("----------------------------------------Formula1 Championship Table --------------------------------" +
                "----------\n");
        String tableHeader = String.format("|%-3s |%-15s |%-15s |%-15s |%-7s |%-7s |%-7s |%-7s |%-7s |%-7s|",
                "Pos", "Driver", "Location", "Team", "Races", "Wins", "2nd", "3rd", "Above", "Points");
        output.append(tableHeader).append("\n");
        formula1Drivers.sort(Collections.reverseOrder());
        int i = 1;
        for (Formula1Driver driver : formula1Drivers) {
            String result = String.format("|%-3d |%-15s |%-15s |%-15s |%-7d |%-7d |%-7d |%-7d |%-7d |%-7d|",
                    i, driver.getDriverName(), driver.getDriverLocation(), driver.getDriverManufactureTeam(),
                    driver.getDriverTotalRaces(), driver.getDriverFirstPlaces(), driver.getDriverSecondPlaces(),
                    driver.getDriverThirdPlaces(),driver.getOtherPlaces().totalOtherPlaces(),
                    driver.getDriverTotalPoints());
            output.append(result).append("\n");
            i++;
        }
        output.append("--------------------------------------------------------------------------------------------------------" +
                "------\n");
        return output.toString();
    }

    // this method returns a list of existing formula drivers
    public List<Formula1Driver> getFormula1Drivers() {
        return formula1Drivers;
    }

    // this method returns a list of all the entertained formula races
    public List<Race> getRaces() {
        return races;
    }

    // method to add a new race to the races list
    @Override
    public void updateRaceDetails(Date raceDate, List<Formula1Driver> participatedDrivers, List<Integer> positions) {
        Race newRace = new Race(raceDate,participatedDrivers,positions);
        races.add(newRace);
        Collections.sort(races);
        updateRaceScores(newRace);
        formula1Drivers.sort(Collections.reverseOrder());
        this.saveData("championshipData.ser");
    }

    // method to update driver scores after a race
    @Override
    public void updateRaceScores(Race race) {
        int i = 0;
        for (Formula1Driver raceDrivers: race.participatedDrivers){
            raceDrivers.incrementRaces();
            raceDrivers.incrementPlaces(race.positions.get(i));
            findWinners(i,race);
            i++;
        }
    }

    // method to set 1,2,3 places of a formula race
    public void findWinners(int i, Race race){
        if (race.getPositions().get(i)==1){
            race.setWinner(race.getParticipatedDrivers().get(i).getDriverName());
        }
        else if (race.getPositions().get(i)==2){
            race.setSecond(race.getParticipatedDrivers().get(i).getDriverName());
        }
        else if (race.getPositions().get(i)==3){
            race.setThird(race.getParticipatedDrivers().get(i).getDriverName());
        }
    }

    // this method returns an existing driver by name and team
    @Override
    public Formula1Driver getDriverByNameAndTeam(String name, String manufacturer) {
        for (Formula1Driver driver: formula1Drivers){
            if (driver.getDriverName().equalsIgnoreCase(name) &&
                    driver.getDriverManufactureTeam().equalsIgnoreCase(manufacturer)){
                return driver;
            }
        }
        return null;
    }

    // method to validate and handle formula GUI running
    @Override
    public boolean userValidator(String user, String password) {
        String superUser = "user";
        String loginKey = "1234";
        return user.equals(superUser) && password.equals(loginKey);
    }

    // method to deserialize program data
    @Override
    @SuppressWarnings("unchecked") // Avoid compilation warning when casting object to Arraylist
    public void loadData(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object driversArrayObject = objectInputStream.readObject();
            Object racesArrayObject =  objectInputStream.readObject();
            formula1Drivers = (ArrayList<Formula1Driver>) driversArrayObject;
            races = (ArrayList<Race>) racesArrayObject;
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException | EOFException e) {
            new File(fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // method to serialize program data
    @Override
    public void saveData(String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(formula1Drivers);
            objectOutputStream.writeObject(races);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
