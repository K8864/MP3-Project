package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Red extends Enemy{

    public Red(GamePanel gp) {
        super(gp);
        hp = 6;
        speed = 0.75;
        getEnemyImage();
        cash = hp*2;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/Red.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "g";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
