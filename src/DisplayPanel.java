import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class DisplayPanel extends JPanel {
    
    private BufferedImage canvasImage;
    private BufferedImage panelImage;

    public DisplayPanel() {
        Dimension size = getDefaultPanelSize();
        setPreferredSize(size);
        setSize(size);
        setOpaque(true);
        setVisible(true);
        setImage(
                new BufferedImage(
                        getWidth(),
                        getHeight(),
                        BufferedImage.TYPE_INT_ARGB
                )
        );
    }


    private static Dimension getDefaultPanelSize() {
        return new Dimension(
                (int)(getMonitorDimension().width / Math.sqrt(2.0)),
                (int)(getMonitorDimension().height / Math.sqrt(2.0))
        );
    }

    private static Dimension getMonitorDimension() {
        DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        return new Dimension(dm.getWidth(), dm.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {//updates the displayed image.
        super.paintComponent(g);
        if (panelImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(panelImage, 0, 0, null);
        }
    }

    private void scaleImage() {
        if (canvasImage == null)
            return;
        double heightRatio = (double)getHeight() / (double)canvasImage.getHeight();
        double widthRatio = (double)getWidth() / (double)canvasImage.getWidth();
        panelImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(widthRatio, heightRatio);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        panelImage = affineTransformOp.filter(canvasImage, panelImage);
    }

    void setImage(BufferedImage bufferedImage) {
        canvasImage = bufferedImage;
        scaleImage();
    }
    
    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        scaleImage();
        repaint();
    }
}
