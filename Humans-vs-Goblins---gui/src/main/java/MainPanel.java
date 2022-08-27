/*

 */

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import com.intellij.uiDesigner.core.*;
import entities.*;

public class MainPanel extends JPanel
{
    private GamePanel gamePanel;
    private JTextPane consoleTextArea;
    private JTextArea statusTextArea;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton inventoryButton;

    private Human human;

    private int maxWidth = 960;
    private int maxHeight = 850;
    private Dimension dimension = new Dimension(maxWidth, maxHeight);
    StyledDocument console;
    Style style;

    public MainPanel()
    {
        this.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);

        //Game Panel
        gamePanel = new GamePanel();
        gamePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        this.add(gamePanel, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(960, 720), new Dimension(960, 720),
                new Dimension(960, 720), 0, false));

        //  Text areas
        JScrollPane statusPane = new JScrollPane();

        statusPane.setFocusable(false);
        statusPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        statusPane.setOpaque(false);
        statusPane.setRequestFocusEnabled(false);
        statusPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        statusPane.setWheelScrollingEnabled(false);
        this.add(statusPane, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(100, 116), new Dimension(100, 116), new Dimension(100, 116), 0, false));

        statusTextArea = new JTextArea();
        statusTextArea.setEditable(false);
        statusTextArea.setFocusable(false);
        statusTextArea.setOpaque(false);
        statusTextArea.setRequestFocusEnabled(false);
        statusPane.setViewportView(statusTextArea);


        JScrollPane consolePane = new JScrollPane();

        consolePane.setFocusable(true);
        consolePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        consolePane.setOpaque(false);
        consolePane.setRequestFocusEnabled(false);
        consolePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(consolePane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(500, 116), new Dimension(500, 116), new Dimension(500, 116), 2, false));

        consoleTextArea = new JTextPane();
        consoleTextArea.setEditable(false);
        consoleTextArea.setFocusable(false);
        consoleTextArea.setOpaque(false);
        consoleTextArea.setRequestFocusEnabled(false);
        DefaultCaret caret = (DefaultCaret)consoleTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        consolePane.setViewportView(consoleTextArea);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        this.add(buttonPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                null, null, null, 0, false));

        upButton = new JButton();
        upButton.setText("North");
        buttonPanel.add(upButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(68, 26), new Dimension(68, 26), new Dimension(68, 26), 0, false));

        downButton = new JButton();
        downButton.setText("South");
        buttonPanel.add(downButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(68, 26), new Dimension(68, 26), new Dimension(68, 26), 0, false));

        leftButton = new JButton();
        leftButton.setText("West");
        buttonPanel.add(leftButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(68, 26), new Dimension(68, 26), new Dimension(68, 26), 0, false));

        rightButton = new JButton();
        rightButton.setText("East");
        buttonPanel.add(rightButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(68, 26), new Dimension(68, 26), new Dimension(68, 26), 0, false));


        inventoryButton = new JButton();
        inventoryButton.setActionCommand("Inventory");
        inventoryButton.setText("Inventory");
        buttonPanel.add(inventoryButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                new Dimension(-1, 26), new Dimension(-1, 26), new Dimension(-1, 26), 0, false));

        console = consoleTextArea.getStyledDocument();
        appendConsole("Welcome to Humans -vs- Goblins.", null);
        appendConsole("Collect the treasure and kill the goblins. Once you kill a goblin a new one will appear.", null);
        appendConsole("To equip items click the corresponding icon in the bottom right of the window.", null);

        gamePanel.setUi(this);
        human = gamePanel.getHuman();
        try
        {
            String name = JOptionPane.showInputDialog(this, "Please enter a name. (6 Characters or less)", null);
            human.setName(name.substring(0, Math.min(name.length(), 6)));
            if (human.getName().length() < 1)
            {
                human.setName("Human");
            }
        }
        catch (NullPointerException e)
        {
            human.setName("Human");
        }
        updateStatus();

        // Button Listeners
        upButton.addActionListener(e -> gamePanel.updateHuman(e.getActionCommand()));
        downButton.addActionListener(e -> gamePanel.updateHuman(e.getActionCommand()));
        leftButton.addActionListener(e -> gamePanel.updateHuman(e.getActionCommand()));
        rightButton.addActionListener(e -> gamePanel.updateHuman(e.getActionCommand()));
        inventoryButton.addActionListener(e ->
                {
                    appendConsole("You open your inventory.", null);
                    appendConsole(("Your inventory includes [Gold: " + human.getInventory().getGold() + " Items: " +
                            human.getInventory().toString()) + "]", Color.BLUE);
                });
    }

    public void appendConsole(String string, Color color)
    {
        if (color == null)
        {
            color = Color.BLACK;
        }
        try
        {
            style = console.addStyle("Color", null);
            StyleConstants.setForeground(style, color);

            console.insertString(console.getLength(), string + "\n", style);
        }
        catch (Exception e)
        {}
    }

    public void updateStatus()
    {
        StringBuilder status = new StringBuilder();

        status.append("Status");
        status.append("\n");
        status.append("------------------------ \n");

        status.append("Name: ");
        status.append(human.getName());
        status.append("\n");

        status.append("Health:    ");
        status.append(human.getHealth());
        status.append("\n");

        status.append("Armor:     ");
        status.append(human.getArmor());
        status.append("\n");

        status.append("Strength: ");
        status.append(human.getStrength());
        status.append("\n");

        status.append("Dexterity: ");
        status.append(human.getDexterity());

        statusTextArea.setText(status.toString());
    }

    public void start()
    {
        gamePanel.start();
    }
}
