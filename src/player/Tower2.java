package player;

import main.GamePanel;

import javax.imageio.ImageIO;

import java.io.FileInputStream;
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
        pierce = 1;
        nextLvlCost = costLvl1;
        nextRate = rate;
        nextPierce = pierce;
        nextRange = 7;
        nextDmg = 3;
        textRange = "" + range + " -> " + nextRange;
        textDmg = "" + dmg + " -> " + nextDmg;
        textPierce = pierce + "";
        textRate = (double)((int)(((double)rate/60)*100))/100 + "sec";
        sellPrice += (price * 0.5);
    }

    public void getTowerImage() {
        try {
            image = ImageIO.read(new FileInputStream("res/towers/Sniper1.png"));
            image2 = ImageIO.read(new FileInputStream("res/towers/Sniper2.png"));
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
                gp.playSE(4, 6);
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
        nextPierce = 2;
        textRange = "" + range + " -> " + nextRange;
        textDmg = "" + dmg + " -> " + nextDmg;
        textPierce = "" + pierce + " -> " + nextPierce;
    }

    private void lvl2() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 8
        dmg = nextDmg;
        // dmg 4
        pierce = nextPierce;
        // pierce 2
        Stats.cash -= costLvl2;
        nextLvlCost = costLvl3;
        nextDmg = 5;
        nextRange = 9;
        textRange = "" + range + " -> " + nextRange;
        nextPierce = 3;
        textDmg = "" + dmg + " -> " + nextDmg;
        textPierce = "" + pierce + " -> " + nextPierce;
    }

    private void lvl3() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        dmg = nextDmg;
        // dmg 5
        pierce = nextPierce;
        // pierce 3
        Stats.cash -= costLvl3;
        nextLvlCost = costLvl4;
        nextRange = 10;
        nextDmg = 12;
        nextRate = 105;
        textRange = "" + range + " -> " + nextRange;
        textRate = "" + (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textDmg = "" + dmg + " -> " + nextDmg;
        textPierce = pierce + "";
    }

    private void lvl4() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 10
        dmg = nextDmg;
        // dmg 12
        rate = nextRate;
        // cool down 90 (1.75 sec)
        Stats.cash -= costLvl4;
        nextLvlCost = Integer.MAX_VALUE;
        textRange = range + "";
        textDmg = dmg + "";
        textRate = (double)((int)(((double)rate/60)*100))/100 + "sec";
    }

    public String toString() {
        return "a";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}