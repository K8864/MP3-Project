package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;

import java.io.FileInputStream;
import java.io.IOException;

public class White extends Enemy{

    public White(GamePanel gp) {
        super(gp);
        hp = 9;
        speed = 3;
        getEnemyImage();
        cash = hp*4;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(new FileInputStream("res/enemy/White.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "]";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
