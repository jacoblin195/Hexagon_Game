
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;


public class movableHex extends hex{
    public final int initicalX;
    public final int initicalY;
    private final Color fillColor;
    private final Color borderColor = Color.BLACK;
    
    public movableHex(int x,int y,Color c){
        
        super(x,y);
        this.initicalX = x;
        this.initicalY = y;
        this.fillColor = c;
    }
    public void update(int delx, int dely){
        
        this.translate(this.initicalX-this.ctr.x, this.initicalY-this.ctr.y);
        this.translate(delx, dely);
        this.ctr = new Point (this.initicalX+delx,this.initicalY+dely);
        
    }
    public void draw(Graphics g){
        g.setColor(this.fillColor);
        g.fillPolygon(this);
        g.setColor(this.borderColor);
        g.drawPolygon(this);
    }
    public Color getFillColor(){
        return this.fillColor;
    }
    
}
