package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.*;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character
{
    private int score = 0;

    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private float airSpeed = 0;
    private float gravity = 0.06f;
    private float jumpSpeed = -3.4f;
    private float fallSpeedAfterCollision = 0.75f;
    private boolean inAir = true;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
    {
            ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            ///Seteaza imaginea de start a eroului
        image = Assets.heroLeft;
            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 0;
        normalBounds.y = 0;
        normalBounds.width = 40;
        normalBounds.height = 60;

            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 48;
        attackBounds.height = 64;
    }
    public boolean CanMoveHere(float x, float y) {
        if(!testSolid(x,y))
            if(!testSolid(x+width,y+height))
                if(!testSolid(x+width,y))
                    if(!testSolid(x,y+height))
                        return true;
        return false;
    }
    public boolean testSolid(float x, float y) {
        if (x < 0 || x >= refLink.GetGame().GetWidth()) {
            return true;
        }
        if (y < 0 || y >= refLink.GetGame().GetHeight()) {
            return true;

        }
        float xIndex = x / Tile.TILE_WIDTH;
        float yIndex = y / Tile.TILE_HEIGHT;
        int val = refLink.GetMap().GetTile((int) xIndex, (int) yIndex).GetId();
        if (val == GroundTile.groundTile.GetId()) {
            return true;
        } else return false;
    }
    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        ///Verifica daca a fost apasata o tasta
        GetInput();
        ///Actualizeaza pozitia

        if (!inAir)
            if (!IsEntityOnFloor(x,y))
                inAir = true;

        if(inAir) {
            if(CanMoveHere(x,y+airSpeed)) {
                y += airSpeed;
                airSpeed += gravity;
                MoveX();
            }
            else {
                y = GetEntityYPosUnderRoofOrAboveFloor(x,y, airSpeed);
                if (airSpeed > 0) {
                    inAir = false;
                    airSpeed = 0;
                }
                else {
                    airSpeed = fallSpeedAfterCollision;
                }
                MoveX();
            }
        }
        else MoveX();




        /*
        if(CanMoveHere(x+xMove,y+yMove)) {
            Move();
        }
        */


        ///Actualizeaza imaginea
        if(refLink.GetKeyManager().left == true)
        {
            image = Assets.heroLeft;
        }
        if(refLink.GetKeyManager().right == true) {
            image = Assets.heroRight;
        }
    }
    public boolean IsEntityOnFloor(float x, float y) {
        // Check the pixel below bottomleft and bottomright
        if (!testSolid(x, y + height + 1))
            if (!testSolid(x + width, y + height + 1))
                return false;
        return true;

    }

    public float GetEntityYPosUnderRoofOrAboveFloor(float x, float y, float airSpeed) {
        int currentTile = (int) (y / Tile.TILE_HEIGHT);
        if (airSpeed > 0) {
            // Falling - touching floor
            int tileYPos = currentTile * Tile.TILE_HEIGHT;
            int yOffset = (height - Tile.TILE_HEIGHT);
            return tileYPos + yOffset + 16;
        } else
            // Jumping
            return currentTile * Tile.TILE_HEIGHT;

    }
    private void jump() {
        if(inAir)
            return;
       inAir = true;
       airSpeed =  jumpSpeed;
    }
    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up)
        {
            jump();
            //yMove = -speed;

        }
            ///Verificare apasare tasta "jos"
        else if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }
            ///Verificare apasare tasta "left"
        else if(refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }
            ///Verificare apasare tasta "dreapta"
        else if(refLink.GetKeyManager().right)
        {
            xMove = speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);

        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}
