package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Tests for the character class
class CharacterTest {
    private Character testCharacter;

    @BeforeEach
    void setup() {
        testCharacter = new Character("Name", 10, 10, 10, 10, 10, 10, "Fighter",
                "test description");
    }

    @Test
    public void constructorTest() {
        assertEquals(testCharacter.getName(), "Name");
        assertEquals(testCharacter.getStr(), 10);
        assertEquals(testCharacter.getDex(), 10);
        assertEquals(testCharacter.getCons(), 10);
        assertEquals(testCharacter.getIntel(), 10);
        assertEquals(testCharacter.getWis(), 10);
        assertEquals(testCharacter.getCha(), 10);
        assertEquals(testCharacter.getCla(), "Fighter");
        assertTrue(testCharacter.getLife());
        assertEquals(testCharacter.getDesc(), "test description");
    }

    @Test
    public void updateLifeCharTest() {
        assertTrue(testCharacter.getLife());
        testCharacter.updateLifeChar();
        assertFalse(testCharacter.getLife());
    }

    @Test
    public void updateLifeCharMultipleTest() {
        assertTrue(testCharacter.getLife());
        testCharacter.updateLifeChar();
        assertFalse(testCharacter.getLife());
        testCharacter.updateLifeChar();
        assertTrue(testCharacter.getLife());
    }


}