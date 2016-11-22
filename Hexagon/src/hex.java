/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */
import java.awt.Polygon;
import java.awt.Point;
import java.util.ArrayList;
import java.lang.Math;
public class hex extends Polygon{
    public Point ctr;
    public ArrayList<Point> pointset = new ArrayList<Point>();
    public hex(int x, int y){
        super();
        this.pointset.add(new Point(x,y-22));
        this.pointset.add(new Point(x-19,y-11));
        this.pointset.add(new Point(x-19,y+11));
        this.pointset.add(new Point(x,y+22));
        this.pointset.add(new Point(x+19,y+11));
        this.pointset.add(new Point(x+19,y-11));
        
        for(Point i:this.pointset){
            this.addPoint(i.x, i.y);
        }
        this.ctr = new Point(x,y);
    } 
}
