package vue;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JList;
import javax.swing.JTextField;
import modele.Feuille;
import modele.Tortue;
import modele.TortueAmelioree;

public class TortueCourranteManager implements Observer
{
    public TortueCourranteManager(Feuille feuilleDessin, JTextField inputName, JList<Tortue> tortueList)
    {
        this.feuilleDessin = feuilleDessin;
        this.inputName = inputName;
        this.tortueList = tortueList;
        
        feuilleDessin.addObserver(this);
    }
    
    private final Feuille feuilleDessin;
    private final JTextField inputName;
    private final JList<Tortue> tortueList;

    @Override
    public void update(Observable o, Object arg)
    {
        if(arg == null)
            return;
        
        if("current".equals(arg.toString().toLowerCase()))
        {
            Tortue t = feuilleDessin.getTortueCourrante();
            
            if(t instanceof TortueAmelioree)
            {
                inputName.setText(((TortueAmelioree)t).getName());
            }
            
            tortueList.setListData(feuilleDessin.getTortues().stream().toArray(Tortue[]::new));
        }
    }
}
