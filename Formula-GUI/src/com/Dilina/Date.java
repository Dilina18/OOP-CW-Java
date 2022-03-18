package com.Dilina;

import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable,Comparable<Date> {
    private int year;
    private int month;
    private int day;
    private boolean isLeapYear = false;


    // set constructor
    public Date() {}

    // set overloaded constructor
    public Date(int year, int month, int day) {
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    // this method returns the year from a date
    public int getYear() { return year; }

    // method to validate and set year of a date
    public void setYear(int year) {
        if (year % 4 == 0 && year >= 0) {
            this.isLeapYear = true;
            this.year = year;
        } else if (year >= 0) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Year should be a positive integer!");
        }
    }

    // this method returns the day from a date
    public int getDay() { return day; }

    // method to validate and set day of a date
    public void setDay(int day) {
        if (getMonth() == 1 || getMonth() == 3 || getMonth() == 5 || getMonth() == 7 ||getMonth() == 8 ||getMonth() == 10 ||getMonth() == 12) {
            if (1 <= day && day <= 31) {
                this.day = day;
            } else {
                throw new IllegalArgumentException("Day should between 1 to 31 days!");
            }
        } else if (getMonth() == 4 || getMonth() == 6 || getMonth() == 9 || getMonth() == 11) {
            if (1 <= day && day <= 30) {
                this.day = day;
            } else {
                throw new IllegalArgumentException("Day should between 1 to 30 days!");
            }
        } else if (getMonth() == 2) {
            if (isLeapYear) {
                if (1 <= day && day <= 29) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("Day should between 1 to 29(Due to leap year)!");
                }
            } else {
                if (1 <= day && day <= 28) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("Day should between 1 to 28 days!");
                }
            }
        }
    }

    // this method returns the month of a date
    public int getMonth() { return month; }

    // method to validate and set month of a date
    public void setMonth(int month) {
        if (month <= 12 && month >= 1) {
            this.month = month;
        } else {
            throw new IllegalArgumentException("Month should between 1 to 12 integer!");
        }
    }

    // overriding toString method
    @Override
    public String toString() {
        return year+"/"+month+"/"+day;
    }

    // method to compare date objects as for the year>month>day priority
    @Override
    public int compareTo(Date o) {
        int result = this.getYear() - o.getYear();
        if (result == 0) {
            result = this.getMonth() - o.getMonth();
            if (result == 0) {
                result = this.getDay() - o.getDay();
                return result;
            }
            return result;
        }
        return result;
    }

    // override method to check object equality according to following
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year && month == date.month && day == date.day && isLeapYear == date.isLeapYear;
    }

    // override method check and returns a unique code for each instance of this class
    // uniquely identify each object using hashcode
    @Override
    public int hashCode() {
        return Objects.hash(year, month, day, isLeapYear);
    }
}
