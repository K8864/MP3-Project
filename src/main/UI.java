package main;

import player.*;
import waves.Waves;

import java.awt.*;

public class UI {
    private final GamePanel gp;
    private final Font font;
    private Graphics2D g2;
    private int commandNum = 2;
    private boolean play = true;
    public int boxY;

    public UI(GamePanel gp) {
        this.gp = gp;
        font = new Font("Arial", Font.PLAIN, 30);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(font);
        if(GamePanel.gameState == GamePanel.titleState) {
            drawTitleScreen();
        }
        else if(GamePanel.gameState == GamePanel.playState) {
            if(!Tower.click)
                drawSide();
            g2.setColor(Color.GREEN);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            g2.drawString("$" + Stats.cash, centerSideX("$" + Stats.cash, g2), gp.getScreenHeight()-gp.getTileSize());
            g2.setColor(Color.BLACK);
            g2.fillRect(gp.getScreenWidth() + 25, gp.getScreenHeight() - gp.getTileSize()*3, (int)(Stats.maxHp * 0.6), (gp.getTileSize()));
            g2.setColor(Color.RED);
            g2.fillRect(gp.getScreenWidth() + 25, gp.getScreenHeight() - gp.getTileSize()*3, (int)(Stats.hp * 0.6), (gp.getTileSize()));
            g2.setColor(Color.WHITE);
            g2.drawString("HP: " + Stats.hp, gp.getScreenWidth() + 25, gp.getScreenHeight() - (int)(gp.getTileSize()*2.25));
        }
        else {
            drawDeadScreen();
            drawSide();
            g2.setColor(Color.GREEN);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            g2.drawString("$" + Stats.cash, centerSideX("$" + Stats.cash, g2), gp.getScreenHeight()-gp.getTileSize());
            g2.setColor(Color.BLACK);
            g2.fillRect(gp.getScreenWidth() + 25, gp.getScreenHeight() - gp.getTileSize()*3, (int)(Stats.maxHp * 0.6), (gp.getTileSize()));
            g2.setColor(Color.RED);
            g2.fillRect(gp.getScreenWidth() + 25, gp.getScreenHeight() - gp.getTileSize()*3, (int)(Stats.hp * 0.6), (gp.getTileSize()));
            g2.setColor(Color.WHITE);
            g2.drawString("HP: " + Stats.hp, gp.getScreenWidth() + 25, gp.getScreenHeight() - (int)(gp.getTileSize()*2.25));
        }
    }

