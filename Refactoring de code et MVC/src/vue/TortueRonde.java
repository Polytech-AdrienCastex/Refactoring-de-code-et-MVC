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
        Point center = tortue.getPosition();
        double r = Math.sqrt( rp*rp + rb*rb );
        
        if(!selected)
            r /= 2;
        else
            r /= 1.2;
        
        return circle(center, r);
    }
}
