
import java.awt.Color;

public class Sol {
    public static double[] x;
    public static double[] y;
    public static double aleatoire = (double)(20.0 + (int) (Math.random()*(40.0)));
    
    Sol()
    {
        x = new double[1000];
        y = new double[1000];
        int i;
        
        for(i = 0; i < 1000; i++)
        {
            x[i] = i;
            y[i] = courbe(i);
        }
        //On donne les valeurs des points extremes qui sont invariants.
        x[0] = -50;
        y[0] = y[2];
        
        y[1] = y[1];
        
        x[997] = 1050;
        y[997] = y[996]; 
        
        x[998] = 1050;
        y[998] = -50;
        
        x[999] = -50;
        y[999] = -50;
        
    }
    
    public void afficherSol()
    {
        //On utilise un polygone pour créer le sol, il est composé des tableaux de valeurs créés au dessus.
        StdDraw.setPenColor(Color.decode("#5C9345"));
        StdDraw.filledPolygon(x,y);
    }
    
    public static double getPosY(double posX)
    {
        //Cela permet de récupérer la valeur y d'un point du sol a la position x donnée.
        int indexX = (int)(posX);
        double newY;
        if(indexX > 999 || indexX < 1)
        {
            return 0;
        }
        else
        {
            newY = y[indexX];
            return newY;
        }
    }
    
    public static double courbe(int i)
    {
        //Fonction donnant la forme courbée et aleatoire du sol.
        double fonction = (Math.sin(i/aleatoire)+Math.cos(i/(aleatoire + 30.0)))*(aleatoire) + 100.0;
        return fonction;
    }
    
    public static void detruire(int x)
    {
        //Méthode gérant la destruction du sol.
        int o;
        if(x > 990)
        {
            for(o = x - 10; o < 995; o++)
            {
                y[o] = y[o] - (10 - Math.abs(x - o));
            }
        }
        else if(x < 10)
        {
            for(o = 5; o < x + 10; o++)
            {
                y[o] = y[o] - (10 - Math.abs(x - o));
            }
        }
        else
        {
           for(o = x - 10; o < x + 10; o++)
            {
                y[o] = y[o] - (10 - Math.abs(x - o));
            } 
        }            
    }
}
