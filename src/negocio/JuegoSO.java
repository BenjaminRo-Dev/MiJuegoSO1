package negocio;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class JuegoSO extends JPanel implements KeyListener {

    public Canon canon;
    private Triesfera t1;
    public static int x = 900;      //Ancho de la pantalla
    public static int y = 700;      //Alto de la pantalla 

    //Clases auxiliares:
    private Posicion p;

    public void iniciarJuego() {

        p = new Posicion(x, y);
        canon = new Canon(p.x(50), p.y(85));
        t1 = new Triesfera(50, 10);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        canon.dibujar(g);
        t1.dibujar(g);

    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            canon.moverIzquierda();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            canon.moverDerecha();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
