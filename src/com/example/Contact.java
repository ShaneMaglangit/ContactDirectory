package com.example;

import java.util.LinkedList;

public class Contact {
    private String name;
    private LinkedList<String> numbers;

    /**
     * Constructor for the contacts
     * @param name of the person for this contact
     * @param number the initial number that will be set
     */
    public Contact(String name, String number) {
        this.name = name;
        this.numbers = new LinkedList<>();
        numbers.add(number);
    }

    /**
     * Used to get the name
     */
    public String getName() {
        return name;
    }

    /**
     * Used to get the instance of the number list
     * @return
     */
    public LinkedList<String> getNumbers() {
        return numbers;
    }

    /**
     * Used to add a new number to the contact
     * @param number
     */
    public void addNumber(String number) {
        this.numbers.add(number);
    }

    /**
     * Used to create a string representation of the properties of this object.
     */
    @Override
    public String toString() {
        return name + " - " + String.join(", ", numbers);
    }
}
