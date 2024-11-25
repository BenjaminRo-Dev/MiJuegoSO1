package negocio;

import java.awt.Graphics;
import java.awt.Color;

public class Triesfera {
    
    private final int x, y;
    private int diametro;
    
    public Triesfera(int x, int y) {
        diametro = 25;
        this.x = x;     //Ancho
        this.y = y;     //Alto
    }
    
    public void dibujar(Graphics g){
        g.setColor(Color.blue);
        g.fillOval(x-25, y, diametro, diametro);
        
        g.setColor(Color.red);
        g.fillOval(x, y, diametro, diametro);
        
        g.setColor(Color.black);
        g.fillOval(x-12, y+22 , diametro, diametro);
       
    }
    
    
    
}
