package persistence;

import model.CharacterList;
import org.json.JSONObject;
import java.io.*;

// Represents a class that writes your program's current state to a JSON file
// Functionality and methods from JsonSerializationDemo
public class JsonWriter {
    private static final int INDENT = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer with a destination file
    public JsonWriter(String d) {
        destination = d;
    }

    // MODIFIES: this
    // EFFECTS: opens a new writer unless there is no destination / destination cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON character list to the file with indent specified
    public void write(CharacterList c) {
        JSONObject json = c.toJson();
        saveToFile(json.toString(INDENT));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
