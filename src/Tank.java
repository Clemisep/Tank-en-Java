import java.awt.event.KeyEvent;

public class Tank {
    double posX;
    double posY;
    public static double rotation;
    public static int direction = 2;
    
    public Tank(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;   
    }
    
    public void move()
    {
        if(posX < 1000 && posX > 0)
        {
           if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
            {
                if(posX > 8)
                {
                    posX = posX - 8;
                }
                else
                {
                    posX = 8;
                }
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
            {
                if(posX < 992)
                {
                    posX = posX + 8;
                }
                else
                {
                    posX = 992;
                }
            } 
        }
    }
    
    public void showTank()
    {
        posY = Sol.getPosY(posX) + 30;
        if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
        {
            StdDraw.picture(posX, posY, "Images/tank-gauche.png", 100, 100);
            StdDraw.picture(posX, posY + 20, "Images/canon-gauche.png", 100, 13, -rotation);
            direction = 1;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
        {
            StdDraw.picture(posX, posY, "Images/tank-droite.png", 100, 100);
            StdDraw.picture(posX, posY + 20, "Images/canon-droite.png", 100, 13, rotation);
            direction = 2;
        }
        else
        {
            if(direction == 1)
            {
                StdDraw.picture(posX, posY, "Images/tank-gauche.png", 100, 100);
                StdDraw.picture(posX, posY + 20, "Images/canon-gauche.png", 100, 13,-rotation);
                if(StdDraw.isKeyPressed(KeyEvent.VK_UP) && rotation < 80)
                {
                    StdDraw.picture(posX, posY + 20, "Images/canon-gauche.png", 100, 13, -rotation++);
                }
                else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && rotation > -30)
                {
                    StdDraw.picture(posX, posY + 20, "Images/canon-gauche.png", 100, 13, -rotation--);
                }
            }
            else
            {
                StdDraw.picture(posX, posY, "Images/tank-droite.png", 100, 100);
                StdDraw.picture(posX, posY + 20, "Images/canon-droite.png", 100, 13, rotation);
                if(StdDraw.isKeyPressed(KeyEvent.VK_UP) && rotation < 80)
                {
                    StdDraw.picture(posX, posY + 20, "Images/canon-droite.png", 100, 13, rotation++);
                }
                else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && rotation > -30)
                {
                    StdDraw.picture(posX, posY + 20, "Images/canon-droite.png", 100, 13, rotation--);
                }
            }
        }
    }
    
    public int getDirection()
    {
        return this.direction;
    }
    
    public double getRotation()
    {
        return this.rotation;
    }
    
    public void shoot() throws InterruptedException
    {
        if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
        {
            if(this.getDirection() == 2)
            {
                //Le canon est dirigé vers la droite
                if(this.getRotation() < 0)
                {
                    double decaly = 50.0*Math.sin(this.getRotation()*Math.PI/180.0);
                    StdDraw.picture(posX + 50, posY + 20 + decaly, "Images/balle.png", 10, 10);
                }
                else if(this.getRotation() >= 0)
                {
                    double decaly = 0.7 * this.getRotation();
                    double decalx = 0.6 * this.getRotation();
                    StdDraw.picture(posX + 50 - decalx, posY + 20 + decaly, "Images/balle.png", 10, 10);
                    
//                    CODE PAS BON
//                    boolean tir = true;
//                    int k = 0;
//                    double vz = 50.0 * Math.sin(this.getRotation());
//                    double posiy;
//                    posiy = posY + 20 + decaly;
//                    
//                    while(tir)
//                    {
//                        k++;
//                        StdDraw.picture(posX + 50 - decalx + k, -0.5 * 9.81 * k * k + vz * k + posiy, "Images/balle.png", 10, 10);
//                        
//                        wait(1);
//                        if (k >= 100)
//                        {
//                            tir = false;
//                        }
//                    }
                }
            }
            else if(this.getDirection() == 1)
            {
                //Le canon est dirigé vers la gauche
                if(this.getRotation() < 0)
                {
                    double decaly = 50.0*Math.sin(this.getRotation()*Math.PI/180.0);
                    StdDraw.picture(posX - 50, posY + 20 + decaly, "Images/balle.png", 10, 10);
                }
                else if(this.getRotation() >= 0)
                {
                    double decaly = 0.7 * this.getRotation();
                    double decalx = 0.6 * this.getRotation();
                    StdDraw.picture(posX - 50 + decalx, posY + 20 + decaly, "Images/balle.png", 10, 10);
                }
            }
        }
    }
}
