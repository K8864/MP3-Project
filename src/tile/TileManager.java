package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public static Point[] Turns = {new Point(6-1,12-1), new Point(6-1,8-1), new Point(11-1, 8-1),
    new Point(11-1, 12-1), new Point(14-1, 12-1), new Point(14-1, 10-1), new Point(18-1, 10-1),
    new Point(18-1, 15-1), new Point(22-1, 15-1), new Point(22-1, 6-1), new Point(16-1, 6-1),
    new Point(16-1, 2-1), new Point(26-1, 2-1), new Point(26-1, 17-1), new Point(30-1, 17-1)};

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];
        loadMap("/maps/Big.txt");
    }

    public void getTileImage(){
        setUp(0, "00", false);
        setUp(1, "01", false);
        setUp(2, "02", false);
        setUp(3, "03", false);
        setUp(4, "04", true);
        setUp(5, "05", false);
        setUp(6, "06", false);
        setUp(7, "07", false);
        setUp(8, "08", false);
        setUp(9, "09", false);
    }

    public void setUp(int index, String imagePath, boolean path) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png")));
            tile[index].setImage(uTool.scaleImage(tile[index].getImage(), gp.getTileSize(), gp.getTileSize()));
            tile[index].setPath(path);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
                String line = br.readLine();
                while(col < gp.getMaxScreenCol()) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                col = 0;
                row++;
            }
            br.close();
        } catch(Exception e) {

        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        while(worldCol<gp.getMaxScreenCol() && worldRow<gp.getMaxScreenRow()){
            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            g2.drawImage(tile[mapTileNum[worldCol][worldRow]].getImage(), worldX, worldY, gp.getTileSize(), gp.getTileSize(), null);
            worldCol++;
            if(worldCol == gp.getMaxScreenCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}