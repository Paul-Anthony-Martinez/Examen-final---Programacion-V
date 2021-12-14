import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlGrupalAbanicos extends JPanel implements ActionListener
{
    MonitorArcsPanel[] panelAbanico;

    private JPanel panelAll;
    private JButton botonStartAll;
    private JButton botonResumeAll;
    private JButton botonSuspendAll;
    private JButton botonStopAll;

    public ControlGrupalAbanicos(MonitorArcsPanel[] panelAbanico)
    {
        this.panelAbanico = panelAbanico;

        panelAll = new JPanel(new FlowLayout(1));

        botonStartAll = new JButton("Start All");
        botonStartAll.setEnabled(true);
        botonStartAll.addActionListener(this);
        botonResumeAll = new JButton("Resume All");
        botonResumeAll.setEnabled(false);
        botonResumeAll.addActionListener(this);
        botonSuspendAll = new JButton("Suspend All");
        botonSuspendAll.setEnabled(false);
        botonSuspendAll.addActionListener(this);
        botonStopAll = new JButton("Stop All");
        botonStopAll.setEnabled(false);
        botonStopAll.addActionListener(this);

        panelAll.add(botonStartAll);
        panelAll.add(botonResumeAll);
        panelAll.add(botonSuspendAll);
        panelAll.add(botonStopAll);
    }

    public JPanel getPanelGrupales()
    {
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
            case "Start All" ->
                    {
                        this.iniciarTodos();
                    }
            case "Resume All" ->
                    {
                        this.reanudarTodos();
                    }
            case "Suspend All" ->
                    {
                        this.suspenderTodos();
                    }
            case "Stop All" ->
                    {
                        this.detenerTodos();
                    }
            default -> System.out.println("This shouldn´t happen");
        }
    }

    public void iniciarTodos()
    {
        panelAbanico[0].startFan(-1);
        panelAbanico[1].startFan(-1);
        panelAbanico[2].startFan(-1);
        panelAbanico[3].startFan(-1);
        this.asignarEnabled(false, false, true, true);
    }

    public void suspenderTodos()
    {
        panelAbanico[0].suspendFan();
        panelAbanico[1].suspendFan();
        panelAbanico[2].suspendFan();
        panelAbanico[3].suspendFan();
        this.asignarEnabled(false, true, false, true);
    }

    public void reanudarTodos()
    {
        panelAbanico[0].resumeFan();
        panelAbanico[1].resumeFan();
        panelAbanico[2].resumeFan();
        panelAbanico[3].resumeFan();
        this.asignarEnabled(false, false, true, true);
    }

    public void detenerTodos()
    {
        panelAbanico[0].stopFan();
        panelAbanico[1].stopFan();
        panelAbanico[2].stopFan();
        panelAbanico[3].stopFan();
        this.asignarEnabled(true, false, false, false);
    }
}
