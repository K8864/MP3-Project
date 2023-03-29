package player;

import main.ClickDetection;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Tower5 extends Tower{
    public static int price = 500;
    public static int costLvl1 = 100;
    public static int costLvl2 = 550;
    public static int costLvl3 = 1200;
    public static int costLvl4 = 1500;

    public Tower5(GamePanel gp) {
        super(gp);
        getTowerImage();
        range = 5;
        rate = 48;
        // 0.8 sec
        current = rate;
        dmg = 2;
        nextLvlCost = costLvl1;
        nextRate = rate;
        nextDmg = dmg;
        nextRange = 7;
        textRange = "" + range + " -> " + nextRange;
        textRate = (double)((int)(((double)rate/60)*100))/100 + "sec";
        textDmg = dmg + "";
        textPierce = 1 + "";
        hasSpread = true;
        sellPrice += (price * 0.5);
    }

    public void getTowerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/towers/Ship1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/towers/Ship1.png"));
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
                if(lvl == 4) {
                    center.makeSides(20, dmg);
                }
                gp.playSE(0, -5);
                break;
            }
        }
    }

    public void draw2(Graphics2D g2) {
        x = ClickDetection.x - gp.getTileSize()/2;
        y = ClickDetection.y - gp.getTileSize()/2;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 36F));
        g2.setColor(Color.BLACK);
        g2.drawImage(image, (int)x, (int)y, gp.getTileSize(), gp.getTileSize(), null);
        g2.setColor(new Color(0, 0, 0, 100));
        rangeCircle.setFrameFromCenter(x + gp.getTileSize()/2, y + gp.getTileSize()/2, (int)(x + range * gp.getTileSize()), (int)(y + range * gp.getTileSize()));
        g2.draw(rangeCircle);
        if(ClickDetection.isPath || !ClickDetection.isWater || !ClickDetection.placeable)
            g2.setColor(new Color(255, 0, 0, 50));
        else
            g2.setColor(new Color(0, 150, 255, 100));
        hitBox.setFrameFromCenter(x + gp.getTileSize()/2, y + gp.getTileSize()/2, x + gp.getTileSize(), y + gp.getTileSize());
        g2.fill(hitBox);
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
        // range 7
        Stats.cash -= costLvl1;
        nextLvlCost = costLvl2;
        textRange = range + "";
        nextDmg = 3;
        textDmg = dmg + " -> " + nextDmg;
        nextRate = 39;
        textRate = (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
    }

    private void lvl2() {
        dmg = nextDmg;
        // dmg 3
        rate = nextRate;
        // cool down 39 (0.65 sec)
        Stats.cash -= costLvl2;
        nextLvlCost = costLvl3;
        nextDmg = 5;
        textDmg = dmg + " -> " + nextDmg;
        nextRate = 30;
        textRate = (double)((int)(((double)rate/60)*100))/100 + " sec -> " + (double)((int)(((double)nextRate/60)*100))/100 + " sec";
    }

    private void lvl3() {
        dmg = nextDmg;
        // dmg 5
        rate = nextRate;
        // cool down 30 (0.5 sec)
        Stats.cash -= costLvl3;
        nextLvlCost = costLvl4;
        nextDmg = 6;
        textDmg = dmg + " -> " + nextDmg;
        textPierce = 1 + " shots" + " -> " + 3 + " shots";
        textRate = (double)((int)(((double)rate/60)*100))/100 + "sec";
    }

    private void lvl4() {
        dmg = nextDmg;
        // dmg 6
        Stats.cash -= costLvl4;
        nextLvlCost = Integer.MAX_VALUE;
        textDmg = dmg + "";
        textPierce = 3 + "";
    }

    public String toString() {
        return "m";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}