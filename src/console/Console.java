package console;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * internal.Console provides a GUI interface on top of swing.
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

    public void addKeyListener(KeyListener keyListener) {
        DISPLAY_FRAME.addKeyListener(keyListener);
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
     * Update the image data on the canvas.
     * These methods do not change the image visible on the screen.
     */
    public void update(int row, int col, ConsoleGlyph cg) {
        update(
                row,
                col,
                cg.SYM,
                cg.FG == null ? CANVAS.getDefaultForegroundColor() : cg.FG,
                cg.BG == null ? CANVAS.getDefaultBackgroundColor() : cg.BG);
    }
    public void update(int row, int col, char symbol) {
        update(row, col, symbol, new Color[0]);
    }

    public void update(int row, int col, char symbol, Color... colorOverrides) {
        if (!validatePosition(row, col))
            throw new IllegalArgumentException("Row or column out of bounds.");
        Color fg = colorOverrides.length > 0 ? colorOverrides[0] : CANVAS.getDefaultForegroundColor();
        Color bg = colorOverrides.length > 1 ? colorOverrides[1] : CANVAS.getDefaultBackgroundColor();
        CANVAS.setTile(row, col, new ASCIITile(bg, fg, symbol));
    }

    public boolean validatePosition(int row, int col) {
        return CANVAS.isInBounds(row, col);
    }
}
