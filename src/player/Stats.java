package player;

import main.GamePanel;

public class Stats {
    GamePanel gp;
    public static int maxHp = 250;
    public static int hp = 250;
    public static int cash = 500;
    public static boolean dead;

    public Stats(GamePanel gp) {
        this.gp = gp;
    }

    public static void die() {
        dead = true;
        GamePanel.gameState = GamePanel.deadState;
        hp = 0;
    }
}
