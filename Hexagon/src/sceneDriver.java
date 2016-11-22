/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.FileWriter;
import java.io.IOException;

public class sceneDriver extends JPanel implements ActionListener,MouseMotionListener,MouseListener {
    public final int FRAME_WIDTH = 1300;
    public final int FRAME_HEIGHT = 830;
    
    private final HexShape[] shapes = new HexShape[3];
    private int dragid = 0;
    private int DeltaX=0;
    private int DeltaY=0;
    private boolean dragged = false;
    private boolean allowForDrag = true;
    private final hexGridPane gridpane  = new hexGridPane(200,400);
    private final Timer timer = new Timer(5, this);
    private int timecounter = 0;
    private int score = 0;
    private int highscore;
    private String highscorename ="";
    private Scanner scan;
    private final File file = new File("highest score.txt");
    private final ProbabilityController generator = new ProbabilityController();
    
    public sceneDriver() {
        super();
        
        this.loaddata();
        
        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        
        shapes[0]=new HexShape(this.generator.generate(),1);
        shapes[1]=new HexShape(this.generator.generate(),2);
        shapes[2]=new HexShape(this.generator.generate(),3);
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

    @Override
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
    public void mouseDragged(MouseEvent e){
        if(this.dragged){
            
            this.shapes[this.dragid].moveto(e.getX(),e.getY(),DeltaX,DeltaY);
            for(inPaneHex p:this.gridpane.grid){
                if(p.hoverable()){
                    if(this.shapes[this.dragid].contains(p.ctr)){
                        p.hover();
                    } else {
                        p.unhover();
                    }
                }
            }

            repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent e){
        if(this.allowForDrag){
            for(int i = 0;i<=2;++i){
                if(this.shapes[i].hitbox().contains(e.getPoint())){
                    this.dragid = i;
                    this.dragged =true;
                    break;
                }
            }
            if(dragged){
                this.DeltaX = e.getPoint().x-this.shapes[this.dragid].getAnchor().x;
                this.DeltaY = e.getPoint().y-this.shapes[this.dragid].getAnchor().y;
            }
            repaint();
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
        if(this.dragged){
            int count=0;
            for(inPaneHex p:this.gridpane.grid){
                if(p.isHovered()){
                    ++count;
                } 
            }
            
            if(count==this.shapes[this.dragid].num){
                // the shape is going to be settled in the grid pane
                
                //settle the shape into the grid pane
                //mark the inPaneHex "needtocheck"
                for(inPaneHex p:this.gridpane.grid){
                    if(p.isHovered()){
                        p.settle(this.shapes[this.dragid].getFillColor());
                        p.markNeedToCheck();
                    }
                }
                
                //generate a new shape on the right side of the screen
                this.shapes[this.dragid] = new HexShape(this.generator.generate(),this.dragid+1);
                
                this.repaint();
                
                //check and mark if inPaneHex need to clean
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
                        this.timer.start();
                        break;
                    }
                }
                
            } else {
                //if the shape is not settled
                
                //move the shape back to the right side
                this.shapes[this.dragid].withdraw();
                
                //unhover all hovered inPaneHex
                for(inPaneHex p:this.gridpane.grid){
                    if(p.isHovered()){
                        p.unhover();
                    }   
                }
                
                this.repaint();
            }
            
        }
        
        this.dragged=false;
        
        if(!timer.isRunning()){
            boolean continueornot = this.gridpane.checkpath(this.shapes[0])||
                    this.gridpane.checkpath(this.shapes[1])||
                    this.gridpane.checkpath(this.shapes[2]);
            //if there is no paths, shutdown the game
            if(!continueornot){
                this.gameOver();
            }
        }
                
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.timecounter == 36){
            this.allowForDrag = true;
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
        } else {
            this.allowForDrag = false;
            for(inPaneHex i:this.gridpane.grid){
                if(i.needToClean()){
                    int r = Math.min(i.getFillColor().getRed() + this.timecounter * 10,255);
                    int b = Math.min(i.getFillColor().getBlue() + this.timecounter * 10,255);
                    int g = Math.min(i.getFillColor().getGreen() + this.timecounter * 10,255);
                    i.setFillColor(r, g, b);
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
    
}
