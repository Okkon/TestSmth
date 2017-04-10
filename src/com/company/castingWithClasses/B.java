package com.company.castingWithClasses;

/**
 * Created by olko1016 on 12/26/2016.
 */
public class B extends A {
    int int_field = 2;


    @Override
    public Integer m1(Number i) {
        return 2;
    }

//    public Number m2(Integer i2) {
//        return 2;
//    }

    /*@Override
    public Number m2() {
        return 1;

        //Вот так нельзя - расширили возвращаемый тип
    }*/

    /*@Override
    protected Integer m2() {
        return 1;
        // И так нельзя - сужение области видимости
    }*/
}
