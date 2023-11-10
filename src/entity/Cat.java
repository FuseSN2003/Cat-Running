package entity;

import constant.Constant;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.AttributedString;
import java.util.Objects;

public class Cat extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    private double score = 0;
    private Rectangle catHitbox;
    private int currentFrame = 0;
    private int frameCounter = 0;
    private int frameDelay = 5;
    private boolean isPlaying = true;

    BufferedImage[] runAnimation;
    int currLane = 0;
    Font aria;

    public Cat(){}

    public Cat(GamePanel gp, KeyHandler keyH) {
        aria = new Font("Arial", Font.PLAIN, 32);
        this.x = 200;
        this.y = 570;
        this.gp = gp;
        this.keyH = keyH;
        this.catHitbox = new Rectangle(this.x+10, this.y+30, Constant.Cat.SIZE-35, Constant.Cat.SIZE-35);
        getImage();
    }

    public Rectangle getCatHitbox() {
        return catHitbox;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public double getScore() {
        return score;
    }

    @Override
    protected void getImage() {
        try {
            runAnimation = new BufferedImage[8];
            runAnimation[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/cat/cat_run1.png")));
            runAnimation[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/cat/cat_run2.png")));
            runAnimation[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/cat/cat_run3.png")));
            runAnimation[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/cat/cat_run4.png")));
            runAnimation[4] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/cat/cat_run5.png")));
            runAnimation[5] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/cat/cat_run6.png")));
            runAnimation[6] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/cat/cat_run7.png")));
            runAnimation[7] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/cat/cat_run8.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restartGame() {
        this.score = 0;
        this.isPlaying = true;
    }

    public void update() {
        score+=0.1;
        if (keyH.upPressed && !keyH.isPressed) {
            if(currLane < 2) {
                currLane += 1;
                y-=100;
                catHitbox.y-=100;
                keyH.isPressed = true;
            }
        }
        if (keyH.downPressed && !keyH.isPressed) {
            if(currLane > 0) {
                currLane -= 1;
                y+=100;
                catHitbox.y+=100;
                keyH.isPressed = true;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = runAnimation[currentFrame];

        g2.drawImage(image, x, y, Constant.Cat.SIZE, Constant.Cat.SIZE, null);
        g2.setFont(aria);
        g2.drawString("score: " + (int) score, 50, 50);

        frameCounter++;
        if (frameCounter >= frameDelay) {
            currentFrame++;
            if (currentFrame >= runAnimation.length) {
                currentFrame = 0;
            }
            frameCounter = 0;
        }
    }
}
