package ru.job4j.generics;

public class Predator extends Animal {

    public Predator(String name) {
        super(name);
    }

    public Predator() {
    }

    @Override
    public String toString() {
        return "Predator{"
                + "name='" + super.getName() + '\''
                + '}';
    }
}
