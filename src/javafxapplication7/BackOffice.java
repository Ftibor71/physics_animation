/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import javafx.animation.PathTransition;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author User
 */
public class BackOffice {
   
    boolean checkData (String field1, String field2, String field3){
        boolean iscorrect;
        double value;
        try{
            value = Double.parseDouble(field1);
            value = Double.parseDouble(field2);
            value = Double.parseDouble(field3);
            iscorrect = true;
        }
        catch (NumberFormatException notdouble){
            iscorrect = false;
            return iscorrect;
        }        
        value = Double.parseDouble(field1);
        if ((value<1) || (value > 50)) {
            iscorrect = false;
            return iscorrect;
        }        
        value = Double.parseDouble(field2);
        if ((value<1) || (value > 10)) {
            iscorrect = false;
            return iscorrect;
        }        
        value = Double.parseDouble(field3);
        if ((value<1) || (value > 30)) {
            iscorrect = false;
            return iscorrect;
        }
        return iscorrect;
    }
    
    PathTransition animateCar(double t){
        Path carpath = new Path();
        carpath.getElements().add(new MoveTo (41, 36));
        carpath.getElements().add(new MoveTo(431, 184));
        
        PathTransition carAnimation = new PathTransition();
        carAnimation.setDuration(Duration.seconds(t));
        carAnimation.setPath(carpath);
        carAnimation.setAutoReverse(false);
        
        return carAnimation;
    }
    
    double Counta (double alfa){ //count velocity (a)
        double a;
        alfa = Math.toRadians(alfa);
        a = sin(alfa)*9.81;
        return a;        
    }
    
    double Countv (double h){
        double v;
        v = sqrt(2*9.81*h);
        return h;
    }
}
