package logic;

public class HitDamage {
    private final int totalDamage;
    private final AttackProps attackProps;

    public HitDamage(int totalDamage, AttackProps attackProps) {
        this.totalDamage = totalDamage;
        this.attackProps = attackProps;
    }

    public int getTotalDamage() {
        return totalDamage;
    }
}
