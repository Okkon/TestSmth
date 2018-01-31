package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег on 31.01.2018.
 */
public enum BaseTypes {
    Footman(
            "Footman",
            6,
            21,
            new ArrayList<>()
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
        this.unitType = new UnitType(typeName, mp, hp, skills);
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
