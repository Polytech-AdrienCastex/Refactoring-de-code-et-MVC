package vue;

import controleur.ButtonActionManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modele.FeuilleDessin;
import modele.Point;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

				J. Ferber - 1999-2001

				Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/


**************************************************************************/


public class Window extends JFrame implements Runnable, IView
{
    /*+*********************************************
     *+* PROPERTIES
     *+*********************************************/
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);

    private PanelFeuilleDessin panelFeuilleDessin;
    private JTextField inputValue;
    private JComboBox colorList;
    
    protected final ButtonActionManager actionManager;


    /*+*********************************************
     *+* CONSTRUCTOR
     *+*********************************************/
    public Window(ButtonActionManager actionManager, FeuilleDessin feuilleDessin)
    {
        super("un logo tout simple");
        
        this.actionManager = actionManager;

        initialization(feuilleDessin);

        addWindowListener(actionManager);
    }
    
    
    /*+*********************************************
     *+* CREATIONS
     *+*********************************************/
    private void initFeuilleDessin(FeuilleDessin feuilleDessin)
    {
        this.panelFeuilleDessin = new PanelFeuilleDessin(feuilleDessin);
        this.panelFeuilleDessin.setBackground(Color.white);
        this.panelFeuilleDessin.setSize(new Dimension(600, 400));
        this.panelFeuilleDessin.setPreferredSize(new Dimension(600, 400));
    }
    private void initMenus(JMenuBar menu)
    {
        JMenu menuFile = new JMenu("File"); // on installe le premier menu
        menu.add(menuFile);

        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuCommandes = new JMenu("Commandes"); // on installe le premier menu
        menu.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
        addMenuItem(menuCommandes, "Droite", "Droite", -1);
        addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
        addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
        addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);

        JMenu menuHelp = new JMenu("Aide"); // on installe le premier menu
        menu.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);
    }
    private void initBottomButtons(JComponent parent)
    {
        addPatternButton(parent, "Proc1");
        addPatternButton(parent, "Proc2");
        addPatternButton(parent, "Proc3");
    }
    private void initTopButtons(JComponent parent)
    {
        addButton(parent, "Effacer", "Nouveau dessin", "/icons/index.png");

        parent.add(Box.createRigidArea(HGAP));
        inputValue = new JTextField("45", 5);
        parent.add(inputValue);
        addButton(parent, "Avancer", "Avancer 50");
        addButton(parent, "Droite",  "Droite 45");
        addButton(parent, "Gauche",  "Gauche 45");
        addButton(parent, "Lever",   "Lever Crayon");
        addButton(parent, "Baisser", "Baisser Crayon");
    }
    private void initColorComponent(JComponent parent)
    {
        JLabel colorLabel = new JLabel("   Couleur: ");
        parent.add(colorLabel);
        colorList = new JComboBox(ColorManager.getColors());
        colorList.setActionCommand("color");
        colorList.addActionListener(actionManager);
        parent.add(colorList);
    }
    
    protected void initialization(FeuilleDessin feuilleDessin)
    {
            getContentPane().setLayout(new BorderLayout(10,10));

            // Top bar
            JToolBar toolBar = new JToolBar();
            
            // Boutons
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(toolBar);

            getContentPane().add(buttonPanel, "North");
            initTopButtons(toolBar);

            // Create the combo box
            toolBar.add(Box.createRigidArea(HGAP));
            initColorComponent(toolBar);

            // Menus
            JMenuBar menubar = new JMenuBar();
            setJMenuBar(menubar);	// on installe le menu bar
            initMenus(menubar);


            // Les boutons du bas
            JPanel p2 = new JPanel(new GridLayout());
            initBottomButtons(p2);

            getContentPane().add(p2, "South");

            // Feuille de dessin
            initFeuilleDessin(feuilleDessin);
            getContentPane().add(this.panelFeuilleDessin, "Center");

            // Finitions
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            pack();
            setVisible(true);
    }

    /*+*********************************************
     *+* MACROS
     *+*********************************************/
    
    // utilitaires pour installer des boutons et des menus
    protected void addPatternButton(JComponent parent, String name)
    {
        JButton button = new JButton(name);
        parent.add(button);
        button.addActionListener(actionManager);
    }
    
    protected void addButton(JComponent p, String name, String tooltiptext)
    {
        addButton(p, name, tooltiptext, null);
    }
    protected void addButton(JComponent p, String name, String tooltiptext, String imageName)
    {
        JButton b;
        if ((imageName == null) || (imageName.equals("")))
        {
            b = (JButton)p.add(new JButton(name));
        }
        else
        {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null)
            {
                ImageIcon im = new ImageIcon (u);
                b = (JButton) p.add(new JButton(im));
            }
            else
                b = (JButton) p.add(new JButton(name));
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
        b.addActionListener(actionManager);
    }
    
    protected void addMenuItem(JMenu m, String label, String command, int key)
    {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(actionManager);
        if (key > 0)
        {
            if (key != KeyEvent.VK_DELETE)
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            else
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
        }
    }

    /*+*********************************************
     *+* RUNNABLE
     *+*********************************************/
    @Override
    public void run()
    {
        this.setVisible(true);
    }

    /*+*********************************************
     *+* IVIEW
     *+*********************************************/
    @Override
    public Integer getDistance() throws NumberFormatException
    {
        try
        {
            return Integer.parseInt(inputValue.getText());
        }
        catch (NumberFormatException ex)
        {
            System.err.println("ce n'est pas un nombre : " + inputValue.getText());
            throw ex;
        }
    }

    @Override
    public Point getFeuilleDessinSize()
    {
        Dimension dim = panelFeuilleDessin.getSize();
        return new Point(dim.width, dim.height);
    }

    @Override
    public Integer getColor()
    {
        return colorList.getSelectedIndex();
    }
}
