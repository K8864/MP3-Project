package main;

import player.*;

import java.awt.*;
import java.awt.event.*;

public class ClickDetection implements MouseListener {
    GamePanel gp;
    public Tower t;
    public static int x = 0;
    public static int y = 0;
    public static boolean click = false;
    private static int count = 0;
    public static boolean isPath;
    public static boolean placeable;

    public void update() {
        x = MouseInfo.getPointerInfo().getLocation().x- Main.getWindow().getLocation().x-7;
        y = MouseInfo.getPointerInfo().getLocation().y-Main.getWindow().getLocation().y-30;
        if(count == 5) {
            click = false;
            count = 0;
        }
        count++;
        onPath();
        canPlace();
    }

    public void select() {
        if (KeyHandler.press1 && Stats.cash >= Tower1.price) {
            t = new Tower1(gp);
            if (ClickDetection.click && !ClickDetection.isPath && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press1 = false;
                Tower1 tower = (Tower1)t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower1.price;
            }
        }
        else if(KeyHandler.press2 && Stats.cash >= Tower2.price) {
            t = new Tower2(gp);
            if (ClickDetection.click && !ClickDetection.isPath && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press2 = false;
                Tower2 tower = (Tower2)t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower2.price;
            }
        }
        else if(KeyHandler.press3 && Stats.cash >= Tower3.price) {
            t = new Tower3(gp);
            if (ClickDetection.click && !ClickDetection.isPath && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press3 = false;
                Tower3 tower = (Tower3) t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower3.price;
            }
        }
        else if(KeyHandler.press4 && Stats.cash >= Tower4.price) {
            t = new Tower4(gp);
            if (ClickDetection.click && !ClickDetection.isPath && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press4 = false;
                Tower4 tower = (Tower4) t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower4.price;
            }
        }
        else
            t = null;
    }

    public ClickDetection(GamePanel gp) {
        this.gp = gp;
    }

    public void onPath() {
        try {
            if (gp.tileM.tile[gp.tileM.mapTileNum[(int)(x-gp.getTileSize()/4.5) / gp.getTileSize()][y / gp.getTileSize()]].isPath() ||
            gp.tileM.tile[gp.tileM.mapTileNum[(int)(x + gp.getTileSize()/4.5) / gp.getTileSize()][y / gp.getTileSize()]].isPath() ||
            gp.tileM.tile[gp.tileM.mapTileNum[(int)(x - gp.getTileSize()/4.5) / gp.getTileSize()][(int)(y + gp.getTileSize()/2.2) / gp.getTileSize()]].isPath() ||
            gp.tileM.tile[gp.tileM.mapTileNum[(int)(x + gp.getTileSize()/4.5) / gp.getTileSize()][(int)(y + gp.getTileSize()/2.2) / gp.getTileSize()]].isPath() ||
            x<0 || x>=gp.getScreenWidth() || y<0 || y>=gp.getScreenHeight()) {
                isPath = true;
            }
            else {
                isPath = false;
            }
        }
        catch (Exception e) {
            isPath = true;
        }
    }

    public void canPlace() {
        if(Tower.selecting) {
            placeable = true;
            for (Tower e : gp.towers) {
                if(ClickDetection.x >= e.getFinalX() - gp.getTileSize()*.5*.49 &&
                ClickDetection.x <= e.getFinalX() + gp.getTileSize()*1.5*.82 &&
                ClickDetection.y >= e.getFinalY() - gp.getTileSize()*.5 &&
                ClickDetection.y <= e.getFinalY() + gp.getTileSize()*1.5) {
                    placeable = false;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        click = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        click = false;
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}