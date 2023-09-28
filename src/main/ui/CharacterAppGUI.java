package ui;

import model.Character;
import model.CharacterList;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.*;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;

// D&D Character Application
// Functionality from TellerApp, JsonSerializationDemo
public class CharacterAppGUI {
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
    private static final String JSON_STORE = "./data/characterListGUI.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final Icon addIcon = new ImageIcon("./data/images/addLogo.png");
    private final Icon removeIcon = new ImageIcon("./data/images/removeLogo.png");
    private final Icon updateIcon = new ImageIcon("./data/images/updateLogo.png");
    private final Icon saveIcon = new ImageIcon("./data/images/saveLogo.png");
    private final Icon loadIcon = new ImageIcon("./data/images/loadLogo.png");
    private final Icon quitIcon = new ImageIcon("./data/images/quitLogo.png");
    private final JButton addButton = new JButton("Add a character", addIcon);
    private final JButton removeButton = new JButton("Remove a character", removeIcon);
    private final JButton updateViewButton = new JButton("Update the Character Viewer", updateIcon);
    private final JButton removeSubmitButton = new JButton("Submit the character for removal", removeIcon);
    private final JButton saveButton = new JButton("Save your character list", saveIcon);
    private final JButton loadButton = new JButton("Load your character list", loadIcon);
    private final JButton quitButton = new JButton("Quit the program", quitIcon);
    private final JTextArea textArea = new JTextArea(20, 50);
    private final JTextField inTextFieldAdd = new JTextField();
    private JScrollPane scrollPane;
    private final JTextArea characterViewer = new JTextArea();
    private JScrollPane scrollPane2;
    private JComboBox<String> removeChooser;
    private final Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
    private String command;
    private int addTextFieldSwitch = -1;
    private final Color characterViewerBackgroundColor = new Color(220, 120, 120);
    private final Color buttonColor = new Color(120, 150, 220);
    //private final Window theWindow = new Window(frame);

