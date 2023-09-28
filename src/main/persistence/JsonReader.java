package persistence;

import model.Character;
import model.CharacterList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads character list from given file
// Functionality and methods from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: creates a new reader, reads from source string
    public JsonReader(String s) {
        source = s;
    }

    // EFFECTS: reads character list from file and returns it, throws IOException if an error occurs
    public CharacterList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCharacterList(jsonObject);
    }

    // EFFECTS: reads source file and returns it as a string, throws IOException if an error occurs
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses character list from JSON and returns it
    private CharacterList parseCharacterList(JSONObject jsonObject) {
        CharacterList characterList = new CharacterList();
        addChars(characterList, jsonObject);
        return characterList;
    }

    // MODIFIES: chrList
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addChars(CharacterList c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("characters");
        for (Object json : jsonArray) {
            JSONObject nextChar = (JSONObject) json;
            addChar(c, nextChar);
        }
    }

    // MODIFIES: chrList
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addChar(CharacterList characterList, JSONObject jsonObject) {
        String n = jsonObject.getString("name");
        int s = jsonObject.getInt("str");
        int d = jsonObject.getInt("dex");
        int co = jsonObject.getInt("cons");
        int i = jsonObject.getInt("intel");
        int w = jsonObject.getInt("wis");
        int ch = jsonObject.getInt("cha");
        String cl = jsonObject.getString("cla");
        String de = jsonObject.getString("desc");
        Character chara = new Character(n, s, d, co, i, w, ch, cl, de);
        characterList.addCharacter(chara);
    }
}
