import javax.swing.*;
import java.awt.*;

public class PanelControles extends JPanel
{
    JFrame frame;
    ControlAbanicos control;

    public PanelControles(ControlesIndividuales iniciarIndividual, ControlesIndividuales detenerIndividual, ControlesIndividuales reverseIndividual)
    {
        frame = new JFrame("Control abanicos - Cliente");
        frame.setLayout(new GridLayout(5,0));

        for (int i = 0; i < 4; i++)
        {
            control = new ControlAbanicos(i, iniciarIndividual, detenerIndividual, detenerIndividual);
            frame.add(control);
        }

        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
