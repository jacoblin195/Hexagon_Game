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
import java.awt.Graphics;
public class inPaneHex extends hex{
    public int eval;
    public int d1 = 0;
    public int d2 = 0;
    public int d3 = 0;
    private final Color borderColor = Color.BLACK;
    private Color fillColor = Color.GRAY;
    private boolean isHovered = false;
    private boolean hoverable = true;
    
    private boolean settled = false;
    private boolean needToCheck = false;
    private boolean needToClean = false;
   
    public inPaneHex(int x, int y){
        super(x,y);
    }
    
    public void setEval(int eval) {
        this.eval = eval;
    }

    private void setD1(int d1) {
        this.d1 = d1;
    }

    private void setD2(int d2) {
        this.d2 = d2;
    }

    private void setD3(int d3) {
        this.d3 = d3;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    private void setIsHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }

    private void setHoverable(boolean hoverable) {
        this.hoverable = hoverable;
    }

    private void setSettled(boolean settled) {
        this.settled = settled;
    }

    private void setNeedToCheck(boolean needToCheck) {
        this.needToCheck = needToCheck;
    }

    private void setNeedToClean(boolean needToClean) {
        this.needToClean = needToClean;
    }
    
    
    public int evaluate(){
        if(this.settled){
            return 0;
        }
        return this.eval;
    }
    public void hover(){
        if(hoverable){
            this.isHovered = true;
            this.fillColor = Color.BLACK;
        }
    }
    
    public void unhover(){
        if(hoverable){
            this.isHovered = false;
            this.fillColor = Color.GRAY;
        }
    }
    
    public void settle(Color c){
        if(hoverable){
            this.fillColor = c;
            this.hoverable = false;
            this.isHovered = false;
            this.settled=true;
        }
    }
    
    public void rebuild(){
        if(settled){
            this.hoverable=true;
            this.isHovered = false;
            this.needToCheck = false;
            this.needToClean = false;
            this.fillColor = Color.GRAY;
            this.settled = false;
        }
    }
    
    public void draw(Graphics g){
        g.setColor(this.fillColor);
        g.fillPolygon(this);
        g.setColor(this.borderColor);
        g.drawPolygon(this);
    }
    
    public boolean isHovered(){
        return this.isHovered;
    }
    
    public boolean hoverable(){
        return this.hoverable;
    }
    
    public Color getFillColor(){
        return this.fillColor;
    }
    public void setFillColor(int r, int g, int b){
        this.fillColor=new Color(r,g,b);
    }
    
    public boolean needToCheck(){
        return this.needToCheck;
    }
    
    public boolean needToClean(){
        return this.needToClean;
    }
    
    public void markNeedToClean(){
        this.needToClean = true;
    }
    public void markNeedToCheck(){
        this.needToCheck = true;
    }
    public boolean settleStatus(){
        return this.settled;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        inPaneHex iPH = new inPaneHex(this.ctr.x,this.ctr.y);
        iPH.setD1(d1);
        iPH.setD2(d2);
        iPH.setD3(d3);
        iPH.setEval(eval);
        iPH.setFillColor(fillColor);
        iPH.setHoverable(hoverable);
        iPH.setIsHovered(isHovered);
        iPH.setNeedToCheck(needToCheck);
        iPH.setNeedToClean(needToClean);
        iPH.setSettled(settled);
        return iPH;
    }

}
