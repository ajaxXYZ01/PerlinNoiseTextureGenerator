import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

    public static void main(String args[]) {
        Noise noise = new Noise(4,4);
        int width  = 512;
        int height = 512;
        
        JFrame window = new JFrame("This is a Perlin Noise Texture");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        BufferedImage image_texture = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float noise_x = x / ((float) width) * noise.getGridWidth();
                float noise_y = y / ((float) height) * noise.getGridHeight();

                float value = noise.sampleFractal(noise_x, noise_y, 1);
                int color = (int)((value + 1) * 0.5f * 255);

                image_texture.setRGB(x, y, new Color(color, color, color).getRGB());
            }
        }

        JPanel canvas = new JPanel() {
            protected void paintComponent(Graphics gfx) {
                super.paintComponent(gfx);
                gfx.drawImage(image_texture, 0, 0, null);
            }
        };

        canvas.setPreferredSize(new Dimension(width, height));

        window.add(canvas);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}