    // EFFECTS: sets up the character application
    public CharacterAppGUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setupButtons();
        setupAddTextField();
        setupTextArea();
        setupCharacterViewer();
        setupFrame();
        mainScreen();
    }

    // MODIFIES: this
    // EFFECTS: sets up the frame, also listening to window
    private void setupFrame() {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("D&D Character Tracker");
        frame.setIconImage(defaultToolkit.getImage("./data/images/logo.png"));
        frame.add(panel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.EAST);
        frame.add(scrollPane2, BorderLayout.CENTER);
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
            }
        });
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: sets up text area to display user prompts
    private void setupTextArea() {
        scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setAutoscrolls(true);
        scrollPane.setAutoscrolls(true);
        textArea.setBorder(BorderFactory.createTitledBorder(border, "Log"));

    }

    // MODIFIES: this
    // EFFECTS: sets up panel where character names are viewed
    private void setupCharacterViewer() {
        scrollPane2 = new JScrollPane(characterViewer);
        characterViewer.setEditable(false);
        characterViewer.setAutoscrolls(true);
        characterViewer.setBackground(characterViewerBackgroundColor);
        characterViewer.setBorder(BorderFactory.createTitledBorder(border, "Character Viewer"));
        characterViewer.append("These are the characters currently in your tracker:");
        characterViewer.append("\n");
    }

    // MODIFIES: this
    // EFFECTS: adds buttons as action listeners
    private void setupButtons() {
        panel.setBorder(BorderFactory.createTitledBorder(border,"Buttons"));
        panel.setLayout(new GridLayout(6, 1));
        addButton.addActionListener(e -> handleAddCharacterGraphics());
        removeButton.addActionListener(e -> handleRemoveCharacterSafe());
        updateViewButton.addActionListener(e -> updateCharacterViewer());
        saveButton.addActionListener(e -> saveCharacterList());
        loadButton.addActionListener(e -> loadCharacterList());
        quitButton.addActionListener(e -> {
            for (Event event : EventLog.getInstance()) {
                System.out.println(event.toString());
            }
            frame.dispose();
        });
        removeSubmitButton.addActionListener(e -> {
            theList.removeCharacter(removeChooser.getSelectedIndex());
            logString("Character successfully removed.");
            panel.remove(removeSubmitButton);
            frame.remove(removeChooser);
            showButtonsAgain();
            frame.pack();
        });
        setupPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel GUI
    private void setupPanel() {
        panel.setBackground(buttonColor);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(updateViewButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(quitButton);
    }

    // MODIFIES: this
    // EFFECTS: hides all buttons except for quit from the user
    private void hideButtonsExceptQuit() {
        panel.remove(addButton);
        panel.remove(removeButton);
        panel.remove(updateViewButton);
        panel.remove(saveButton);
        panel.remove(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: shows all buttons again
    private void showButtonsAgain() {
        panel.remove(quitButton);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(updateViewButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(quitButton);
        frame.pack();
    }

    // MODIFIES: this
    // EFFECTS: logs the given message to the text area
    public void logString(String s) {
        textArea.append(s);
        textArea.append("\n");
    }

    // MODIFIES: this
    // EFFECTS: logs the given string to the character viewer
    public void logStringCharacterViewer(String s) {
        characterViewer.append(s);
        characterViewer.append("\n");
    }

    // REQUIRES: c is not null
    // EFFECTS: logs the character to the Character Viewer text area
    public void logCharacter(Character c) {
        logStringCharacterViewer(c.getName());
        logStringCharacterViewer("Strength: " + c.getStr());
        logStringCharacterViewer("Dexterity: " + c.getDex());
        logStringCharacterViewer("Constitution: " + c.getCons());
        logStringCharacterViewer("Intelligence: " + c.getIntel());
        logStringCharacterViewer("Wisdom: " + c.getWis());
        logStringCharacterViewer("Charisma: " + c.getCha());
        logStringCharacterViewer("Class: " + c.getCla());
        logStringCharacterViewer("Description: " + c.getDesc());
        logStringCharacterViewer("\n");
    }

    // MODIFIES: this
    // EFFECTS: prints out the list of characters to the character viewer
    public void updateCharacterViewer() {
        characterViewer.setText("These are the characters currently in your tracker:\n");
        for (Character c : theList.getCharacters()) {
            logCharacter(c);
        }
        logString("Updated.");
    }

    // EFFECTS: shows the main screen prompts
    private void mainScreen() {
        logString("Greetings, traveller. What can I do for you?");
        logString("Press any button to start.");
    }

    // MODIFIES: this
    // EFFECTS: sets up text field to be written in
    private void setupAddTextField() {
        inTextFieldAdd.setBorder(BorderFactory.createTitledBorder("Enter your input here"));
        inTextFieldAdd.addActionListener(e -> {
            addTextFieldSwitch++;
            command = inTextFieldAdd.getText();
            inTextFieldAdd.setText("");
            try {
                addSwitch();
            } catch (NumberFormatException nfe) {
                logString("Sorry, invalid input used.");
                addTextFieldSwitch = -1;
                frame.remove(inTextFieldAdd);
                showButtonsAgain();
            }
        });
    }

    // EFFECTS: switches between commands to iterate through and prompt the user for input. adds char to list at end
    private void addSwitch() {
        if (addTextFieldSwitch == 0) {
            handleName(command);
        } else if (addTextFieldSwitch == 1) {
            handleStr(command);
        } else if (addTextFieldSwitch == 2) {
            handleDex(command);
        } else if (addTextFieldSwitch == 3) {
            handleCons(command);
        } else if (addTextFieldSwitch == 4) {
            handleIntel(command);
        } else if (addTextFieldSwitch == 5) {
            handleWis(command);
        } else if (addTextFieldSwitch == 6) {
            handleCha(command);
        } else if (addTextFieldSwitch == 7) {
            handleCla(command);
        } else {
            frame.remove(inTextFieldAdd);
            handleDesc(command);
            addCharacterToList();
            showButtonsAgain();
            addTextFieldSwitch = -1;
        }
    }

    // MODIFIES: this
    // EFFECTS: Puts the screen in "adding" mode (no buttons except quit, text bar)
    private void handleAddCharacterGraphics() {
        frame.add(inTextFieldAdd, BorderLayout.AFTER_LAST_LINE);
        frame.pack();
        hideButtonsExceptQuit();
        addCharacterMainScreenText();
    }

    // MODIFIES: this
    // EFFECTS: creates a new character with the add variables and adds it to CharacterList
    private void addCharacterToList() {
        Character addCharacter;
        addCharacter = new Character(addName, addStr, addDex, addCons, addIntel, addWis, addCha, addClass, addDesc);
        theList.addCharacter(addCharacter);
        logString("Your character has successfully been added.");
    }

    // MODIFIES: this
    // EFFECTS: Handles a name based on input, gives user back confirmation of update.
    private void handleName(String s) {
        addName = s;
        logString("Your character name has been set to " + s);
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleStr(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addStr = Integer.parseInt(s);
            logString("Your character strength has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleDex(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addDex = Integer.parseInt(s);
            logString("Your character dexterity has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleCons(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addCons = Integer.parseInt(s);
            logString("Your character constitution has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleIntel(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addIntel = Integer.parseInt(s);
            logString("Your character intelligence has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleWis(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addWis = Integer.parseInt(s);
            logString("Your character wisdom has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles stat based on input, gives user back confirmation of update.
    private void handleCha(String s) throws NumberFormatException {
        if ((Integer.parseInt(s) > 18) || (Integer.parseInt(s) < 3)) {
            throw new NumberFormatException();
        } else {
            addCha = Integer.parseInt(s);
            logString("Your character charisma has been set to " + s);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles class based on input, gives user back confirmation of update.
    private void handleCla(String s) {
        addClass = s;
        logString("Your character class has been set to " + s);
    }

    // MODIFIES: this
    // EFFECTS: Handles description based on input, gives user back confirmation of update.
    private void handleDesc(String s) {
        addDesc = s;
        logString("Your character description has been set to: \n" + s);
    }

    // EFFECTS: Displays the text for the main screen of the add character screen
    private void addCharacterMainScreenText() {
        logString("Please input, in this order: ");
        logString("- Your character's name");
        logString("As an integer, 3 to 18:");
        logString("   * Strength");
        logString("   * Dexterity");
        logString("   * Constitution");
        logString("   * Intelligence");
        logString("   * Wisdom");
        logString("   * Charisma");
        logString("- Your character's class, as a string (and one of the main D&D classes)");
        logString("- A brief description of your character");
        logString("Make sure to enter each input individually, pressing return after each.");
    }

    // MODIFIES: this
    // EFFECTS: Runs removeCharacterScreen with exception handling
    private void handleRemoveCharacterSafe() {
        try {
            handleRemoveCharacter();
        } catch (NumberFormatException exception) {
            logString("Sorry, invalid input used.");
            frame.remove(removeChooser);
            showButtonsAgain();
        }
    }

    // MODIFIES: this
    // EFFECTS: Puts the screen in "remove" mode (no buttons except quit, slider)
    private void handleRemoveCharacterGraphics() {
        removeChooser = new JComboBox<>(theList.viewCharacters().toArray(new String[0]));
        removeChooser.setBorder(BorderFactory.createTitledBorder("Choose here, then press submit when done."));
        frame.add(removeChooser, BorderLayout.PAGE_END);
        panel.add(removeSubmitButton);
        frame.pack();
        hideButtonsExceptQuit();
    }

    // EFFECTS: Displays the text for the remove character screen
    private void handleRemoveCharacter() throws NumberFormatException {
        if (theList.getSize() == 0) {
            logString("You have no characters in your list.");
        } else {
            handleRemoveCharacterGraphics();
            logString("Which character do you want to remove?");
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the workroom to file
    // Functionality from JsonSerializationDemo
    private void saveCharacterList() {
        try {
            jsonWriter.open();
            jsonWriter.write(theList);
            jsonWriter.close();
            logString("Saved your list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            logString("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    // Functionality from JsonSerializationDemo
    private void loadCharacterList() {
        try {
            theList = jsonReader.read();
            logString("Loaded the list from " + JSON_STORE);
        } catch (IOException e) {
            logString("Unable to read from file: " + JSON_STORE);
        }
    }
}

