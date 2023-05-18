package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    private static Assets instance = new Assets();
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public BufferedImage heroLeft;
    public BufferedImage heroRight;
    public BufferedImage ball;
    public BufferedImage ground;
    public BufferedImage pills;
    public BufferedImage air;
    public BufferedImage enemyDog;
    public BufferedImage star;
    public BufferedImage gunRight;
    public BufferedImage gunLeft;
    public BufferedImage hp3;
    public BufferedImage hp2;
    public BufferedImage hp1;
    public BufferedImage play;
    public BufferedImage settings;
    public BufferedImage credits;
    public BufferedImage playSelected;
    public BufferedImage settingsSelected;
    public BufferedImage creditsSelected;
    public BufferedImage save;
    public BufferedImage load;
    public BufferedImage saveSelected;
    public BufferedImage loadSelected;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static Assets GetInstance() {
        return instance;
    }

    private Assets()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet ssheet = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png"));
        MenuSheet msheet =  new MenuSheet(ImageLoader.LoadImage("/textures/PaooGameMenuSheet.png"));
            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        ground = ssheet.crop(0, 0);
        ball = ssheet.crop(1, 0);
        air = ssheet.crop(2, 0);
        pills = ssheet.crop(3, 0);
        enemyDog = ssheet.crop(2,1);
        star = ssheet.crop(3, 1);
        heroLeft = ssheet.crop(0, 2);
        heroRight = ssheet.crop(1, 2);
        gunRight = ssheet.crop(1,1);
        gunLeft = ssheet.crop(0,1);
        hp3 = ssheet.crop(0,3);
        hp2 = ssheet.crop(1,3);
        hp1 = ssheet.crop(2,3);
        //meniu
        play = msheet.crop(0,0);
        settings = msheet.crop(0,1);
        credits = msheet.crop(0,2);
        save = msheet.crop(0,3);
        load = msheet.crop(0, 4);
        playSelected = msheet.crop(1,0);
        settingsSelected = msheet.crop(1,1);
        creditsSelected = msheet.crop(1,2);
        saveSelected = msheet.crop(1,3);
        loadSelected = msheet.crop(1,4);
    }
}
