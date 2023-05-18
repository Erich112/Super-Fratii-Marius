package PaooGame;

import PaooGame.GameWindow.GameWindow;

public class Main
{
    public static void main(String[] args)
    {

        Game paooGame = new Game("SUPER FRAȚII MARIUS - dev", 960, 480);
        //fereastra jocului trebuie sa aiba dimensiuni proportionale cu dimensuinea hartii
        //fereastra (in dale) = 20 x 10
        //harta va fi mai lunga in lungime
        paooGame.StartGame();
    }
}
