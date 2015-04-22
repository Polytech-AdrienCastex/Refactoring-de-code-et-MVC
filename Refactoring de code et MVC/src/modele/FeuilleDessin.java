package modele;

// package logo;

import left.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin extends Observable
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

    public void addTortue(Tortue o)
    {
        tortues.add(o);
        
        super.setChanged();
        super.notifyObservers();
    }

    public List<Tortue> getTortues()
    {
        return tortues;
    }

    public void reset()
    {
        tortues.forEach(t -> t.reset());
        
        super.setChanged();
        super.notifyObservers();
    }
}
