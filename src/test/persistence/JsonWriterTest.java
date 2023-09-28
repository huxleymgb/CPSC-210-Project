package persistence;

import model.CharacterList;
import model.Character;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Functionality from JsonSerializationDemo
// I.e. how to design tests for a JsonWriter
public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            CharacterList cl = new CharacterList();
            JsonWriter writer = new JsonWriter("./data/my\0IllegalFileName");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Intended behaviour since you shouldn't be able to make a file with this name
        }
    }

    // This test functionality and design from JsonSerializationDemo
    @Test
    void testWriterEmptyCharacterList() {
        try {
            CharacterList cl = new CharacterList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            cl = reader.read();
            assertEquals(0, cl.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // This test functionality and design from JsonSerializationDemo
    @Test
    void testWriterGeneralCharacterList() {
        try {
            CharacterList cl = new CharacterList();
            cl.addCharacter(new Character("a", 3, 3, 3, 3, 3, 3,"b", "c"));
            cl.addCharacter(new Character("x", 3, 3, 3, 3, 3, 3,"y", "z"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneral.json");
            cl = reader.read();
            List<Character> characters = cl.getCharacters();
            assertEquals(2, characters.size());
            checkChar("a", 3, 3, 3, 3, 3, 3,"b", "c", characters.get(0));
            checkChar("x", 3, 3, 3, 3, 3, 3,"y", "z", characters.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
