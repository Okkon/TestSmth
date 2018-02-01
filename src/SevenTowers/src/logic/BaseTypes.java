package logic;

import logic.mods.Armor;

import java.util.ArrayList;
import java.util.List;

public enum BaseTypes {
    Footman(
            "Footman",
            6,
            21,
            new ArrayList<>(),
            new Armor(2)
    ),
    Inquisitor(
            "Inquisitor",
            5,
            26,
            new ArrayList<>()
    ),
    Archer(
            "Archer",
            2,
            21,
            new ArrayList<>()
    ),
    Mage(
            "Mage",
            1,
            21,
            new ArrayList<>()
    ),
    Assassin(
            "Assassin",
            4,
            31,
            new ArrayList<>()
    );

    private UnitType unitType;

    BaseTypes(String typeName, int hp, int mp, List<Skill> skills, GMod... mods) {
        this.unitType = new UnitType(typeName, mp, hp, skills, mods);
    }

    public static List<UnitType> getTypes() {
        List<UnitType> types = new ArrayList<>();
        for (BaseTypes value : values()) {
            types.add(value.getType());
        }
        return types;
    }

    public UnitType getType() {
        return unitType;
    }
}
