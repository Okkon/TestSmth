package com.company.fxapp.specific;

public class UnitTypeFactory {
    private static UnitTypeFactory INSTANCE = new UnitTypeFactory();

    private UnitTypeFactory() {
    }

    public static UnitTypeFactory getInstance() {
        return INSTANCE;
    }

    enum types {Horse, Archer, Footman, Crusher}


}
