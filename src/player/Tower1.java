package player;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tower1 extends Tower{
    public static int price = 100;
    public static int costLvl1 = 50;
    public static int costLvl2 = 200;
    public static int costLvl3 = 300;
    public static int costLvl4 = 500;

    public Tower1(GamePanel gp) {
        super(gp);
        getTowerImage();
        range = 4;
        rate = 90;
        current = rate;
        dmg = 1;
        nextLvlCost = costLvl1;
        nextRate = 75;
        nextDmg = dmg;
        nextRange = 4.5;
        textRange = "" + range + " -> " + nextRange;
        textRate = "" + (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        sellPrice += (price * 0.5);
    }

    public void getTowerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/towers/Pizzle.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/towers/Pizzle.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attack() {
        for(int i=gp.enemies.size()-1; i>=0; i--) {
            double dist = Math.sqrt((finalX - gp.enemies.get(i).x) * (finalX - gp.enemies.get(i).x) + (finalY - gp.enemies.get(i).y) * (finalY - gp.enemies.get(i).y));
            if(dist + gp.getTileSize()/3 <= range * gp.getTileSize()) {
                gp.shots.add(new Bullet(finalX, finalY, (int) gp.enemies.get(i).x, (int) gp.enemies.get(i).y, dmg, pierce, gp));
                gp.playSE(1, 1);
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
    }

    private void lvl1() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize() / 2, finalY + gp.getTileSize() / 2, (int) (finalX + range * gp.getTileSize()), (int) (finalY + range * gp.getTileSize()));
        // range 4.5
        rate = nextRate;
        // cool down 75 (1.25 sec)
        Stats.cash -= costLvl1;
        nextLvlCost = costLvl2;
        nextRange = 5;
        nextRate = 60;
        nextDmg = 2;
        textRange = "" + range + " -> " + nextRange;
        textRate = "" + (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textDmg = "" + dmg + " -> " + nextDmg;
    }

    private void lvl2() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 5
        rate = nextRate;
        // cool down 60 (1 sec)
        dmg = nextDmg;
        // dmg 2
        Stats.cash -= costLvl2;
        nextLvlCost = costLvl3;
        nextRange = 6;
        nextDmg = 3;
        textRange = "" + range + " -> " + nextRange;
        textRate = null;
        textDmg = "" + dmg + " -> " + nextDmg;
    }

    private void lvl3() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 6
        dmg = nextDmg;
        // dmg 3
        Stats.cash -= costLvl3;
        nextLvlCost = costLvl4;
        nextRange = 6.5;
        nextRate = 45;
        nextDmg = 5;
        textRange = "" + range + " -> " + nextRange;
        textRate = "" + (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textDmg = "" + dmg + " -> " + nextDmg;
    }

    private void lvl4() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 6.5
        rate = nextRate;
        // cool down 45 (0.75 sec)
        dmg = nextDmg;
        // dmg 5
        Stats.cash -= costLvl4;
        nextLvlCost = Integer.MAX_VALUE;
        textRange = null;
        textRate = null;
        textDmg = null;
    }
}