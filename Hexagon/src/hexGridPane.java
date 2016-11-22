/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */
import java.util.ArrayList;
import java.awt.Graphics;

public class hexGridPane {
    public ArrayList<inPaneHex> grid = new ArrayList<inPaneHex>();
    public hexGridPane(int a, int b) {
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
}
