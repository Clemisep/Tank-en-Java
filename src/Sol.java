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
            if(i == 0)
            {
                x[i] = -50;
                y[i] = 100;
            }
            else if(i == 1)
            {
                x[i] = 0;
                y[i] = y[0];
            }
            else if(i == 997)
            {
                x[i] = 1050;
                y[i] = y[996];
            }
            else if(i == 998)
            {
                x[i] = 1050;
                y[i] = -50;
            }
            else if(i == 999)
            {
                x[i] = -50;
                y[i] = -50;
            }
            else
            {
                x[i] = i;
                y[i] = courbe(i);
            }
        }
        y[0] = y[2];
        y[1] = y[2];
    }
    
    public void afficherSol()
    {
        StdDraw.filledPolygon(x,y);
        StdDraw.setPenColor(Color.decode("#5C9345"));
    }
    
    public static double getPosY(double posX)
    {
        int indexX = (int)(posX);
        double newY;
        newY = y[indexX];
        return newY;
    }
    
    public static double courbe(int i)
    {
        double fonction = (Math.sin(i/aleatoire)+Math.cos(i/(aleatoire + 30.0)))*(aleatoire) + 100.0;
        return fonction;
    }
}
