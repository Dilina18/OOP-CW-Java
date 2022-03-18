package com.Dilina;

import java.io.Serializable;
import java.util.Objects;

public abstract class Driver implements Serializable {
    private String driverName;
    private String driverLocation;
    private String driverManufacturerTeam;

    // set constructor
    public Driver() {
    }

    // set overloaded constructor
    public Driver(String driverName, String driverLocation, String driverManufactureTeam) {
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.driverManufacturerTeam = driverManufactureTeam;
    }

    // this method returns the name of a formula driver
    public String getDriverName() {
        return driverName;
    }

    // this method returns the location of a formula driver
    public String getDriverLocation() {
        return driverLocation;
    }

    // this method returns the team name of a formula driver
    public String getDriverManufactureTeam() {
        return driverManufacturerTeam;
    }

    // this method set the team name of a formula driver
    public void setDriverManufactureTeam(String driverManufactureTeam) {
        this.driverManufacturerTeam = driverManufactureTeam;
    }

    // override toString method as follows
    @Override
    public String toString() {
        return "Driver Name: " + this.driverName +
                "\nDriver Location: " + this.driverLocation +
                "\nDriver Manufacturer Team: " + this.driverManufacturerTeam;
    }

    // override method to check object equality according to following
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driverName, driver.driverName) &&
                Objects.equals(driverLocation, driver.driverLocation) &&
                Objects.equals(driverManufacturerTeam, driver.driverManufacturerTeam);
    }

    // override method check and returns a unique code for each instance of this class
    // uniquely identify each object using hashcode
    @Override
    public int hashCode() {
        return Objects.hash(driverName, driverLocation, driverManufacturerTeam);
    }
}
