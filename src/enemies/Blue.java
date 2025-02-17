package enemies;

import main.GamePanel;
import player.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Blue extends Enemy{

    public Blue(GamePanel gp) {
        super(gp);
        hp = 7;
        speed = 0.85;
        getEnemyImage();
        cash = hp*2;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/Blue.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "v";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