    public void drawSide() {
        g2.setColor(new Color(172, 153, 7));
        g2.fillRect(gp.getScreenWidth(), 0, 200, gp.getScreenHeight());
        Tower1 t1 = new Tower1(gp);
        Tower2 t2 = new Tower2(gp);
        Tower3 t3 = new Tower3(gp);
        Tower4 t4 = new Tower4(gp);
        Tower5 t5 = new Tower5(gp);
        int size = (int)(gp.getTileSize()*1.5);
        int add = (int)(gp.getTileSize()*.25);
        //tower 1
        int x = gp.getScreenWidth() + (int)(200-2*gp.getTileSize()*1.5)/3;
        int y = (int)(gp.getTileSize()-gp.getTileSize()*.25);
        if(Stats.cash >= 100)
            g2.setColor(new Color(0, 150, 255));
        else
            g2.setColor(new Color(255, 40, 0));
        g2.fillRect(x, y, size, size);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
        g2.setColor(Color.BLACK);
        g2.drawString("1", x, y-5);
        x += add;
        y += add;
        t1.display(g2, x, y);
        g2.setColor(Color.GREEN);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        x -= 7;
        y += 2*gp.getTileSize();
        g2.drawString("$100", x, y);
        y -= 2*gp.getTileSize();
        // tower 2
        x = gp.getScreenWidth() + 2 * (int)(200-2*gp.getTileSize()*1.5)/3 + (int)(gp.getTileSize()*1.5);
        y -= add;
        if(Stats.cash >= 300)
            g2.setColor(new Color(0, 150, 255));
        else
            g2.setColor(new Color(255, 40, 0));
        g2.fillRect(x, y, size, size);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
        g2.setColor(Color.BLACK);
        g2.drawString("2", x, y-5);
        x += add;
        y += add;
        t2.display(g2, x, y);
        g2.setColor(Color.GREEN);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        x -= 7;
        y += 2*gp.getTileSize();
        g2.drawString("$300", x, y);
        y -= 2*gp.getTileSize();
        // tower 3
        x = gp.getScreenWidth() + (int)(200-2*gp.getTileSize()*1.5)/3;
        y += 3*gp.getTileSize();
        if(Stats.cash >= 800)
            g2.setColor(new Color(0, 150, 255));
        else
            g2.setColor(new Color(255, 40, 0));
        g2.fillRect(x, y, size, size);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
        g2.setColor(Color.BLACK);
        g2.drawString("3", x, y-5);
        x += add;
        y += add;
        t3.display(g2, x, y);
        g2.setColor(Color.GREEN);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        x -= 7;
        y += 2*gp.getTileSize();
        g2.drawString("$800", x, y);
        y -= 2*gp.getTileSize();
        // tower 4
        x = gp.getScreenWidth() + 2 * (int)(200-2*gp.getTileSize()*1.5)/3 + (int)(gp.getTileSize()*1.5);
        y -= add;
        if(Stats.cash >= 250)
            g2.setColor(new Color(0, 150, 255));
        else
            g2.setColor(new Color(255, 40, 0));
        g2.fillRect(x, y, size, size);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
        g2.setColor(Color.BLACK);
        g2.drawString("4", x, y-5);
        x += add;
        y += add;
        t4.display(g2, x, y);
        g2.setColor(Color.GREEN);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        x -= 7;
        y += 2*gp.getTileSize();
        g2.drawString("$250", x, y);
        y -= 2*gp.getTileSize();
        // tower 5
        x = gp.getScreenWidth() + (int)(200-2*gp.getTileSize()*1.5)/3;
        y += 3*gp.getTileSize();
        if(Stats.cash >= 500)
            g2.setColor(new Color(0, 150, 255));
        else
            g2.setColor(new Color(255, 40, 0));
        g2.fillRect(x, y, size, size);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
        g2.setColor(Color.BLACK);
        g2.drawString("5", x, y-5);
        x += add;
        y += add;
        t5.display(g2, x, y);
        g2.setColor(Color.GREEN);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        x -= 7;
        y += 2*gp.getTileSize();
        g2.drawString("$800", x, y);
        y -= 2*gp.getTileSize();
    }

