package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, isPressed = false;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.cat.isPlaying()) {
            if(code == 38 || code == 87) {
                upPressed = true;
            }
            if(code == 40 || code == 83) {
                downPressed = true;
            }
        } else {
            if(code == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            } else {
                gp.resetGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 38 || code == 87) {
            upPressed = false;
            isPressed = false;
        }
        if (code == 40 || code == 83) {
            downPressed = false;
            isPressed = false;
        }
    }
}
