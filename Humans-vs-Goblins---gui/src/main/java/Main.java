/*

 */

import images.Sprite;

import javax.swing.*;

public class Main
{
    private static String title = "Humans -vs- Goblins";

    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(title);

        MainPanel game = new MainPanel();
        window.add(game);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game.start();
    }
}
