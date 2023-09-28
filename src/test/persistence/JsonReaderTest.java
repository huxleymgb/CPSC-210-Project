package persistence;

import model.CharacterList;
import model.Character;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Functionality from JsonSerializationDemo
// I.e. the JsonTest framework and the different types of files to test
public class JsonReaderTest extends JsonTest{

    @Test
    void TestReaderFileNotThere() {
        JsonReader reader = new JsonReader("./data/thisFileDoesNotExist");
        try {
            CharacterList cl = reader.read();
            fail("Expected IO exception to throw but did not.");
        } catch (IOException e) {
            // Intended behaviour since the file does not exist
        }
    }

    @Test
    void testReaderEmptyCharacterList() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            CharacterList cl = reader.read();
            assertEquals(0, cl.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCharacterList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
        try {
            CharacterList cl = reader.read();
            List<Character> characters = cl.getCharacters();
            assertEquals(3, characters.size());
            checkChar("Tobs", 18, 18, 18, 18, 18, 18, "Fighter", "Dog", characters.get(0));
            checkChar("Pirate", 10, 10, 10, 10, 10, 10, "Rogue", "Arr!", characters.get(1));
            checkChar("Bob", 10, 10, 10, 10, 10, 10, "Barbarian", "Bob", characters.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
