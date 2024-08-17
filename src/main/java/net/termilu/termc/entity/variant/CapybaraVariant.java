package net.termilu.termc.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum CapybaraVariant {
    //Variant enums
    BROWN(0),
    WHITE(1);

    private static final CapybaraVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            CapybaraVariant::getId)).toArray(CapybaraVariant[]::new);
    private final int id;

    CapybaraVariant(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public static CapybaraVariant byID(int id){
        return BY_ID[id % BY_ID.length];
    }


}
