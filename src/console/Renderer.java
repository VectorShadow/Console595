package console;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Use a hidden JLabel to generate an image for the Canvas.
 */
public class Renderer {
    private static JLabel renderLabel;

    public static void initialize(Dimension tileDimension) {
        renderLabel = new JLabel();
        renderLabel.setSize(tileDimension);
        renderLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, (int)(tileDimension.getWidth() + 1)));
        renderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        renderLabel.setOpaque(true);
    }

    /**
     * Used by ASCIITiles to set the Renderer's label fields to match their data.
     */
    public static void updateLabel(Color background, Color foreground, char symbol) {
        renderLabel.setBackground(background);
        renderLabel.setForeground(foreground);
        renderLabel.setText("" + symbol);
    }

    /**
     * Return a BufferedImage onto which this label's image has been painted.
     */
    public static BufferedImage paint() {
        BufferedImage bi =
                new BufferedImage(
                        renderLabel.getWidth(),
                        renderLabel.getHeight(),
                        BufferedImage.TYPE_INT_RGB
                );
        renderLabel.paint(bi.getGraphics());
        return bi;
    }
}
