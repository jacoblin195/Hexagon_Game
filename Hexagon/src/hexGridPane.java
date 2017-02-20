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
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Iterator;

public class hexGridPane {
    public int a;
    public int b;
    public ArrayList<inPaneHex> grid = new ArrayList<inPaneHex>();
    public hexGridPane(int a, int b) {
        this.a = a;
        this.b = b;
        for(int j = 0;j<=4;++j){
            for (int i = 0;i<=8-j;++i){
                if(j==0){
                    grid.add(new inPaneHex(a+i*48+j*24,b+j*38));
                } else {
                    grid.add(new inPaneHex(a+i*48+j*24,b+j*38));
                    grid.add(new inPaneHex(a+i*48+j*24,b-j*38));
                }
            }
        }
        
        for(inPaneHex h:grid){
            h.d1 = (h.ctr.y - (b-4*38))/(38) + 1;
        }
        for(int j = 0;j<=60;++j){
            if(j==0||j==9||j==25||j==39||j==51){
                inPaneHex temp = grid.get(j);
                temp.d2=1;
                grid.set(j,temp);
            } else if (j==10||j==1||j==11|j==27||j==41||j==53){
                inPaneHex temp = grid.get(j);
                temp.d2=2;
                grid.set(j,temp);
            } else if (j==26||j==12||j==2||j==13||j==29||j==43||j==55){
                inPaneHex temp = grid.get(j);
                temp.d2=3;
                grid.set(j,temp);
            } else if (j==40||j==28||j==14||j==3||j==15||j==31||j==45||j==57){
                inPaneHex temp = grid.get(j);
                temp.d2=4;
                grid.set(j,temp);
            } else if (j==52||j==42||j==30||j==16||j==4||j==17||j==33||j==47||j==59){
                inPaneHex temp = grid.get(j);
                temp.d2=5;
                grid.set(j,temp);
            } else if (j==54||j==44||j==32||j==18||j==5||j==19||j==35||j==49){
                inPaneHex temp = grid.get(j);
                temp.d2=6;
                grid.set(j,temp);
            } else if (j==56||j==46||j==34||j==20||j==6||j==21||j==37){
                inPaneHex temp = grid.get(j);
                temp.d2=7;
                grid.set(j,temp);
            } else if (j==58||j==48||j==36||j==22||j==7||j==23){
                inPaneHex temp = grid.get(j);
                temp.d2=8;
                grid.set(j,temp);
            } else if (j==60||j==50||j==38||j==24||j==8){
                inPaneHex temp = grid.get(j);
                temp.d2=9;
                grid.set(j,temp);
            }
        }
        for(int j = 0;j<=60;++j){
            if(j==0||j==10||j==26||j==40||j==52){
                inPaneHex temp = grid.get(j);
                temp.d3=1;

                grid.set(j,temp);
            } else if (j==9||j==1||j==12|j==28||j==42||j==54){
                inPaneHex temp = grid.get(j);
                temp.d3=2;
                grid.set(j,temp);
            } else if (j==25||j==11||j==2||j==14||j==30||j==44||j==56){
                inPaneHex temp = grid.get(j);
                temp.d3=3;
                grid.set(j,temp);
            } else if (j==39||j==27||j==13||j==3||j==16||j==32||j==46||j==58){
                inPaneHex temp = grid.get(j);
                temp.d3=4;
                grid.set(j,temp);
            } else if (j==51||j==41||j==29||j==15||j==4||j==18||j==34||j==48||j==60){
                inPaneHex temp = grid.get(j);
                temp.d3=5;
                grid.set(j,temp);
            } else if (j==53||j==43||j==31||j==17||j==5||j==20||j==36||j==50){
                inPaneHex temp = grid.get(j);
                temp.d3=6;
                grid.set(j,temp);
            } else if (j==55||j==45||j==33||j==19||j==6||j==22||j==38){
                inPaneHex temp = grid.get(j);
                temp.d3=7;
                grid.set(j,temp);
            } else if (j==57||j==47||j==35||j==21||j==7||j==24){
                inPaneHex temp = grid.get(j);
                temp.d3=8;
                grid.set(j,temp);
            } else if (j==59||j==49||j==37||j==23||j==8){
                inPaneHex temp = grid.get(j);
                temp.d3=9;
                grid.set(j,temp);
            }
        }
        int[] zone5 = {0,9,25,39,51,53,55,57,59,49,37,23,8,24,38,50,60,58,56,54,52,40,26,10};
        int[] zone4 = {1,11,27,41,43,45,47,35,21,7,22,36,48,46,44,42,28,12};
        int[] zone3 = {2,13,29,31,33,19,6,20,34,32,30,14};
        int[] zone2 = {3,15,17,5,18,16};
        for(Integer i:zone5){
            this.grid.get(i).setEval(1);
        }
        for(Integer i:zone4){
            this.grid.get(i).setEval(2);
        }
        for(Integer i:zone3){
            this.grid.get(i).setEval(3);
        }
        for(Integer i:zone2){
            this.grid.get(i).setEval(4);
        }
        this.grid.get(4).setEval(5);
    }
    public void draw(Graphics g){
        for(inPaneHex i:grid){
            i.draw(g);
        }
    }
    public void check(int direction,int indexid){
        boolean allsettle = true;
        if (direction == 1){
            for (inPaneHex h:this.grid){
                if(h.d1==indexid ) {
                    
                    allsettle = allsettle && h.settleStatus();
                }
            }
            if (allsettle == true){
                for (inPaneHex h:this.grid){
                    if(h.d1==indexid) {
                        h.markNeedToClean();
                    }
                }
            }
        } else if (direction == 2){
            for (inPaneHex h:this.grid){
                if(h.d2==indexid) {
                    allsettle = allsettle && h.settleStatus();
                }
            }
            if (allsettle == true){
                for (inPaneHex h:this.grid){
                    if(h.d2==indexid) {
                        h.markNeedToClean();
                    }
                }
            }
        } else if(direction == 3){
            for (inPaneHex h:this.grid){
                if(h.d3==indexid) {
                    allsettle = allsettle && h.settleStatus();
                }
            }
            if (allsettle == true){
                for (inPaneHex h:this.grid){
                    if(h.d3==indexid) {
                        h.markNeedToClean();
                    }
                }
            }
        }
    }
    public boolean checkpath(HexShape k){
        boolean result = false;
        HexShape s = k;
        if(s.typeCopy==1){
            result = true;
        }
        for(int i = 0;i<=60;i++){
            s.virtualmove(this.grid.get(i).ctr.x,this.grid.get(i).ctr.y);
            int count = 0;
            for(inPaneHex p:this.grid){
                if(p.hoverable() && s.contains(p.ctr)){
                        ++count;
                }
                if(count == s.num){
                    result = true;
                    break;
                }
            }
            if(result){
                break;
            }
            s.withdraw();
        }
        s.withdraw();
        return result;
    }
    public int evaluate(){
        int sum = 0;
        for(inPaneHex h:this.grid){
            sum+=h.evaluate();
        }
        return sum;
    }
    public int AICheckPath(HexShape k,int pos) throws CloneNotSupportedException{
        k.virtualmove(this.grid.get(pos).ctr.x,this.grid.get(pos).ctr.y);
        int count = 0;
        for(inPaneHex p:this.grid){
            if(p.hoverable() && k.contains(p.ctr)){
                    ++count;
            }
        }
        if(count != k.num){
            k.withdraw();
            return 0;
        }
        hexGridPane g = new hexGridPane(this.a,this.b);
        g.grid = new ArrayList();
        for(int ii = 0;ii<=60;ii++){
            g.grid.add((inPaneHex)this.grid.get(ii).clone());
        }
        for(inPaneHex h:g.grid){
            for(movableHex mh:k.hexset){
                if(h.contains(mh.ctr)){
                    h.settle(Color.red);
                    h.markNeedToCheck();
                    break;
                }
            }
        }
        for(Iterator<inPaneHex> it = g.grid.iterator();it.hasNext();){
            inPaneHex p = it.next();
            if(p.needToCheck()){
                g.check(1,p.d1);
                g.check(2,p.d2);
                g.check(3,p.d3);
            }
        }
        for(inPaneHex p:g.grid){
            if(p.needToClean()){
                p.rebuild();
            }
        }
        k.withdraw();
        return g.evaluate();
    }
}
