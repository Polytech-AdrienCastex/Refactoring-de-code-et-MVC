package vue;

import java.awt.Polygon;
import modele.Point;
import modele.Tortue;

public class TortueTriangle extends TortueDrawer
{
    public TortueTriangle()
    {
        super();
    }

    @Override
    protected Polygon getShape(Tortue tortue, boolean selected)
    {
        Polygon arrow = new Polygon();
        
        //Calcule des deux bases
        //Angle de la droite
        double theta = ratioDegRad * (-tortue.getDirection());
        //Demi angle au sommet du triangle
        double alpha = Math.atan((float)rb / (float)rp);
        //Rayon de la fleche
        double r = Math.sqrt( rp*rp + rb*rb );
        
        if(selected)
            r *= 1.2;
        
        //Sens de la fleche

        //Pointe
        Point p2 = new Point(
                (int) Math.round(tortue.getPosition().x+r*Math.cos(theta)),
                (int) Math.round(tortue.getPosition().y-r*Math.sin(theta)));
        
        arrow.addPoint(p2.x, p2.y);
        arrow.addPoint(
                (int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        arrow.addPoint(
                (int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        arrow.addPoint(p2.x, p2.y);
        
        return arrow;
    }
}
