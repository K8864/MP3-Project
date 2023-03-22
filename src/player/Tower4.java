package player;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tower4 extends Tower{
    public static int price = 250;
    public static int costLvl1 = 100;
    public static int costLvl2 = 200;
    public static int costLvl3 = 1000;
    public static int costLvl4 = 1500;

    public Tower4(GamePanel gp) {
        super(gp);
        getTowerImage();
        range = 2.5;
        rate = 60;
        dmg = 1;
        peirce = 1;
        nextLvlCost = costLvl1;
        textPeirce = 3 + " shots" + " -> " + 5 + " shots";
    }

    public void getTowerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/towers/Shotty1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/towers/Shotty2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attack() {
        for(int i=gp.enemies.size()-1; i>=0; i--) {
            double dist = Math.sqrt((finalX - gp.enemies.get(i).x) * (finalX - gp.enemies.get(i).x) + (finalY - gp.enemies.get(i).y) * (finalY - gp.enemies.get(i).y));
            if(dist + gp.getTileSize()/3 <= range * gp.getTileSize()) {
                Bullet center = new Bullet(finalX, finalY, (int) gp.enemies.get(i).x, (int) gp.enemies.get(i).y, dmg, 1, gp);
                gp.shots.add(center);
                    center.makeSides(15, dmg);

                if(lvl >= 1) {
                    center.makeSides(30, dmg);
                }
                if(lvl >= 3) {
                    center.makeSides(10, dmg);
                    center.makeSides(20, dmg);
                    center.makeSides(25, dmg);
                }
                image1 = !(gp.enemies.get(i).x >= finalX);
                break;
            }
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
        sellPrice += (price * 0.5);
    }

    private void lvl1() {
        Stats.cash -= costLvl1;
        nextLvlCost = costLvl2;
        nextRange = 3;
        textRange = "" + range + " -> " + nextRange;
        nextRate = 45;
        textRate = (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textPeirce = null;
    }

    private void lvl2() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 3
        rate = nextRate;
        // cool down 45 (0.75 sec)
        Stats.cash -= costLvl2;
        nextLvlCost = costLvl3;
        nextRange = 3.5;
        textRange = "" + range + " -> " + nextRange;
        nextRate = 30;
        textRate = (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textPeirce = 5 + " shots" + " -> " + 11 + " shots";
    }

    private void lvl3() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 3.5
        rate = nextRate;
        // cool down 30 (0.5 sec)
        Stats.cash -= costLvl3;
        nextLvlCost = costLvl4;
        nextDmg = 2;
        textDmg = "" + dmg + " -> " + nextDmg;
        textRange = null;
        textRate = null;
        textPeirce = null;
    }

    private void lvl4() {
        dmg = nextDmg;
        // dmg 2
        Stats.cash -= costLvl4;
        nextLvlCost = Integer.MAX_VALUE;
        textDmg = null;
    }
}