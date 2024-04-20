package Enums;

import java.awt.Color;

public enum ColorEnum {
    ATHS_SPECIAL(0xeaebd0),
    ASPARAGUS(0x739452),
    DUNE(0x302e2b);

    private final Color color;

    ColorEnum(int hexValue) {
        this.color = new Color(hexValue);
    };

    public Color getColor() {
        return this.color;
    };
};