    public void drawTowerStats(Tower t, Graphics2D g2) {
        g2.setColor(new Color(172, 153, 7));
        g2.fillRect(gp.getScreenWidth(), 0, 200, gp.getScreenHeight());

        g2.setColor(Color.RED);
        int x = gp.getScreenWidth() + 50;
        int y = gp.getTileSize()/2;
        g2.fillRoundRect(x, y, 100, (int)(gp.getTileSize()*1.25), 20, 20);

        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
        String text = "Sell for $" + t.sellPrice;
        x = centerSideX(text, g2);
        y += (int)(gp.getTileSize()*1.5/2);
        g2.drawString(text, x, y);

        x = gp.getScreenWidth() + 40;
        y = gp.getTileSize() * 2;
        g2.setColor(new Color(140, 84, 39));
        for(int i=0; i<4; i++) {
            g2.fillRoundRect(x, y, 20, 20, 3, 3);
            x += 30;
        }
        x = gp.getScreenWidth() + 40;
        g2.setColor(Color.GREEN);
        for(int i=0; i<t.lvl; i++) {
            g2.fillRoundRect(x+2, y+2, 16, 16, 3, 3);
            x += 30;
        }
        x += 2;
        y += 2;
        g2.setColor(Color.BLACK);
        for(int i=0; i<4-t.lvl; i++) {
            g2.fillRoundRect(x, y, 16, 16, 3, 3);
            x += 30;
        }

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.setColor(Color.BLACK);
        text = "Damage:";
        x = centerSideX(text, g2);
        y += gp.getTileSize() * 2 - 2;
        g2.drawString(text, x, y);
        text = t.textDmg;
        x = centerSideX(text, g2);
        y += gp.getTileSize()*0.75;
        g2.drawString(text, x, y);

        text = "Range:";
        x = centerSideX(text, g2);
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        text = t.textRange;
        x = centerSideX(text, g2);
        y += gp.getTileSize()*0.75;
        g2.drawString(text, x, y);

        text = "Fire Rate:";
        x = centerSideX(text, g2);
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        text = t.textRate;
        x = centerSideX(text, g2);
        y += gp.getTileSize()*0.75;
        g2.drawString(text, x, y);

        if(!t.hasSpread)
            text = "Pierce:";
        else
            text = "Shots:";
        x = centerSideX(text, g2);
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        text = t.textPierce;
        x = centerSideX(text, g2);
        y += gp.getTileSize()*0.75;
        g2.drawString(text, x, y);

        text = "Upgrade";
        x = centerSideX(text, g2);
        y += gp.getTileSize() * 1.5;
        g2.drawString(text, x, y);

        g2.setColor(Color.GREEN);
        x = gp.getScreenWidth() + 50;
        y += gp.getTileSize()*.5;
        boxY = y;
        g2.fillRoundRect(x, y, 100, (int)(gp.getTileSize()*1.25), 20, 20);

        text = "$" + t.nextLvlCost;
        y += (int)(gp.getTileSize()*1.5/2);
        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
        x = centerSideX(text, g2);
        g2.drawString(text, x, y);
    }

    public void drawTitleScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "TOWER DEFENSE";
        int x = centerX(text);
        int y = (int)(gp.getTileSize()*1.5*3);
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Play";
        x = centerX(text);
        y = (int)(gp.getTileSize()*1.5*7);
        if(play)
            g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.setColor(Color.GRAY);
            g2.drawRect((int)(x-gp.getTileSize()*1.5), (int)(y-gp.getTileSize()*1.5), (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()*2, (int)(gp.getTileSize()*1.5+16));
            g2.setColor(Color.WHITE);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Don't Play";
        x = centerX(text);
        y = (int)(gp.getTileSize()*1.5*8.5);
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.setColor(Color.GRAY);
            g2.drawRect((int)(x-gp.getTileSize()*1.5), (int)(y-gp.getTileSize()*1.5), (int)(g2.getFontMetrics().getStringBounds(text, g2).getWidth()*1.5), (int)(gp.getTileSize()*1.5+16));
        }
    }

    public void drawDeadScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        g2.setColor(Color.RED);
        String text = "GGs, you survived " + (gp.wave.nextWave-1) + " waves";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        int x = centerX(text) - 100;
        int y = gp.getScreenHeight()/2 - gp.getTileSize();
        g2.drawString(text, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 36F));
        g2.setColor(Color.WHITE);
        y += gp.getTileSize() * 2;
        x = centerX(text) - 60;
        g2.drawString("(You kinda suck ngl)", x, y);
    }

    public int centerX(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.getScreenWidth()+200)/2 - length/2;
    }

    public int centerSideX(String text, Graphics2D g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getCenterX();
        return gp.getScreenWidth() + (100 - (length));
    }

    public void update() {
        if(ClickDetection.y >= gp.getTileSize()*1.5*7 - (gp.getTileSize()*1.5+16) && ClickDetection.y <= gp.getTileSize()*1.5*6 + (gp.getTileSize()*1.5+16) && play) {
            commandNum = 0;
            if(ClickDetection.click)
                GamePanel.gameState = GamePanel.playState;
        }
        else if(ClickDetection.y >= gp.getTileSize()*1.5*8.5 - (gp.getTileSize()*1.5+16) && ClickDetection.y <= gp.getTileSize()*1.5*7.5 + (gp.getTileSize()*1.5+16)) {
            commandNum = 1;
            if(ClickDetection.click)
                play = false;
        }
        else commandNum = 2;
    }
}
