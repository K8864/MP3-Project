package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;

import java.io.FileInputStream;
import java.io.IOException;

public class Black extends Enemy{

    public Black(GamePanel gp) {
        super(gp);
        hp = 100;
        speed = 0.5;
        getEnemyImage();
        cash = hp*2;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(new FileInputStream("res/enemy/Black.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "w";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
