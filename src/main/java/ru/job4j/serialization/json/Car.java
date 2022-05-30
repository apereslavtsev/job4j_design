package ru.job4j.serialization.json;

public class Car {
    String regNumber;
    String name;
    boolean automaticTransmission;
    byte[] wheelRadius;
    Person driver;

    public Car(String regNumber, String name, boolean automaticTransmission, byte[] wheelRadius, Person driver) {
        this.regNumber = regNumber;
        this.name = name;
        this.automaticTransmission = automaticTransmission;
        this.wheelRadius = wheelRadius;
        this.driver = driver;
    }

    public static void main(String[] args) {
        Car car1 = new Car()


    }
}
