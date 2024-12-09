package negocio;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Triesfera extends PCB {
    
    public Esfera e1, e2, e3;
    private final int velocidad = 20;
    private int medio, lugar, cantLugares;
    
    private boolean bandera = true;
    
    private Posicion p;
    
    private long tiempoUltimoDisparo = 0;
//    private long tiempoEntreDisparos = 3000; // Tiempo de espera entre disparos de triesferas (3 segundos)
    private long tiempoEntreDisparos;
    
    public Triesfera(int y, int lugar, int cantLugares) {
        p = new Posicion(JuegoSO.x, JuegoSO.y);
        
        medio = (100/cantLugares) / 2;                      //Centro del lugar
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
        //x - esfera.diametro
        if(x-25 > p.x(100/cantLugares * (lugar-1))){
            e1.x -= velocidad;
            e2.x -= velocidad;
            e3.x -= velocidad;
            x -= velocidad;
        }else{
            bandera = false;
        }
    }
    
    public void moverDerecha(){
        if( x+25 < p.x(100/cantLugares * (lugar))){
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
    
    @Override
    public boolean fueraDePantalla(){
        return y < 0;
    }
    
    @Override
    public void disparar(Cola cola, ArrayList<Bala> balas) {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoDisparo >= tiempoEntreDisparos) {
            try {
                Bala bala = new Bala(this.x, this.y, Color.black); // Bala de triesfera
                cola.meter(bala); // Agregamos la bala al planificador
                balas.add(bala);  // Añadimos la bala a la lista de balas
                tiempoUltimoDisparo = tiempoActual;

                // Generar un nuevo tiempo aleatorio entre disparos para la siguiente bala
                Random random = new Random();
                this.tiempoEntreDisparos = 1000 + random.nextInt(4000); // Nuevo intervalo aleatorio
                System.out.println("Triesfera disparó una bala.");
            } catch (Exception ex) {
                System.out.println("ERROR: No se pudo disparar la bala desde la triesfera.");
                System.out.println(ex);
            }
        }
    }
    
    
    
    
}