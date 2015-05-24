import java.awt.Color;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000, 600);
        StdDraw.setYscale(0, 600);
        StdDraw.setXscale(0, 1000);
        int joueur;
        joueur = 1;
        
        Tank tank1 = new Tank(100, 0);
        
        Tank tank2 = new Tank(900, 0);
        tank2.setxVie(900);
        
        Sol sol = new Sol();
        
        while(true)
        {
            StdDraw.clear(Color.WHITE);
            StdDraw.picture(500, 300, "Images/fond.png");
            sol.afficherSol();
            
            tank1.afficherTank(1);
            tank2.afficherTank(2);
            
            tank1.afficherVie(1);
            tank2.afficherVie(2);
            
            //On fait jouer le joueur sélectionné
            if(joueur == 1)
            {
                tank1.deplacement(10, 490);
                tank1.tirer(1);
                if((tank1.balleX < tank2.getPosX() + 35) && (tank1.balleX > tank2.getPosX() - 35) && (tank1.balleY < tank2.getPosY() + 35) && (tank1.balleY > tank2.getPosY() - 35))
                {
                    tank2.setVie(tank2.getVie() - 1);
                    tank2.setxVie(tank2.getxVie() + 1);
                    tank1.tir = false;
                    tank1.deplacer = true;
                    tank1.i = 0;
                }
            }
            else if(joueur == 2)
            {
                tank2.deplacement(510, 990);
                tank2.tirer(2);
            }
            //On change de joueur
            if(tank1.isFin() == true)
            {
                joueur = 2;
            }
            else if(tank2.isFin() == true)
            {
                joueur = 1;
            }
            StdDraw.show(20);
        }
    }
    
}
