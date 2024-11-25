package negocio;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelJuego extends JPanel{
    
    private Triesfera triesfera;
    private Canon canon;
    
    public PanelJuego(Triesfera triesfera, Canon canon) {
        this.triesfera = triesfera;
        this.canon = canon;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        triesfera.dibujar(g);
        canon.dibujar(g);
    }
    
}
