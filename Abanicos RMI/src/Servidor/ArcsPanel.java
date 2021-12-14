import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Stefany J. Ríos Q.
 * @author Pablo A. Martínez G.
 *
 */

//clase anidad que pinta el abanicos en un JPanel
public class ArcsPanel extends JPanel
{
    public double currentTheta;
    public int reverse = 1;

    public void cambiarGiro()
    {
        reverse *= -1;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        currentTheta = currentTheta - 1;

        int xCenter = 0;
        int yCenter = 0;

        int radius = (int) (Math.min(getWidth(), getHeight()) * 0.4) - 30;
        int x = xCenter - radius;
        int y = yCenter - radius;
        int sLength = (int) (radius * 0.8);

        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);

        //Frame
        g.setColor(Color.red);
        g.drawRect(-100, -100, 200, 200);

        //Horizontal Line
        g.setColor(Color.black);
        g.drawLine(-100, -25, 100, -25);
        g.drawLine(-100, -50, 100, -50);
        g.drawLine(-100, -75, 100, -75);
        g.drawLine(-100, 0, 100, 0);
        g.drawLine(-100, 25, 100, 25);
        g.drawLine(-100, 50, 100, 50);
        g.drawLine(-100, 75, 100, 75);

        //Handle
        g.setColor(Color.blue);
        g.drawLine(-30, -100, -25, -120);
        g.drawLine(30, -100, 25, -120);
        g.drawLine(-25, -120, 25, -120);

        //Vertical Lines
        g.setColor(Color.black);
        g.drawLine(-75, 100, -75, -100);
        g.drawLine(-50, 100, -50, -100);
        g.drawLine(-25, 100, -25, -100);
        g.drawLine(0, 100, 0, -100);
        g.drawLine(25, 100, 25, -100);
        g.drawLine(50, 100, 50, -100);
        g.drawLine(75, 100, 75, -100);

        //Fan blades orientation
        if (reverse < 0)
        {
            g2d.rotate(-currentTheta);
        }
        else if (reverse > 0)
        {
            g2d.rotate(currentTheta);
        }

        g.setColor(Color.blue);
        g.fillArc(x, y, 2 * radius, 2 * radius, 0, 45);

        g.fillArc(x, y, 2 * radius, 2 * radius, 90, 45);

        g.fillArc(x, y, 2 * radius, 2 * radius, 180, 45);

        g.fillArc(x, y, 2 * radius, 2 * radius, 270, 45);
    }
}