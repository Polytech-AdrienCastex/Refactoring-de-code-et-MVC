package modele;

public class TortueBalle extends TortueAmelioree
{
    public TortueBalle(int bodyColor)
    {
        super(bodyColor);
        
        this.hasTheBall = false;
    }
    
    private boolean hasTheBall;
    
    public void setTheBall(boolean hasTheBall)
    {
        this.hasTheBall = hasTheBall;
    }
    
    public boolean hasTheBall()
    {
        return hasTheBall;
    }
}
