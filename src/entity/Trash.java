package entity;

import constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Trash extends Entity {
    public final int SIZE = 70;
    Rectangle trashHitbox;
    private BufferedImage trashImage;

    public Trash(int y) {
        this.y = y;
        this.x = Constant.GamePanel.ScreenWidth;
        trashHitbox = new Rectangle(x, y+30, SIZE-40, SIZE-40);
        getImage();
    }

    @Override
    protected void getImage() {
        try {
            trashImage = ImageIO.read(Objects.requireNonNull(Trash.class.getResourceAsStream("/res/object/trash.png")));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(trashImage, x, y, SIZE, SIZE, null);
    }

    public void update(Cat cat){
        x-=4;
        trashHitbox.x-=4;
        if(trashHitbox.intersects(cat.getCatHitbox())){
            cat.setPlaying(false);
        }
    }
}
