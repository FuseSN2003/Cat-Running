package main;

import constant.Constant;
import entity.Cat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    Cat cat = new Cat(this, keyH);
    TrashManager trashes = new TrashManager();
    LoseGame lostGame = new LoseGame();

    BufferedImage bg;
    private int backgroundX = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.loadBackground();
    }

    public void resetGame() {
        cat.restartGame();
        trashes.getTrashes().clear();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000_000_000.0 / 120;
        double timePerUpdate = 1000_000_000.0 / 60;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        int frames = 0;
        int updates = 0;

        long lastTimeCheck = System.currentTimeMillis();

        long now;

        while (gameThread != null) {
            now = System.nanoTime();
            //update
            if ( now - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updates++;
            }

            //render
            if( now - lastFrame >= timePerFrame) {
                lastFrame = now;
                this.update();
                this.repaint();
                frames++;
                backgroundX-=1;
                if(backgroundX == -1280) {
                    backgroundX = 0;
                }
            }

            if( System.currentTimeMillis() - lastTimeCheck >= 1000 ) {
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public void update() {
        if(cat.isPlaying()) {
            cat.update();
            trashes.update(cat);
        }
    }

    private void loadBackground() {
        bg = null;
        try {
            bg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/bg/bg.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        if(cat.isPlaying()) {
            for(int i = 0; i < 2; i++) {
                g.drawImage(bg, backgroundX+(1280*i)-2, 0, Constant.GamePanel.ScreenWidth, Constant.GamePanel.ScreenHeight, null);
            }
            cat.draw(g2);
            trashes.draw(g2);
        } else {
            lostGame.draw(g2, cat.getScore());
        }
    }
}
