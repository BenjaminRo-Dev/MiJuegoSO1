package negocio;

import java.awt.Graphics;
import java.awt.Color;

public class Triesfera extends PCB {
    
    private Esfera e1, e2, e3;
    private final int velocidad = 20;
    private int medio, lugar, cantLugares;
    
    private boolean bandera = true;
    
    private Posicion p;
    
    public Triesfera(int y, int lugar, int cantLugares) {
        p = new Posicion(JuegoSO.x, JuegoSO.y);
        
        medio = (100/cantLugares) / 2;                      //Parte del medio del lugar
        int x = (p.x(100/cantLugares * lugar - medio));     //Posición de la triesfera
        this.lugar = lugar;
        this.cantLugares = cantLugares;
        
        this.x = x;     //Posición en el eje x
        this.y = y;     //Posición en el eje y
        e1 = new Esfera(x-25, y, Color.red);
        e2 = new Esfera(x, y, Color.green);
        e3 = new Esfera(x-12, y+22, Color.blue);
        
        this.tipo = 0;  //0 = Triesfera
        this.retardo = 200;
        this.hora = System.currentTimeMillis();
    }
    
    @Override
    public void dibujar(Graphics g){
//        g.drawLine (x, y, x, y-1000);//Linea al centro
        e1.dibujar(g);
        e2.dibujar(g);
        e3.dibujar(g);
    }
    
    public void moverIzquierda(){
        
        if(x > p.x(100/cantLugares * (lugar-1))){
            e1.x -= velocidad;
            e2.x -= velocidad;
            e3.x -= velocidad;
            x -= velocidad;
        }else{
            bandera = false;
        }
    }
    
    public void moverDerecha(){
        if( x < p.x(100/cantLugares * (lugar))){
            e1.x += velocidad;
            e2.x += velocidad;
            e3.x += velocidad;
            x += velocidad;
        }else{
            bandera = true;
        }
    }
    
    @Override
    public void mover(){
        //Mover todo a la izq y luego todo a la der:
        if(bandera)
            moverIzquierda();
        else
            moverDerecha();
        
        //Mover aleatoriamente:
//        int numero = (int) (Math.random() * 2);
//        switch (numero) {
//            case 0:
//                moverIzquierda();
//                break;
//            case 1:
//                moverDerecha();
//                break;
//            default:
//                throw new AssertionError();
//        }
    }
    
    
    
    
}