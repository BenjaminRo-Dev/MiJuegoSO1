package negocio;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PCB {
//    int PID, dir, ancho, alto;
    int tipo;   //Triesfera, BalaU, BalaN
    int x, y;
    int ancho, alto;
    Color color;
    long hora, retardo;
    int velocidad;
    
    //Para la triesfera:
    int medio, lugar, cantLugares;
    boolean bandera;
    long tiempoUltimoDisparo;
    long tiempoEntreDisparos;
    
    //Para la esfera:
    int diametro;        //Tamaño de la esfera
    
    public void dibujar(Graphics g){
        
    }
    
    public void mover(){
        System.out.println("Mover Padre");
    }
    
    public boolean fueraDePantalla(){
        System.out.println("Fuera de pantalla del Padre");
        return false;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public void disparar(Cola cola, ArrayList<Bala> balas){
        System.out.println("Disparar del padre");
    }
}
