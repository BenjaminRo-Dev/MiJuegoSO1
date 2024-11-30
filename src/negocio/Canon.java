package negocio;

import java.awt.Color;
import java.awt.Graphics;

public class Canon extends PCB{
    
    private final int velocidad = 10;       //Velocidad de movimiento del cañon
    private int anchoD;                     //Ancho del disparador
    private int altoD;                      //Altura del disparador
    private Posicion p;
    
    public Canon(int x, int y){
        p = new Posicion(JuegoSO.x, JuegoSO.y);
        this.x = x;
        this.y = y;
        this.alto = 30;
        this.ancho = 30;
        this.color = Color.yellow;
        this.anchoD = ancho/5;      //Si ancho = 30 -> anchoD = 6
        this.altoD = (alto/5) * 2;  //Si alto = 30 -> altoD = 12
    }
    
    public void dibujar(Graphics g){
        g.drawLine (x, y, x, y-1000);//Linea al centro
        
        //Cuerpo
        g.setColor (this.color);
        g.fillRect (x-(ancho/2), y, ancho, alto);
        //Se empieza a dibujar en "x menos la mitad del ancho" (pa que el cañon quede centrado)
        
        //Disparador
        
        g.setColor (Color.MAGENTA);
        g.fillRect (x - (anchoD/2), y - altoD, anchoD, altoD); //6,12
        
    }
    
    public void moverIzquierda(){
        x -= velocidad;
        if(x <= p.x(0))
            x = 0;
        
    }
    
    public void moverDerecha(){
        x += velocidad;
        if(x >= p.x(98))
            x = p.x(98);
    }
    
    
    
    
}
