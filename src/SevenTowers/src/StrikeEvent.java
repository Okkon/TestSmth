public class StrikeEvent extends AbstractEvent {
    private final GUnit actor;
    private final PlaceHaving aim;
    private final AttackProps attackProps;

    public <T extends PlaceHaving> StrikeEvent(GUnit actor, T aim, AttackProps attackProps) {
        this.actor = actor;
        this.aim = aim;
        this.attackProps = attackProps;
    }

    @Override
    protected void perform() {
        if (aim instanceof GUnit) {
            GUnit unit = (GUnit) aim;
            new UnitHitEvent(unit, attackProps.generateHit()).process();
        }
    }
}
