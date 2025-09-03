// Daniel - Boost item logic: spawn, movement, drawing, and collision box

import java.awt.*;

public class Boost {
    private int x, y, speed = 4;
    private final int SIZE = 30;

    public Boost() {
        int[] lanes = {100, 180, 260};
        this.x = lanes[(int)(Math.random() * lanes.length)] + 5;
        this.y = -50;
    }

    // Draw the boost item
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, SIZE, SIZE);
    }

    // Move boost downward
    public void move() {
        y += speed;
    }

    public int getY() {
        return y;
    }

    // Return collision boundary
    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}
