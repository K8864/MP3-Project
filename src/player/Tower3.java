package player;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tower3 extends Tower{
    public static int price = 800;
    public static int costLvl1 = 100;
    public static int costLvl2 = 900;
    public static int costLvl3 = 2900;
    public static int costLvl4 = 7500;

    public Tower3(GamePanel gp) {
        super(gp);
        getTowerImage();
        range = 5;
        rate = 15;
        current = rate;
        dmg = 1;
        nextLvlCost = costLvl1;
        nextRate = rate;
        nextDmg = dmg;
        nextRange = 6;
        textRange = "" + range + " -> " + nextRange;
        textDmg = dmg + "";
        textRate = (double)((int)(((double)rate/60)*100))/100 + "sec";
        textPierce = pierce + "";
        sellPrice += (price * 0.5);
    }

    public void getTowerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/towers/MachineGunner1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/towers/MachineGunner2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attack() {
        for(int i=gp.enemies.size()-1; i>=0; i--) {
            double dist = Math.sqrt((finalX - gp.enemies.get(i).x) * (finalX - gp.enemies.get(i).x) + (finalY - gp.enemies.get(i).y) * (finalY - gp.enemies.get(i).y));
            if(dist + gp.getTileSize()/3 <= range * gp.getTileSize()) {
                Bullet b = new Bullet(finalX, finalY, (int) gp.enemies.get(i).x, (int) gp.enemies.get(i).y, dmg, pierce, gp);
                gp.shots.add(b);
                gp.playSE(0, -5);
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
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 6
        Stats.cash -= costLvl1;
        nextLvlCost = costLvl2;
        nextDmg = 2;
        textRange = range + "";
        nextRate = 12;
        textDmg = dmg + " -> " + nextDmg;
        textRate = (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
    }

    private void lvl2() {
        dmg = nextDmg;
        // dmg 2
        rate = nextRate;
        // cool down 12 (0.2 sec)
        Stats.cash -= costLvl2;
        nextLvlCost = costLvl3;
        nextRate = 9;
        nextDmg = 5;
        textRate = (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textDmg = dmg + " -> " + nextDmg;
    }

    private void lvl3() {
        rate = nextRate;
        // cool down 9 (0.15 sec)
        dmg = nextDmg;
        // dmg 5
        Stats.cash -= costLvl3;
        nextLvlCost = costLvl4;
        nextRate = 6;
        nextRange = 7;
        nextDmg = 10;
        textRange = "" + range + " -> " + nextRange;
        textRate = "" + (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
        textDmg = "" + dmg + " -> " + nextDmg;
    }

    private void lvl4() {
        range = nextRange;
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        // range 7
        rate = nextRate;
        // rate 6 (0.1 sec)
        dmg = nextDmg;
        // dmg 10
        Stats.cash -= costLvl4;
        nextLvlCost = Integer.MAX_VALUE;
        textRange = range + "";
        textRate = (double)((int)(((double)rate/60)*100))/100 + "sec";
        textDmg = dmg + "";
    }

    public String toString() {
        return "h";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}