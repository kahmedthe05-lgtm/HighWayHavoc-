// Aisha - Class for the player’s car, including movement, drawing, and boost effects

import java.awt.*;

public class PlayerCar {
    private int x, y;
    private final int WIDTH = 40, HEIGHT = 80;
    private boolean boosted = false;
    private int boostTimer = 0;


    public PlayerCar(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Draw the player's car
    public void draw(Graphics g) {
        g.setColor(boosted ? Color.YELLOW : Color.BLUE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    // Move left within lane bounds
    public void moveLeft() {
        if (x > 100) x -= 80;
    }

    // Move right within lane bounds
    public void moveRight() {
        if (x < 260) x += 80;
    }

    // Activate temporary boost effect
    public void activateBoost() {
        boosted = true;
        boostTimer = 100; // Duration of boost effect
    }

    // Handle boost countdown
    public void update() {
        if (boosted) {
            boostTimer--;
            if (boostTimer <= 0) boosted = false;
        }
    }

    // Return collision boundary
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
