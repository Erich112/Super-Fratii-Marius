package PaooGame.Maps;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map
{
    private BufferedImage background;
    public static int currlevel = 1;
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    public int Clear = 0;

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public Map(RefLinks refLink) {
            /// Retine referinta "shortcut".
        this.refLink = refLink;
            ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld(currlevel);
        if(currlevel == 2)
            importImg("/textures/cercrazy1.png");
        else if (currlevel == 3)
            importImg("/textures/cercrazy2.png");
        else importImg("/textures/cer.png");
    }
    public int GetWidth_Tiles()
    {
        return width;
    }
    public int GetHeigth_Tiles()
    {
        return height;
    }
    public int GetWidth_Pixels()
    {
        return (width-1)*Tile.TILE_WIDTH;
    }
    public int GetHeigth_Pixels()
    {
        return height*Tile.TILE_HEIGHT;
    }

    private void importImg(String bg) {
        InputStream cer = getClass().getResourceAsStream(bg);
        try  {
            background = ImageIO.read(cer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try  {
                cer.close();
            }
            catch (IOException e) {
                e.printStackTrace();

            }
        }

    }


    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update()
    {


    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */


    //returneaza dimensiunile in pixeli


    public void Draw(Graphics g,int lvlOffset)
    {
        g.drawImage(background, 0, 0, Tile.TILE_WIDTH*width, height*Tile.TILE_HEIGHT,null);

            ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                GetTile(x, y).Draw(g, (int)(x * Tile.TILE_HEIGHT- lvlOffset), (int)y * Tile.TILE_WIDTH);
            }
        }
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y)
    {
        return Tile.GetTile(tiles[x][y]);
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld(int lvl)
    {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
            ///Se stabileste latimea hartii in numar de dale.
        width = 25;
            ///Se stabileste inaltimea hartii in numar de dale
        height = 10;
            ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
            //Se incarca matricea cu coduri
        Scanner input;
        if(lvl == 1) {
            try {
                input = new Scanner(new File("res/map1.txt"));
                for(int y = 0; y < height; y++)
                {
                    for(int x = 0; x < width; x++)
                    {
                        if(input.hasNextInt())
                            tiles[x][y] = input.nextInt();
                    }
                }
                input.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(lvl == 2) {
            try {
                input = new Scanner(new File("res/map2.txt"));
                for(int y = 0; y < height; y++)
                {
                    for(int x = 0; x < width; x++)
                    {
                        if(input.hasNextInt())
                            tiles[x][y] = input.nextInt();
                    }
                }
                input.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(lvl == 3) {
            try {
                input = new Scanner(new File("res/map3.txt"));
                for(int y = 0; y < height; y++)
                {
                    for(int x = 0; x < width; x++)
                    {
                        if(input.hasNextInt())
                            tiles[x][y] = input.nextInt();
                    }
                }
                input.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*! \fn private int MiddleEastMap(int x ,int y)
        \brief O harta incarcata static.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */
    public int[][] GetTileMap() {return tiles;}
    public void ChangeTile(int x, int y,int id) {
        tiles[x][y] = id;
    }
    public int GetLevel(){
        return currlevel;
    }
    public void SetLevel(int lvl){
        currlevel = lvl;
    }
}