package PaooGame.UI;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.Graphics.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;


public class MainMenu {
    private BufferedImage background;
    private BufferedImage title;
    private BufferedImage copyright;

    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private MenuButton[] buttons = new MenuButton[3];

    public MainMenu(RefLinks refLink) {

        /// Retine referinta "shortcut".
        this.refLink = refLink;
        buttons[0] = new MenuButton(Assets.play);
        buttons[1] = new MenuButton(Assets.settings);
        buttons[2] = new MenuButton(Assets.credits);

    }
    public void Update() {

    }
    public void Draw(Graphics g)
    {
        background = ImageLoader.LoadImage("/textures/cer.png");
        title = ImageLoader.LoadImage("/textures/TITLE.png");
        copyright = ImageLoader.LoadImage("/textures/Erich21studios.png");
        g.drawImage(background, 0, 0, refLink.GetGame().GetWidth(), refLink.GetGame().GetHeight(),null);
        int y = 40;
        for(int i=0; i<3; i++) {
            buttons[i].Draw(g,20,y);
            y += 40;
        }

        g.drawImage(title,220,0,null);
        g.drawImage(copyright,650,400,null);
    }

}
