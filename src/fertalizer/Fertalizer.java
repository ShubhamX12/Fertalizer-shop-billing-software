/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fertalizer;
import fertalizer.frontend.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author shubham
 */
public class Fertalizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                     //</editor-fold>
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login_page().setVisible(true);
            }
        });
    }
}
