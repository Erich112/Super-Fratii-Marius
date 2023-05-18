package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.*;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character
{
    public int score = 0;
    private static Hero instance;
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
    {
            ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            ///Seteaza imaginea de start a eroului
        image = Assets.GetInstance().heroLeft;
            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 0;
        normalBounds.y = 0;
        normalBounds.width = 30;
        normalBounds.height = 50;

            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 48;
        attackBounds.height = 64;
    }
    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        ///Verifica daca a fost apasata o tasta
        GetInput();
        ///Actualizeaza pozitiadw
        //cu gravitatie
        jumpAndGravity();



        /* fara gravitatie
        if(CanMoveHere(x,y))
            Move();
        */

        ///Actualizeaza imaginea
        if(refLink.GetKeyManager().left == true)
        {
            image = Assets.GetInstance().heroLeft;
        }
        if(refLink.GetKeyManager().right == true) {
            image = Assets.GetInstance().heroRight;
        }
    }


    private void jump() {
        if(inAir) {
            return;
        }
       inAir = true;
       airSpeed =  jumpSpeed;
    }
    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up)
        {
            jump();
            //yMove = -speed;

        }
            ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().down)
        {
            yMove += speed;
        }
            ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left)
        {
            xMove += -speed;
        }
            ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right)
        {
            xMove += speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawImage(image, (int)x, (int)y, width, height, null);
        if (life == 3)
            g.drawImage(Assets.GetInstance().hp3, (int)x, (int)y - 20, width, height, null);
        if (life == 2)
            g.drawImage(Assets.GetInstance().hp2, (int)x, (int)y - 20, width, height, null);
        if (life == 1)
            g.drawImage(Assets.GetInstance().hp1, (int)x, (int)y - 20, width, height, null);
        g.drawString("Score: "+ score, 20,20);
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int) (x + xDrawOffset), (int) (y + yDrawOffset), normalBounds.width, normalBounds.height);
    }
}
