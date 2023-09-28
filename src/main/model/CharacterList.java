package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a list of the characters that this tracker contains.
public class CharacterList implements Writable {

    private ArrayList<Character> characters;

    public CharacterList() {
        characters = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new character to the list.
    public void addCharacter(Character c) {
        characters.add(c);
        EventLog.getInstance().logEvent(new Event("Added a new character to list."));
    }

    // REQUIRES: Integer i is within the index of characters. Characters isn't empty.
    // MODIFIES: this
    // EFFECTS: removes the character at the given index.
    public void removeCharacter(int i) {
        characters.remove(characters.get(i));
        EventLog.getInstance().logEvent(new Event("Removed a character from list."));
    }

    // EFFECTS: prints a list of all character names
    public ArrayList<String> viewCharacters() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (Character c : characters) {
            stringArrayList.add(c.getName());
        }
        return stringArrayList;
    }

    public int getSize() {
        return characters.size();
    }

    // REQUIRES: Integer i is within the index of characters
    // EFFECTS: returns the character at the given index
    public Character getCharacter(int i) {
        return characters.get(i);
    }

    // EFFECTS: return the full list of characters in a List<Character> form
    public List<Character> getCharacters() {
        return new ArrayList<>(characters);
    }

    // Functionality from JsonSerializationDemo.
    @Override
    public JSONObject toJson() {
        JSONObject jsonList = new JSONObject();
        jsonList.put("characters", charactersToJson());
        return jsonList;
    }

    // EFFECTS: returns characters in list as a JSON array
    // Functionality from JsonSerializationDemo
    private JSONArray charactersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Character c : characters) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
