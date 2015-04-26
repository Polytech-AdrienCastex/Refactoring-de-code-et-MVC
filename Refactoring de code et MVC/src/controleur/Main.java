package controleur;

import javax.swing.SwingUtilities;
import vue.Window;

public class Main
{
    public static void main(String[] args)
    {
        ButtonActionManager actionManager = new ButtonActionManager();
        
        Window app = new Window(actionManager, actionManager.feuille);
        actionManager.setView(app);
        
        SwingUtilities.invokeLater(app);
    }
}
