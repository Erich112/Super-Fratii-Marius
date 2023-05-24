package PaooGame.States;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;
import PaooGame.Tiles.*;

import java.awt.*;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Enemy enemy;
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/

    private Gun gun;

    private int xLvlOffset = 0;
    private int leftBorder = (int) (0.2 * refLink.GetGame().GetWidth());
    private int rightBorder = (int) (0.8 * refLink.GetWidth());
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        map = new Map(refLink);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul
        hero = new Hero(refLink,40, 300);
        enemy = EnemyFactory.enemyMaker(refLink, 500, 300);
        gun = new Gun(refLink,500,200,Tile.TILE_WIDTH,Tile.TILE_HEIGHT);
        maxTilesOffset = map.GetWidth_Pixels() - refLink.GetWidth();
        maxLvlOffsetX = maxTilesOffset * Tile.TILE_WIDTH;
    }
    private void checkCloseBorder() {
        int playerX = (int)hero.GetX();
        int diff = playerX - xLvlOffset;
        if (diff > rightBorder) {
            xLvlOffset += diff - rightBorder;
        }
        else if (diff < leftBorder) {
            xLvlOffset += diff - leftBorder;
        }

        if(xLvlOffset > maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = 0;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        map.Update();
        hero.Update();
        enemy.Update();
        checkCloseBorder();

        float xIndex = hero.GetX() / Tile.TILE_WIDTH + 1;
        float yIndex = hero.GetY() / Tile.TILE_HEIGHT + 1;
        int val = map.GetTile((int) xIndex, (int) yIndex).GetId();
        //System.out.printf("%f\t%f\t%d\n",xIndex, yIndex, val);

        //coliziuni cu iteme
        if (val == BallTile.ballTile.GetId()) {
            hero.score++;
            map.ChangeTile((int) xIndex, (int) yIndex,AirTile.airTile.GetId());
            //gun.SetXY(hero.GetX(),hero.GetY());
        }
        if (val == PillsTile.pillsTile.GetId()) {
            if(hero.life < 3)
                hero.life++;
            map.ChangeTile((int) xIndex, (int) yIndex,AirTile.airTile.GetId());
        }
        if (val == StarTile.starTile.GetId()) {
            map.ChangeTile((int) xIndex, (int) yIndex,AirTile.airTile.GetId());
            map.Clear = 1;
        }

        //coliziuni cu inamicii
        float xIndexE = enemy.GetX() / Tile.TILE_WIDTH + 1;
        float yIndexE = enemy.GetY() / Tile.TILE_HEIGHT + 1;
        if(xIndex==xIndexE && yIndex==yIndexE) {
            hero.life--;
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g,xLvlOffset);
        hero.Draw(g);
        enemy.Draw(g);
        gun.Draw(g);
        if(map.Clear==1) {
            g.drawString("            BRAVO!            ", 300,refLink.GetHeight()/2);
            g.drawString("APASA SPACE PT URMATORUL NIVEL", 300,refLink.GetHeight()/2 + 20);
        }
    }
}
