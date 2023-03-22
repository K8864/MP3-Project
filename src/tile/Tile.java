package tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    public BufferedImage getImage() {return image;}
    public void setImage(BufferedImage image) {this.image = image;}
    private boolean path = false;
    public boolean isPath() {return path;}
    public void setPath(boolean path) {this.path = path;}
}
