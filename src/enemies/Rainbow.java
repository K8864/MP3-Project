package enemies;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Rainbow extends Enemy{

    public Rainbow(GamePanel gp) {
        super(gp);
        hp = 200;
        speed = 1.25;
        getEnemyImage();
        cash = hp*3;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/Rainbow.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
