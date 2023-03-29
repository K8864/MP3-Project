package enemies;

import main.GamePanel;
import player.Entity;
import player.Stats;
import tile.TileManager;

import java.awt.*;

public class Enemy extends Entity {
    GamePanel gp;

    public int hp;
    double speed;
    String direction = "right";
    int turns = 0;
    double distanceTraveled;
    public double getDistanceTraveled() {return distanceTraveled;}
    public boolean dead = false;
    public int cash;

    public Enemy(GamePanel gp) {
        this.gp = gp;
        x = 0;
        y = 11*gp.getTileSize();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)x, (int)y, gp.getTileSize(), gp.getTileSize(), null);
    }

    public void turn() {
        if (2 >= Math.abs(TileManager.Turns[turns].x * gp.getTileSize()-x) && 2 >= Math.abs(TileManager.Turns[turns].y * gp.getTileSize()-y)) {
            x = TileManager.Turns[turns].x * gp.getTileSize();
            y = TileManager.Turns[turns].y * gp.getTileSize();
            turns++;
            if (turns == 1) {
                direction = "up";
            }
            else if (turns == 2) {
                direction = "right";
            }
            else if (turns == 3) {
                direction = "down";
            }
            else if (turns == 4) {
                direction = "right";
            }
            else if (turns == 5) {
                direction = "up";
            }
            else if (turns == 6) {
                direction = "right";
            }
            else if (turns == 7) {
                direction = "down";
            }
            else if (turns == 8) {
                direction = "right";
            }
            else if (turns == 9) {
                direction = "up";
            }
            else if (turns == 10) {
                direction = "left";
            }
            else if (turns == 11) {
                direction = "up";
            }
            else if (turns == 12) {
                direction = "right";
            }
            else if (turns == 13) {
                direction = "down";
            }
            else if (turns == 14) {
                direction = "right";
            }
        }
    }

    public void update() {
        turn();
        if(direction.equals("right")) {
            x += speed;
        }
        else if(direction.equals("left")) {
            x -= speed;
        }
        else if(direction.equals("down")) {
            y += speed;
        }
        else if(direction.equals("up")) {
            y -= speed;
        }
        distanceTraveled += speed;
        if(hp <= 0) {
            dead = true;
            Stats.cash += cash;
        }
    }

    public String toString() {
        return ";";
    }
    public boolean equals(Entity other) {
        return other == this;
    }
}