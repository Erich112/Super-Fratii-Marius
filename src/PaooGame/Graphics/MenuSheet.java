package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala)
 */
public class MenuSheet
{
    private BufferedImage       menuSheet;        /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private static final int    btnWidth   = 178;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    btnHeight  = 60;   /*!< Inaltime unei dale din sprite sheet.*/

    /*! \fn public SpriteSheet(BufferedImage sheet)
        \brief Constructor, initializeaza spriteSheet.

        \param buffImg Un obiect BufferedImage valid.
     */
    public MenuSheet(BufferedImage buffImg)
    {
        /// Retine referinta catre BufferedImage object.
        menuSheet = buffImg;
    }

    /*! \fn public BufferedImage crop(int x, int y)
        \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        \param x numarul dalei din sprite sheet pe axa x.
        \param y numarul dalei din sprite sheet pe axa y.
     */
    public BufferedImage crop(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return menuSheet.getSubimage(x * btnWidth, y * btnHeight, btnWidth, btnHeight);
    }
}
