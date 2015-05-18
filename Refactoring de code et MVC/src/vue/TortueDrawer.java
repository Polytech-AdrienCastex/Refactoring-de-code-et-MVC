package vue;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import modele.ColorableTortue;
import modele.Point;
import modele.Tortue;
import modele.TortueAmelioree;
import modele.TortueBalle;

public abstract class TortueDrawer
{
    // Attributs statiques
    protected static final int      rp = 10, // Taille de la pointe et de la base de la fleche
                                    rb = 5;
    protected static final double   ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    
    
    protected static Polygon circle(Point center, double radius)
    {
        Polygon circle = new Polygon();
        
        for(int i = 0; i < 360; i++)
            circle.addPoint(center.x + (int)(radius * Math.cos(i * ratioDegRad)), center.y + (int)(radius * Math.sin(i * ratioDegRad)));
        
        return circle;
    }
    
    
    
    
    
    protected abstract Polygon getShape(Tortue tortue, boolean selected);
    
    public void drawTortue(Graphics graph, Tortue tortue, boolean selected)
    {
        if (graph == null)
            return;
        
        // Dessine les segments
        /*synchronized(tortue)
        {*/
            tortue.getSegments()
                    .forEach(s -> DrawableSegment.drawSegment(graph, s));

            //Calcule les 3 coins du triangle a partir de
            // la position de la tortue p
            Point p = tortue.getPosition();


            if(tortue instanceof ColorableTortue)
                graph.setColor(ColorManager.getColor(((ColorableTortue)tortue).getBodyColor()));
            else
                graph.setColor(Color.green);
            graph.fillPolygon(getShape(tortue, selected));

            if(tortue instanceof TortueAmelioree)
            {
                FontMetrics m = graph.getFontMetrics();
                String text = tortue.toString();
                graph.drawString(text, p.x - m.stringWidth(text) / 2, p.y + m.getHeight() + 5);
            }
            
            if(tortue instanceof TortueBalle)
            {
                TortueBalle tb = (TortueBalle)tortue;
                
                if(tb.hasTheBall())
                {
                    graph.setColor(Color.red);
                    graph.fillPolygon(circle(tb.getPosition(), 4));
                }
            }
        //}
    }
}
