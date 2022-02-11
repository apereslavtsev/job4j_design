package ru.job4j.generics;

public class Tiger extends Predator {
    public Tiger(String name) {
        super(name);
    }

    public Tiger() {
    }

    @Override
    public String toString() {
        return "Tiger{"
                + "name='" + super.getName() + '\''
                + '}';
    }
}
