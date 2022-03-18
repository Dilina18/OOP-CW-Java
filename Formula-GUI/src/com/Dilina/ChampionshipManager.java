package com.Dilina;

import java.util.List;

public interface ChampionshipManager {
    void addNewDriver(String name,String location, String manufacturer);
    void displayDriverList();
    boolean deleteDriver(String name, String manufacturer);
    boolean changeDriverManufacturerTeam(String name,String currentManufacturer, String newManufacturer);
    String displayDriverStatistics(String name, String manufacturer);
    String displayFormulaTable();
    void updateRaceDetails(Date raceDate, List<Formula1Driver> participatedDrivers, List<Integer> positions);
    void updateRaceScores(Race race);
    Formula1Driver getDriverByNameAndTeam(String name, String manufacturer);
    void loadData(String fileName);
    void saveData(String fileName);
    boolean userValidator(String user, String password);
}
// all the methods contain in this class are overridden in FormulaChampionship Class.