package main;

import player.*;

import java.awt.*;

public class UI {
    private final GamePanel gp;
    private final Font font;
    private Graphics2D g2;
    private int commandNum = 2;
    private boolean play = true;

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
            g2.setColor(Color.orange);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            g2.drawString("$" + Stats.cash, 25, 128);
            g2.setColor(Color.BLACK);
            g2.fillRect(25, 32, (int)(Stats.maxHp * 1.6), (int)(gp.getTileSize()*1.5));
            g2.setColor(Color.RED);
            g2.fillRect(25, 32, (int)(Stats.hp * 1.6), (int)(gp.getTileSize()*1.5));
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            g2.drawString("HP: " + Stats.hp, 25, 72);
            if(!Tower.click)
                drawSide();
        }

    }

    public void drawSide() {
        g2.setColor(new Color(172, 153, 7));
        g2.fillRect(gp.getScreenWidth(), 0, 200, gp.getScreenHeight());
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
        text = "Damage: " + t.dmg;
        x = centerSideX(text, g2);
        y += gp.getTileSize() * 2 - 2;
        g2.drawString(text, x, y);

        text = "Range: " + t.range;
        x = centerSideX(text, g2);
        y += gp.getTileSize();
        g2.drawString(text, x, y);

        text = "Fire Rate: " + (double)((int)(((double)t.rate/60)*100))/100 + " sec";
        x = centerSideX(text, g2);
        y += gp.getTileSize();
        g2.drawString(text, x, y);

        text = "Pierce: " + t.pierce;
        x = centerSideX(text, g2);
        y += gp.getTileSize();
        g2.drawString(text, x, y);

        text = "Upgrade";
        x = centerSideX(text, g2);
        y += gp.getTileSize() * 1.5;
        g2.drawString(text, x, y);

        g2.setColor(Color.GREEN);
        x = gp.getScreenWidth() + 50;
        y += gp.getTileSize()*.5;
        g2.fillRoundRect(x, y, 100, (int)(gp.getTileSize()*1.25), 20, 20);

        text = "$" + t.nextLvlCost;
        y += (int)(gp.getTileSize()*1.5/2);
        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 14F));
        x = centerSideX(text, g2);
        g2.drawString(text, x, y);

        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        y += gp.getTileSize() * 0.5;

        if(t.textRange != null) {
            text = "Range";
            x = centerSideX(text, g2);
            y += gp.getTileSize();
            g2.drawString(text, x, y);
            text = t.textRange;
            x = centerSideX(text, g2);
            y += gp.getTileSize() * .75;
            g2.drawString(text, x, y);
        }

        if(t.textRate != null) {
            text = "Fire Rate";
            x = centerSideX(text, g2);
            y += gp.getTileSize();
            g2.drawString(text, x, y);
            text = t.textRate;
            x = centerSideX(text, g2);
            y += gp.getTileSize() * .75;
            g2.drawString(text, x, y);
        }

        if(t.textDmg != null) {
            text = "Damage";
            x = centerSideX(text, g2);
            y += gp.getTileSize();
            g2.drawString(text, x, y);
            text = t.textDmg;
            x = centerSideX(text, g2);
            y += gp.getTileSize() * .75;
            g2.drawString(text, x, y);
        }

        if(t.textPierce != null) {
            text = "Pierce";
            x = centerSideX(text, g2);
            y += gp.getTileSize();
            g2.drawString(text, x, y);
            text = t.textPierce;
            x = centerSideX(text, g2);
            y += gp.getTileSize() * .75;
            g2.drawString(text, x, y);
        }
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
