package enemies;

import main.GamePanel;

import javax.imageio.ImageIO;
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
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/White.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
