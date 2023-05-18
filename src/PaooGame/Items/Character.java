package PaooGame.Items;

import PaooGame.RefLinks;
import PaooGame.Tiles.GroundTile;
import PaooGame.Tiles.Tile;

/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item
{
    public static final int DEFAULT_LIFE            = 3;   /*!< Valoarea implicita a vietii unui caracter.*/
    public static final float DEFAULT_SPEED         = 3.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 48;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 64;   /*!< Inaltimea implicita a imaginii caracterului.*/

    public int life;     /*!< Retine viata caracterului.*/
    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/
    public float airSpeed = 0;
    public float gravity = 0.06f;
    public float jumpSpeed = -3.8f;
    public float fallSpeedAfterCollision = 0.75f;
    public boolean inAir = true;
    public int heldGun = 0;

    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
            ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
            //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
        life    = DEFAULT_LIFE;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului
     */
    public void Move()
    {
            ///Modifica pozitia caracterului pe axa X.
            ///Modifica pozitia caracterului pe axa Y.
        MoveX();
        MoveY();

    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */
    public void MoveX()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.

        x += xMove;

    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        y += yMove;
    }
    public void jumpAndGravity() {
        if(!inAir) {
            if(!IsEntityOnFloor(x,y)) {
                inAir = true;
            }
        }
        if(inAir) {
            if (CanMoveHere(x, y + airSpeed)) {
                y += airSpeed;
                airSpeed += gravity;
                MoveX();
            } else {
                y = GetEntityYPosUnderRoofOrAboveFloor(x,y, airSpeed);
                if (airSpeed > 0) {
                    inAir = false;
                    airSpeed = 0;
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xMove);
            }
        }
        else
        {
            updateXPos(xMove);
        }
    }
    public boolean CanMoveHere(float x, float y) {
        if(!testSolid(x,y))
            if(!testSolid(x+normalBounds.width,y+normalBounds.height))
                if(!testSolid(x+normalBounds.width,y))
                    if(!testSolid(x,y+normalBounds.height))
                        return true;
        return false;
    }
    public boolean testSolid(float x, float y) {
        if (x < 0 || x >= refLink.GetMap().GetWidth_Pixels()) {
            return true;
        }
        if (y < 0 || y >= refLink.GetMap().GetHeigth_Pixels()) {
            return true;

        }
        float xIndex = x / Tile.TILE_WIDTH;
        float yIndex = y / Tile.TILE_HEIGHT;
        int val = refLink.GetMap().GetTile((int) xIndex, (int) yIndex).GetId();
        if (val == GroundTile.groundTile.GetId()) {
            return true;
        } else return false;
    }
    public boolean IsEntityOnFloor(float x, float y) {
        // Check the pixel below bottomleft and bottomright
        if (!testSolid(x, y + height + 1))
            if (!testSolid(x + width, y + height + 1))
                return false;
        return true;

    }
    public float GetEntityYPosUnderRoofOrAboveFloor(float x,float y, float airSpeed) {
        int currentTile = (int) (y / Tile.TILE_HEIGHT);
        if (airSpeed > 0) {
            // Falling - touching floor
            int tileYPos = currentTile * Tile.TILE_HEIGHT;
            int yOffset = (height - Tile.TILE_HEIGHT);
            return tileYPos + yOffset + 20;
        } else
            // Jumping
            return currentTile * Tile.TILE_HEIGHT;

    }
    public float GetEntityXPosNextToWall(float x,float y, float xSpeed) {
        int currentTile = (int) (x / Tile.TILE_WIDTH);
        if (xSpeed > 0) {
            // Right
            int tileXPos = currentTile * Tile.TILE_WIDTH;
            int xOffset = (normalBounds.width - Tile.TILE_WIDTH );
            return tileXPos + xOffset + 30;
        } else
            // Left
            return currentTile * Tile.TILE_WIDTH;
    }
    public void updateXPos(float xSpeed) {
        if(CanMoveHere(x + xSpeed, y)) {
            x += xSpeed;
        } else {
            //thanks to this we'll have a new thing in HelpMethods :D
            x = GetEntityXPosNextToWall(x,y, xSpeed);
        }
    }
    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */
    public int GetLife()
    {
        return life;
    }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed()
    {
        return speed;
    }

    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
     */
    public void SetLife(int life)
    {
        this.life = life;
    }

    /*! \fn public void SetSpeed(float speed)
        \brief
     */
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }
}

