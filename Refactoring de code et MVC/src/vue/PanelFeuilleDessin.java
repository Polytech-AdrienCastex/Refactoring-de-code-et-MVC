package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import modele.FeuilleDessin;

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

        feuilleDessin.getTortues()
                .forEach(t -> DrawableTortue.drawTortue(g, t));
    }

    @Override
    public void update(Observable o, Object arg)
    {
        this.repaint();
    }
}
