package waves;

import enemies.*;
import main.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Waves {
    GamePanel gp;
    public int nextWave;
    ArrayList<ArrayList> spawns = new ArrayList<>();
    public int frames = 0;
    public boolean done = false;
    public int cashGive = 50;

    public Waves(GamePanel gp, int wave) {
        this.gp = gp;
        nextWave = wave+1;
        readWave(wave);
    }

    public void readWave(int wave) {
        try {
            InputStream is = getClass().getResourceAsStream("/waves/wave" + wave + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int spawns = Integer.parseInt(br.readLine());
            for(int i=0; i<spawns; i++) {
                String enemy = br.readLine();
                int count = Integer.parseInt(br.readLine());
                int frame = Integer.parseInt(br.readLine());
                for(int j=0; j<count; j++) {
                    ArrayList arr = new ArrayList<>();
                    arr.add(enemy);
                    arr.add(frame);
                    this.spawns.add(arr);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        frames++;
        if((spawns.size()==0 && checkDone()) || (nextWave<18 && frames>=1800) || (nextWave>=18 && frames>=2700)) {
            done = true;
        }
        else if(spawns.size() != 0){
            if(frames >= (int)(spawns.get(0).get(1))) {
                frames = 0;
                if(spawns.get(0).get(0).equals("Red"))
                    gp.enemies.add(new Red(gp));
                else if(spawns.get(0).get(0).equals("Blue"))
                    gp.enemies.add(new Blue(gp));
                else if(spawns.get(0).get(0).equals("Green"))
                    gp.enemies.add(new Green(gp));
                else if(spawns.get(0).get(0).equals("Yellow"))
                    gp.enemies.add(new Yellow(gp));
                else if(spawns.get(0).get(0).equals("Pink"))
                    gp.enemies.add(new Pink(gp));
                else if(spawns.get(0).get(0).equals("Black"))
                    gp.enemies.add(new Black(gp));
                else if(spawns.get(0).get(0).equals("White"))
                    gp.enemies.add(new White(gp));
                else if(spawns.get(0).get(0).equals("Zebra"))
                    gp.enemies.add(new Zebra(gp));
                else if(spawns.get(0).get(0).equals("Rainbow"))
                    gp.enemies.add(new Rainbow(gp));
                spawns.remove(0);
            }
        }
    }

    private boolean checkDone() {
        for(int i=0; i<gp.enemies.size(); i++) {
            if(!gp.enemies.get(i).dead)
                return false;
        }
        return true;
    }
}