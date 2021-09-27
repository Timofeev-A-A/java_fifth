package second.source;

import java.rmi.RemoteException;

public class EquationImpl implements Equation {

    private double x1;
    private double x2;
    private boolean onlyOne = false;
    private boolean unreal = false;



    @Override
    public String solve(double a, double b, double c) throws RemoteException {
        if (a==0 & b==0) {
            this.x1 = -1;
            this.x2 = -1;
            this.unreal = true;
        }
        else if (a==0 & b!=0) {
            this.x1 = (-c)/b;
            this.x2 = -1;
            this.onlyOne = true;
        }
        else if (b==0) {
            this.x1 = Math.sqrt((-c)/b);
            this.x2 = -(Math.sqrt((-c)/b));
        }
        else if (c==0) {
            this.x1 = -(b)/a;
            this.x2 = 0;
        }
        else {
            double d = Math.pow(b, 2) - (4*a*c);
            if (d<0) {
                this.x1 = 0;
                this.x2 = 0;
                this.unreal = true;
            }
            else if (d==0) {
                this.x1 = -(b)/(2*a);
                this.x2 = -1;
                this.onlyOne = true;
            }
            else {
                this.x1 = (-(b) + Math.sqrt(d))/(2*a);
                this.x2 = (-(b) - Math.sqrt(d))/(2*a);
            }
        }
        return this.toString();
    }

    @Override
    public String toString() {
        return "EquationImpl{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", onlyOne=" + onlyOne +
                ", unreal=" + unreal +
                '}';
    }
}
