import java.awt.*;

/**
 * Console provides a GUI interface on top of swing.
 * It uses a Canvas object to render an image from selected console tiles, then paints that image
 * onto the DisplayFrame.
 */
public class Console {

    private final Canvas CANVAS;
    private final DisplayFrame DISPLAY_FRAME;

    public Console(int canvasRows, int canvasColumns, Dimension tileDimension) {
        CANVAS = new Canvas(canvasRows, canvasColumns, tileDimension);
        DISPLAY_FRAME = new DisplayFrame();
        refresh();
    }

    /**
     * Clear the canvas by calling its clearTiles method.
     * This removes all image data set via the update methods, but does not clear the image visible on the screen.
     */
    public void clearScreen() {
        CANVAS.clearTiles();
    }

    /**
     * Update the canvas image based on its current tile image data, then use the updated image to repaint the
     * DisplayFrame.
     * Calling this method immediately updates the image visible on the screen.
     */
    public void refresh() {
        CANVAS.paintCanvas();
        DISPLAY_FRAME.getPanel().setImage(CANVAS.getImage());
        DISPLAY_FRAME.getPanel().repaint();
    }

    public void setDefaultColors(Color background, Color foreground) {
        CANVAS.setDefaultBackgroundColor(background);
        CANVAS.setDefaultForegroundColor(foreground);
    }

    /**
     * Update the image data on the canvas. These methods do not change the image visible on the screen.
     */
    public void update(int row, int col, char symbol) {
        update(row, col, CANVAS.getDefaultForegroundColor(), symbol);
    }
    public void update(int row, int col, Color foreground, char symbol) {
        update(row, col, CANVAS.getDefaultBackgroundColor(), foreground, symbol);
    }
    public void update(int row, int col, Color background, Color foreground, char symbol) {
        if (!validatePosition(row, col))
            throw new IllegalArgumentException("Row or column out of bounds.");
        CANVAS.setTile(row, col, new ASCIITile(background, foreground, symbol));
    }

    public boolean validatePosition(int row, int col) {
        return CANVAS.isInBounds(row, col);
    }
}
