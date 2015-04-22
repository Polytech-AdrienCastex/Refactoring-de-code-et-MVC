package vue;

import javax.swing.SwingUtilities;

public class Main
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Window app = new Window();
        
        SwingUtilities.invokeLater(app);
    }
}
