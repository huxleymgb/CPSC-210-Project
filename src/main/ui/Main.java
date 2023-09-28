package ui;

import model.Event;
import model.EventLog;

import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) {
        try {
            new CharacterAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Error - File not found. Unable to run application.");
        }
    }
}
