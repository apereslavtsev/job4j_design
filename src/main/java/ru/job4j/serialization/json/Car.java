package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import javax.xml.bind.*;
import java.io.StringReader;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    int regNumber;
    String name;
    @XmlAttribute
    boolean automaticTransmission;
    @XmlElementWrapper(name = "radiuses")
    @XmlElement(name = "radius")
    String[] wheelRadius;
    Person driver;
    public Car() {

    }

    public Car(int regNumber, String name) {
        this.regNumber = regNumber;
        this.name = name;
    }

    public Car(int regNumber, String name, boolean automaticTransmission, Person driver, String... wheelRadius) {
        this.regNumber = regNumber;
        this.name = name;
        this.automaticTransmission = automaticTransmission;
        this.wheelRadius = wheelRadius;
        this.driver = driver;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAutomaticTransmission() {
        return automaticTransmission;
    }

    public void setAutomaticTransmission(boolean automaticTransmission) {
        this.automaticTransmission = automaticTransmission;
    }

    public String[] getWheelRadius() {
        return wheelRadius;
    }

    public void setWheelRadius(String[] wheelRadius) {
        this.wheelRadius = wheelRadius;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
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

    private static void toJSON(Car car) {
        final Gson gson = new GsonBuilder().create();

        System.out.println(gson.toJson(car));

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

    private static void toXML(Car car) throws Exception {

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* ?????????????????????? */
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* ?????????????????????????? */
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }

    private static void toJsonObj(Car car) {
        JSONObject jsonObject = new JSONObject(car);
        System.out.println(jsonObject.toString());
    }

    public static void main(String[] args) throws Exception {
        Car car07 = new Car(
                254,
                "VAZ 2107",
                false,
                new Person(
                        true,
                        22,
                        new Contact("22-55-48"),
                        "Driver", "Mechanic"),
                "13", "13", "14", "14");


        toJsonObj(car07);
        toJSON(car07);
        toXML(car07);
    }
}
