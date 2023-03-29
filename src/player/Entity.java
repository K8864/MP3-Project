package player;

import java.awt.image.BufferedImage;

public class Entity {
    protected double x, y;
    protected BufferedImage image, image2;
    public Entity() {
    }
    public String toString() {
        return "";
    }
    public boolean equals(Object other) {
        return other == this;
    }
}