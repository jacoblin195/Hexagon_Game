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
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HexShape {
    public ArrayList<movableHex> hexset = new ArrayList<movableHex>();
    public final int num;
    public final int p;
    public final int typeCopy;
    public HexShape(int type,int po){
        
        this.p=po;
        this.typeCopy=type;
        
        int x = 0;
        int y = 0;
        
        if(po == 1){
            x=900;
            y=150;
        } else if (po == 2){
            x = 900;
            y = 400;
        } else if (po == 3){
            x = 900;
            y = 650;
        }
        if(type==1){
            this.hexset.add(new movableHex(x,y,Color.green));
        } else if (type == 2){
            x=x-24-48;
            this.hexset.add(new movableHex(x,y,Color.green));
            this.hexset.add(new movableHex(x+48,y,Color.green));
            this.hexset.add(new movableHex(x+96,y,Color.green));
            this.hexset.add(new movableHex(x+96+48,y,Color.green));
        } else if (type == 3){
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.BLUE));
            this.hexset.add(new movableHex(x-24,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x+24,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x,y+76,Color.BLUE));
        } else if (type == 4){
            x=x-48;
            this.hexset.add(new movableHex(x,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+48,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+96,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+72,y-38,Color.ORANGE));
        } else if (type == 5){
            x=x-48;
            this.hexset.add(new movableHex(x,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+48,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+96,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+72,y+38,Color.ORANGE));
        } else if (type == 6){
            x=x-48;
            this.hexset.add(new movableHex(x,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+48,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+96,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+24,y-38,Color.ORANGE));
        } else if (type == 7){
            x=x-48;
            this.hexset.add(new movableHex(x,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+48,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+96,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+24,y+38,Color.ORANGE));
        } else if (type == 8){
            x=x-24;
            y=y-19;
            this.hexset.add(new movableHex(x,y,Color.BLUE));
            this.hexset.add(new movableHex(x-24,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x+24,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x+48,y,Color.BLUE));
        } else if (type == 9){
            x=x-24;
            y=y-19;
            this.hexset.add(new movableHex(x,y,Color.BLUE));
            this.hexset.add(new movableHex(x+48,y,Color.BLUE));
            this.hexset.add(new movableHex(x+72,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x+24,y+38,Color.BLUE));
        } else if (type == 10){
            x=x-36;
            y=y-40;
            this.hexset.add(new movableHex(x,y,Color.green));
            this.hexset.add(new movableHex(x+24,y+38,Color.green));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.green));
            this.hexset.add(new movableHex(x+24+24+24,y+38+38+38,Color.green));
        } else if (type == 11){
            x=x+36;
            y=y-40;
            this.hexset.add(new movableHex(x,y,Color.green));
            this.hexset.add(new movableHex(x-24,y+38,Color.green));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.green));
            this.hexset.add(new movableHex(x-24-24-24,y+38+38+38,Color.green));
        } else if (type == 12){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.red));
            this.hexset.add(new movableHex(x+24,y+38,Color.red));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.red));
            this.hexset.add(new movableHex(x+48,y,Color.red));
        } else if (type == 13){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.red));
            this.hexset.add(new movableHex(x+24,y+38,Color.red));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.red));
            this.hexset.add(new movableHex(x+24-48,y+38,Color.red));
        } else if (type == 14){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.red));
            this.hexset.add(new movableHex(x+24,y+38,Color.red));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.red));
            this.hexset.add(new movableHex(x+24+48,y+38,Color.red));
        } else if (type == 15){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.red));
            this.hexset.add(new movableHex(x+24,y+38,Color.red));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.red));
            this.hexset.add(new movableHex(x,y+38+38,Color.red));
        } else if (type == 16){
            x=x+24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24,y+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-48,y,Color.MAGENTA));
        } else if (type == 17){
            x=x+24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24,y+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-48,y+38,Color.MAGENTA));
        } else if (type == 18){
            x=x+24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24,y+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24+48,y+38,Color.MAGENTA));
        } else if (type == 19){
            x=x+24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24,y+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24+48,y+38+38,Color.MAGENTA));
        } else if (type == 20){
            x=x-48;
            y = y+19;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x+24,y-38,Color.yellow));
            this.hexset.add(new movableHex(x+24+48,y-38,Color.yellow));
            this.hexset.add(new movableHex(x+48+48,y,Color.yellow));
        } else if (type == 21){
            x=x-48;
            y = y-19;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x+24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x+24+48,y+38,Color.yellow));
            this.hexset.add(new movableHex(x+48+48,y,Color.yellow));
        } else if (type == 22){
            x=x-24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x+48,y,Color.yellow));
            this.hexset.add(new movableHex(x+48+24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x+48,y+38+38,Color.yellow));
        } else if (type == 23){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x-24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x,y+38+38,Color.yellow));
            this.hexset.add(new movableHex(x+48,y+38+38,Color.yellow));
        } else if (type == 24){
            x=x+24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x+24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x,y+38+38,Color.yellow));
            this.hexset.add(new movableHex(x-48,y+38+38,Color.yellow));
        } else if (type == 25){
            x=x+24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x-48,y,Color.yellow));
            this.hexset.add(new movableHex(x-48-24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x-48,y+76,Color.yellow));
        }
        if (type == 1) {
            this.num = 1;
        } else {
            this.num = 4;
        }
    }
    public boolean contains(Point p){
        boolean result = false;
        for (movableHex m:this.hexset){
            result = result || m.contains(p);
            if(result){
                break;
            }
        }
        return result;
    }
    public void moveto(int x,int y,int DeltaX,int DeltaY){
        int delx = x-DeltaX-this.hexset.get(0).initicalX;
        int dely = y-DeltaY-this.hexset.get(0).initicalY;
        for(movableHex m:this.hexset){
            m.update(delx, dely);
        }
    }
    public Point getAnchor(){
        return this.hexset.get(0).ctr;
    }
    public Color getFillColor(){
        return this.hexset.get(0).getFillColor();
    }
    public Rectangle hitbox(){
        int beginX = this.hexset.get(0).ctr.x;
        int beginY = this.hexset.get(0).ctr.y;
        int EndX = this.hexset.get(0).ctr.x;
        int EndY = this.hexset.get(0).ctr.y;
        for(movableHex h:this.hexset){
            if(h.ctr.x<beginX){
                beginX=h.ctr.x;
            } else if (h.ctr.x>EndX){
                EndX = h.ctr.x;
            }
            if(h.ctr.y<beginY){
                beginY=h.ctr.y;
            } else if (h.ctr.y>EndY){
                EndY = h.ctr.y;
            }
        }
        int lux = beginX-19;
        int luy = beginY-22;
        int dx = EndX-lux+19;
        int dy = EndY-luy+22;
        return new Rectangle(lux,luy,dx,dy);
    }
    public void virtualmove(int x,int y){
        int delx = x-this.hexset.get(0).ctr.x;
        int dely = y-this.hexset.get(0).ctr.y;
        for(movableHex m:this.hexset){
            m.update(delx, dely);
        }
    }
    public void draw(Graphics g){
        for(movableHex m:this.hexset){
            m.draw(g);
        }
    }
    public void withdraw(){
        hexset = new ArrayList<movableHex>();
        int x = 0;
        int y = 0;
        
        if(p == 1){
            x=900;
            y=150;
        } else if (p == 2){
            x = 900;
            y = 400;
        } else if (p == 3){
            x = 900;
            y = 650;
        }
        if(typeCopy==1){
            this.hexset.add(new movableHex(x,y,Color.green));
        } else if (typeCopy == 2){
            x=x-24-48;
            this.hexset.add(new movableHex(x,y,Color.green));
            this.hexset.add(new movableHex(x+48,y,Color.green));
            this.hexset.add(new movableHex(x+96,y,Color.green));
            this.hexset.add(new movableHex(x+96+48,y,Color.green));
        } else if (typeCopy == 3){
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.BLUE));
            this.hexset.add(new movableHex(x-24,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x+24,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x,y+76,Color.BLUE));
        } else if (typeCopy == 4){
            x=x-48;
            this.hexset.add(new movableHex(x,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+48,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+96,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+72,y-38,Color.ORANGE));
        } else if (typeCopy == 5){
            x=x-48;
            this.hexset.add(new movableHex(x,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+48,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+96,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+72,y+38,Color.ORANGE));
        } else if (typeCopy == 6){
            x=x-48;
            this.hexset.add(new movableHex(x,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+48,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+96,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+24,y-38,Color.ORANGE));
        } else if (typeCopy == 7){
            x=x-48;
            this.hexset.add(new movableHex(x,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+48,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+96,y,Color.ORANGE));
            this.hexset.add(new movableHex(x+24,y+38,Color.ORANGE));
        } else if (typeCopy == 8){
            x=x-24;
            y=y-19;
            this.hexset.add(new movableHex(x,y,Color.BLUE));
            this.hexset.add(new movableHex(x-24,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x+24,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x+48,y,Color.BLUE));
        } else if (typeCopy == 9){
            x=x-24;
            y=y-19;
            this.hexset.add(new movableHex(x,y,Color.BLUE));
            this.hexset.add(new movableHex(x+48,y,Color.BLUE));
            this.hexset.add(new movableHex(x+72,y+38,Color.BLUE));
            this.hexset.add(new movableHex(x+24,y+38,Color.BLUE));
        } else if (typeCopy == 10){
            x=x-36;
            y=y-40;
            this.hexset.add(new movableHex(x,y,Color.green));
            this.hexset.add(new movableHex(x+24,y+38,Color.green));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.green));
            this.hexset.add(new movableHex(x+24+24+24,y+38+38+38,Color.green));
        } else if (typeCopy == 11){
            x=x+36;
            y=y-40;
            this.hexset.add(new movableHex(x,y,Color.green));
            this.hexset.add(new movableHex(x-24,y+38,Color.green));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.green));
            this.hexset.add(new movableHex(x-24-24-24,y+38+38+38,Color.green));
        } else if (typeCopy == 12){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.red));
            this.hexset.add(new movableHex(x+24,y+38,Color.red));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.red));
            this.hexset.add(new movableHex(x+48,y,Color.red));
        } else if (typeCopy == 13){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.red));
            this.hexset.add(new movableHex(x+24,y+38,Color.red));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.red));
            this.hexset.add(new movableHex(x+24-48,y+38,Color.red));
        } else if (typeCopy == 14){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.red));
            this.hexset.add(new movableHex(x+24,y+38,Color.red));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.red));
            this.hexset.add(new movableHex(x+24+48,y+38,Color.red));
        } else if (typeCopy == 15){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.red));
            this.hexset.add(new movableHex(x+24,y+38,Color.red));
            this.hexset.add(new movableHex(x+24+24,y+38+38,Color.red));
            this.hexset.add(new movableHex(x,y+38+38,Color.red));
        } else if (typeCopy == 16){
            x=x+24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24,y+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-48,y,Color.MAGENTA));
        } else if (typeCopy == 17){
            x=x+24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24,y+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-48,y+38,Color.MAGENTA));
        } else if (typeCopy == 18){
            x=x+24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24,y+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24+48,y+38,Color.MAGENTA));
        } else if (typeCopy == 19){
            x=x+24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24,y+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24,y+38+38,Color.MAGENTA));
            this.hexset.add(new movableHex(x-24-24+48,y+38+38,Color.MAGENTA));
        } else if (typeCopy == 20){
            x=x-48;
            y = y+19;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x+24,y-38,Color.yellow));
            this.hexset.add(new movableHex(x+24+48,y-38,Color.yellow));
            this.hexset.add(new movableHex(x+48+48,y,Color.yellow));
        } else if (typeCopy == 21){
            x=x-48;
            y = y-19;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x+24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x+24+48,y+38,Color.yellow));
            this.hexset.add(new movableHex(x+48+48,y,Color.yellow));
        } else if (typeCopy == 22){
            x=x-24;
            y = y-38;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x+48,y,Color.yellow));
            this.hexset.add(new movableHex(x+48+24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x+48,y+38+38,Color.yellow));
        } else if (typeCopy == 23){
            x=x-24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x-24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x,y+38+38,Color.yellow));
            this.hexset.add(new movableHex(x+48,y+38+38,Color.yellow));
        } else if (typeCopy == 24){
            x=x+24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x+24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x,y+38+38,Color.yellow));
            this.hexset.add(new movableHex(x-48,y+38+38,Color.yellow));
        } else if (typeCopy == 25){
            x=x+24;
            y=y-38;
            this.hexset.add(new movableHex(x,y,Color.yellow));
            this.hexset.add(new movableHex(x-48,y,Color.yellow));
            this.hexset.add(new movableHex(x-48-24,y+38,Color.yellow));
            this.hexset.add(new movableHex(x-48,y+76,Color.yellow));
        }
    }
}
