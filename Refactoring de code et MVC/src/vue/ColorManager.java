package vue;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class ColorManager
{
    private final static String[] colors = new String[]
    {
        "noir",
        "bleu",
        "cyan",
        "gris fonce",
        "rouge",
        "vert",
        "gris clair",
        "magenta",
        "orange",
        "gris",
        "rose",
        "jaune"
    };
    private static final List<String> colorList = Arrays.asList(colors);
    
    public static String[] getColors()
    {
        return colors;
    }
    
    public static Color getColor(String value)
    {
        return getColor(colorList.indexOf(value));
    }
    
    public static Color getColor(int index)
    {
        switch(index)
        {
            case 0: return(Color.black);
            case 1: return(Color.blue);
            case 2: return(Color.cyan);
            case 3: return(Color.darkGray);
            case 4: return(Color.red);
            case 5: return(Color.green);
            case 6: return(Color.lightGray);
            case 7: return(Color.magenta);
            case 8: return(Color.orange);
            case 9: return(Color.gray);
            case 10: return(Color.pink);
            case 11: return(Color.yellow);
            default : return(Color.black);
        }
    }
}
