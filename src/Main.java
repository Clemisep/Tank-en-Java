import java.awt.Color;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000, 600);
        StdDraw.setYscale(0, 600);
        StdDraw.setXscale(0, 1000);
        
        Tank tank1 = new Tank(100, 0);
        
        Sol sol = new Sol();
        
        while(true)
        {
            StdDraw.clear(Color.WHITE);
            StdDraw.picture(500, 300, "Images/fond.png");
            sol.afficherSol();
            tank1.showTank();
            tank1.move();
            tank1.shoot();
            StdDraw.show(20);
        }
    }
    
}
