package PaooGame.UI;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.Graphics.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import static java.lang.Thread.sleep;

public class MainMenu {
    private BufferedImage background;
    private BufferedImage title;
    private BufferedImage copyright;

    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private MenuButton[] buttons;
    private int currentBtn = 0;

    private boolean updateButtons = true; // Acest flag marcheaza momentul in care butoanele din meniu pot fi updatate

    public MainMenu(RefLinks refLink) {
        Assets assets = Assets.GetInstance();

        /// Retine referinta "shortcut".
        this.refLink = refLink;
        buttons = new MenuButton[5];
        buttons[0] = new MenuButton(assets.play,  assets.playSelected);
        buttons[1] = new MenuButton(assets.save, assets.saveSelected);
        buttons[2] = new MenuButton(assets.load, assets.loadSelected);
        buttons[3] = new MenuButton(assets.settings, assets.settingsSelected);
        buttons[4] = new MenuButton(assets.credits, assets.creditsSelected);

        buttons[currentBtn].isSelected = true;
    }
    public void Update() {
        boolean keyPressed;
        int newBtnIndex = currentBtn;
        if(refLink.GetKeyManager().down){
            newBtnIndex++;
            keyPressed = true;
        }
        else if(refLink.GetKeyManager().up) {
            newBtnIndex--;
            keyPressed = true;
        }
        else {
            keyPressed = false;
        }

        // Updateaza butoanele la prima apasare a tastei, nu si atunci cand tasta continua sa fie apasata
        // Navigarea prin meniu se va face apasand in mod consecutiv o tasta
        if(!keyPressed){
            updateButtons = true;
        }
        else if(updateButtons && keyPressed){
            // Testeaza daca indexul pentru butoane nu este OutOfRange
            if(newBtnIndex>=0 && newBtnIndex < buttons.length)  currentBtn = newBtnIndex;

            // Reseteaza starea tuturor butoanelor si activeaza doar butonul selectat
            for(MenuButton bt: buttons){
                bt.Reset();
            }
            buttons[currentBtn].isSelected = true;

            // Blocheaza update-ul pe butoane pana cand se ridica tasta
            updateButtons = false;
        }

    }

    public MenuButton[] getButtons() {
        return buttons;
    }

    public void Draw(Graphics g)
    {
        background = ImageLoader.LoadImage("/textures/cer.png");
        title = ImageLoader.LoadImage("/textures/TITLE.png");
        copyright = ImageLoader.LoadImage("/textures/Erich21studios.png");
        g.drawImage(background, 0, 0, refLink.GetGame().GetWidth(), refLink.GetGame().GetHeight(),null);
        int y = 40;
        for(int i=0; i<buttons.length; i++) {
            buttons[i].Draw(g,20,y);
            y += 40;
        }

        g.drawImage(title,220,0,null);
        g.drawImage(copyright,650,400,null);
    }

}
