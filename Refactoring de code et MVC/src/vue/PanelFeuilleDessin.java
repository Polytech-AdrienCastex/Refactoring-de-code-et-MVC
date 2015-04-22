/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import modele.FeuilleDessin;

/**
 *
 * @author p1002239
 */
public class PanelFeuilleDessin extends JPanel implements Observer
{
    public PanelFeuilleDessin(FeuilleDessin feuilleDessin)
    {
        super();
        
        this.feuilleDessin = feuilleDessin;
        this.feuilleDessin.addObserver(this);
    }
    
    protected final FeuilleDessin feuilleDessin;
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    public void showTurtles(Graphics g)
    {
        feuilleDessin.getTortues()
                .forEach(t -> t.drawTurtle(g));
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(o == feuilleDessin)
        {
            
        }
        
        this.repaint();
    }
}
