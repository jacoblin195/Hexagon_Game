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
public class AIwindow extends JFrame{
    JPanel s = new AISceneDriver();
    public AIwindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(s);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String[] args) {
        JFrame AIwindow = new AIwindow();
    // TODO code application logic here
    }
}
