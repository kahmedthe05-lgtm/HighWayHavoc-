// Daniel - Obstacle (traffic) car logic: position, movement, drawing, and collision box

import java.awt.*;

public class TrafficCar {
    private int x, y, speed;
    private final int WIDTH = 40, HEIGHT = 80;

    public TrafficCar(int speed) {
        int[] lanes = {100, 180, 260}; // Three lanes
        this.x = lanes[(int) (Math.random() * lanes.length)];
        this.y = -100; // Start above screen
        this.speed = speed; // Random speed
    }

    // Draw the obstacle car
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    // Move car downward
    public void move() {
        y += speed;
    }

    public int getY() {
        return y;
    }

    // Return collision boundary
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
