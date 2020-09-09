import java.awt.image.BufferedImage;

/**
 * A representation of a single tile on the screen, used to paint that tile's image onto the canvas.
 */
public abstract class ConsoleTile {
    public abstract BufferedImage paint();
}
