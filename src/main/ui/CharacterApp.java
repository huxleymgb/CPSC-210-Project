package ui;

import model.Character;
import model.CharacterList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// D&D Character Application
// Functionality from TellerApp, JsonSerializationDemo
public class CharacterApp {
    private Scanner userInput;
    String addName;
    int addStr;
    int addDex;
    int addCons;
    int addIntel;
    int addWis;
    int addCha;
    String addClass;
    String addDesc;
    private CharacterList theList = new CharacterList();
    private static final String JSON_STORE = "./data/characterList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the character application
    public CharacterApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: runs the app allowing for user input
    // Disclaimer: function of this method (as well as methods using Scanner) learnt from Teller app
    private void runApp() {
        boolean quit = false;
        String command;
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");

        while (!quit) {
            mainScreen();
            command = userInput.next();

            if (command.equals("quit")) {
                quit = true;
            } else {
                try {
                    userCommand(command);
                } catch (NumberFormatException e) {
                    System.out.println("Sorry, invalid input used.");
                }
            }
        }

        System.out.println("See you next time, traveller.");
    }

    // EFFECTS: shows the main screen with a list of commands
    private void mainScreen() {
        System.out.println("Welcome back, traveller. What can I do for you?");
        System.out.println("Type 'add' to add a character.");
        System.out.println("type 'remove' to remove a character.");
        System.out.println("Type 'view' to view all characters.");
        System.out.println("Type 'viewchar' to view a specific character.");
        System.out.println("Type 'life' to mark the death of a specific character, or resurrect them.");
        System.out.println("Type 'save' to save your character list.");
        System.out.println("Type 'load' to load your character list.");
        System.out.println("Type 'quit' to quit.");
    }

    // MODIFIES: this
    // EFFECTS: processes given command from the user
    // Disclaimer: function of this method learnt from Teller app
    private void userCommand(String command) throws NumberFormatException {
        if (command.equals("add")) {
            addCharacterScreen();
        } else if (command.equals("remove")) {
            removeCharacterScreen();
        } else if (command.equals("view")) {
            viewCharactersScreen();
        } else if (command.equals("viewchar")) {
            viewCharScreen();
        } else if (command.equals("life")) {
            lifeScreen();
        } else if (command.equals("save")) {
            saveCharacterList();
        } else if (command.equals("load")) {
            loadCharacterList();
        } else {
            System.out.println("Sorry, I didn't understand that. Sending you back to the main screen...");
        }
    }

    private void addCharacterScreen() throws NumberFormatException {
        addCharacterMainScreenText();
        handleName(userInput.next());
        handleStr(userInput.next());
        handleDex(userInput.next());
        handleCons(userInput.next());
        handleIntel(userInput.next());
        handleWis(userInput.next());
        handleCha(userInput.next());
        handleCla(userInput.next());
        handleDesc(userInput.next());
        addCharacterToList();
    }

    // EFFECTS: creates a new character with the add variables and adds it to CharacterList
    private void addCharacterToList() {
        Character addCharacter;
        addCharacter = new Character(addName, addStr, addDex, addCons, addIntel, addWis, addCha, addClass, addDesc);
        theList.addCharacter(addCharacter);
        System.out.println("Your character has successfully been added.");
    }

