
public class UnitHitEvent extends AbstractEvent {
    private final GUnit aim;
    private HitDamage hitDamage;

    public UnitHitEvent(GUnit aim, HitDamage hitDamage) {
        this.aim = aim;
        this.hitDamage = hitDamage;
    }

    @Override
    protected void perform() {
        aim.loseHp(hitDamage);
    }
}
