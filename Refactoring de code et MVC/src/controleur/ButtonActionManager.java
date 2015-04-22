package controleur;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import left.Tortue;
import modele.FeuilleDessin;
import vue.PanelFeuilleDessin;

public class ButtonActionManager extends WindowAdapter implements ActionListener
{
    private FeuilleDessin feuille;
    private PanelFeuilleDessin panelFeuilleDessin;
    
    private JTextField inputValue;
    private Tortue courante;
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String c = e.getActionCommand();

        if(c == null)
            return;
        
        switch(c.toLowerCase())
        {
            case "avant":
                try
                {
                    courante.avancer(Integer.parseInt(inputValue.getText()));
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("ce n'est pas un nombre : " + inputValue.getText());
                }
                break;
                
            case "droite":
                try
                {
                    courante.droite(Integer.parseInt(inputValue.getText()));
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("ce n'est pas un nombre : " + inputValue.getText());
                }
                break;
                
            case "gauche":
                try
                {
                    courante.gauche(Integer.parseInt(inputValue.getText()));
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("ce n'est pas un nombre : " + inputValue.getText());
                }
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
                
                Dimension size = panelFeuilleDessin.getSize();
                courante.setPosition(size.width/2, size.height/2);
                break;
                
            case "quitter":
                System.exit(0);
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
