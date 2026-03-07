import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    
    public static void main(String args[]) {
        Noise noise = new Noise(8, 8);
        
        JFrame window = new JFrame();
        JPanel canvas = new JPanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.setPreferredSize(new Dimension(512, 512));
        canvas.setBackground(Color.RED);

        window.add(canvas);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}