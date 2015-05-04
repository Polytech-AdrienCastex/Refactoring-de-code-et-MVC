package vue;

import java.awt.Polygon;
import modele.Point;
import modele.Tortue;

public class TortueRonde extends TortueDrawer
{
    public TortueRonde()
    {
        super();
    }

    @Override
    protected Polygon getShape(Tortue tortue, boolean selected)
    {
        Polygon circle = new Polygon();
        
        Point center = tortue.getPosition();
        double r = Math.sqrt( rp*rp + rb*rb );
        
        if(!selected)
            r /= 2;
        else
            r /= 1.2;
        
        for(int i = 0; i < 360; i++)
            circle.addPoint(center.x + (int)(r * Math.cos(i * ratioDegRad)), center.y + (int)(r * Math.sin(i * ratioDegRad)));
        
        return circle;
    }
}
