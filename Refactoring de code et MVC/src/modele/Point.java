package modele;

public class Point
{
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int x;
    public int y;
    
    @Override
    public Point clone()
    {
        return new Point(x, y);
    }
    
    private static double square(int d)
    {
        return (double)d * d;
    }
    public double getDistance(Point p2)
    {
        return getDistance(this, p2);
    }
    public static double getDistance(Point p1, Point p2)
    {
        return Math.sqrt(square(p1.x - p2.x) + square(p1.y - p2.y));
    }
    
    public boolean intersect(Point p, double distance)
    {
        return intersect(this, p, distance);
    }
    public static boolean intersect(Point p1, Point p2, double distance)
    {
        return getDistance(p1, p2) <= distance;
    }
}
