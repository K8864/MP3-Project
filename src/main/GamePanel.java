package main;

import enemies.*;
import player.*;
import tile.TileManager;
import wave.Waves;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {

    //stuff
    private final int originalTileSize = 16;
    public static double scaleX = 2;
    public static double scaleY = 2;
    private final int tileSize = (int)(originalTileSize * scaleX + 0.5);
    public int getTileSize() {return tileSize;}
    private final int maxScreenCol = 30;
    private final int maxScreenRow = 18;
    private final int screenWidth = tileSize * maxScreenCol;
    public int getScreenWidth() {return screenWidth;}
    private final int screenHeight = tileSize * maxScreenRow;
    public int getScreenHeight() {return screenHeight;}
    public int getMaxScreenCol() {return maxScreenCol;}
    public int getMaxScreenRow() {return maxScreenRow;}

    //game state
    public static int titleState = 0;
    public static int playState = 1;
    public static int deadState = 2;
    public static int gameState;

    //entities
    public ArrayList<Tower> towers = new ArrayList<>();
    public ArrayList<Bullet> shots = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();

    //classes
    private Thread gameThread;
    public TileManager tileM = new TileManager(this);
    private final KeyHandler keyH = new KeyHandler(this);
    public Sound sound = new Sound();
    private final ClickDetection clickD = new ClickDetection(this);
    public UI ui = new UI(this);
    public Waves wave;
    Tower t;

    // for requirement 9x.
    Tower[] required = new Tower[] {new Tower1(this), new Tower2(this)};

    //fps
    public int FPS = 60;
    private int frames = 0;

    //waves
    public boolean spawn = true;
    public int betweenWave = 0;

    public void setUpGame() {
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth + 200, screenHeight));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.addMouseListener(clickD);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        FPS = keyH.fps;
        double drawInterval; // 0.0167 seconds
        double nextDrawTime;
        while (gameThread != null) {
            FPS = keyH.fps;
            drawInterval = 1000000000 / FPS;
            nextDrawTime = System.nanoTime() + drawInterval;
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        clickD.update();
        if(gameState == titleState) {
            ui.update();
        }
        else if(gameState == playState) {
            try {
                enemies.sort(new EnemyComparator());
            }
            catch(Exception e) {
                System.out.println("modding");
            }
            if(spawn && frames >= 300) {
                wave = new Waves(this, 1);
                spawn = false;
            }
            if(wave != null &&!wave.done)
                wave.update();
            else if(wave != null && betweenWave >= 300) {
                betweenWave = 0;
                try {
                    wave = new Waves(this, wave.nextWave);
                    wave.cashGive = 50 + (wave.nextWave-3) * 10;
                    Stats.cash += wave.cashGive;
                } catch (Exception e) {
                    System.out.print("");
                }
            }
            else {
                betweenWave++;
            }
            clickD.select();
            t = clickD.t;
            if(Stats.hp <= 0) {
                Stats.die();
            }
            Tower.click = false;
            for(int i=0; i<enemies.size(); i++) {
                try {
                    enemies.get(i).update();
                    if(enemies.get(i).dead) {
                        enemies.set(i, null);
                        playSE(2, 6);
                        enemies.remove(i);
                        i--;
                    }
                }
                catch (Exception e) {
                    Stats.hp -= enemies.get(i).hp;
                    enemies.set(i, null);
                    enemies.remove(i);
                    i--;
                }
            }
            for(int i=0; i<shots.size(); i++) {
                if((shots.get(i)).end) {
                    shots.set(i, null);
                    shots.remove(i);
                    i--;
                }
                else
                    (shots.get(i)).update();
            }
            for (int i=0; i<towers.size(); i++) {
                towers.get(i).update();
                if(towers.get(i).sold) {
                    towers.set(i, null);
                    towers.remove(i);
                    i--;
                }
                else if(towers.get(i).callUpgrade) {
                    towers.get(i).upgrade();
                }
            }
            frames++;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        long drawStart = 0;
        if (keyH.isShowDrawTime())
            drawStart = System.nanoTime();
        tileM.draw(g2);
        if(gameState != titleState) {
            if (t != null) {
                if (Tower.selecting)
                    t.draw2(g2);
            }
            try {
                for(Enemy e : enemies) {
                    e.draw(g2);
                }
                for(Bullet b : shots) {
                    b.draw(g2, tileSize);
                }
                for(Tower e : towers) {
                    e.draw(g2);
                }
            }
            catch (Exception e) {
                System.out.print("");
            }
        }
        ui.draw(g2);

        if(keyH.isShowDrawTime()) {
            Stats.cash = 999999;
            Stats.hp = 250;
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 36F));
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.RED);
            g2.drawString("Draw Time: " + passed, 10, 500);
            g2.drawString("y: " + ClickDetection.y, 10, 450);
            g2.drawString("X: " + ClickDetection.x, 10 , 400);
            g2.drawString("Towers: " + Tower.count, 10, 350);
        }

        g2.dispose();
    }

    public void playSE(int i, int v) {
        try {
            sound.setFile(i);
            sound.scaleVol(v);
            sound.play();
        }
        catch(Exception E) {
            
        }
    }
    // This meets 9 ix, subclasses of Enemy are being put through this
    private static class EnemyComparator implements Comparator<Enemy> {
        @Override
        public int compare(Enemy e1, Enemy e2) {
            return Double.compare(e1.getDistanceTraveled(), e2.getDistanceTraveled());
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
