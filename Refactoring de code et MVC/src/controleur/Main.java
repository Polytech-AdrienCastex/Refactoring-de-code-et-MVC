package controleur;

import javax.swing.SwingUtilities;
import vue.Window;

public class Main
{
    public static void main(String[] args)
    {
        JeuDeBalle actionManager = new JeuDeBalle(10);
        
        Window app = new Window(actionManager, actionManager.feuille);
        actionManager.setView(app);
        actionManager.initialize();
        
        SwingUtilities.invokeLater(app);
        
        actionManager.run();
    }
}
