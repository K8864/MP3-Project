package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;

import java.io.FileInputStream;
import java.io.IOException;

public class Ceramic extends Enemy{

    public Ceramic(GamePanel gp) {
        super(gp);
        hp = 3000;
        speed = 1;
        getEnemyImage();
        cash = hp;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(new FileInputStream("res/enemy/Ceramic.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "z";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
