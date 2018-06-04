package logic;

import logic.mods.Armor;
import logic.skills.AttackSkill;
import logic.skills.EndTurnSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BaseTypes {
    Footman(
            "Footman",
            6,
            21,
            Arrays.asList(new AttackSkill(2, 2), EndTurnSkill.getInstance()),
            new Armor(2)
    ),
    Inquisitor(
            "Inquisitor",
            5,
            26,
            Arrays.asList(new AttackSkill(2, 2), EndTurnSkill.getInstance())
    ),
    Archer(
            "Archer",
            2,
            21,
            Arrays.asList(new AttackSkill(1, 1), EndTurnSkill.getInstance())
    ),
    Mage(
            "Mage",
            1,
            21,
            Arrays.asList(new AttackSkill(0, 2), EndTurnSkill.getInstance())
    ),
    Assassin(
            "Assassin",
            4,
            31,
            Arrays.asList(new AttackSkill(1, 3), EndTurnSkill.getInstance())
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
