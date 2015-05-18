package modele;

import java.util.*;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

	Source originale : J. Ferber - 1999-2001

			   Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/2001

**************************************************************************/

/** La classe Tortue qui se deplace en coordonnees polaires
**/

public class Tortue extends Observable
{
    // Attributs statiques
    protected static final double   ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    // Attributs
    protected final List<Segment> listSegments; // Trace de la tortue

    protected Point     location;   // Coordonnees de la tortue
    protected int       dir;        // Direction de la tortue (angle en degres)
    protected boolean   crayon;     // par defaut on suppose qu'on dessine
    protected int       coul;

    
    
    
    public List<Segment> getSegments()
    {
        return listSegments;
    }

    public Point getPosition()
    {
        return location;
    }

    public int getDirection()
    {
        return dir;
    }

    


    // Methodes
    public void setColor(int n) {coul = n;}
    public int getColor() {return coul;}

    public Tortue()
    {
        listSegments = new ArrayList<>();
        
        reset();
    }

    public void reset()
    {
        // on initialise la position de la tortue
        setPosition(0, 0);
        dir = -90;
        coul = 0;
        crayon = true;
        listSegments.clear();
        notifyChanges();
    }

    public void setPosition(int newX, int newY)
    {
        location = new Point(newX, newY);
        notifyChanges();
    }
    public void setPosition(Point p)
    {
        setPosition(p.x, p.y);
    }



    /* les procedures de base de fonctionnement de la tortue */

    // avancer de n pas
    public void avancer(int dist)
    {
        Point newLocation = new Point(
                (int) Math.round(location.x+dist*Math.cos(ratioDegRad*dir)),
                (int) Math.round(location.y+dist*Math.sin(ratioDegRad*dir)));

        if (crayon)
        {
            Segment seg = new Segment();

            seg.ptStart = location.clone();
            seg.ptEnd = newLocation.clone();
            seg.color = coul;

            listSegments.add(seg);
        }

        setPosition(newLocation);
        notifyChanges();
    }
    
    public Point nextPosition(int dist)
    {
        Point newLocation = new Point(
                (int) Math.round(location.x+dist*Math.cos(ratioDegRad*dir)),
                (int) Math.round(location.y+dist*Math.sin(ratioDegRad*dir)));

        return newLocation;
    }

    // aller a droite
    public void droite(int ang)
    {
        dir = (dir + ang) % 360;
        notifyChanges();
    }

    // aller a gauche
    public void gauche(int ang)
    {
        dir = (dir - ang) % 360;
        notifyChanges();
    }

    // baisser le crayon pour dessiner
    public void baisserCrayon()
    {
        crayon = true;
        notifyChanges();
    }

    // lever le crayon pour ne plus dessiner
    public void leverCrayon()
    {
        crayon = false;
        notifyChanges();
    }

    // pour changer de couleur de dessin
    public void couleur(int n)
    {
        coul = n % 12;
        notifyChanges();
    }

    public void couleurSuivante()
    {
        couleur(coul+1);
    }

    /** quelques classiques */

    public void carre()
    {
        for (int i = 0; i < 4; i++)
        {
            avancer(100);
            droite(90);
        }
    }

    public void poly(int n, int a)
    {
        for (int j = 0; j < a; j++)
        {
            avancer(n);
            droite(360/a);
        }
    }

    public void spiral(int n, int k, int a)
    {
        for (int i = 0; i < k; i++)
        {
            couleur(coul+1);
            avancer(n);
            droite(360/a);
            n = n+1;
        }
    }
    
    protected void notifyChanges()
    {
        setChanged();
        notifyObservers();
    }
}
