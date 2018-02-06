package logic.attack;

import logic.GUnit;
import logic.OwnerHaving;

import java.util.Random;

public class AttackProps {
    static private Random r = new Random();
    //skill props
    private int minDamage;
    private int randDamage;
    private AttackTypes attackType;
    //attack props
    private OwnerHaving attacker;
    private GUnit aim;
    //hit props
    private int totalDamage = -1;

    public AttackProps(int minDamage, int randDamage) {
        this.minDamage = minDamage;
        this.randDamage = randDamage;
        this.attackType = AttackTypes.Physic;
    }

    public AttackProps generateHit() {
        totalDamage = minDamage;
        for (int i = 0; i < randDamage; i++) {
            if (r.nextBoolean()) {
                totalDamage++;
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "%s-%s%s",
                minDamage,
                minDamage + randDamage,
                totalDamage >= 0
                        ? String.format("(%s)", totalDamage)
                        : ""
        );
    }

    public void setAttacker(OwnerHaving attacker) {
        this.attacker = attacker;
    }

    public void setAim(GUnit aim) {
        this.aim = aim;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public OwnerHaving getAttacker() {
        return attacker;
    }

    public int reduceDamage(int reduceBy, AttackTypes attackType) {
        if (!this.attackType.equals(attackType)) {
            return 0;
        }
        int value = Math.min(totalDamage, reduceBy);
        totalDamage -= value;
        return value;
    }

    public AttackTypes getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackTypes attackType) {
        this.attackType = attackType;
    }
}
