package controleur;

import java.awt.event.ActionEvent;
import java.util.Random;
import modele.Point;
import modele.Tortue;
import modele.TortueAmelioree;
import modele.TortueBalle;

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
            TortueBalle t = new TortueBalle(i);
            t.setColor(i);
            t.setPosition(rnd.nextInt(size.x), rnd.nextInt(size.y));
            if(i == 0)
                t.setTheBall(true);
            feuille.addTortue(t);
        }
        
        feuille.getTortues()
                .stream()
                .map(t -> (TortueAmelioree)t)
                .forEach(t -> feuille.getTortues().stream()
                        .filter(ts -> !ts.equals(t))
                        .forEach(ts -> t.addTortue(ts)));
        
        feuille.getTortues()
                .stream()
                .forEach(t -> t.leverCrayon());
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

            final Point size = view.getFeuilleDessinSize();
            feuille.getTortues()
                    .stream()
                    .filter(t -> !feuille.getTortueCourrante().equals(t))
                    .forEach(t ->
                    {
                        synchronized(t)
                        {
                            switch(rnd.nextInt(2))
                            {
                                case 0:
                                    int av = rnd.nextInt(7) + 1;
                                    Point nextPos = t.nextPosition(av);
                                    if(nextPos.x > 0 && nextPos.x < size.x && nextPos.y > 0 && nextPos.y < size.y)
                                        t.avancer(av);
                                    break;

                                case 1:
                                    t.droite(rnd.nextInt(360));
                                    break;
                            }
                        }
                    });
        } while(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        
        String c = e.getActionCommand();

        if(c == null)
            return;
        
        switch(c.toLowerCase())
        {
            case "lancer":
                Thread th = new Thread(this);
                th.start();
                break;
        }
    }
}
