package model;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// Test for the characterList class
public class CharacterListTest {

    private CharacterList testCharacters = new CharacterList();
    private Character c1 = new Character("1", 10, 10, 10, 10, 10, 10, "Fighter", "d1");
    private Character c2 = new Character("2", 10, 10, 10, 10, 10, 10, "Fighter", "d2");
    private Character c3 = new Character("3", 10, 10, 10, 10, 10, 10, "Fighter", "d3");

    @BeforeEach
    void setup() {
        testCharacters.addCharacter(c1);
        testCharacters.addCharacter(c2);
    }

    @Test
    public void addCharacterTest() {
        assertEquals(2, testCharacters.getSize());
        testCharacters.addCharacter(c3);
        assertEquals(3, testCharacters.getSize());
    }

    @Test
    public void addSameCharacterTest() {
        assertEquals(2, testCharacters.getSize());
        testCharacters.addCharacter(c2);
        // App should allow the same character as many times as user desires.
        assertEquals(3, testCharacters.getSize());
        testCharacters.addCharacter(c1);
        assertEquals(4, testCharacters.getSize());
    }

    @Test
    public void removeCharacterTest(){
        assertEquals(2, testCharacters.getSize());
        testCharacters.removeCharacter(1);
        assertEquals(1, testCharacters.getSize());
        testCharacters.removeCharacter(0);
        assertEquals(0, testCharacters.getSize());
        // don't have to test further, since method requires list not to be empty / index not to be outside the list
    }

    @Test
    public void viewCharactersNoCharactersTest(){
        testCharacters.removeCharacter(0);
        testCharacters.removeCharacter(0);
        assertEquals(testCharacters.viewCharacters(), Collections.EMPTY_LIST);
    }

    @Test
    public void viewCharactersTest(){
        ArrayList<String> testStringList = new ArrayList<>();
        testStringList.add("1");
        testStringList.add("2");
        assertEquals(testCharacters.viewCharacters(), testStringList);

    }

    @Test
    public void getCharacterTest(){
        assertEquals(testCharacters.getCharacter(0), c1);
        assertEquals(testCharacters.getCharacter(1), c2);
    }

    @Test
    public void getCharactersTest(){
        List<Character> testList = new ArrayList<>();
        testList.add(c1);
        testList.add(c2);
        assertEquals(testList, testCharacters.getCharacters());
    }

    @Test
    public void getCharactersNoneTest(){
        List<Character> testList = Collections.EMPTY_LIST;
        testCharacters.removeCharacter(0);
        testCharacters.removeCharacter(0);
        assertEquals(testList, testCharacters.getCharacters());
    }

    @Test
    public void addCharacterLogTest(){
        EventLog.getInstance().clear();
        int logContains = 0;
        testCharacters.addCharacter(c1);
        for (Event event: EventLog.getInstance()) {
            if (Objects.equals(event.getDescription(), "Added a new character to list.")) {
                logContains++;
            }
        }
        assertEquals(logContains, 1);
    }

    @Test
    public void addMultipleCharactersLogTest(){
        EventLog.getInstance().clear();
        int logContains = 0;
        testCharacters.addCharacter(c1);
        testCharacters.addCharacter(c2);
        for (Event event: EventLog.getInstance()) {
            if (Objects.equals(event.getDescription(), "Added a new character to list.")) {
                logContains++;
            }
        }
        assertEquals(logContains, 2);
    }

    @Test
    public void removeCharacterLogTest(){
        EventLog.getInstance().clear();
        int logContains = 0;
        testCharacters.addCharacter(c1);
        testCharacters.removeCharacter(0);
        for (Event event: EventLog.getInstance()) {
            if (Objects.equals(event.getDescription(), "Removed a character from list.")) {
                logContains++;
            }
        }
        assertEquals(logContains, 1);
    }

    @Test
    public void removeMultipleCharactersLogTest(){
        EventLog.getInstance().clear();
        int logContains = 0;
        testCharacters.addCharacter(c1);
        testCharacters.addCharacter(c2);
        testCharacters.removeCharacter(0);
        testCharacters.removeCharacter(0);
        for (Event event: EventLog.getInstance()) {
            if (Objects.equals(event.getDescription(), "Removed a character from list.")) {
                logContains++;
            }
        }
        assertEquals(logContains, 2);
    }
}
