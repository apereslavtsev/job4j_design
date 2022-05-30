package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    int regNumber;
    String name;
    boolean automaticTransmission;
    byte[] wheelRadius;
    Person driver;

    public Car(int regNumber, String name, boolean automaticTransmission, byte[] wheelRadius, Person driver) {
        this.regNumber = regNumber;
        this.name = name;
        this.automaticTransmission = automaticTransmission;
        this.wheelRadius = wheelRadius;
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Car{"
                + "regNumber=" + regNumber
                + ", name='" + name + '\''
                + ", automaticTransmission=" + automaticTransmission
                + ", wheelRadius=" + Arrays.toString(wheelRadius)
                + ", driver=" + driver
                + '}';
    }

    public static void main(String[] args) {
        Car car1 = new Car(
                254,
                "VAZ 2107",
                false,
                new byte[]{13, 13, 14, 14},
                new Person(
                        true,
                        22,
                        new Contact(1, "22-55-48"),
                        new String[]{"Driver", "Mechanic"}));

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car1));

        String car1Text = "{"
                            + "\"regNumber\":254,"
                            + "\"name\":\"VAZ 2107\","
                            + "\"automaticTransmission\":false,"
                            + "\"wheelRadius\":[13,13,14,14],"
                            + "\"driver\":{\"sex\":true,"
                                            + "\"age\":22,"
                                            + "\"contact\":{\"zipCode\":1,"
                                                            + "\"phone\":\"22-55-48\"},"
                                            + "\"statuses\":[\"Driver\",\"Mechanic\"]"
                                            + "}"
                            + "}";
        Car carFromText = gson.fromJson(car1Text, Car.class);
        System.out.println(carFromText);
    }
}
