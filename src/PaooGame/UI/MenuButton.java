package PaooGame.UI;
import PaooGame.States.*;
import PaooGame.RefLinks;
import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.Graphics.*;
public class MenuButton {
    protected float x;                  /*!< Pozitia pe axa X a unui buton.*/
    protected float y;                  /*!< Pozitia pe axa Y a unui buton.*/
    public static final int BTN_WIDTH  = 178;                       /*!< Latimea unei dale.*/
    public static final int BTN_HEIGHT = 60;                       /*!< Inaltimea unei dale.*/
    public boolean isSelected = false;

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    public MenuButton(BufferedImage image)
    {
        img = image;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }
    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
        /// Desenare
        g.drawImage(img, x, y, BTN_WIDTH, BTN_HEIGHT, null);
    }
}
