package com.Dilina;

import java.io.Serializable;

public class DriverOtherPlaces implements Serializable {
    private int fourthPlaces;
    private int fifthPlaces;
    private int sixthPlaces;
    private int seventhPlaces;
    private int eighthPlaces;
    private int ninthPlaces;
    private int tenthPlace;
    private int aboveTenth;

    // set constructor
    public DriverOtherPlaces() {
    }

    // set overloaded constructor
    public DriverOtherPlaces(int fourthPlaces, int fifthPlaces, int sixthPlaces, int seventhPlaces, int eighthPlaces,
                             int ninthPlaces, int tenthPlace, int aboveTenth) {
        this.fourthPlaces = fourthPlaces;
        this.fifthPlaces = fifthPlaces;
        this.sixthPlaces = sixthPlaces;
        this.seventhPlaces = seventhPlaces;
        this.eighthPlaces = eighthPlaces;
        this.ninthPlaces = ninthPlaces;
        this.tenthPlace = tenthPlace;
        this.aboveTenth = aboveTenth;
    }

    // following methods returns the total of each mentioned places achieved by formula driver

    public int getFourthPlaces() {
        return fourthPlaces;
    }

    public void setFourthPlaces(int fourthPlaces) {
        this.fourthPlaces = fourthPlaces;
    }

    public int getFifthPlaces() {
        return fifthPlaces;
    }

    public void setFifthPlaces(int fifthPlaces) {
        this.fifthPlaces = fifthPlaces;
    }

    public int getSixthPlaces() {
        return sixthPlaces;
    }

    public void setSixthPlaces(int sixthPlaces) {
        this.sixthPlaces = sixthPlaces;
    }

    public int getSeventhPlaces() {
        return seventhPlaces;
    }

    public void setSeventhPlaces(int seventhPlaces) {
        this.seventhPlaces = seventhPlaces;
    }

    public int getEighthPlaces() {
        return eighthPlaces;
    }

    public void setEighthPlaces(int eighthPlaces) {
        this.eighthPlaces = eighthPlaces;
    }

    public int getNinthPlaces() {
        return ninthPlaces;
    }

    public void setNinthPlaces(int ninthPlaces) {
        this.ninthPlaces = ninthPlaces;
    }

    public int getTenthPlace() {
        return tenthPlace;
    }

    public void setTenthPlace(int tenthPlace) {
        this.tenthPlace = tenthPlace;
    }

    public int getAboveTenth() {
        return aboveTenth;
    }

    public void setAboveTenth(int aboveTenth) {
        this.aboveTenth = aboveTenth;
    }

    // this method increase the total places according to position achieved by driver
    // note call this method to update places 4th to above
    public void incrementOtherPlaces(int place) {
        switch (place) {
            case 4:
                setFourthPlaces(getFourthPlaces()+1);
                break;
            case 5:
                setFifthPlaces(getFifthPlaces()+1);
                break;
            case 6:
                setSixthPlaces(getSixthPlaces()+1);
                break;
            case 7:
                setSeventhPlaces(getSeventhPlaces()+1);
                break;
            case 8:
                setEighthPlaces(getEighthPlaces()+1);
                break;
            case 9:
                setNinthPlaces(getNinthPlaces()+1);
                break;
            case 10:
                setTenthPlace(getTenthPlace()+1);
                break;
            default:
                setAboveTenth(getAboveTenth()+1);
                break;
        }
    }

    // this method returns total points for a driver according to 4th-10th places
    public int fourToTenthPointCalculator(){
        return getFourthPlaces()*12 + getFifthPlaces()*10 + getSixthPlaces()*8 + getSeventhPlaces()*6 +
                getEighthPlaces()*4 + getNinthPlaces()*2 + getTenthPlace();
    }
    // this method returns the total of all the places between 4th and above for a driver
    public int totalOtherPlaces(){
        return getFourthPlaces() + getFifthPlaces() + getSixthPlaces() + getSeventhPlaces() +
                getEighthPlaces() + getNinthPlaces() + getTenthPlace() + getAboveTenth();
    }
}
