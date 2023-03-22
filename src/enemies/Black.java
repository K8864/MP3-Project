package enemies;

import main.GamePanel;

import javax.imageio.ImageIO;
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
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/Black.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
