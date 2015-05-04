/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author p1002239
 */
public class TortueAmelioree extends ColorableTortue
{
    public TortueAmelioree(int bodyColor)
    {
        super(bodyColor);
        initialize();
    }
    
    private static int  uid = 0;
    protected String    name; // Nom de la tortue
    protected final Set<Tortue> connaissances = new HashSet<>();
    
    
    private synchronized void initialize()
    {
        uid++;
        name = "Tortue " + uid;
    }
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
        
        notifyChanges();
    }
    
    public boolean addTortue(Tortue t)
    {
        if(t == this)
            return false;
        
        synchronized(connaissances)
        {
            connaissances.add(t);
        }
        return true;
    }
    public void removeTortue(Tortue t)
    {
        synchronized(connaissances)
        {
            connaissances.remove(t);
        }
    }
    
    public double distance(Tortue t)
    {
        return distance(this, t);
    }
    public static double distance(Tortue t1, Tortue t2)
    {
        return t1.location.getDistance(t2.location);
    }
    
    @Override
    public void setPosition(int x, int y)
    {
        super.setPosition(x, y);
        
        if(connaissances != null)
            synchronized(connaissances)
            {
                connaissances.stream()
                        .filter(t -> this.distance(t) <= 15)
                        .forEach(t ->
                        {
                            System.out.println("Bonjour " + t);
                            t.avancer(15);
                        });
            }
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
