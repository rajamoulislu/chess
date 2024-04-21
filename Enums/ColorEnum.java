package Enums;

import java.awt.Color;

public enum ColorEnum {
    ATHS_SPECIAL(0xeaebd0),
    ASPARAGUS(0x739452),//board
    DUNE(0x302e2b),//Background
    WILD_WILLOW(0xB9CA70),
    SELECTED_WHITE(255, 255, 0, 150),
    MINDARO(0x9CCC65);

    private final Color color;

    ColorEnum(int hexValue) {
        this.color = new Color(hexValue);
    };

    ColorEnum(int r, int g, int b, int a) {
        this.color = new Color(r, g, b, a);
    }

    public Color getColor() {
        return this.color;
    };
};