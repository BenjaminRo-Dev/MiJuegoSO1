package negocio;

import java.awt.Color;
import java.awt.Graphics;

public class Esfera extends PCB{
        
    public Esfera(int x, int y, Color color){
        
        this.diametro = 25;
        this.velocidad = 45;
        
        this.x = x;
        this.y = y;
        this.ancho = diametro;
        this.alto = diametro;
        this.color = color;
        
        this.tipo = 1;  //1 = Esfera
        this.retardo = 200;
        this.hora = System.currentTimeMillis();
        
    }
    
    @Override
    public void dibujar(Graphics g){
        g.setColor(this.color);
        g.fillOval(x, y, this.diametro, this.diametro);
    }
    
    @Override
    public void mover(){
        y -= velocidad;
    }
    
    @Override
    public boolean fueraDePantalla(){
        return y < 0;
    }
}
