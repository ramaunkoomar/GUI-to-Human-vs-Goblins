/*

 */

package images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Sprite
{
    BufferedImage sprite;
    private int size = 32;
    private int tiles = 0;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public Sprite(String resource)
    {
        try
        {
            URL file = getClass().getClassLoader().getResource(resource);
            sprite = ImageIO.read(Paths.get(file.toURI()).toFile());
            tiles = sprite.getWidth() * sprite.getHeight();

            for (int y = 0; y < sprite.getHeight(); y += size)
            {
                for (int x = 0; x < sprite.getWidth(); x += size)
                {
                    sprites.add(sprite.getSubimage(x, y, size, size));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error loading sprite file " + resource);
            System.out.println(e.getMessage());
        }
    }

    public int getSize()
    {
        return size;
    }

    public BufferedImage getSprite(int index)
    {
        return sprites.get(index);
    }

    @Override
    public String toString()
    {
        return "Sprite{" +
                "sprite=" + sprite +
                ", size=" + size +
                ", tiles=" + tiles +
                '}';
    }
}


