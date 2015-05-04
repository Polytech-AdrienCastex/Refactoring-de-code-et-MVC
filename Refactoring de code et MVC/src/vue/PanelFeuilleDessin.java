package vue;

import controleur.ButtonActionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JList;
import javax.swing.JPanel;
import modele.Feuille;
import modele.Tortue;

public class PanelFeuilleDessin extends JPanel implements Observer
{
    public PanelFeuilleDessin(Feuille feuilleDessin, ButtonActionManager actionManager, JList<Tortue> tortueList)
    {
        super();
        
        this.feuilleDessin = feuilleDessin;
        this.feuilleDessin.addObserver(this);
        this.tortueList = tortueList;
        this.tortueDrawer = new TortueRonde();
        
        this.addMouseListener(actionManager);
    }
    
    protected final TortueDrawer tortueDrawer;
    protected final Feuille feuilleDessin;
    protected final JList<Tortue> tortueList;
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);

        Tortue courrante = feuilleDessin.getTortueCourrante();
        feuilleDessin.getTortues()
                .forEach(t -> tortueDrawer.drawTortue(g, t, courrante.equals(t)));
    }

    @Override
    public void update(Observable o, Object arg)
    {
        tortueList.setListData(feuilleDessin.getTortues().stream().toArray(Tortue[]::new));
        this.repaint();
    }
}
