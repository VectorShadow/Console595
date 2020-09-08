import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCIITile extends ConsoleTile {

    private final Color BACKGROUND_COLOR;
    private final Color FOREGROUND_COLOR;
    private final char SYMBOL;

    public ASCIITile(Color backgroundColor, Color foregroundColor, char symbol) {
        BACKGROUND_COLOR = backgroundColor;
        FOREGROUND_COLOR = foregroundColor;
        SYMBOL = symbol;
    }

    @Override
    public BufferedImage paint() {
        Renderer.updateLabel(BACKGROUND_COLOR, FOREGROUND_COLOR, SYMBOL);
        return Renderer.paint();
    }
}
