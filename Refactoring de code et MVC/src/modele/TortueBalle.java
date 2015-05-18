package modele;

public class TortueBalle extends TortueAmelioree
{
    private final long MIN_BALL_OWNING_TIME = 1000; // ms
    
    public TortueBalle(int bodyColor)
    {
        super(bodyColor);
        
        this.hasTheBall = false;
        this.lastBallObtained = 0;
    }
    
    private boolean hasTheBall;
    private long lastBallObtained;
    
    public void setTheBall(boolean hasTheBall)
    {
        if(this.hasTheBall != hasTheBall)
        {
            this.hasTheBall = hasTheBall;
            
            if(this.hasTheBall)
                lastBallObtained = System.currentTimeMillis();
            
            setChanged();
        }
    }
    
    public boolean hasTheBall()
    {
        return hasTheBall;
    }
    
    @Override
    public void setPosition(int x, int y)
    {
        location = new Point(x, y);
        
        long currentTime = System.currentTimeMillis();
        
        if(this.hasTheBall() && currentTime - lastBallObtained > MIN_BALL_OWNING_TIME && connaissances != null)
        {
            lastBallObtained = currentTime;
            synchronized(connaissances)
            {
                connaissances.stream()
                        .filter(t -> this.distance(t) <= 50)
                        .filter(t -> t instanceof TortueBalle)
                        .map(t -> (TortueBalle)t)
                        .findFirst()
                        .ifPresent(t ->
                        {
                            System.out.println("Prend la balle " + t + "!");
                            t.setTheBall(true);
                            this.setTheBall(false);
                        });
            }
        }
    }
}
