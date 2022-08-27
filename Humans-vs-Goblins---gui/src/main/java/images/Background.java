/*

 */

package images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.file.Paths;

public class Background
{
    BufferedImage background;

    public Background(String resource)
    {
        try
        {
            URL file = getClass().getClassLoader().getResource(resource);
            background = ImageIO.read(Paths.get(file.toURI()).toFile());
        }
        catch (Exception e)
        {
            System.out.println("Error loading background file " + resource);
            System.out.println(e.getMessage());
        }
    }

    public BufferedImage getBackground()
    {
        return background;
    }

    @Override
    public String toString()
    {
        return "Background{ " +
                "height: " +
                background.getHeight() +
                " width: " +
                background.getWidth() +
                "}";
    }
}
