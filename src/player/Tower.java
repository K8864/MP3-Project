package player;

import main.ClickDetection;
import main.GamePanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Tower extends Entity{
    public static boolean click;
    boolean clicked = false;
    public static int count = 0;
    GamePanel gp;
    Ellipse2D hitBox = new Ellipse2D.Double();
    int finalX;
    public int getFinalX() {return finalX;}
    int finalY;
    public int getFinalY() {return finalY;}
    public static boolean selecting = false;
    int current;
    boolean image1 = true;
    public boolean sold = false;
    public int sellPrice;

    //tower stats
    public double range;
    public Ellipse2D rangeCircle = new Ellipse2D.Double();

    public int lvl = 0;
    public boolean callUpgrade = false;
    int upgradeDelay = 10;
    public int nextLvlCost;
    public int dmg;
    public int rate;
    public int pierce = 1;
    public int nextPierce = pierce;
    public String textPierce;
    public int nextDmg = dmg;
    public String textDmg;
    public double nextRange = range;
    public String textRange;
    public int nextRate = rate;
    public String textRate;
    public boolean hasSpread;

    public Tower(GamePanel gp) {
        this.gp = gp;
        x = ClickDetection.x;
        y = ClickDetection.y;
        hitBox.setFrameFromCenter(x + (gp.getTileSize()/2), y + (gp.getTileSize()/2), x + 16 * GamePanel.scaleX, y + 16 * GamePanel.scaleY);
    }

    public void draw(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 36F));
        g2.setColor(Color.BLACK);
        if(image1)
            g2.drawImage(image, finalX, finalY, gp.getTileSize(), gp.getTileSize(), null);
        else
            g2.drawImage(image2, finalX, finalY, gp.getTileSize(), gp.getTileSize(), null);
        if(clicked) {
            g2.setColor(new Color(0, 0, 0, 100));
            g2.draw(rangeCircle);
            gp.ui.drawTowerStats(this, g2);
        }
    }

    public void display(Graphics2D g2, int x, int y) {
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }

    public void finalizeThings() {
        finalX = (int)(x - gp.getTileSize()/2);
        finalY = (int)(y - gp.getTileSize()/2);
        hitBox.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, finalX + gp.getTileSize(), finalY + gp.getTileSize());
        rangeCircle.setFrameFromCenter(finalX + gp.getTileSize()/2, finalY + gp.getTileSize()/2, (int)(finalX + range * gp.getTileSize()), (int)(finalY + range * gp.getTileSize()));
        gp.playSE(5, 6);
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
        if(ClickDetection.isPath || ClickDetection.isWater || ClickDetection.edgeWater || !ClickDetection.placeable)
            g2.setColor(new Color(255, 0, 0, 50));
        else
            g2.setColor(new Color(0, 150, 255, 100));
        hitBox.setFrameFromCenter(x + gp.getTileSize()/2, y + gp.getTileSize()/2, x + gp.getTileSize(), y + gp.getTileSize());
        g2.fill(hitBox);
    }

    public void update() {
        if(current >= rate) {
            attack();
            current = 0;
        }
        else {
            current++;
        }
        if(ClickDetection.click &&
        ClickDetection.x >= finalX + gp.getTileSize() * .1 &&
        ClickDetection.x <= finalX + gp.getTileSize() * .85 &&
        ClickDetection.y >= finalY &&
        ClickDetection.y <= finalY + gp.getTileSize() ||
        ClickDetection.x >= gp.getScreenWidth() &&
        clicked) {
            for(int i=0; i<gp.towers.size(); i++) {
                gp.towers.get(i).clicked = false;
            }
            clicked = true;
            if(ClickDetection.click &&
            ClickDetection.x >= gp.getScreenWidth() + 50 &&
            ClickDetection.x <= gp.getScreenWidth() + 150 &&
            ClickDetection.y >= gp.ui.boxY &&
            ClickDetection.y <= gp.ui.boxY + gp.getTileSize()*1.25 &&
            Stats.cash >= nextLvlCost) {
                if(upgradeDelay >= 10) {
                    callUpgrade = true;
                    upgradeDelay = 0;
                }
            }
            else if(ClickDetection.click &&
            ClickDetection.x >= gp.getScreenWidth() + 50 &&
            ClickDetection.x <= gp.getScreenWidth() + 150 &&
            ClickDetection.y >= gp.getTileSize()/2 &&
            ClickDetection.y <= gp.getTileSize()*1.75
            ) {
                sold = true;
                Stats.cash += sellPrice;
                count--;
            }
        }
        else if(ClickDetection.click)
            clicked = false;
        if(clicked) {
            click = true;
        }
        upgradeDelay++;
    }

    public void attack() {
        for(int i=gp.enemies.size()-1; i>=0; i--) {
            double dist = Math.sqrt((finalX - gp.enemies.get(i).x) * (finalX - gp.enemies.get(i).x) + (finalY - gp.enemies.get(i).y) * (finalY - gp.enemies.get(i).y));
            if(dist + gp.getTileSize()/3 <= range * gp.getTileSize()) {
                gp.shots.add(new Bullet(finalX, finalY, (int) gp.enemies.get(i).x, (int) gp.enemies.get(i).y, dmg, pierce, gp));
                image1 = !(gp.enemies.get(i).x >= finalX);
                break;
            }
        }
    }

    public void upgrade() {

    }

    public String toString() {
        return "2";
    }
    public boolean equals(Entity other) {
        return other == this;
    }

}