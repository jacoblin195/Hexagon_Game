
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */
public class AISceneDriver extends JPanel implements ActionListener{
    public final int FRAME_WIDTH = 1300;
    public final int FRAME_HEIGHT = 830;
    
    private final HexShape[] shapes = new HexShape[3];
    private final hexGridPane gridpane  = new hexGridPane(200,400);
    private final Timer timer = new Timer(5, this);
    
    private int highscore;
    private int timecounter = 0;
    private int score = 0;
    private String highscorename ="";
    private final File file = new File("highest score.txt");
    private final ProbabilityController generator = new ProbabilityController();
    private Scanner scan;
    private boolean wait = false;
    
    public AISceneDriver() {
        super();
        
        this.loaddata();
        
        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        
        shapes[0]=new HexShape(this.generator.generate(),1);
        shapes[1]=new HexShape(this.generator.generate(),2);
        shapes[2]=new HexShape(this.generator.generate(),3);
        
        try {
            this.think_and_do();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AISceneDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loaddata(){
        if (!file.exists()){
            //if the file doesn't exist,
            //Create the file and save default data.
            try{
                file.createNewFile();

                FileWriter writer = new FileWriter(file);
                writer.write("0 Anonymous");
                this.highscorename = "Anonymous";
                this.highscore = 0;
                writer.flush();
                writer.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        } else {
            //if the file exists
            try {
                //initialize the scanner
                scan = new Scanner(file);
            } catch(FileNotFoundException e){
            }

            try {
                //read score
                this.highscore = this.scan.nextInt();
                //read player's name
                while(this.scan.hasNext()){
                    this.highscorename += (this.scan.next()+" ");
                }
                if (highscorename.equals("")){
                    this.highscorename = "Anonymous";
                }
            } catch (NoSuchElementException e ) {
                //change data to default if format inconsistency
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write("0 Anonymous");
                    writer.flush();
                    writer.close();
                    this.highscorename = "Anonymous";
                    this.highscore = 0;
                } catch (IOException g) {
                    g.printStackTrace();
                }
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        this.gridpane.draw(g);
        
        this.shapes[0].draw(g);
        this.shapes[1].draw(g);
        this.shapes[2].draw(g);
        
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 22)); 
        g.drawString("Score:  " + score,30,30);
        g.drawString("High score:  " + highscore + "  " + this.highscorename,300,30);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(wait){
            this.timecounter++;
            if(this.timecounter == 50){
                this.timecounter = 0;
                wait = false;
                try {
                    this.think_and_do();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(AISceneDriver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return;
        }
        if (this.timecounter == 50){
            this.timer.stop();
            
            this.timecounter = 0;
            for(inPaneHex i:this.gridpane.grid){
                if(i.needToClean()){
                    i.rebuild();
                    score++;
                }
            }
            repaint();
            boolean continueornot = this.gridpane.checkpath(this.shapes[0])||
                this.gridpane.checkpath(this.shapes[1])||
                this.gridpane.checkpath(this.shapes[2]);
            if(!continueornot){
                this.gameOver();
            }
            this.wait = true;
            this.timecounter=0;
            this.timer.start();
            try {
                    this.think_and_do();
            } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(AISceneDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            for(inPaneHex i:this.gridpane.grid){
                if(i.needToClean()){
                    if(i.getFillColor()!=Color.GRAY){
                        int r = Math.min(i.getFillColor().getRed() + 3,255);
                        int b = Math.min(i.getFillColor().getBlue() + 3,255);
                        int g = Math.min(i.getFillColor().getGreen() + 3,255);
                        if(r ==255 && b == 255 && g == 255){
                            i.setFillColor(Color.GRAY);
                        } else {
                            i.setFillColor(r, g, b);
                        }
                    }
                    
                }
            }
            repaint();
            ++this.timecounter;
        }
    }
    public void gameOver(){
        JFrame frame = new JFrame("End");
        if(this.score>this.highscore){
            String name = JOptionPane.showInputDialog(frame,"No available moves. \n"+"New Record!\n"
                        + "Your score: "+score+"!\n"+"Please type your name here\n","End",JOptionPane.INFORMATION_MESSAGE,null,null,highscorename).toString();
            if (name.equals("")){
                name = "Anonymous";
            }
            try{
                if (!file.exists()){
                    file.createNewFile();
                }
                FileWriter writer = new FileWriter(file);
                writer.write(this.score+ " " + name);
                writer.flush();
                writer.close();
            }catch(IOException e){
            }
        } else {
            JOptionPane.showMessageDialog(frame,"No available moves. Your score: "+score+"!","End",JOptionPane.INFORMATION_MESSAGE);
        }
        System.exit(0);
    }

    private void think_and_do() throws CloneNotSupportedException {
        boolean continueornot = this.gridpane.checkpath(this.shapes[0])||
                this.gridpane.checkpath(this.shapes[1])||
                this.gridpane.checkpath(this.shapes[2]);
        if(!continueornot){
            this.gameOver();
        }
        int[] all = new int[183];
        for(int i = 0;i<=2;i++){
            for(int j = 0;j<=60;j++){
                all[i*61+j]=this.gridpane.AICheckPath(this.shapes[i], j);
            }
        }
        int max = all[0];
        int maxpointer = 0;
        for(int i = 0;i<183;i++){
            if(max<all[i]) {
                max = all[i];
                maxpointer = i;
            }
        }
        HexShape hs = this.shapes[maxpointer/61];
        hs.virtualmove(this.gridpane.grid.get(maxpointer%61).ctr.x, this.gridpane.grid.get(maxpointer%61).ctr.y);
        for(inPaneHex h:this.gridpane.grid){
            for(movableHex mh:hs.hexset){
                if(h.contains(mh.ctr)){
                    h.settle(hs.getFillColor());
                    h.markNeedToCheck();
                    break;
                }
            }
        }
        this.shapes[maxpointer/61] = new HexShape(this.generator.generate(),(maxpointer/61)+1);
        for(Iterator<inPaneHex> it = this.gridpane.grid.iterator();it.hasNext();){
            inPaneHex p = it.next();
            if(p.needToCheck()){
                this.gridpane.check(1,p.d1);
                    this.gridpane.check(2,p.d2);
                    this.gridpane.check(3,p.d3);
            }
        }
            //start the clean process if some inPaneHex need to be cleaned
        for(inPaneHex p:this.gridpane.grid){
            if(p.needToClean()){
                this.wait = false;
                this.timecounter = 0;
                this.timer.start();
                return;
            }
        }
        this.wait = true;
        this.timecounter = 0;
        this.timer.start();
        repaint();
    }
}
