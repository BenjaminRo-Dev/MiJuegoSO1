package negocio;

import java.awt.Graphics;
import java.awt.Color;

public class Triesfera extends PCB {
    
    private Esfera e1, e2, e3;
    
    public Triesfera(int x, int y) {
        this.x = x;     //Posición en el eje x
        this.y = y;     //Posición en el eje y
        e1 = new Esfera(x-25, y, Color.red);
        e2 = new Esfera(x, y, Color.green);
        e3 = new Esfera(x-12, y+22, Color.blue);
    }
    
    public void dibujar(Graphics g){
        e1.dibujar(g);
        e2.dibujar(g);
        e3.dibujar(g);
    }
    
    
    
    
}
