package negocio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JuegoSO extends JPanel implements KeyListener {

    public static int x = 900;      //Ancho de la pantalla
    public static int y = 700;      //Alto de la pantalla 

    public Canon canon;
    private ArrayList<Triesfera> triesferas;
    private ArrayList<Esfera> esferas;
    private ArrayList<Bala> balas;

    private PCB PRUN;

    private Cola cola;
    private int estado = 1;

    private int indiceColor = 0;

    //Clases auxiliares:
    private Posicion p;

    public JuegoSO() {
        p = new Posicion(x, y);
        this.triesferas = new ArrayList<>();
        this.esferas = new ArrayList<>();
        this.balas = new ArrayList<>();

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
        if (estado != 0) {
            estado = 0;
            Thread hiloJuego = new Thread(() -> {
                cicloJuego(); // Ejecutar el ciclo del juego en un hilo separado
            });
            hiloJuego.start(); // Iniciar el hilo del juego
        }
    }

    public void pausarJuego() {
        if (estado == 0) {
            estado = 1;
        } else {
            iniciarJuego();
        }
    }

    private void planificador() {
        try {
            synchronized (this) {
                PRUN = cola.sacar();
            }

        } catch (Exception e) {
            System.out.println("CATCH - No hay nada para sacar");
            estado = 1;  // Cambia el estado del juego si no hay procesos
            return;
        }

        if (PRUN == null) {
            this.estado = 1;
            System.out.println("PRRUN ES NULL NO SE PUEDE PROCESAR");
            return;
        }

        if (PRUN instanceof Triesfera) {
            ((Triesfera) PRUN).disparar(cola, balas);
        }

        // Verificar si ya es tiempo de que este proceso se mueva
        if (System.currentTimeMillis() - PRUN.hora >= PRUN.retardo) {

            PRUN.mover();// Mover el proceso actual
            repaint();
            PRUN.hora = System.currentTimeMillis();// Actualizar el tiempo de ejecución del proceso

            verificarColisionTriesfera();
            canon.verificarColisionCanon(balas);
//            System.out.println(this.cola.length() + 1);//+1 porque se saco el prun actual

            if (!PRUN.fueraDePantalla()) {
                try {
                    cola.meter(PRUN);
                } catch (Exception e) {
                    System.out.println("NO se pudo meter el proceso");
                }
            } else {
                //Si esta fuera, ya no repintarla:
                if (this.PRUN.tipo == 1)//Tipo esfera
                {
                    this.esferas.remove(PRUN);
                } else if (this.PRUN.tipo == 0)//Tipo triesfera
                {
                    this.triesferas.remove(PRUN);
                    if (triesferas.size() == 0) {
                        this.estado = 1;
                        JOptionPane.showMessageDialog(null, "Ganaste =)");
                        System.exit(0);
                    }

                } else if (PRUN.tipo == 2) { //Tipo bala
                    balas.remove(PRUN);
                }
            }

        } else {// Si aún no es hora, se reinserta el proceso sin moverlo

            try {
                cola.meter(PRUN);
            } catch (Exception e) {
                System.out.println("NO se pudo meter el proceso");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        canon.dibujar(g);

        // Dibujar todas las balas
        for (Esfera esfera : esferas) {
            esfera.dibujar(g);
        }

        for (Bala bala : balas) {
            bala.dibujar(g);
        }

        for (Triesfera triesfera : triesferas) {
            triesfera.dibujar(g);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            canon.moverIzquierda();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            canon.moverDerecha();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            synchronized (this) {
                canon.dispararEsfera(esferas, cola);
            }
        }
        repaint();
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
//            try {
//                Thread.sleep(1);  // 500 milisegundos entre cada iteración (ajústalo según la velocidad deseada)
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }


    private void verificarColisionTriesfera() {
        Iterator<Esfera> iterador = esferas.iterator();
        while (iterador.hasNext()) {
            Esfera disparo = iterador.next(); // Obtengo la siguiente esfera disparada
            for (Triesfera triesfera : triesferas) { // Recorro todas las triesferas
                if (triesfera.detectarColision(disparo)) {
                    triesfera.cambiarColor(disparo);
                    disparo.y = -100;
                    triesfera.verificarTriesferaMismoColor();
                    break;  //Finaliza el loop
                }
            }
        }
    }

}
