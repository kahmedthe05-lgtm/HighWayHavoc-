// Aisha - Core game logic: GUI, input, animation, game loop, and state management

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer; // Timer for frame updates
    private PlayerCar player; // The player-controlled car
    private ArrayList<TrafficCar> trafficCars; // List of obstacle cars
    private ArrayList<Boost> boosts; // List of active boost items
    private boolean gameOver; // Game state flag
    private int lives = 3; // Player lives
    private int score = 0; // Tracks player score
    private long difficultyStartTime;
    private int trafficSpeed = 5;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.DARK_GRAY); // Road color

        // Initialize game elements
        player = new PlayerCar(180, 500);
        trafficCars = new ArrayList<>();
        boosts = new ArrayList<>();

        difficultyStartTime = System.currentTimeMillis();
        // Start the game loop (30ms/frame ≈ 33 FPS)
        timer = new Timer(30, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the player
        player.draw(g);

        // Draw all traffic cars
        for (TrafficCar car : trafficCars) {
            car.draw(g);
        }

        // Draw boosts
        for (Boost b : boosts) {
            b.draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 10, 30); // Dispalays the score at top left corner


        // Draw game over screen if needed
        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", 110, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press R to Restart", 120, 340);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Increase difficulty every 10 seconds
            if (System.currentTimeMillis() - difficultyStartTime > 10000) {
                trafficSpeed++;
                difficultyStartTime = System.currentTimeMillis();
            }

            // Move and update traffic cars
            for (int i = trafficCars.size() - 1; i >= 0; i--) {
                trafficCars.get(i).move();
                if (trafficCars.get(i).getY() > 600) {
                    trafficCars.remove(i);
                }
                score += 1; // Increaces score as time passes (1 point per frame)
            }

            // Move and update boosts
            for (int i = boosts.size() - 1; i >= 0; i--) {
                boosts.get(i).move();
                if (boosts.get(i).getY() > 600) {
                    boosts.remove(i);
                }
            }

            // Random chance to spawn traffic cars and boosts
            if (Math.random() < 0.02) {
                trafficCars.add(new TrafficCar(trafficSpeed));
            }
            if (Math.random() < 0.009) {
                boosts.add(new Boost());
            }

            // Check for collisions with traffic cars
            for (TrafficCar car : trafficCars) {
                if (player.getBounds().intersects(car.getBounds())) {
                    lives--;
                    if (lives <= 0) gameOver = true;
                    break;
                }
            }

            // Check for collisions with boosts
            for (int i = boosts.size() - 1; i >= 0; i--) {
                if (player.getBounds().intersects(boosts.get(i).getBounds())) {
                    player.activateBoost();
                    boosts.remove(i);
                }
            }

            // Update player state (e.g., boost)
            player.update();
        }

        // Repaint the screen
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) player.moveLeft();
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.moveRight();
        } else {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                restartGame(); // Restart the game if R is pressed
            }
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    // Resets all game state and variables
    private void restartGame() {
        gameOver = false;
        lives = 3;
        trafficCars.clear();
        boosts.clear();
        player = new PlayerCar(180, 500);
        score = 0;
    }
}
