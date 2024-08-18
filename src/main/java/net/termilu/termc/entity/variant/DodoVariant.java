package net.termilu.termc.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum DodoVariant {
    //Variant enums
    BLUE(0),
    BLACK(1);

    private static final DodoVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            DodoVariant::getId)).toArray(DodoVariant[]::new);
    private final int id;

    DodoVariant(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public static DodoVariant byID(int id){
        return BY_ID[id % BY_ID.length];
    }


}
