package PaooGame.Items;

import PaooGame.RefLinks;

public class EnemyFactory{
    public static Enemy enemyMaker(RefLinks refLink, float x, float y) {
        return new Enemy(refLink,x,y);
    }
}
