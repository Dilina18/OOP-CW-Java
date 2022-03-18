package com.Dilina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Race implements Serializable,Comparable<Race> {

    private Date raceDate;
    private String winner;
    private String second;
    private String third;
    List<Formula1Driver> participatedDrivers = new ArrayList<>();
    List<Integer> positions = new ArrayList<>();

    // set constructor
    public Race() {
    }

    // set overloaded constructor
    public Race(Date raceDate, List<Formula1Driver> participatedDrivers, List<Integer> positions) {
        this.setRaceDate(raceDate);
        this.setParticipatedDrivers(participatedDrivers);
        this.setPositions(positions);
    }

    // this method returns the instance of the Date which race held
    // note-above instance includes the year,month and day
    public Date getRaceDate() {
        return raceDate;
    }

    // // this method set the instance of the Date which race held
    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }

    // this method returns a list of all the formula drivers participated in the race.
    // note-above list is an object List of FormulaDrivers
    public List<Formula1Driver> getParticipatedDrivers() {
        return participatedDrivers;
    }

    // this method set a list of all the formula drivers participated in the race.
    public void setParticipatedDrivers(List<Formula1Driver> participatedDrivers) {
        this.participatedDrivers = participatedDrivers;
    }

    // this method returns a list of all the positions drivers achieved in the race.
    // note - position List contains positions according to the drivers list by default
    public List<Integer> getPositions() {
        return positions;
    }

    // this method set a list of all the formula drivers participated in the race.
    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    // this method returns the winner of the race.
    public String getWinner() {
        return winner;
    }

    // this set returns the winner of the race.
    public void setWinner(String winner) {
        this.winner = winner;
    }

    // this method returns the 2nd place of the race.
    public String getSecond() {
        return second;
    }

    // this method set the 2nd place of the race.
    public void setSecond(String second) {
        this.second = second;
    }

    // this method returns the 3rd place of the race.
    public String getThird() {
        return third;
    }

    // this method set the 3rd place of the race.
    public void setThird(String third) {
        this.third = third;
    }


    // this method compared Race objects according to the details in race Date instance.
    @Override
    public int compareTo(Race o) {
        return getRaceDate().compareTo(o.getRaceDate());
    }
}
