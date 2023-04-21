package PaooGame;
import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class Camera {
    protected float x;                  /*!< Pozitia pe axa X a camerei.*/
    protected float y;                  /*!< Pozitia pe axa Y a camerei.*/
    protected int width;                /*!< Latimea camerei.*/
    protected int height;               /*!< Inaltimea camerei.*/

    protected RefLinks refLink;
    public int MapToCameraX() {
        return (int) (refLink.GetMap().GetWidth_Pixels() - this.x);
    }
    public int MapToCameraY() {
        return (int) (refLink.GetMap().GetHeigth_Pixels() - this.y);
    }
    public int CameraToMapX() {
        return (int) (this.x + refLink.GetMap().GetWidth_Pixels());
    }
    public int CameraToMapY() {
        return (int) (this.y + refLink.GetMap().GetHeigth_Pixels());
    }
    //player are poz ct, trb desenate dalele doar in int camera

}
