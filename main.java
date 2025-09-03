// Aisha - JFrame setup and main game launcher

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        // Create the game window
        JFrame frame = new JFrame("Highway Havoc");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setResizable(false);

        // Add the custom game panel
        frame.add(new GamePanel());

        frame.setVisible(true); // Display the window
    }
}
