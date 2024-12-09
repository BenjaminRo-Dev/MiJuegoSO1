package negocio;
//@author BenRo

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Bala extends PCB{
    
    public Bala(int x, int y, Color color){
        this.diametro = 10;
        this.velocidad = 30;
        
        this.x = x;
        this.y = y;
        this.ancho = diametro;
        this.alto = diametro;
        this.color = color;
        
        this.tipo = 2;  //2 = Bala
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
        y += velocidad;
    }
    
    @Override
    public boolean fueraDePantalla(){
        return y > JuegoSO.x;
    }
}