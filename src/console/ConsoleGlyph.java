package console;

import java.awt.*;

/**
 * Wraps all fields required to be drawn on the console.
 */
public class ConsoleGlyph {
    final Color BG;
    final Color FG;
    final char SYM;

    /**
     * @param bg specify the background color of this glyph. null uses canvas default
     * @param fg specify the foreground color of this glyph. null uses canvas default
     * @param sym specify the symbol for this glyph. must not be null
     */
    public ConsoleGlyph(Color bg, Color fg, char sym) {
        BG = bg;
        FG = fg;
        SYM = sym;
    }
}
