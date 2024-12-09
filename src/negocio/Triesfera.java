package negocio;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Triesfera extends PCB {

    public Esfera e1, e2, e3;
    private Posicion p;

    public Triesfera(int y, int lugar, int cantLugares) {
        p = new Posicion(JuegoSO.x, JuegoSO.y);

        this.medio = (100 / cantLugares) / 2;                      //Centro del lugar
        int x = (p.x(100 / cantLugares * lugar - medio));     //Posición de la triesfera
        this.lugar = lugar;
        this.cantLugares = cantLugares;

        this.bandera = true;
        this.tiempoUltimoDisparo = 0;

        this.x = x;     //Posición en el eje x
        this.y = y;     //Posición en el eje y
        this.velocidad = 20;

        this.tipo = 0;  //0 = Triesfera
        this.retardo = 200;
        this.hora = System.currentTimeMillis();

        e1 = new Esfera(x - 25, y, Color.red);
        e2 = new Esfera(x, y, Color.green);
        e3 = new Esfera(x - 12, y + 22, Color.blue);
    }

    @Override
    public void dibujar(Graphics g) {
        e1.dibujar(g);
        e2.dibujar(g);
        e3.dibujar(g);
    }

    private void moverIzquierda() {
        //x - esfera.diametro = x-25
        if (x - e1.diametro > p.x(100 / cantLugares * (lugar - 1))) {
            e1.x -= velocidad;
            e2.x -= velocidad;
            e3.x -= velocidad;
            x -= velocidad;
        } else {
            bandera = false;
        }
    }

    private void moverDerecha() {
        if (x + e1.diametro < p.x(100 / cantLugares * (lugar))) {
            e1.x += velocidad;
            e2.x += velocidad;
            e3.x += velocidad;
            x += velocidad;
        } else {
            bandera = true;
        }
    }

    @Override
    public void mover() {
        //Mover todo a la izq y luego todo a la der:
        if (bandera) {
            moverIzquierda();
        } else {
            moverDerecha();
        }
    }

    @Override
    public boolean fueraDePantalla() {
        return y < 0;
    }

    @Override
    public void disparar(Cola cola, ArrayList<Bala> balas) {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoDisparo >= tiempoEntreDisparos) {
            try {
                Bala bala = new Bala(this.x, this.y, Color.black); // Bala de triesfera
                cola.meter(bala);
                balas.add(bala);
                tiempoUltimoDisparo = tiempoActual;

                // Generar un nuevo tiempo aleatorio entre disparos para la siguiente bala
                Random random = new Random();
                this.tiempoEntreDisparos = 1000 + random.nextInt(4000); // Nuevo intervalo aleatorio
            } catch (Exception ex) {
                System.out.println("ERROR: No se pudo disparar la bala desde la triesfera." + ex);
            }
        }
    }

    public boolean detectarColision(Esfera disparo) {
        // Verifica si las posiciones del disparo están cerca de la triesfera
        int distanciaX = Math.abs(disparo.x - this.x);
        int distanciaY = Math.abs(disparo.y - this.y);

        return (distanciaX < 50 && distanciaY < 50); // 50 es un rango aproximado
    }
    
    public void cambiarColor(Esfera disparo) {
        // Verifica qué esferas tienen un color diferente al disparo
        if (!this.e1.getColor().equals(disparo.getColor())) {
            this.e1.setColor(disparo.getColor());
        } else if (!this.e2.getColor().equals(disparo.getColor())) {
            this.e2.setColor(disparo.getColor());
        } else if (!this.e3.getColor().equals(disparo.getColor())) {
            this.e3.setColor(disparo.getColor());
        }
    }
    
    
    public void verificarTriesferaMismoColor() {
        if (this.e1.getColor().equals(this.e2.getColor())
                && this.e1.getColor().equals(this.e3.getColor())) {
            System.out.println("Triesfera eliminada");
            this.y = -100;

        }
    }

}
