package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage ball;
    public static BufferedImage ground;
    public static BufferedImage pills;
    public static BufferedImage air;
    public static BufferedImage enemyDog;
    public static BufferedImage star;
    public static BufferedImage gunRight;
    public static BufferedImage gunLeft;
    public static BufferedImage play;
    public static BufferedImage settings;
    public static BufferedImage credits;

    public static BufferedImage playSelected;
    public static BufferedImage settingsSelected;
    public static BufferedImage creditsSelected;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet ssheet = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png"));
        MenuSheet msheet =  new MenuSheet(ImageLoader.LoadImage("/textures/PaooGameMenuSheet.png"));
            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        ground = ssheet.crop(0, 0);
        ball = ssheet.crop(1, 0);
        air = ssheet.crop(2, 0);
        pills = ssheet.crop(3, 0);
        enemyDog = ssheet.crop(3,1);
        star = ssheet.crop(3, 1);
        heroLeft = ssheet.crop(0, 2);
        heroRight = ssheet.crop(1, 2);
        gunRight = ssheet.crop(1,1);
        gunLeft = ssheet.crop(0,1);
        //meniu
        play = msheet.crop(0,0);
        settings = msheet.crop(0,1);
        credits = msheet.crop(0,2);
        playSelected = msheet.crop(1,0);
        settingsSelected = msheet.crop(1,1);
        creditsSelected = msheet.crop(1,2);
    }
}
