package negocio;

import java.awt.Color;
import java.awt.Graphics;

public class PCB {
//    int PID, dir, ancho, alto;
    int tipo;   //Triesfera, BalaU, BalaN
    int x, y;
    int ancho, alto;
    Color color;
    long hora, retardo;
    
    public void dibujar(Graphics g){
        
    }
    
    public void mover(){
        System.out.println("Mover Padre");
    }
}
