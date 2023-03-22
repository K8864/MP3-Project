package enemies;

import main.GamePanel;

import javax.imageio.ImageIO;
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
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/Zebra.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
