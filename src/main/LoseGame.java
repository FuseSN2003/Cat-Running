package main;

import constant.Constant;

import java.awt.*;

public class LoseGame {
    Font aria, aria16, aria12;

    public LoseGame(){
        aria = new Font("Arial", Font.PLAIN, 32);
        aria12 = new Font("Arial", Font.PLAIN, 12);
        aria16 = new Font("Arial", Font.PLAIN, 16);
    }

    public void draw(Graphics2D g2, double score) {
        g2.setPaint(Color.black);
        g2.fillRect(0, 0, Constant.GamePanel.ScreenWidth, Constant.GamePanel.ScreenHeight);

        g2.setColor(Color.white);
        g2.setFont(aria);

        int textLength = (int) g2.getFontMetrics().getStringBounds("Your score:", g2).getWidth();
        g2.drawString("Your score:", Constant.GamePanel.ScreenWidth/2 - textLength/2, 200);
        int textLength2 = (int) g2.getFontMetrics().getStringBounds(String.valueOf((int) score), g2).getWidth();
        g2.drawString(String.valueOf((int) score), Constant.GamePanel.ScreenWidth/2 - textLength2/2, 250);

        g2.setFont(aria16);
        int textLength3 = (int) g2.getFontMetrics().getStringBounds("Pressed any key to play again", g2).getWidth();
        g2.drawString("Press any keys to play again", Constant.GamePanel.ScreenWidth/2 - textLength3/2, Constant.GamePanel.ScreenHeight/2);
        g2.setFont(aria12);
        int textLength4 = (int) g2.getFontMetrics().getStringBounds("Pressed ESC to exit the game", g2).getWidth();
        g2.drawString("Pressed ESC to exit the game", Constant.GamePanel.ScreenWidth/2 - textLength4/2, Constant.GamePanel.ScreenHeight/2 + 40);
    }
}
