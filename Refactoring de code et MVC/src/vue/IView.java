package vue;

import modele.Point;
import modele.Tortue;

public interface IView
{
    /**
     * Get the <b>distance</b> traveled.
     * @return Integer
     */
    public Integer getDistance();
    
    /**
     * Get the <b>size</b> of the "surface" used to draw.
     * @return Point
     */
    public Point getFeuilleDessinSize();
    
    /**
     * Get the selected <b>color index</b>.
     * @return Integer
     */
    public Integer getColor();
    
    public String getTortueName();
    
    public Tortue getSelectedFriend();
}
