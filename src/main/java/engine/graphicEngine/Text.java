package engine.graphicEngine;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Text {
    private String text;
    private double width;
    private double height;
    private Font font;

    public Text (String text){
        this.text = text;
        font = new Font("font",Font.PLAIN,18);
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        width = font.getStringBounds(getText(),frc).getWidth();
        height = font.getStringBounds(getText(),frc).getHeight();
    }

    public String getText() {
        return text;
    }

    public void setText(String text){ this.text = text;}

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
