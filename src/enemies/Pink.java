package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Pink extends Enemy{

    public Pink(GamePanel gp) {
        super(gp);
        hp = 7;
        speed = 2;
        getEnemyImage();
        cash = hp*4;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/Pink.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "6";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
