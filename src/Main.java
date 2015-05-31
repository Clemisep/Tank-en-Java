import java.awt.Color;
import java.awt.Font;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Main {

    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000, 600);
        StdDraw.setYscale(0, 600);
        StdDraw.setXscale(0, 1000);
        playSound("RS.wav", (float) 6.0);
        Font gameOver = new Font("Arial", Font.BOLD, 30);
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
                    if(tank2.getVie() > 0)
                    {
                        tank2.setVie(tank2.getVie() - 10);
                        tank2.setxVie(tank2.getxVie() + 10);
                        tank1.balleX = 0;
                        tank1.fin = true;
                    }
                    tank1.tir = false;
                    tank1.deplacer = true;   
                }
            }
            else if(joueur == 2)
            {
                tank2.deplacement(510, 990);
                tank2.tirer(2);
                if((tank2.balleX < tank1.getPosX() + 35) && (tank2.balleX > tank1.getPosX() - 35) && (tank2.balleY < tank1.getPosY() + 35) && (tank2.balleY > tank1.getPosY() - 35))
                {
                    if(tank2.getVie() > 0)
                    {
                        tank1.setVie(tank1.getVie() - 10);
                        tank1.setxVie(tank1.getxVie() - 10);
                        tank2.balleX = 1000;
                        tank2.fin = true;
                    }
                    tank2.tir = false;
                    tank2.deplacer = true;
                }
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
            //Fin du jeu
            if(tank1.vie <= 0)
            {
                StdDraw.setPenColor(32,138,69);
                StdDraw.filledRectangle(0, 0, 1100, 700);
                StdDraw.setPenColor(Color.white);
                StdDraw.setFont(gameOver);
                StdDraw.text(500, 300, "Joueur 2 a gagné !");
            }
            else if(tank2.vie <= 0)
            {
                StdDraw.setPenColor(32,138,69);
                StdDraw.filledRectangle(0, 0, 1100, 700);
                StdDraw.setPenColor(Color.white);
                StdDraw.setFont(gameOver);
                StdDraw.text(500, 300, "Joueur 1 a gagné !");
            }
            StdDraw.show(20);
        }
    }
    
    public static synchronized void playSound(final String url,final float value) {
        new Thread(new Runnable() {
        // The wrapper thread is unnecessary, unless it blocks on the
        // Clip finishing; see comments.
            @Override
            public void run() {

                try {

                Clip clip = AudioSystem.getClip();


                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                Main.class.getResourceAsStream("Musique/" + url));
                clip.open(inputStream);
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
                volume.setValue(value);
                // Clip.LOOP_CONTINUOUSLY
                clip.loop(0);
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
