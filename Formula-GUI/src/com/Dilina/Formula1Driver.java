package com.Dilina;

import java.io.Serializable;
import java.util.Objects;

public class Formula1Driver extends Driver implements Serializable,Comparable<Formula1Driver> {

    private int driverFirstPlaces;
    private int driverSecondPlaces;
    private int driverThirdPlaces;
    private int driverTotalRaces;
    private int driverTotalPoints;
    private DriverOtherPlaces otherPlaces;

    // set constructor
    public Formula1Driver() {
    }

    // set overloaded constructor
    public Formula1Driver(String driverName, String driverLocation, String driverManufactureTeam) {
        super(driverName, driverLocation, driverManufactureTeam);
        this.otherPlaces = new DriverOtherPlaces(0,0,0,0,0,
                0,0,0);

    }

    // set overloaded constructor
    // use this constructor to add a mistakenly deleted driver
    public Formula1Driver(String driverName, String driverLocation, String driverManufactureTeam, int driverFirstPlaces,
                          int driverSecondPlaces, int driverThirdPlaces,int fourthPlaces,int fifthPlaces,
                          int sixthPlaces,int seventhPlaces,int eighthPlaces,int ninthPlaces,int tenthPlaces,
                          int aboveTenthPlaces, int driverTotalRaces, int driverTotalPoints) {
        super(driverName, driverLocation, driverManufactureTeam);
        this.driverFirstPlaces = driverFirstPlaces;
        this.driverSecondPlaces = driverSecondPlaces;
        this.driverThirdPlaces = driverThirdPlaces;
        this.driverTotalRaces = driverTotalRaces;
        this.driverTotalPoints = driverTotalPoints;
        this.otherPlaces = new DriverOtherPlaces(fourthPlaces,fifthPlaces,sixthPlaces,seventhPlaces,eighthPlaces,
                ninthPlaces,tenthPlaces,aboveTenthPlaces);
    }

    // this method returns the total amount of driver 1st places
    public int getDriverFirstPlaces() {
        return driverFirstPlaces;
    }

    // this method set the total amount of driver 1st places
    public void setDriverFirstPlaces(int driverFirstPlaces) {
        if (driverFirstPlaces >= 0) {
            this.driverFirstPlaces = driverFirstPlaces;
        } else throw new IllegalArgumentException("Number of First Places should be a positive Integer!");
    }

    // this method returns the total amount of driver 2nd places
    public int getDriverSecondPlaces() {
        return driverSecondPlaces;
    }

    // this method set the total amount of driver 2nd places
    public void setDriverSecondPlaces(int driverSecondPlaces) {
        if (driverSecondPlaces >= 0) {
            this.driverSecondPlaces = driverSecondPlaces;
        } else throw new IllegalArgumentException("Number of Second Places should be a positive Integer!");
    }

    // this method returns the total amount of driver 3rd places
    public int getDriverThirdPlaces() {
        return driverThirdPlaces;
    }

    // this method set the total amount of driver 3rd places
    public void setDriverThirdPlaces(int driverThirdPlaces) {
        if (driverThirdPlaces >= 0) {
            this.driverThirdPlaces = driverThirdPlaces;
        } else throw new IllegalArgumentException("Number of Third Places should be a positive Integer!");
    }

    // this method returns the total amount of races driver participated
    public int getDriverTotalRaces() {
        return driverTotalRaces;
    }

    // this method set the total amount of races driver participated
    public void setDriverTotalRaces(int driverTotalRaces) {
        if (driverTotalRaces >= 0) {
            this.driverTotalRaces = driverTotalRaces;
        } else throw new IllegalArgumentException("Number of Total Races should be a positive Integer!");
    }

    // this method returns the total points of  driver
    // note this method returns the total according all the places has achieved(1 to above)
    public int getDriverTotalPoints() {
        setDriverTotalPoints(driverPointsCalculator());
        return driverTotalPoints;
    }

    // this method set the all-time total points of driver
    public void setDriverTotalPoints(int driverTotalPoints) {
        if (driverTotalPoints >= 0) {
            this.driverTotalPoints = driverTotalPoints;
        } else throw new IllegalArgumentException("Number of Total Points should be a positive Integer!");
    }

    // this method returns the instance of DriverOtherPlaces.
    // DriverOtherPlaces class includes all the details of 4th and above places
    public DriverOtherPlaces getOtherPlaces() {
        return otherPlaces;
    }

    // this method set the instance of DriverOtherPlaces.
    public void setOtherPlaces(DriverOtherPlaces otherPlaces) {
        this.otherPlaces = otherPlaces;
    }

    // method to increase the total race count
    public void incrementRaces() {
        this.driverTotalRaces++;
    }

    //method to validate and increment total count of respective place
    public void incrementPlaces(int achievedPlace) {
        if (achievedPlace > 0) {
            if (achievedPlace == 1) {
                this.driverFirstPlaces++;
            } else if (achievedPlace == 2) {
                this.driverSecondPlaces++;
            } else if (achievedPlace == 3) {
                this.driverThirdPlaces++;
            } else {
                otherPlaces.incrementOtherPlaces(achievedPlace);
            }
        } else throw new IllegalArgumentException("Place should be a positive Integer!");
    }

    // methods to calculate all-time total points of the driver
    public int driverPointsCalculator(){
        int total1 = this.driverFirstPlaces*25 + this.driverSecondPlaces*18 + this.driverThirdPlaces*15;
        int total2 = otherPlaces.fourToTenthPointCalculator();
        return total1 + total2;
    }


    // override method to check object equality according to following
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Formula1Driver that = (Formula1Driver) o;
        return driverFirstPlaces == that.driverFirstPlaces && driverSecondPlaces == that.driverSecondPlaces &&
                driverThirdPlaces == that.driverThirdPlaces && driverTotalRaces == that.driverTotalRaces &&
                driverTotalPoints == that.driverTotalPoints && Objects.equals(otherPlaces, that.otherPlaces);
    }

    // override method check and returns a unique code for each instance of this class
    // uniquely identify each object using hashcode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), driverFirstPlaces, driverSecondPlaces, driverThirdPlaces,
                driverTotalRaces, driverTotalPoints, otherPlaces);
    }

    // override toString method as follows
    @Override
    public String toString() {
        return this.getDriverName();
    }

    // method to compare driver objects according to total points
    @Override
    public int compareTo(Formula1Driver o) {
        int result = this.getDriverTotalPoints() - o.getDriverTotalPoints();
        if (result == 0){
            return this.getDriverFirstPlaces() - o.getDriverFirstPlaces();
        }
        return result;
    }
}
