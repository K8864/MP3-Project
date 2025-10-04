package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;

import java.io.FileInputStream;
import java.io.IOException;

public class Green extends Enemy{

    public Green(GamePanel gp) {
        super(gp);
        hp = 10;
        speed = 0.85;
        getEnemyImage();
        cash = hp*2;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(new FileInputStream("res/enemy/Green.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "q";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
