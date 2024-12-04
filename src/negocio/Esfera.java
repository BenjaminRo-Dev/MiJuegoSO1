package negocio;

import java.awt.Color;
import java.awt.Graphics;

public class Esfera extends PCB{
    
    private final int diametro = 25;        //Tamaño de la esfera
    private final int velocidad = 20;
    
    public Esfera(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.ancho = diametro;
        this.alto = diametro;
        this.color = color;
        
        this.tipo = 1;  //1 = Esfera
        this.retardo = 200;
        this.hora = System.currentTimeMillis();
        
        
    }
    
    public void dibujar(Graphics g){
        g.setColor(this.color);
        g.fillOval(x, y, this.diametro, this.diametro);
    }
    
    public void mover(){
        y -= velocidad;
    }
    
    @Override
    public boolean fueraDePantalla(){
        return y < 0;
    }
}
