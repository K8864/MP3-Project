package main;

import enemies.Enemy;
import player.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;

public class ClickDetection implements MouseListener {
    GamePanel gp;
    public Tower t;
    public static int x = 0;
    public static int y = 0;
    public static boolean click = false;
    private static int count = 0;
    public static boolean isPath;
    public static boolean isWater;
    public static boolean edgeWater;
    public static boolean placeable;

    public void update() {
        x = MouseInfo.getPointerInfo().getLocation().x- Main.getWindow().getLocation().x-7;
        y = MouseInfo.getPointerInfo().getLocation().y-Main.getWindow().getLocation().y-30;
        if(count >= 5) {
            click = false;
            count = 0;
        }
        count++;
        onPath();
        onWater();
        canPlace();
    }

    public void select() {
        if (KeyHandler.press1 && Stats.cash >= Tower1.price) {
            t = new Tower1(gp);
            if (ClickDetection.click && !ClickDetection.isPath && !isWater && !edgeWater && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press1 = false;
                Tower1 tower = (Tower1)t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower1.price;
                sortTowers();
            }
        }
        else if(KeyHandler.press2 && Stats.cash >= Tower2.price) {
            t = new Tower2(gp);
            if (ClickDetection.click && !ClickDetection.isPath && !isWater && !edgeWater && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press2 = false;
                Tower2 tower = (Tower2)t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower2.price;
                sortTowers();
            }
        }
        else if(KeyHandler.press3 && Stats.cash >= Tower3.price) {
            t = new Tower3(gp);
            if (ClickDetection.click && !ClickDetection.isPath && !isWater && !edgeWater && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press3 = false;
                Tower3 tower = (Tower3) t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower3.price;
                sortTowers();
            }
        }
        else if(KeyHandler.press4 && Stats.cash >= Tower4.price) {
            t = new Tower4(gp);
            if (ClickDetection.click && !ClickDetection.isPath && !isWater && !edgeWater && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press4 = false;
                Tower4 tower = (Tower4) t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower4.price;
                sortTowers();
            }
        }
        else if(KeyHandler.press5 && Stats.cash >= Tower5.price) {
            t = new Tower5(gp);
            if (ClickDetection.click && !ClickDetection.isPath && isWater && !edgeWater && ClickDetection.placeable) {
                Tower.selecting = false;
                KeyHandler.press5 = false;
                Tower5 tower = (Tower5) t;
                tower.finalizeThings();
                gp.towers.add(tower);
                Tower.count++;
                Stats.cash -= Tower5.price;
                sortTowers();
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

    public void onWater() {
        try {
            if (gp.tileM.tile[gp.tileM.mapTileNum[(int)(x - gp.getTileSize()/4.5) / gp.getTileSize()][y / gp.getTileSize()]].isWater() &&
            gp.tileM.tile[gp.tileM.mapTileNum[(int)(x + gp.getTileSize()/4.5) / gp.getTileSize()][y / gp.getTileSize()]].isWater() &&
            gp.tileM.tile[gp.tileM.mapTileNum[(int)(x - gp.getTileSize()/4.5) / gp.getTileSize()][(int)(y + gp.getTileSize()/2.2) / gp.getTileSize()]].isWater() &&
            gp.tileM.tile[gp.tileM.mapTileNum[(int)(x + gp.getTileSize()/4.5) / gp.getTileSize()][(int)(y + gp.getTileSize()/2.2) / gp.getTileSize()]].isWater()) {
                isWater = true;
                edgeWater = false;
            }
            else if (gp.tileM.tile[gp.tileM.mapTileNum[(int)(x) / gp.getTileSize()][y / gp.getTileSize()]].isWater() ||
            gp.tileM.tile[gp.tileM.mapTileNum[(int)(x) / gp.getTileSize()][(int)(y + gp.getTileSize()/2.2) / gp.getTileSize()]].isWater()) {
                edgeWater = true;
                isWater = false;
            }
            else {
                isWater = false;
                edgeWater = false;
            }
        }
        catch (Exception e) {
            isWater = true;
        }
    }

    public void canPlace() {
        if(Tower.selecting) {
            placeable = true;
            for (Tower e : gp.towers) {
                if(ClickDetection.x >= e.getFinalX() &&
                ClickDetection.x <= e.getFinalX() + gp.getTileSize() &&
                ClickDetection.y >= e.getFinalY() &&
                ClickDetection.y <= e.getFinalY() + gp.getTileSize()) {
                    placeable = false;
                    break;
                }
            }
        }
    }

    public void sortTowers() {
        gp.towers.sort(new Comparator<>() {
            @Override
            public int compare(Tower e1, Tower e2) {
                return Double.compare(e1.getFinalY(), e2.getFinalY());
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });
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