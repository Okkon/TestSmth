package logic.skills;

import logic.ClassFilter;
import logic.IsNearFilter;
import logic.PlaceHaving;
import logic.Skill;
import logic.attack.AttackProps;
import logic.events.AttackEvent;

public class AttackSkill extends Skill {
    private final AttackProps attackProps;

    public AttackSkill(int minDamage, int randDamage) {
        attackProps = new AttackProps(minDamage, randDamage);
    }

    @Override
    public void init() {
        addAimFilter("Where to attack", ClassFilter.getInstance(PlaceHaving.class), IsNearFilter.getInstance());
    }

    @Override
    protected void setUpFilters() {
        IsNearFilter.getInstance().setPlace(getActor());
    }

    @Override
    public void doAction() {
        new AttackEvent(getActor(), getAim(), attackProps).process();
    }
}
