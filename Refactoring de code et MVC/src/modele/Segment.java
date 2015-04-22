package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Segment
{
    public Point ptStart, ptEnd;
    public Color color;

    public Segment()
    {
        ptStart = new Point(0, 0);
        ptEnd = new Point(0, 0);
    }

    public void drawSegment(Graphics graph)
    {
        if (graph==null)
            return;

        graph.setColor(color);
        graph.drawLine(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y);
    }
}
