package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modele.FeuilleDessin;
import modele.Point;
import modele.Tortue;
import vue.IView;

public class ButtonActionManager extends WindowAdapter implements ActionListener
{
    protected final FeuilleDessin feuille;
    protected final Tortue courante;
    
    private IView view;
    
    public ButtonActionManager()
    {
        feuille = new FeuilleDessin();
        courante = new Tortue();
        feuille.addTortue(courante);
    }
    
    public void setView(IView view)
    {
        this.view = view;
        
        Point size = view.getFeuilleDessinSize();
        courante.setPosition(size.x / 2, size.y / 2);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String c = e.getActionCommand();

        if(c == null)
            return;
        
        switch(c.toLowerCase())
        {
            case "avancer":
                try
                {
                    courante.avancer(view.getDistance());
                }
                catch (NumberFormatException ex)
                { }
                break;
                
            case "droite":
                try
                {
                    courante.droite(view.getDistance());
                }
                catch (NumberFormatException ex)
                { }
                break;
                
            case "gauche":
                try
                {
                    courante.gauche(view.getDistance());
                }
                catch (NumberFormatException ex)
                { }
                break;
                
            case "lever":
                courante.leverCrayon();
                break;
                
            case "baisser":
                courante.baisserCrayon();
                break;
                
            case "proc1":
                courante.carre();
                break;
                
            case "proc2":
                courante.poly(60,8);
                break;
                
            case "proc3":
                courante.spiral(50,40,6);
                break;
                
            case "effacer":
                feuille.reset();
                
                Point size = view.getFeuilleDessinSize();
                courante.setPosition(size.x / 2, size.y / 2);
                break;
                
            case "quitter":
                System.exit(0);
                break;
                
            case "color":
                courante.setColor(view.getColor());
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        super.windowClosing(e);
        System.exit(0);
    }
}
