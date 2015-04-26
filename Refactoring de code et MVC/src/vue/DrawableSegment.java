package vue;

import java.awt.Graphics;
import modele.Segment;

public class DrawableSegment
{
    public static void drawSegment(Graphics graph, Segment segment)
    {
        if (graph == null)
            return;

        graph.setColor(ColorManager.getColor(segment.color));
        graph.drawLine(segment.ptStart.x, segment.ptStart.y, segment.ptEnd.x, segment.ptEnd.y);
    }
}
