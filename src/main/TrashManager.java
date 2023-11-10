package main;

import entity.Cat;
import entity.Trash;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TrashManager {
    private ArrayList<Trash> trashes = new ArrayList<>();
    int[] y = { 610, 530, 430 };
    Random random = new Random();

    public ArrayList<Trash> getTrashes() {
        return trashes;
    }

    int counter = 0;
    public void update(Cat cat){
        counter++;
        if(counter >= 120) {
            int idx = random.nextInt(y.length);
            int idx2 = random.nextInt(y.length);
            trashes.add(new Trash(y[idx]));
            trashes.add(new Trash(y[idx2]));
            counter = 0;
        }
        for(Trash trash: trashes) {
            trash.update(cat);
        }
    }

    public void draw(Graphics2D g2){
        for(Trash trash: trashes) {
            trash.draw(g2);
        }
    }
}
