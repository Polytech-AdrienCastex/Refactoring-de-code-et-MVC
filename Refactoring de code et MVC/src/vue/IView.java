package vue;

import modele.Point;

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
}
