package model;

import java.io.Serializable;
import java.util.Random;

public class Car implements Serializable {

    private static final long serialVersionUID = -2785326698250367788L;
    private String brand;
    private String model;
    private int year;
    private double engine;

    private String brands[] = {"audi", "bmv", "mazda", "mercedes", "opel", "honda"};
    private String models[] = {"cr-v", "combo", "x5", "vito", "q6"};
    private int years[] = {1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999,
            2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012,
            2013, 2014, 2015, 2016, 2017, 2018, 2019};
    private double engines[] = {1.2, 1.7, 2.2, 3.0, 3.5};

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", engine=" + engine +
                '}';
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Car() {
        this.brand = makeBrand(6);
        this.model = makeModel(5);
        this.year = makeYear(30);
        this.engine = makeEngine(5);
    }

    private static int randomForSwitch(int n) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(n);
    }

    private String makeModel(int n) {
        model = models[randomForSwitch(n)];
        return model;
    }

    private String makeBrand(int n) {
        brand = brands[randomForSwitch(n)];
        return brand;
    }

    private int makeYear(int n) {
        year = years[randomForSwitch(n)];
        return year;
    }

    private double makeEngine(int n) {
        engine = engines[randomForSwitch(n)];
        return engine;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getEngine() {
        return engine;
    }
}