    // MODIFIES: this
    // EFFECTS: Handles a name based on input, gives user back confirmation of update.
    private void handleName(String s) {
        addName = s;
        System.out.println("Your character name has been set to " + s);
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleStr(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addStr = Integer.parseInt(s);
            System.out.println("Your character strength has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleDex(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addDex = Integer.parseInt(s);
            System.out.println("Your character dexterity has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleCons(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addCons = Integer.parseInt(s);
            System.out.println("Your character constitution has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleIntel(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addIntel = Integer.parseInt(s);
            System.out.println("Your character intelligence has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleWis(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addWis = Integer.parseInt(s);
            System.out.println("Your character wisdom has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleCha(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addCha = Integer.parseInt(s);
            System.out.println("Your character charisma has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles class based on input, gives user back confirmation of update.
    private void handleCla(String s) {
        addClass = s;
        System.out.println("Your character class has been set to " + s);
    }

    // MODIFIES: this
    // EFFECTS: Handles description based on input, gives user back confirmation of update.
    private void handleDesc(String s) {
        addDesc = s;
        System.out.println("Your character description has been set to: \n" + s);
    }

    // EFFECTS: Displays the text for the main screen of the add character screen
    private void addCharacterMainScreenText() {
        System.out.println("Please input, in this order: \n- Your character's name");
        System.out.println("As an integer, 3 to 18: \n\t- Strength\n\t- Dexterity\n\t- Constitution");
        System.out.println("\t- Intelligence\n\t- Wisdom\n\t- Charisma");
        System.out.println("- Your character's class, as a string (and one of the main D&D classes)");
        System.out.println("- A brief description of your character");
        System.out.println("Make sure to enter each input individually, pressing return after each.");
    }

    // EFFECTS: Displays the text for the remove character screen
    private void removeCharacterScreen() throws NumberFormatException {
        if (theList.getSize() == 0) {
            System.out.println("You have no characters in your list. Sending you back to the main screen...");
        } else {
            System.out.println("Which character do you want to remove?");
            System.out.println("Please enter the index (integer starting at 0) of which character you want to remove:");
            System.out.println(theList.viewCharacters());
            String input = userInput.next();
            if ((Integer.parseInt(input) > (theList.getSize() - 1)) || Integer.parseInt(input) < 0) {
                throw new NumberFormatException();
            } else {
                theList.removeCharacter(Integer.parseInt(input));
                System.out.println("Character successfully removed.");
            }
        }
    }

    // EFFECTS: Displays a list of your characters names, or says you have none
    private void viewCharactersScreen() {
        if (theList.getSize() == 0) {
            System.out.println("You have no characters in your list. Sending you back to the main screen...");
        } else {
            System.out.println("The characters in your tracker are:");
            System.out.println(theList.viewCharacters());
        }
    }

    // EFFECTS: displays the main screen for selecting a specific character to view.
    private void viewCharScreen() throws NumberFormatException {
        Character selectedCharacter;
        if (theList.getSize() == 0) {
            System.out.println("You have no characters in your list. Sending you back to the main screen...");
        } else {
            System.out.println("Please enter the index (integer starting at 0) of which character you want to view:");
            System.out.println(theList.viewCharacters());
            String input = userInput.next();
            if ((Integer.parseInt(input) > (theList.getSize() - 1)) || Integer.parseInt(input) < 0) {
                throw new NumberFormatException();
            } else {
                selectedCharacter = (theList.getCharacter(Integer.parseInt(input)));
                System.out.println(selectedCharacter.getName());
                System.out.println("Strength: " + selectedCharacter.getStr());
                System.out.println("Dexterity: " + selectedCharacter.getDex());
                System.out.println("Constitution: " + selectedCharacter.getCons());
                System.out.println("Intelligence: " + selectedCharacter.getIntel());
                System.out.println("Wisdom: " + selectedCharacter.getWis());
                System.out.println("Charisma: " + selectedCharacter.getCha());
                System.out.println("Class: " + selectedCharacter.getCla());
                viewCharScreenLife(selectedCharacter.getLife());
                System.out.println("Description: " + selectedCharacter.getDesc());
            }
        }
    }

    private void viewCharScreenLife(Boolean b) {
        if (b) {
            System.out.println("Alive");
        } else {
            System.out.println("Dead");
        }
    }

    // EFFECTS: displays the main screen for updating a character's life status.
    private void lifeScreen() throws NumberFormatException {
        Character selectedCharacter;
        if (theList.getSize() == 0) {
            System.out.println("You have no characters in your list. Sending you back to the main screen...");
        } else {
            System.out.println("Please enter the index (integer starting at 0) of the character to update:");
            System.out.println(theList.viewCharacters());
            String input = userInput.next();
            if ((Integer.parseInt(input) > (theList.getSize() - 1)) || Integer.parseInt(input) < 0) {
                throw new NumberFormatException();
            } else {
                selectedCharacter = (theList.getCharacter(Integer.parseInt(input)));
                selectedCharacter.updateLifeChar();
                System.out.println("Your character's life status has been updated.");
            }
        }
    }

    // EFFECTS: saves the workroom to file
    // Functionality from JsonSerializationDemo
    private void saveCharacterList() {
        try {
            jsonWriter.open();
            jsonWriter.write(theList);
            jsonWriter.close();
            System.out.println("Saved your list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    // Functionality from JsonSerializationDemo
    private void loadCharacterList() {
        try {
            theList = jsonReader.read();
            System.out.println("Loaded the list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}