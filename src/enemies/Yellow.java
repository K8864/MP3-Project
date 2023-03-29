package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Yellow extends Enemy{

    public Yellow(GamePanel gp) {
        super(gp);
        hp = 24;
        speed = 0.6;
        getEnemyImage();
        cash = hp*2;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/Yellow.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "t";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
