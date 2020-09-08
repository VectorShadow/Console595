import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {
    private static JLabel renderLabel;

    public static void initialize(Dimension tileDimension) {
        renderLabel = new JLabel();
        renderLabel.setSize(tileDimension);
        renderLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, (int)(tileDimension.getHeight() * 0.75)));
        renderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        renderLabel.setOpaque(true);
    }

    public static void updateLabel(Color background, Color foreground, char symbol) {
        renderLabel.setBackground(background);
        renderLabel.setForeground(foreground);
        renderLabel.setText("" + symbol);
    }

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
