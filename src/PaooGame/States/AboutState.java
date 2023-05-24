package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{
    private BufferedImage background;
    private BufferedImage title;
    private BufferedImage copyright;
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        background = ImageLoader.LoadImage("/textures/cer.png");
        title = ImageLoader.LoadImage("/textures/TITLE.png");
        copyright = ImageLoader.LoadImage("/textures/Erich21studios.png");
        g.drawImage(background, 0, 0, refLink.GetGame().GetWidth(), refLink.GetGame().GetHeight(),null);
        g.drawImage(title,220,0,null);
        g.drawImage(copyright,650,400,null);
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("SUPER FRAȚII MARIUS - an Erich21 original game", 220,refLink.GetHeight()/2);
        g.drawString("Major characters by: KC & Adrian Iștoc", 220,refLink.GetHeight()/2 + 20);
        g.drawString("Assets, story and development: Erich21", 220,refLink.GetHeight()/2 + 40);
        g.drawString("Developed for: AC", 220,refLink.GetHeight()/2 + 60);
        g.drawString("Special thanks to: Kaarin Gaming, Walker", 220,refLink.GetHeight()/2 + 80);
        g.drawString("With the help of: Mihai Pipirig", 220,refLink.GetHeight()/2 + 100);
    }
}
