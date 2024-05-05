package Enums;

import java.awt.Color;

/**
 * An enumeration of custom colors with associated hexadecimal or RGBA values.
 */
public enum ColorEnum {
    /**
     * Special color defined by ATHS.
     */
    ATHS_SPECIAL(0xeaebd0),

    /**
     * Asparagus color for board.
     */
    ASPARAGUS(0x739452),

    /**
     * Dune color for background.
     */
    DUNE(0x302e2b),

    /**
     * Wild Willow color.
     */
    WILD_WILLOW(0xB9CA70),

    /**
     * Selected White color with alpha.
     */
    SELECTED_WHITE(255, 255, 0, 150),

    /**
     * Mindaro color.
     */
    MINDARO(0xF5F682);

    private final Color color;

    /**
     * Constructor for colors specified by hexadecimal value.
     *
     * @param hexValue The hexadecimal value of the color.
     */
    ColorEnum(int hexValue) {
        this.color = new Color(hexValue);
    };

    /**
     * Constructor for colors specified by RGBA values.
     *
     * @param r The red component of the color.
     * @param g The green component of the color.
     * @param b The blue component of the color.
     * @param a The alpha component of the color.
     */
    ColorEnum(int r, int g, int b, int a) {
        this.color = new Color(r, g, b, a);
    }

    /**
     * Get the color associated with this enumeration constant.
     *
     * @return The Color object representing this color.
     */
    public Color getColor() {
        return this.color;
    };
};
