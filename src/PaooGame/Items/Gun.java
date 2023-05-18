package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Gun extends Item {
    public int bullets;
    private BufferedImage image;
    public Gun(RefLinks refLink, float x, float y, int width, int height)
    {
        ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
        image = Assets.GetInstance().gunLeft;
        bullets = 30;
        //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
    }
    public void SetXY(float Nx, float Ny){
        x = Nx;
        y = Ny;
    }
    @Override
    public void Update() {

    }
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

}
