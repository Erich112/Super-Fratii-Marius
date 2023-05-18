package PaooGame;
import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
public class Camera {
    private Rectangle bounds; // limitele vizibile ale camerei
    private double x; //coordonata x a camerei
    private double y; //coordonata y a camerei
    private double scale; // factorul de scalare al camerei
    // starea camerei; poziție și dimensiune
    public Camera(double x, double y, double width, double height) {
        this.bounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
        this.x = x;
        this.y = y;
        this.scale = 1.0;
    }
    // metoda prin care se setează poziția camerei
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // metoda prin care se setează factorul de scalare, daca este nevoie de el
    public void setScale(double scale) {
        this.scale = scale;
    }
    // metoda de aplicare a matricei de vizualizare a camerei
    public void apply(Graphics2D g2d) {
        AffineTransform transform = new AffineTransform();
        transform.translate(-x, -y); // translarea poziției camerei
        transform.scale(scale, scale); // aplicarea factorului de scală
        g2d.setTransform(transform);
    }
    // metoda returnează limitele vizibile ale camerei
    public Rectangle getBounds() {
        return bounds;
    }
}
