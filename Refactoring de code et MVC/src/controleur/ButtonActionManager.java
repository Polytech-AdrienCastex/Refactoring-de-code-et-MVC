package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modele.Feuille;
import modele.Point;
import modele.Tortue;
import modele.TortueAmelioree;
import vue.IView;

public class ButtonActionManager extends WindowAdapter implements ActionListener, MouseListener
{
    protected final Feuille feuille;
    protected Tortue getCourrante()
    {
        return feuille.getTortueCourrante();
    }
    
    protected IView view;
    
    public ButtonActionManager()
    {
        feuille = new Feuille();
    }
    
    public void setView(IView view)
    {
        this.view = view;
    }
    
    public void initialize()
    {
        feuille.addTortue(new TortueAmelioree(view.getColor()));
        
        Point size = view.getFeuilleDessinSize();
        getCourrante().setPosition(size.x / 2, size.y / 2);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Point size;
        Tortue t;
        
        String c = e.getActionCommand();

        if(c == null)
            return;
        
        switch(c.toLowerCase())
        {
            case "avancer":
                try
                {
                    getCourrante().avancer(view.getDistance());
                }
                catch (NumberFormatException ex)
                { }
                break;
                
            case "droite":
                try
                {
                    getCourrante().droite(view.getDistance());
                }
                catch (NumberFormatException ex)
                { }
                break;
                
            case "gauche":
                try
                {
                    getCourrante().gauche(view.getDistance());
                }
                catch (NumberFormatException ex)
                { }
                break;
                
            case "lever":
                getCourrante().leverCrayon();
                break;
                
            case "baisser":
                getCourrante().baisserCrayon();
                break;
                
            case "proc1":
                getCourrante().carre();
                break;
                
            case "proc2":
                getCourrante().poly(60,8);
                break;
                
            case "proc3":
                getCourrante().spiral(50,40,6);
                break;
                
            case "effacer":
                feuille.reset();
                
                size = view.getFeuilleDessinSize();
                getCourrante().setPosition(size.x / 2, size.y / 2);
                break;
                
            case "quitter":
                System.exit(0);
                break;
                
            case "color":
                getCourrante().setColor(view.getColor());
                break;
                
            case "ajouter":
                t = new TortueAmelioree(view.getColor());
                t.setColor(view.getColor());
                size = view.getFeuilleDessinSize();
                t.setPosition(size.x / 2, size.y / 2);
                
                feuille.addTortue(t);
                feuille.setSelectedTortue(t);
                break;
                
            case "renommer":
                if(getCourrante() instanceof TortueAmelioree)
                    ((TortueAmelioree)getCourrante()).setName(view.getTortueName());
                break;
                
            case "ajouter ami":
                t = view.getSelectedFriend();
                if(t != null)
                {
                    if(getCourrante() instanceof TortueAmelioree)
                        ((TortueAmelioree)getCourrante()).addTortue(t);
                    if(t instanceof TortueAmelioree)
                        ((TortueAmelioree)t).addTortue(getCourrante());
                }
                break;
                
            case "supprimer ami":
                t = view.getSelectedFriend();
                if(t != null)
                {
                    if(getCourrante() instanceof TortueAmelioree)
                        ((TortueAmelioree)getCourrante()).removeTortue(t);
                    if(t instanceof TortueAmelioree)
                        ((TortueAmelioree)t).removeTortue(getCourrante());
                }
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        super.windowClosing(e);
        System.exit(0);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point p = new Point(e.getX(), e.getY());
        
        Tortue courrante = feuille.getTortueCourrante();
        
        Double min = feuille.getTortues()
                .stream()
                .filter(t -> !t.equals(courrante))
                .mapToDouble(t -> t.getPosition().getDistance(p))
                .min()
                .orElse(-1.0);
        
        if(min.equals(-1.0))
            return;
        
        Tortue selected = feuille.getTortues()
                .stream()
                .filter(t -> !t.equals(courrante))
                .filter(t -> min.equals(t.getPosition().getDistance(p)))
                .findFirst()
                .orElse(null);
        
        if(selected != null)
        {
            feuille.setSelectedTortue(selected);
            e.consume();
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    { }

    @Override
    public void mouseReleased(MouseEvent e)
    { }

    @Override
    public void mouseEntered(MouseEvent e)
    { }

    @Override
    public void mouseExited(MouseEvent e)
    { }
}
