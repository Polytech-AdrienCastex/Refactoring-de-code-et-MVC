package modele;

public class ColorableTortue extends Tortue
{
    public ColorableTortue(int color)
    {
        super();
        
        bodyColor = color;
    }
    
    protected final int bodyColor;
    
    public int getBodyColor()
    {
        return bodyColor;
    }
    
}
