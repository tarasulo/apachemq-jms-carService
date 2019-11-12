package model;

import java.io.Serializable;

public class Car implements Serializable {

    private static final long serialVersionUID = -2785326698250367788L;
    private String brand;
    private String model;
    private int year;
    private double engine;

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

    public void setYear(int year) {
        this.year = year;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }

    public double getEngine() {
        return engine;
    }
}
