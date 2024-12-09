package negocio;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Canon extends PCB{
    
    private int anchoD;                     //Ancho del disparador
    private int altoD;                      //Altura del disparador
    private Posicion p;
    
    private int indiceColor = 0;
    
    public Canon(int x, int y){
        p = new Posicion(JuegoSO.x, JuegoSO.y);
        
        this.velocidad = 10;            //Velocidad de movimiento del cañon
        
        this.x = x;
        this.y = y;
        this.alto = 30;
        this.ancho = 30;
        this.color = Color.yellow;
        this.anchoD = ancho/5;      //Si ancho = 30 -> anchoD = 6
        this.altoD = (alto/5) * 2;  //Si alto = 30 -> altoD = 12
    }
    
    @Override
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
    
    public void dispararEsfera(ArrayList<Esfera> esferas, Cola cola) {
        try {
            Color colorDisparo;

            switch (indiceColor) {
                case 0:
                    colorDisparo = Color.red;
                    break;
                case 1:
                    colorDisparo = Color.green;
                    break;
                case 2:
                    colorDisparo = Color.blue;
                    break;
                default:
                    colorDisparo = Color.blue;
                    break;
            }

            Esfera esfera = new Esfera(this.x, this.y, colorDisparo);
            //synchronized (this) {
                cola.meter(esfera);
            //}
            esferas.add(esfera);
            indiceColor = (indiceColor + 1) % 3;
        } catch (Exception ex) {
            System.out.println("ERROR: No se pudo disparar la esfera: ");
            Logger.getLogger(JuegoSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean detectarColision(Bala disparo) {
        int distanciaX = Math.abs(disparo.x - this.x);
        int distanciaY = Math.abs(disparo.y - this.y);

        return (distanciaX < 30 && distanciaY < 30);
    }
    
    public void verificarColisionCanon(ArrayList<Bala> balas) {
        Iterator<Bala> iterador = balas.iterator();
        while (iterador.hasNext()) {
            Bala disparo = iterador.next();
            for (Bala bala : balas) {
                if (this.detectarColision(disparo)) {
                    System.out.println("Cañon eliminado");
                    JOptionPane.showMessageDialog(null, "Perdiste =(");
                    System.exit(0);
                    disparo.y = +100;
                    break;  //Finaliza el loop
                }
            }
        }
    }
    
    
    
    
}
