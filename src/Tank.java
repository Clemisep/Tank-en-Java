import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class Tank {
    double posX;
    double posY;
    double rotation;
    
    double yant;
    double ypos;
    double diff;
    
    double rotsol;
    double decalcanonX;
    double decalcanonY;
    
    double addrot;
    double posiX;
    double posiY;
    
    boolean tirer;
    boolean deplacer;
    boolean tir = false;
    boolean tirCloche = false;
    boolean tirClochePhaseBool = false;
    boolean fin = false;
    
    int i = -1;
    int solx;
    int tirClochePhase = 1;
    
    int vie;
    int xVie;
    int degat = 10;
    
    int balleX;
    int balleY;
    
    Font name = new Font("Arial", Font.BOLD, 18);
    
    Tank(int x, int y)
    {
        this.posX = x;
        this.posY = y;
        vie = 100;
        xVie = 100;
    }
    
    public void deplacement(int min, int max)
    {
        if(deplacer == true)
        {
            //On peut déplacer le tank entre la valeur min et max en abscisse
            if(posX <= max && posX >= min)
            {
                if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
                {
                    //On bloque le tir pendant le déplacement
                    tirer = false;
                    if(posX > min + 5)
                    {
                        posX = posX - 5;
                    }
                    else
                    {
                        posX = min + 5;
                    }
                }
                else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
                {
                    //On bloque le tir pendant le déplacement
                    tirer = false;
                    if(posX < max - 5)
                    {
                        posX = posX + 5;
                    }
                    else
                    {
                        posX = max - 5;
                    }
                }
                else
                {
                    tirer = true;
                }
                //Gestion de la rotation du canon
                if(rotation > -10 && rotation < 80)
                {
                    if(StdDraw.isKeyPressed(KeyEvent.VK_UP) && rotation < 79)
                    {
                        rotation++;
                    }
                    else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && rotation > -9)
                    {
                        rotation--;
                    }
                }
            }
        }
    }
    
    public void afficherTank(int x)
    {
        //Pente du tank dû à la pente du sol
        yant = Sol.getPosY(posX - 1);
        ypos = Sol.getPosY(posX + 1);
        diff = yant - ypos;
        if(diff != 0)
        {
            rotsol = -50.0 * Math.atan(diff / 2);
        }
        else
        {
            rotsol = 0;
        }
        
        decalcanonX = -14.0 * Math.sin(rotsol * Math.PI / 180.0);
        decalcanonY = -Math.cos(rotsol * Math.PI / 180.0);
        
        posY = Sol.getPosY(posX) + 18;
        //joueur de gauche = 1 | joueur de droite = 2
        if(x == 1)
        {
            if(rotation + rotsol > 80)
            {
                rotation = rotation - 2;
            }
            StdDraw.picture(posX, posY, "Images/tank-droite.png", 70, 70, rotsol);
            StdDraw.picture(posX + decalcanonX, posY + 14 + decalcanonY, "Images/canon-droite.png", 70, 9, rotation + rotsol);
        }
        else if(x == 2)
        {
            if(-rotation + rotsol < -80)
            {
                rotation = rotation - 2;
            }
            StdDraw.picture(posX, posY, "Images/tank-gauche.png", 70, 70, rotsol);
            StdDraw.picture(posX + decalcanonX, posY + 14 + decalcanonY, "Images/canon-gauche.png", 70, 9, -rotation + rotsol);
        }  
        //On réinitialise fin à false
        fin = false;
    }
    
    public void tirer(int x)
    {
        //Code pour le tir simple
        if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && tirer == true)
        {
            tir = true;
            i = 0;
            //On bloque le déplacement pendant le tir
            deplacer = false;
        }
        
        if(tir == true)
        {
            //joueur de gauche = 1 | joueur de droite = 2
            if(x == 1)
            {
                addrot = rotsol + rotation;
                //position de départ du canon
                posiX = 35.0 * Math.cos(addrot * Math.PI / 180.0);
                posiY = 35.0 * Math.sin(addrot * Math.PI / 180.0);
                StdDraw.picture(posX + decalcanonX + posiX + i, posY + 14 + decalcanonY + posiY + y(i), "Images/balle.png", 10, 10);
                
                if(posY + 14 + decalcanonY + posiY + y(i) <= Sol.getPosY(posX + decalcanonX + posiX + i) + 5.0)
                {
                    solx = (int) (posX + decalcanonX + posiX + i);
                    Sol.detruire(solx);
                    i = 0;
                    deplacer = true;
                    fin = true;
                    tir = false;
                }
                balleX = (int) (posX + decalcanonX + posiX + i);
                balleY = (int) (posY + 14 + decalcanonY + posiY + y(i));
            }
            else if(x == 2)
            {
                addrot = -rotsol + rotation;
                //position de départ du canon
                posiX = -35.0 * Math.cos(addrot * Math.PI / 180.0);
                posiY = 35.0 * Math.sin(addrot * Math.PI / 180.0);
                StdDraw.picture(posX + decalcanonX + posiX + i, posY + 14 + decalcanonY + posiY + yy(i), "Images/balle.png", 10, 10);
                
                if(posY + 14 + decalcanonY + posiY + yy(i) <= Sol.getPosY(posX + decalcanonX + posiX + i) + 5.0)
                {
                    solx = (int) (posX + decalcanonX + posiX + i);
                    Sol.detruire(solx);
                    i = 0;
                    deplacer = true;
                    fin = true;
                    tir = false;
                }
                balleX = (int) (posX + decalcanonX + posiX + i);
                balleY = (int) (posY + 14 + decalcanonY + posiY + yy(i));
            }
            if(posX + decalcanonX + posiX + i > 999)
            {
                tir = false;
                deplacer = true;
                i = 0;
            }
            if(x == 1)
            {
                i = i + 5;
            }
            else if(x == 2)
            {
                i = i - 5;
            }
        }
        else
        {
            tir = false;
            deplacer = true;
        }
        
        //Code pour le tir en cloche
        if(StdDraw.isKeyPressed(KeyEvent.VK_C) && tirer == true)
        {
            tirCloche = true;
            i = 0;
            //On bloque le déplacement pendant le tir
            deplacer = false;
        }
        
        if(tirCloche == true)
        {
            if(tirClochePhase == 1)
            {
                //joueur de gauche = 1 | joueur de droite = 2
                if(x == 1)
                {
                    addrot = rotsol + rotation;
                    //position de départ du canon
                    posiX = 35.0 * Math.cos(addrot * Math.PI / 180.0);
                    posiY = 35.0 * Math.sin(addrot * Math.PI / 180.0);
                    StdDraw.picture(posX + decalcanonX + posiX + i, posY + 14 + decalcanonY + posiY + y(i), "Images/balle.png", 10, 10);

                    if(posY + 14 + decalcanonY + posiY + y(i) <= Sol.getPosY(posX + decalcanonX + posiX + i) + 5.0)
                    {
                        solx = (int) (posX + decalcanonX + posiX + i);
                        Sol.detruire(solx);
                        i = 0;
                        deplacer = true;
                        fin = true;
                        tirCloche = false;
                    }
                    balleX = (int) (posX + decalcanonX + posiX + i);
                    balleY = (int) (posY + 14 + decalcanonY + posiY + y(i));
                }
                else if(x == 2)
                {
                    addrot = -rotsol + rotation;
                    //position de départ du canon
                    posiX = -35.0 * Math.cos(addrot * Math.PI / 180.0);
                    posiY = 35.0 * Math.sin(addrot * Math.PI / 180.0);
                    StdDraw.picture(posX + decalcanonX + posiX + i, posY + 14 + decalcanonY + posiY + yy(i), "Images/balle.png", 10, 10);

                    if(posY + 14 + decalcanonY + posiY + yy(i) <= Sol.getPosY(posX + decalcanonX + posiX + i) + 5.0)
                    {
                        solx = (int) (posX + decalcanonX + posiX + i);
                        Sol.detruire(solx);
                        i = 0;
                        deplacer = true;
                        fin = true;
                        tirCloche = false;
                    }
                    balleX = (int) (posX + decalcanonX + posiX + i);
                    balleY = (int) (posY + 14 + decalcanonY + posiY + yy(i));
                }
                if(posX + decalcanonX + posiX + i > 999)
                {
                    tir = false;
                    deplacer = true;
                    i = 0;
                }
                if(x == 1)
                {
                    i = i + 5;
                }
                else if(x == 2)
                {
                    i = i - 5;
                }
            }
            else if(tirClochePhase == 2)
            {
                balleY--;
            }
            //Changement de phase
            if(x == 1)
            {
                if(posY + 14 + decalcanonY + posiY + y(i-1) == posY + 14 + decalcanonY + posiY + y(i))
                {
                    tirClochePhaseBool = true;
                }
            }
            else if(x == 2)
            {
                if(posY + 14 + decalcanonY + posiY + y(i+1) == posY + 14 + decalcanonY + posiY + y(i))
                {
                    tirClochePhaseBool = true;
                }
            }
            if(tirClochePhaseBool == true)
            {
                tirClochePhase = 2;
            }
        }
    }
    
    public double y(double x)
    {
        return (double) (-1 * 9.81 * x * x / 2000.0 + 50.0 * Math.sin((addrot/20.0)*Math.PI/180.0) * x);     
    }
    
    public double yy(double x)
    {
        return (double) (-1 * 9.81 * x * x / 2000.0 + 50.0 * Math.sin((-addrot/20.0)*Math.PI/180.0) * x);     
    }
    
    public void afficherVie(int x)
    {
        if(x == 1)
        {
            StdDraw.setPenColor(18,77,229);
            StdDraw.textLeft(0, 570, "Joueur 1");
            StdDraw.filledRectangle(this.xVie, 540, this.vie, 10);
            StdDraw.setFont(name);
            StdDraw.text(this.xVie, 500, Double.toString(this.vie));
        }
        else if(x == 2)
        {
            StdDraw.setPenColor(206,55,44);
            StdDraw.textRight(1000, 570, "Joueur 2");
            StdDraw.filledRectangle(this.xVie, 540, this.vie, 10);
            StdDraw.setFont(name);
            StdDraw.text(this.xVie, 500, Double.toString(this.vie));
        }
    }
    
    public boolean isFin() {
        return fin;
    }
    
    public int getVie() {
        return this.vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getxVie() {
        return xVie;
    }

    public void setxVie(int xVie) {
        this.xVie = xVie;
    }
    
}
