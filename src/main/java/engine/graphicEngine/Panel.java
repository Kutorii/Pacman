package engine.graphicEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private final List<GraphicEntity> graphicEntityList = new LinkedList<>();

    public Panel(int width, int height, Color color) {
        setPreferredSize(new Dimension(width, height));
        setBackground(color);
        setVisible(true);
        setEnabled(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (GraphicEntity e : graphicEntityList) {
            if (e.getImage() != null) {
                g.drawImage(e.getImage(), e.getX(), e.getY(), null);
            } else if (e.getCircle() != null) {
                g.setColor(Color.WHITE);
                g.fillOval(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            } else if (e.getText() != null) {
                g.drawString(e.getText().getText(),e.getX(),e.getY());
            } else {
                g.drawRect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            }
        }
    }

    protected void addEntity(GraphicEntity entity) {
        graphicEntityList.add(entity);
    }

    protected void removeEntity(GraphicEntity entity) {
        graphicEntityList.remove(entity);
    }

    protected void removeAllEntity() {
        graphicEntityList.clear();
    }
}
