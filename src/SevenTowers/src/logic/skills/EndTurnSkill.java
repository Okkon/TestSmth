package logic.skills;

import logic.Skill;


public class EndTurnSkill extends Skill {
    private static EndTurnSkill Instance = new EndTurnSkill();

    private EndTurnSkill() {
    }

    ;

    public static EndTurnSkill getInstance() {
        return Instance;
    }

    @Override
    public void init() {
        //do nothing
    }

    @Override
    public void doAction() {
        //DO nothing, ends unit turn
    }
}
