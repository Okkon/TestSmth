package logic.skills;

import logic.IsNearFilter;
import logic.ShiftObjectEvent;
import logic.Skill;
import tools.PEConst;

public class MoveSkill extends Skill {
    private static MoveSkill INSTANCE = new MoveSkill();

    private MoveSkill() {
    }

    ;

    public static MoveSkill getInstance() {
        return INSTANCE;
    }

    @Override
    public void init() {
        addAimFilter("Where to move", PEConst.CELL_FILTER, IsNearFilter.getInstance());
    }

    @Override
    protected void setUpFilters() {
        IsNearFilter.getInstance().setPlace(getActor());
    }

    @Override
    public void doAction() {
        new ShiftObjectEvent(getActor(), getAim()).process();
    }
}
