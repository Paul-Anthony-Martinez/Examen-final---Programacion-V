import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class PanelControles extends JPanel implements ActionListener
{
    JFrame frame;
    ControlAbanicos control;

    public JButton botonStartAll;
    public JButton botonResumeAll;
    public JButton botonSuspendAll;
    public JButton botonStopAll;

    private ControlesIndividuales iniciarTodos;
    private ControlesIndividuales reanudarTodos;
    private ControlesIndividuales suspenderTodos;
    private ControlesIndividuales detenerTodos;

    public PanelControles(ControlesIndividuales iniciarIndividual, ControlesIndividuales reverseIndividual, ControlesIndividuales detenerIndividual, ControlesIndividuales iniciarTodos, ControlesIndividuales suspenderTodos, ControlesIndividuales reanudarTodos, ControlesIndividuales detenerTodos)
    {
        this.iniciarTodos = iniciarTodos;
        this.reanudarTodos = reanudarTodos;
        this.suspenderTodos = suspenderTodos;
        this.detenerTodos = detenerTodos;

        frame = new JFrame("Control abanicos - Cliente");
        frame.setLayout(new GridLayout(6,0));

        for (int i = 0; i < 4; i++)
        {
            control = new ControlAbanicos(i, iniciarIndividual, reverseIndividual, detenerIndividual);
            frame.add(control);
        }
        frame.add(this.crearControlesGrupales());

        frame.setSize(500,500);
        frame.setVisible(true);
    }

    public JPanel crearControlesGrupales()
    {
        JPanel panelAll = new JPanel(new FlowLayout(1));

        botonStartAll = new JButton("startAll");
        botonStartAll.setEnabled(true);
        botonStartAll.addActionListener(this);
        botonResumeAll = new JButton("resumeAll");
        botonResumeAll.setEnabled(false);
        botonResumeAll.addActionListener(this);
        botonSuspendAll = new JButton("suspendAll");
        botonSuspendAll.setEnabled(false);
        botonSuspendAll.addActionListener(this);
        botonStopAll = new JButton("stopAll");
        botonStopAll.setEnabled(false);
        botonStopAll.addActionListener(this);

        panelAll.add(botonStartAll);
        panelAll.add(botonResumeAll);
        panelAll.add(botonSuspendAll);
        panelAll.add(botonStopAll);

        return panelAll;
    }

    public void asignarEnabled(boolean b1, boolean b2, boolean b3, boolean b4)
    {
        botonStartAll.setEnabled(b1);
        botonResumeAll.setEnabled(b2);
        botonSuspendAll.setEnabled(b3);
        botonStopAll.setEnabled(b4);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "startAll" -> {
                try
                {
                    iniciarTodos.MoverTodos();
                    this.asignarEnabled(false, false, true, true);
                }
                catch (RemoteException ex)
                {
                    ex.printStackTrace();
                }
            }
            case "suspendAll" -> {
                try
                {
                    suspenderTodos.suspenderTodos();
                    this.asignarEnabled(false, true, false, true);
                }
                catch (RemoteException ex)
                {
                    ex.printStackTrace();
                }
            }
                case "resumeAll" -> {
                    try
                    {
                        reanudarTodos.ReanudarTodos();
                        this.asignarEnabled(false, false, true, true);
                    }
                    catch (RemoteException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                case "stopAll" -> {
                    try
                    {
                        detenerTodos.DetenerTodos();
                        this.asignarEnabled(true, false, false, false);
                    }
                    catch (RemoteException ex)
                    {
                        ex.printStackTrace();
                    }
            }
        }
    }
}
