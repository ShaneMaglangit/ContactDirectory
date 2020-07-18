package com.example;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static LinkedList<Contact> contacts = new LinkedList<>();

    public static void main(String[] args) {
        mainLoop:
        while(true) {
            String[] input;

            // Get the command inputted by the user
            System.out.print("Command> ");
            // Split the command into an array of string
            // A space will be the delimiter
            input = scanner.nextLine().split(" ");

            // Get the first string in the command
            // Convert it to uppercase to make the cases case insensitive.
            switch (input[0].toUpperCase()) {
                // If the input is "I" or "i", then invoke the insert command.
                // input is the command inputted by the user
                case "I":
                    insert(input);
                    break;
                // If the input is "D" or "d", then invoke the delete command.
                // input is the command inputted by the user
                case "D":
                    delete(input);
                    break;
                // If the input is "V" or "v", then invoke the view command.
                // input is the command inputted by the user
                case "V":
                    view(input);
                    break;
                // If the input is "M" or "m", then invoke the modify command.
                // input is the command inputted by the user
                case "M":
                    modify(input);
                    break;
                // If the input is "X" or "x", then break the main loop
                // This causes the program to end
                case "X":
                    break mainLoop;
                // If an unindentified command is given, then show an error message
                default:
                    System.out.println("Remarks: Invalid command");
            }
        }

        System.out.println("Remarks: Program ended.");
    }

    /**
     * Used to insert a new contact or number to the existing list
     * Command must be "I <name> <contact number>"
     * @param input array of string containing the commands
     */
    public static void insert(String[] input) {
        // Insert required 2 extra parameters, if the length of the command is not equal to
        // 3, then it is an invalid command.
        if(input.length != 3) {
            System.out.println("Remarks: Invalid command.");
        }
        // A contact number must be either 7 or 11 digits
        else if (input[2].length() != 11 && input[2].length() != 7) {
            System.out.println("Remarks: Number must be a 7 or 11 digit number.");
        }
        // An 11 digit contact number must start with 09
        else if (input[2].length() == 11 && !input[2].substring(0, 2).equals("09")) {
            System.out.println("Remarks: Number must start with 09");
        }
        // This code block will be invoked when there is no error found with the command.
        else {
            // Iterate through the list of contacts
            for(Contact contact : contacts) {
                // Check if the 1st parameter of the command is equal to the contact's name
                if(contact.getName().equalsIgnoreCase(input[1])) {
                    // If the number already exists, show an error message.
                    if(contact.getNumbers().contains(input[2])) {
                        System.out.println("Remarks: Number already exists for this contact.");
                    }
                    // Else add the number to the list of numbers for this contact.
                    else {
                        contact.addNumber(input[2]);
                        System.out.println("Remarks: New number has been added.");
                    }
                    return;
                }
            }

            // Creates an entirely new contact if it doesn't exists yet
            contacts.add(new Contact(input[1], input[2]));
            System.out.println("Remarks: New contact added.");
        }
    }

    /**
     * Used to delete a contact from the list
     * Command must be "D <name>"
     * @param input array of string containing the commands
     */
    public static void delete(String[] input) {
        // Delete requires one extra parameters, therefore check if the length of the command is 2.
        if(input.length == 2) {
            // Iterate through the contact list
            for (Contact contact : contacts) {
                // Find the contact where the name matches with the command's parameter
                if (contact.getName().equalsIgnoreCase(input[1])) {
                    contacts.remove(contact);
                    System.out.println("Remarks: " + input[1] + " has been removed.");
                    return;
                }
            }

            System.out.println("Remarks: Contact does not exists.");
        }
        // If the command's length is not equal to 2, then it is an invalid command
        else {
            System.out.println("Remarks: Invalid command.");
        }
    }

    /**
     * Used to view the directory or a specific contact within the list
     * Command must be "V <name> or V"
     * @param input array of string containing the commands
     */
    public static void view(String[] input) {
        // If the command does not contain any extra parameters, then show the complete directory
        if(input.length == 1) {
            // Iterate through the contact and print each of them.
            System.out.println("\tPhonebook directory:");
            for(Contact contact : contacts) {
                // putting contact inside the println() implicitly calls the toString() method of the object.
                // therefore, this is equivalent to println("\t" + contact.toString())
                System.out.println("\t" + contact);
            }
        }
        // If the command contains a parameter, then find the contact with the matching name
        else if(input.length == 2) {
            // Iterate through each contact on the list
            for(Contact contact : contacts) {
                // Check if the name matches the parameters
                if(contact.getName().equalsIgnoreCase(input[1])) {
                    // putting contact inside the println() implicitly calls the toString() method of the object.
                    // therefore, this is equivalent to println("\t" + contact.toString())
                    System.out.println("\tPhonebook directory:");
                    System.out.println("\t" + contact);
                    return;
                }
            }

            System.out.println("Remarks: Contact does not exists.");
        } else {
            System.out.println("Remarks: Invalid command.");
        }
    }

    public static void modify(String[] input) {
        if(input.length == 4) {
            for(Contact contact : contacts) {
                if(contact.getName().equalsIgnoreCase(input[1])) {
                    if(contact.getNumbers().contains(input[2])) {
                        int oldIndex = contact.getNumbers().indexOf(input[2]);
                        contact.getNumbers().set(oldIndex, input[3]);
                        System.out.println(oldIndex);
                        System.out.println("Remarks: Contact number updated.");
                    } else {
                        System.out.println("Remarks: This number does not yet exists.");
                    }
                    return;
                }
            }

            System.out.println("Remarks: This contact does not exists.");
        } else {
            System.out.println("Remarks: Invalid command.");
        }
    }
}
