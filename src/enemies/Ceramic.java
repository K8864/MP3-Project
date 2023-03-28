package enemies;

import main.GamePanel;

import javax.imageio.ImageIO;
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
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/Ceramic.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
