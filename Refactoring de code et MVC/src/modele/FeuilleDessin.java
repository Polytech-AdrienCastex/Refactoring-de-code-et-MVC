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

public class FeuilleDessin extends Observable implements Observer
{
    /**
     * Liste des tortues enregistrées
     */
    private final List<Tortue> tortues;

    public FeuilleDessin()
    {
        super();

        tortues = new ArrayList<>();
    }

    public void addTortue(Tortue tortue)
    {
        tortues.add(tortue);
        tortue.addObserver(this);
        
        notifyChanged();
    }

    public List<Tortue> getTortues()
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
        super.setChanged();
        super.notifyObservers();
    }
}
