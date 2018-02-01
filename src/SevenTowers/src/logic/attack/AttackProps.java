package logic.attack;

import logic.GUnit;

import java.util.Random;

public class AttackProps {
    static private Random r = new Random();
    private int minDamage;
    private int randDamage;
    private GUnit attacker;
    private GUnit aim;
    private int totalDamage = -1;

    public AttackProps(int minDamage, int randDamage) {
        this.minDamage = minDamage;
        this.randDamage = randDamage;
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

    public void setAttacker(GUnit attacker) {
        this.attacker = attacker;
    }

    public void setAim(GUnit aim) {
        this.aim = aim;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public GUnit getAttacker() {
        return attacker;
    }
}
