package player;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tower2 extends Tower{
    public static int price = 300;
    public static int costLvl1 = 200;
    public static int costLvl2 = 300;
    public static int costLvl3 = 750;
    public static int costLvl4 = 2000;

    public Tower2(GamePanel gp) {
        super(gp);
        getTowerImage();
        range = 6;
        rate = 120;
        current = rate;
        dmg = 2;
        peirce = 1;
        nextLvlCost = costLvl1;
        nextRate = rate;
        nextPeirce = peirce;
        nextRange = 7;
        nextDmg = 3;
        textRange = "" + range + " -> " + nextRange;
        textDmg = "" + dmg + " -> " + nextDmg;
        sellPrice += (price * 0.5);
    }

    public void getTowerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/towers/Sniper1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/towers/Sniper2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upgrade() {
        if(lvl == 0) {
            lvl1();
            sellPrice += (costLvl1 * 0.5);
        }
        else if(lvl == 1) {
            lvl2();
            sellPrice += (costLvl2 * 0.5);
        }
        else if(lvl == 2) {
            lvl3();
            sellPrice += (costLvl3 * 0.5);
        }
        else if(lvl == 3) {
            lvl4();
            sellPrice += (costLvl4 * 0.5);
        }
        lvl++;
        callUpgrade = false;
    }

    private void lvl1() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 7
        dmg = nextDmg;
        // dmg 3
        Stats.cash -= costLvl1;
        nextLvlCost = costLvl2;
        nextRange = 8;
        nextDmg = 4;
        nextPeirce = 2;
        textRange = "" + range + " -> " + nextRange;
        textDmg = "" + dmg + " -> " + nextDmg;
        textPeirce = "" + peirce + " -> " + nextPeirce;
    }

    private void lvl2() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 8
        dmg = nextDmg;
        // dmg 4
        peirce = nextPeirce;
        // peirce 2
        Stats.cash -= costLvl2;
        nextLvlCost = costLvl3;
        nextDmg = 6;
        nextRange = 9;
        textRange = "" + range + " -> " + nextRange;
        nextRate = 105;
        textRate = "" + (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textDmg = "" + dmg + " -> " + nextDmg;
        textPeirce = null;
    }

    private void lvl3() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        dmg = nextDmg;
        // dmg 7
        rate = nextRate;
        // cool down 105 (1.75 sec)
        Stats.cash -= costLvl3;
        nextLvlCost = costLvl4;
        nextRange = 10;
        nextDmg = 11;
        nextRate = 90;
        textRange = "" + range + " -> " + nextRange;
        textRate = "" + (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textDmg = "" + dmg + " -> " + nextDmg;
    }

    private void lvl4() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 10
        dmg = nextDmg;
        // dmg 11
        rate = nextRate;
        // cool down 90 (1.5 sec)
        Stats.cash -= costLvl4;
        nextLvlCost = Integer.MAX_VALUE;
        textRange = null;
        textDmg = null;
        textPeirce = null;
    }
}