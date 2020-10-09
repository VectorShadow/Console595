package console;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A canvas is used to draw the screen image from an array of ConsoleTiles.
 */
public class Canvas {

    private Color defaultBackgroundColor = Color.BLACK;
    private Color defaultForegroundColor = Color.WHITE;

    private final BufferedImage IMAGE;

    private final ConsoleTile[][] TILES;

    private final int HEIGHT;
    private final int WIDTH;

    private final Dimension TILE_DIMENSION;

    Canvas(int rows, int columns, Dimension tileDimension) {
        HEIGHT = rows;
        WIDTH = columns;
        TILE_DIMENSION = tileDimension;
        Renderer.initialize(TILE_DIMENSION);
        TILES = new ConsoleTile[HEIGHT][WIDTH];
        IMAGE = new BufferedImage(
                WIDTH * TILE_DIMENSION.width,
                HEIGHT * TILE_DIMENSION.height,
                BufferedImage.TYPE_INT_RGB
        );
        clearTiles();
        paintCanvas();
    }

    void clearTiles() {
        for (int row = 0; row < HEIGHT; ++row) {
            for (int col = 0; col < WIDTH; ++col) {
                setTile(row, col, new ASCIITile(defaultBackgroundColor, defaultForegroundColor, ' '));
            }
        }
    }

    Color getDefaultBackgroundColor() {
        return defaultBackgroundColor;
    }

    Color getDefaultForegroundColor() {
        return defaultForegroundColor;
    }

    BufferedImage getImage() {
        return IMAGE;
    }

    boolean isInBounds(int row, int col) {
        return row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH;
    }

    /**
     * We paint the screen image by iterating through each console tile, and copying each pixel of its image
     * onto the appropriate position in the canvas image.
     */
    void paintCanvas() {
        final int X = TILE_DIMENSION.width;
        final int Y = TILE_DIMENSION.height;
        for (int row = 0; row < HEIGHT; ++row) {
            for (int col = 0; col < WIDTH; ++col) {
                BufferedImage tileImage = TILES[row][col].paint();
                for (int x = 0; x < X; ++x) {
                    for (int y = 0; y < Y; ++y) {
                        int rgb = tileImage.getRGB(x, y);
                        IMAGE.setRGB(col * X + x, row * Y + y, rgb);
                    }
                }
            }
        }
    }

    void setDefaultBackgroundColor(Color color) {
        defaultBackgroundColor = color;
    }

    void setDefaultForegroundColor(Color color) {
        defaultForegroundColor = color;
    }

    void setTile(int row, int col, ConsoleTile consoleTile) {
        TILES[row][col] = consoleTile;
    }

}
