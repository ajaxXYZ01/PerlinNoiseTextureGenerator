import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

    Main() {

        Noise noise = new Noise(512,512);

        int width  = 512;
        int height = 512;
        
        JFrame window = new JFrame("This is a Perlin Noise Texture");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        BufferedImage image_texture = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        float scale = 1 / 32f;

        redraw(noise, width, height, scale, image_texture);

        // writePNG(image_texture, "noise_render_" + System.currentTimeMillis() + ".png");

        JPanel canvas = new JPanel() {
            protected void paintComponent(Graphics gfx) {
                super.paintComponent(gfx);
                gfx.drawImage(image_texture, 0, 0, null);
            }
        };

        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setBackground(Color.RED);

        window.add(canvas);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // while (true) {
        //     scale -= 0.001f;
        //     redraw(noise, width, height, scale, image_texture);
        //     canvas.repaint();
        //     try {
        //         Thread.sleep(16);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }
    }

    public static void main(String args[]) {        
        new Main();
    }

    public void redraw(Noise noise, int width, int height, float scale, BufferedImage image_texture) {
        int color;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float noise_x = x / ((float) width)  * noise.getGridWidth()  * scale;
                float noise_y = y / ((float) height) * noise.getGridHeight() * scale;
                
                float value = noise.sampleFractal(noise_x, noise_y, 8);

                if (value < 0.05f) {
                    image_texture.setRGB(x, y, Color.BLUE.getRGB());
                    continue;
                } else if (value >= 0.05f && value < 0.1f) {
                    image_texture.setRGB(x, y, Color.YELLOW.getRGB());
                    continue;
                } else if (value >= 0.1f) {
                    image_texture.setRGB(x, y, Color.GREEN.getRGB());
                    continue;
                }
                else {
                    color = (int)((value + 1) * 0.5f * 255);
                }

                image_texture.setRGB(x, y, new Color(color, color, color).getRGB());
            }
        }
    }

    public static void writePNG(BufferedImage image, String fileName) {
        File outputFile = new File("renders\\" + fileName);
        try {
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}