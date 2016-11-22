/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */
import java.lang.Math;
public class ProbabilityController {
    private double rand;
    public ProbabilityController(){
        rand = Math.random();
    }
    public int generate(){
        rand = Math.random();
        if(rand<=0.4){
            rand = Math.random();
            if(rand<=0.58){
                return 1;
            } else if(rand<=0.72){
                return 2;
            } else if(rand<=0.86){
                return 10;
            } else {
                return 11;
            }
        } else if (rand<=0.5){
            rand = Math.random();
            if (rand<=0.333){
                return 3;
            } else if(rand<=0.667){
                return 8;
            } else {
                return 9;
            }
        } else if (rand<=0.85){
            rand = Math.random();
            if (rand<=0.333){
                rand = Math.random();
                if (rand<=0.25){
                    return 4;
                } else if (rand<=0.5){
                    return 5;
                } else if (rand<=0.75){
                    return 6;
                } else {
                    return 7;
                }
            } else if (rand<= 0.667){
                rand = Math.random();
                if (rand<=0.25){
                    return 12;
                } else if (rand<=0.5){
                    return 13;
                } else if (rand<=0.75){
                    return 14;
                } else {
                    return 15;
                }
            } else {
                rand = Math.random();
                if (rand<=0.25){
                    return 16;
                } else if (rand<=0.5){
                    return 17;
                } else if (rand<=0.75){
                    return 18;
                } else {
                    return 19;
                }
            }
        } else {
            rand = Math.random();
            if (rand<=0.5){
                rand = Math.random();
                if (rand<=0.333){
                    return 20;
                } else if (rand<= 0.667){
                    return 21;
                } else {
                    return 22;
                }
            } else {
                rand = Math.random();
                if (rand<=0.333){
                    return 23;
                } else if (rand<= 0.667){
                    return 24;
                } else {
                    return 25;
                }
            }
        }
    }
}
