package persistence;

import model.Character;

import static org.junit.jupiter.api.Assertions.*;

// Functionality from JsonSerializationDemo
public class JsonTest {
    protected void checkChar(String n, int s, int d, int co, int i, int w, int ch, String cl, String de, Character c) {
        assertEquals(n, c.getName());
        assertEquals(s, c.getStr());
        assertEquals(d, c.getDex());
        assertEquals(co, c.getCons());
        assertEquals(i, c.getIntel());
        assertEquals(w, c.getWis());
        assertEquals(ch, c.getCha());
        assertEquals(cl, c.getCla());
        assertEquals(de, c.getDesc());

    }
}
