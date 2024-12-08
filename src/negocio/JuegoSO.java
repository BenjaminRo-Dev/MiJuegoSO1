package negocio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class JuegoSO extends JPanel implements KeyListener {

    public static int x = 900;      //Ancho de la pantalla
    public static int y = 700;      //Alto de la pantalla 

    public Canon canon;
    private ArrayList<Triesfera> triesferas;
    private ArrayList<Esfera> esferas;

    private PCB PRUN;

    private Cola cola;
    private int estado;

    private int indiceColor = 0;

    //Clases auxiliares:
    private Posicion p;

    public JuegoSO() {
        p = new Posicion(x, y);
        this.triesferas = new ArrayList<>();
        this.esferas = new ArrayList<>();

        cola = new Cola();

        Triesfera t1 = new Triesfera(5, 1, 3);
        Triesfera t2 = new Triesfera(5, 2, 3);
        Triesfera t3 = new Triesfera(5, 3, 3);
        this.triesferas.add(t1);
        this.triesferas.add(t2);
        this.triesferas.add(t3);

        try {
            cola.meter(t1);
            cola.meter(t2);
            cola.meter(t3);
        } catch (Exception e) {
            System.out.println("ERROR: NO SE METIERON LAS TRIESFERAS A LA COLA");
            Logger.getLogger(JuegoSO.class.getName()).log(Level.SEVERE, null, e);
        }
        canon = new Canon(p.x(50), p.y(85));
    }

    public void iniciarJuego() {
        estado = 0;
        Thread hiloJuego = new Thread(() -> {
            cicloJuego(); // Ejecutar el ciclo del juego en un hilo separado
        });
        hiloJuego.start(); // Iniciar el hilo del juego
    }

    private void dispararEsfera() {
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

            Esfera esfera = new Esfera(canon.x, canon.y, colorDisparo);
            cola.meter(esfera);
            esferas.add(esfera);
            indiceColor = (indiceColor + 1) % 3;
        } catch (Exception ex) {
            System.out.println("ERROR: No se pudo disparar la esfera: ");
            Logger.getLogger(JuegoSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void planificador() {
        try {
            PRUN = cola.sacar();
        } catch (Exception e) {
            System.out.println("CATCH - No hay nada para sacar");
            estado = 1;  // Cambia el estado del juego si no hay procesos
            return;
        }

        // Verificar si ya es tiempo de que este proceso se mueva
        if (System.currentTimeMillis() - PRUN.hora >= PRUN.retardo) {
            // System.out.println("YA ES HORA");

            PRUN.mover();// Mover el proceso actual
            repaint();
            PRUN.hora = System.currentTimeMillis();// Actualizar el tiempo de ejecución del proceso


            // Reinsertar el proceso en la cola
            if (PRUN.tipo == 1) {//Si es esfera
                if (!PRUN.fueraDePantalla()) {
                    try {
                        cola.meter(PRUN);
                    } catch (Exception e) {
                        System.out.println("NO se pudo meter el proceso");
                    }
                } else {
                    //Si esta fuera, ya no repintarla:
                    this.esferas.remove(PRUN);
                }
            } else {
                try {
                    cola.meter(PRUN);
                } catch (Exception e) {
                    System.out.println("NO se pudo meter el proceso");
                }
            }

        } else {// Si aún no es hora, se reinserta el proceso sin moverlo

            try {
                cola.meter(PRUN);
            } catch (Exception e) {
                System.out.println("NO se pudo meter el proceso");
            }
        }
        verificarColision();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        canon.dibujar(g);

        // Dibujar todas las balas
        for (Esfera esfera : esferas) {
            esfera.dibujar(g);
        }

        for (Triesfera triesfera : triesferas) {
            triesfera.dibujar(g);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        int antX = canon.x;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            canon.moverIzquierda();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            canon.moverDerecha();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            dispararEsfera();
        }
        repaint();//Repinta todo

        //        System.out.println(this.esferas.size());
        //        System.out.println(this.triesferas.getFirst().x);
        //        borrar(PRUN, antX);
        //        repaint(canon.x - 15, canon.y, 60, 60);  // Nueva área
        {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void cicloJuego() {
        while (estado == 0) {
            planificador();

            // Pausa en la ejecución
            try {
                Thread.sleep(1);  // 500 milisegundos entre cada iteración (ajústalo según la velocidad deseada)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean detectarColision(Esfera disparo, Triesfera triesfera) {
        // Verifica si las posiciones del disparo están cerca de la triesfera (ajusta el rango de colisión según sea necesario)
        int distanciaX = Math.abs(disparo.x - triesfera.x);
        int distanciaY = Math.abs(disparo.y - triesfera.y);

        return (distanciaX < 50 && distanciaY < 50); // 50 es un rango aproximado, ajusta según el tamaño de los objetos
    }

    public void cambiarColorTriesfera(Triesfera triesfera, Esfera disparo) {
        // Verifica qué esferas tienen un color diferente al disparo
        if (!triesfera.e1.getColor().equals(disparo.getColor())) {
            triesfera.e1.setColor(disparo.getColor());
        } else if (!triesfera.e2.getColor().equals(disparo.getColor())) {
            triesfera.e2.setColor(disparo.getColor());
        } else if (!triesfera.e3.getColor().equals(disparo.getColor())) {
            triesfera.e3.setColor(disparo.getColor());
        }
    }
    
    private void verificarColision(){
        for (Esfera disparo : esferas) { // Recorre todas las esferas disparadas
                for (Triesfera triesfera : triesferas) { // Recorre todas las triesferas
                    if (detectarColision(disparo, triesfera)) {
                        cambiarColorTriesfera(triesfera, disparo);
                        disparo.y = -100;
                        verificarTriesferaMismoColor(triesfera);
                    }
                }
            }
        
    }
    
    private void verificarTriesferaMismoColor(Triesfera triesfera){
        if(triesfera.e1.getColor().equals(triesfera.e2.getColor()) &&
           triesfera.e1.getColor().equals(triesfera.e3.getColor())){
//            System.out.println("Triesfera eliminada");
            this.triesferas.remove(triesfera);
            triesfera = null;
            
            
        }
    }
//    private void borrar(PCB pcb, int antX) {
//        repaint(antX - 15, pcb.y, 60, 60);  // Área aproximada
//    }
}
