import java.awt.*;

public class Console {

    private final Canvas CANVAS;
    private final DisplayFrame DISPLAY_FRAME;

    public Console(int canvasRows, int canvasColumns, Dimension tileDimension) {
        CANVAS = new Canvas(canvasRows, canvasColumns, tileDimension);
        DISPLAY_FRAME = new DisplayFrame();
        refresh();
    }

    public void clearScreen() {
        CANVAS.clearTiles();
    }

    public void refresh() {
        CANVAS.paintCanvas();
        DISPLAY_FRAME.getPanel().setImage(CANVAS.getImage());
        DISPLAY_FRAME.getPanel().repaint();
    }

    public void setDefaultColors(Color background, Color foreground) {
        CANVAS.setDefaultBackgroundColor(background);
        CANVAS.setDefaultForegroundColor(foreground);
    }

    public void update(int row, int col, char symbol) {
        update(row, col, CANVAS.getDefaultForegroundColor(), symbol);
    }
    public void update(int row, int col, Color foreground, char symbol) {
        update(row, col, CANVAS.getDefaultBackgroundColor(), foreground, symbol);
    }
    public void update(int row, int col, Color background, Color foreground, char symbol) {
        CANVAS.setTile(row, col, new ASCIITile(background, foreground, symbol));
    }
}
