package presentacion;
//@author Benjamin Romero

import javax.swing.JFrame;
import negocio.JuegoSO;


public class Juego extends javax.swing.JFrame {

    JuegoSO juegoSo;

    public Juego() {
        initComponents();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Juego SO1 - Benjamin Romero");       
        
        this.setSize(JuegoSO.x, JuegoSO.y);
        this.setLocationRelativeTo(null);
        
        
        juegoSo = new JuegoSO();
//        juegoSo.iniciarJuego();//TODO: Esta línea tiene que ir en un boton

        this.addKeyListener(juegoSo);
                
        panelContenedor.setLayout(new java.awt.CardLayout());
        panelContenedor.add(juegoSo);
//        panelContenedor.setVisible(false);//TODO: Descomentar esta línea
        panelContenedor.setVisible(true);
        
    }
    
    
    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_iniciar = new javax.swing.JMenu();
        menu_pausar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelContenedor.setBackground(new java.awt.Color(102, 255, 102));

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        getContentPane().add(panelContenedor, java.awt.BorderLayout.CENTER);

        m_iniciar.setText("Iniciar");
        m_iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_iniciarMouseClicked(evt);
            }
        });
        jMenuBar1.add(m_iniciar);

        menu_pausar.setText("Pausar");
        menu_pausar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_pausarMouseClicked(evt);
            }
        });
        jMenuBar1.add(menu_pausar);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void m_iniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_iniciarMouseClicked
//        panelContenedor.setVisible(true);ç
        juegoSo.iniciarJuego();
        menu_pausar.transferFocusUpCycle();
    }//GEN-LAST:event_m_iniciarMouseClicked

    private void menu_pausarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_pausarMouseClicked
        juegoSo.pausarJuego();
    }//GEN-LAST:event_menu_pausarMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu m_iniciar;
    private javax.swing.JMenu menu_pausar;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}
