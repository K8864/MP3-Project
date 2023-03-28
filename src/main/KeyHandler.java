package main;


import player.*;
import enemies.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    private boolean showDrawTime = false;
    public int fps = 60;
    public static boolean press1 = false;
    public static boolean press2 = false;
    public static boolean press3 = false;
    public static boolean press4 = false;
    public static boolean press5 = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean isShowDrawTime() {
        return showDrawTime;
    }

    //useless
    @Override
    public void keyTyped(KeyEvent e) {}
    //useless

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Towers
        if(code == KeyEvent.VK_1) {
            press1 = !press1;
            press2 = false;
            press3 = false;
            press4 = false;
            press5 = false;
            Tower.selecting = press1;
        }
        else if(code == KeyEvent.VK_2) {
            press1 = false;
            press2 = !press2;
            press3 = false;
            press4 = false;
            press5 = false;
            Tower.selecting = press2;
        }
        else if(code == KeyEvent.VK_3) {
            press1 = false;
            press2 = false;
            press3 = !press3;
            press4 = false;
            press5 = false;
            Tower.selecting = press3;
        }
        else if(code == KeyEvent.VK_4) {
            press1 = false;
            press2 = false;
            press3 = false;
            press4 = !press4;
            press5 = false;
            Tower.selecting = press4;
        }
        else if(code == KeyEvent.VK_5) {
            press1 = false;
            press2 = false;
            press3 = false;
            press4 = false;
            press5 = !press5;
            Tower.selecting = press5;
        }
        // Towers
        // Balls
        if(code == KeyEvent.VK_P) {
            gp.enemies.add(new Red(gp));
        }
        if(code == KeyEvent.VK_O) {
            gp.enemies.add(new Blue(gp));
        }
        if(code == KeyEvent.VK_I) {
            gp.enemies.add(new Green(gp));
        }
        if(code == KeyEvent.VK_U) {
            gp.enemies.add(new Yellow(gp));
        }
        if(code == KeyEvent.VK_Y) {
            gp.enemies.add(new Pink(gp));
        }
        if(code == KeyEvent.VK_T) {
            gp.enemies.add(new Black(gp));
        }
        if(code == KeyEvent.VK_R) {
            gp.enemies.add(new White(gp));
        }
        if(code == KeyEvent.VK_E) {
            gp.enemies.add(new Zebra(gp));
        }
        if(code == KeyEvent.VK_W) {
            gp.enemies.add(new Rainbow(gp));
        }
        if(code == KeyEvent.VK_Q) {
            gp.enemies.add(new Ceramic(gp));
        }
        // Balls
        // Debug
        if(code == KeyEvent.VK_Z) {
            showDrawTime = !showDrawTime;
        }
        // Debug
        // Speed settings
        if(code == KeyEvent.VK_M) {
            fps = 60;
        }
        if(code == KeyEvent.VK_N) {
            fps = 120;
        }
        if(code == KeyEvent.VK_B) {
            fps = 180;
        }
        if(code == KeyEvent.VK_V) {
            fps = 240;
        }
        if(code == KeyEvent.VK_C) {
            fps = 300;
        }
        if(code == KeyEvent.VK_X) {
            fps = 600;
        }
        // Speed settings
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
