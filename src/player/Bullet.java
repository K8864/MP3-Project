package player;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Bullet extends Entity{
    GamePanel gp;
    private int hits, dist;
    private final int damage;
    private final int totalHits;
    private final double slope;
    private final double cosine;
    private final double sine;
    private double degrees;
    public boolean end;
    private final ArrayList<Integer> alrHit = new ArrayList<>();

    public Bullet(int startX, int startY, int endX, int endY, int dmg, int totalHits, GamePanel gp) {
        super();
        x = startX;
        y = startY;
        //this.endX = endX;
        //this.endY = endY;
        damage = dmg;
        hits = 0;
        this.totalHits = totalHits;
        slope = Math.sqrt((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY));
        cosine = (endX - startX)/slope;
        sine = (endY - startY)/slope;
        degrees = Math.asin(sine) * 180 / 3.14159265359;
        getBulletImage();
        this.gp = gp;
    }

    public void getBulletImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/bullet/Bullet.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        dist += 20;
        double speed = 20;
        double addX = (speed * cosine);
        double addY = (speed * sine);
        x += addX;
        y += addY;
        if(x<0 || x>gp.getScreenWidth() || y<0 || y>gp.getScreenHeight() || dist >= 1200)
            end = true;
        for(int i=gp.enemies.size()-1 ;i>=0; i--) {
            if(Math.abs(x - gp.enemies.get(i).x) <= gp.getTileSize()*0.75 &&
            Math.abs(y - gp.enemies.get(i).y) <= gp.getTileSize()*0.75 &&
            !checkHit(i)) {
                if(hits >= totalHits-1) {
                    gp.enemies.get(i).hp -= damage;
                    end = true;
                    break;
                }
                hits++;
                gp.enemies.get(i).hp -= damage;
                if(gp.enemies.get(i).hp > 0) {
                    alrHit.add(i);
                }
                break;
            }
        }
    }

    private boolean checkHit(int i) {
        for(int n: alrHit) {
            if(i==n) {
                return true;
            }
        }
        return false;
    }

    public void makeSides(int spread, int dmg) {
        if(cosine > 0 || cosine == 0 && sine > 0) {
            gp.shots.add(new Bullet((int)x, (int)y, (int)(x + Math.cos((degrees + spread) * 3.14159265359 / 180) * slope), (int)(y + Math.sin((degrees + spread) * 3.14159265359 / 180) * slope), dmg, totalHits, gp));
            gp.shots.add(new Bullet((int)x, (int)y, (int)(x + Math.cos((degrees - spread) * 3.14159265359 / 180) * slope), (int)(y + Math.sin((degrees - spread) * 3.14159265359 / 180) * slope), dmg, totalHits, gp));
        }
        else if(cosine < 0 || cosine == 0 && sine < 0) {
            gp.shots.add(new Bullet((int)x, (int)y, (int)(x - Math.cos((degrees + spread) * 3.14159265359 / 180) * slope), (int)(y + Math.sin((degrees + spread) * 3.14159265359 / 180) * slope), dmg, totalHits, gp));
            gp.shots.add(new Bullet((int)x, (int)y, (int)(x - Math.cos((degrees - spread) * 3.14159265359 / 180) * slope), (int)(y + Math.sin((degrees - spread) * 3.14159265359 / 180) * slope), dmg, totalHits, gp));
        }
    }

    public void draw(Graphics2D g2, int tileSize) {
        g2.drawImage(image, (int)x, (int)y, tileSize, tileSize, null);
    }

    public String toString() {
        return "1";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}
