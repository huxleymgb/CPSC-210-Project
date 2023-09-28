package model;

import org.json.JSONObject;
import persistence.Writable;
import model.Event;
import model.EventLog;

// Represents a D&D character with name, 6 stats, class, life status, and description.
public class Character implements Writable {
    private String name;
    private int str; // 6 stats are strength, dexterity, constitution, intelligence, wisdom, charisma
    private int dex;
    private int cons;
    private int intel;
    private int wis;
    private int cha;
    private String cla;
    private boolean life;
    private String desc;

    // REQUIRES: Name has a non 0 length, class should be one of the standard D&D classes, integers are 3-18
    // MODIFIES: this
    // EFFECTS: creates a new character with the given attributes
    public Character(String n, int s, int d, int co, int i, int w, int ch, String cl, String de) {
        name = n;
        str = s;
        dex = d;
        cons = co;
        intel = i;
        wis = w;
        cha = ch;
        cla = cl;
        life = true;
        desc = de;
    }

    // MODIFIES: this
    // EFFECTS: sets character's life value to false if true, true if false.
    public void updateLifeChar() {
        life = !life;
    }

    public String getName() {
        return name;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getCons() {
        return cons;
    }

    public int getIntel() {
        return intel;
    }

    public int getWis() {
        return wis;
    }

    public int getCha() {
        return cha;
    }

    public String getCla() {
        return cla;
    }

    public Boolean getLife() {
        return life;
    }

    public String getDesc() {
        return desc;
    }

    // Functionality from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject jsonChar = new JSONObject();
        jsonChar.put("name", name);
        jsonChar.put("str", str);
        jsonChar.put("dex", dex);
        jsonChar.put("cons", cons);
        jsonChar.put("intel", intel);
        jsonChar.put("wis", wis);
        jsonChar.put("cha", cha);
        jsonChar.put("cla", cla);
        jsonChar.put("life", life);
        jsonChar.put("desc", desc);
        return jsonChar;
    }
}
