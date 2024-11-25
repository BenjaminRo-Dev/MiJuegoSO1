package negocio;

import java.awt.Color;
import java.awt.Graphics;

public class Canon {
    
    private int x, y;
    private int velocidad = 10;   //Velocidad del cañon
    
    public Canon(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void dibujar(Graphics g){
        g.drawLine (x, y, x, y-1000);//Linea al centro
        
        //Cuerpo
        g.setColor (Color.black);
        g.fillRect (x-15, y, 30, 30);
        
        //Disparador
        g.setColor (Color.MAGENTA);
        g.fillRect (x-3, y-12, 6, 12);
        
    }
    
    public void moverIzquierda(){
        x -= velocidad;
    }
    
    public void moverDerecha(){
        x += velocidad;
    }
    
    
    
    
}
