package engine.graphicEngine;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

public class CoreGraphics  {
    private final JFrame mainFrame;
    private Panel mainPanel;
    private final int width;
    private final int height;

    public CoreGraphics(int width, int height, String name) {
        this.width = width;
        this.height = height;
        mainFrame = new JFrame(name);
        mainFrame.setPreferredSize(new Dimension(width, height));
        mainFrame.pack();

        mainPanel = new Panel(width, height, Color.BLACK);
        initFrame();
    }

    private void initFrame() {
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
    }

    public void launchFrame() {
        mainFrame.setVisible(true);
    }

    public void repaint() {
        mainFrame.repaint();
    }

    public void addEntity(GraphicEntity entity) {
        mainPanel.addEntity(entity);
    }

    public void removeEntity(GraphicEntity entity) {
        mainPanel.removeEntity(entity);

    }

    public void removeAllEntities() {
        mainPanel.removeAllEntity();
    }

    public void setTitle(String s) {
        mainFrame.setTitle(s);
    }

    public void setBackgroundColor(Color c) {
        mainFrame.setBackground(c);
    }

    public JFrame getFrame() {
        return mainFrame;
    }
}
