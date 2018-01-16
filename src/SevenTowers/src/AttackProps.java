import java.util.Random;

public class AttackProps {
    static private Random r = new Random();
    private int minDamage;
    private int randDamage;

    public HitDamage generateHit() {
        int totalDamage = minDamage;
        for (int i = 0; i < randDamage; i++) {
            if (r.nextBoolean()) {
                totalDamage++;
            }
        }
        return new HitDamage(totalDamage, this);
    }
}
