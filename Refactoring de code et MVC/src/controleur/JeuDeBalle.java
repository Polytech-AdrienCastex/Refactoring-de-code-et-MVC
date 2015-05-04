package controleur;

import java.util.Random;
import modele.Point;
import modele.Tortue;
import modele.TortueAmelioree;

public class JeuDeBalle extends ButtonActionManager implements Runnable
{
    public JeuDeBalle(int nbUnits)
    {
        this.nbUnits = nbUnits;
    }
    
    protected final int nbUnits;
    
    @Override
    public void initialize()
    {
        Random rnd = new Random();
        Point size = view.getFeuilleDessinSize();
        
        for(int i = 0; i < nbUnits; i++)
        {
            Tortue t = new TortueAmelioree(i);
            t.setColor(i);
            t.setPosition(rnd.nextInt(size.x), rnd.nextInt(size.y));
            feuille.addTortue(t);
        }
    }
    
    @Override
    public void run()
    {
        Random rnd = new Random();
        do
        {
            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException ex)
            {
                System.err.println("ERROR");
                return;
            }

            feuille.getTortues()
                    .stream()
                    .filter(t -> !feuille.getTortueCourrante().equals(t))
                    .forEach(t ->
                    {
                        switch(rnd.nextInt(3))
                        {
                            case 0:
                                t.avancer(rnd.nextInt(50));
                                break;
                                
                            case 1:
                                t.droite(rnd.nextInt(360));
                                break;
                                
                            case 2:
                                t.gauche(rnd.nextInt(360));
                                break;
                        }
                    });
        } while(true);
    }
}
