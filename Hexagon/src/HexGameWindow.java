/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
public class HexGameWindow extends JFrame{
    JPanel s = new sceneDriver();
    public HexGameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(s);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String[] args) {
        JFrame HexGameWindow = new HexGameWindow();
    // TODO code application logic here
    }
}
