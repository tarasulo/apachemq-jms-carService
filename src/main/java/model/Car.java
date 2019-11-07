package model;

import java.io.Serializable;
import java.util.Random;

public class Car implements Serializable {

    private static final long serialVersionUID = 2966742605062054882L;
    private String brand;
    private String model;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", engine=" + engine +
                '}';
    }

    private int year;
    private double engine;

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
        return randomGenerator.nextInt(n) + 1;
    }

    private String makeModel(int n) {
        switch (randomForSwitch(n)) {
            case 1:
                model = "CR-V";
                break;
            case 2:
                model = "Combo";
                break;
            case 3:
                model = "X5";
                break;
            case 4:
                model = "Vito";
                break;
            case 5:
                model = "Q6";
                break;
            default:
                model = "Not Found";
                break;
        }
        return model;
    }

    private String makeBrand(int n) {
        switch (randomForSwitch(n)) {
            case 1:
                brand = "Audi";
                break;
            case 2:
                brand = "BMW";
                break;
            case 3:
                brand = "Mazda";
                break;
            case 4:
                brand = "Mercedes";
                break;
            case 5:
                brand = "Opel";
                break;
            case 6:
                brand = "Honda";
                break;
            default:
                brand = "Not Found";
                break;
        }
        return brand;
    }

    private int makeYear(int n) {
        switch (randomForSwitch(n)) {
            case 1:
                year = 1990;
                break;
            case 2:
                year = 1991;
                break;
            case 3:
                year = 1992;
                break;
            case 4:
                year = 1993;
                break;
            case 5:
                year = 1994;
                break;
            case 6:
                year = 1995;
                break;
            case 7:
                year = 1996;
                break;
            case 8:
                year = 1997;
                break;
            case 9:
                year = 1998;
                break;
            case 10:
                year = 1999;
                break;
            case 11:
                year = 2000;
                break;
            case 12:
                year = 2001;
                break;
            case 13:
                year = 2002;
                break;
            case 14:
                year = 2003;
                break;
            case 15:
                year = 2004;
                break;
            case 16:
                year = 2005;
                break;
            case 17:
                year = 2006;
                break;
            case 18:
                year = 2007;
                break;
            case 19:
                year = 2008;
                break;
            case 20:
                year = 2009;
                break;
            case 21:
                year = 2010;
                break;
            case 22:
                year = 2011;
                break;
            case 23:
                year = 2012;
                break;
            case 24:
                year = 2013;
                break;
            case 25:
                year = 2014;
                break;
            case 26:
                year = 2015;
                break;
            case 27:
                year = 2016;
                break;
            case 28:
                year = 2017;
                break;
            case 29:
                year = 2018;
                break;
            case 30:
                year = 2019;
                break;
            default:
                break;
        }
        return year;
    }

    private double makeEngine(int n) {
        switch (randomForSwitch(n)) {
            case 1:
                engine = 1.2;
                break;
            case 2:
                engine = 1.7;
                break;
            case 3:
                engine = 2.2;
                break;
            case 4:
                engine = 3.0;
                break;
            case 5:
                engine = 3.5;
                break;
            default:
                break;
        }
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
