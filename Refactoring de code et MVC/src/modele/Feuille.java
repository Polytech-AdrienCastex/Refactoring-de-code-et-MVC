package modele;

import java.util.*;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class Feuille extends Observable implements Observer
{
    /**
     * Liste des tortues enregistrées
     */
    private final Set<Tortue> tortues;
    
    private Tortue courrante = null;
    
    public void setSelectedTortue(Tortue t)
    {
        courrante = t;
        notifyChanged("current");
    }
    public Tortue getTortueCourrante()
    {
        return courrante;
    }

    public Feuille()
    {
        super();

        tortues = new HashSet<>();
    }

    public void addTortue(Tortue tortue)
    {
        tortues.add(tortue);
        tortue.addObserver(this);
        
        if(courrante == null)
            setSelectedTortue(tortue);
        
        notifyChanged();
    }

    public Set<Tortue> getTortues()
    {
        return tortues;
    }

    public void reset()
    {
        tortues.forEach(t -> t.reset());
        notifyChanged();
    }

    @Override
    public void update(Observable object, Object param)
    {
        notifyChanged();
    }
    protected void notifyChanged()
    {
        notifyChanged(null);
    }
    protected void notifyChanged(String name)
    {
        super.setChanged();
        super.notifyObservers(name);
    }
}
