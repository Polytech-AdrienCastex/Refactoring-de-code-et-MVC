package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import modele.Point;
import modele.Tortue;

public class DrawableTortue
{
    // Attributs statiques
    protected static final int      rp = 10, // Taille de la pointe et de la base de la fleche
                                    rb = 5;
    protected static final double   ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    
    
    public static void drawTortue(Graphics graph, Tortue tortue)
    {
        if (graph == null)
            return;
        
        // Dessine les segments
        tortue.getSegments()
                .forEach(s -> DrawableSegment.drawSegment(graph, s));

        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = tortue.getPosition();
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = ratioDegRad * (-tortue.getDirection());
        //Demi angle au sommet du triangle
        double alpha = Math.atan((float)rb / (float)rp);
        //Rayon de la fleche
        double r = Math.sqrt( rp*rp + rb*rb );
        //Sens de la fleche

        //Pointe
        Point p2 = new Point(
                (int) Math.round(p.x+r*Math.cos(theta)),
                (int) Math.round(p.y-r*Math.sin(theta)));
        arrow.addPoint(p2.x, p2.y);
        arrow.addPoint(
                (int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        arrow.addPoint(
                (int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        arrow.addPoint(p2.x,p2.y);
        graph.setColor(Color.green);
        graph.fillPolygon(arrow);
    }
}
