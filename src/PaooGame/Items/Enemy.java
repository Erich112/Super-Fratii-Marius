package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.*;
public class Enemy extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    public Enemy(RefLinks refLink, float x, float y)
    {
        ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a eroului
        image = Assets.GetInstance().enemyDog;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 0;
        normalBounds.y = 0;
        normalBounds.width = 30;
        normalBounds.height = 50;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 48;
        attackBounds.height = 64;
    }
    @Override
    public void Update()
    {
        jumpAndGravity();
        x--;
    }
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        if (heldGun == 1)
            g.drawImage(Assets.GetInstance().gunLeft, (int)x, (int)y, width, height, null);
        if (life == 3)
            g.drawImage(Assets.GetInstance().hp3, (int)x, (int)y - 20, width, height, null);
        if (life == 2)
            g.drawImage(Assets.GetInstance().hp2, (int)x, (int)y, width, height, null);
        if (life == 1)
            g.drawImage(Assets.GetInstance().hp1, (int)x, (int)y, width, height, null);
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int) (x + xDrawOffset), (int) (y + yDrawOffset), normalBounds.width, normalBounds.height);
    }
}
