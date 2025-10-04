package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;

import java.io.FileInputStream;
import java.io.IOException;

public class Zebra extends Enemy{

    public Zebra(GamePanel gp) {
        super(gp);
        hp = 50;
        speed = 1.5;
        getEnemyImage();
        cash = hp*3;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(new FileInputStream("res/enemy/Zebra.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "ball";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
