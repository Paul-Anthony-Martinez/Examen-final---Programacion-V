import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ControlAbanicos extends JPanel implements ActionListener
{
    public int indice;
    private JButton botonStop, botonReverse;
    private JRadioButton botonLow, botonMid, botonHigh;
    private ControlesIndividuales iniciarIndividual;
    private ControlesIndividuales reverseIndividual;
    private ControlesIndividuales detenerIndividual;

    public ControlAbanicos(int indice, ControlesIndividuales iniciarIndividual, ControlesIndividuales reverseIndividual, ControlesIndividuales detenerIndividual)
    {
        this.iniciarIndividual = iniciarIndividual;
        this.reverseIndividual = reverseIndividual;
        this.detenerIndividual = detenerIndividual;


        this.indice = indice;
        this.crearControlesIndividuales();
    }

    public void crearControlesIndividuales()
    {
        JPanel panelMonitor = new JPanel();

        botonLow = new JRadioButton("Low");
        botonLow.addActionListener(this);
        botonMid = new JRadioButton("Mid");
        botonMid.addActionListener(this);
        botonHigh = new JRadioButton("High");
        botonHigh.addActionListener(this);
        botonStop = new JButton("Stop");
        botonStop.addActionListener(this);
        botonReverse = new JButton("Reverse");
        botonReverse.addActionListener(this);

        ButtonGroup grupoRadio = new ButtonGroup();

        grupoRadio.add(botonLow);
        grupoRadio.add(botonMid);
        grupoRadio.add(botonHigh);

        panelMonitor.add(botonLow);
        panelMonitor.add(botonMid);
        panelMonitor.add(botonHigh);
        panelMonitor.add(botonStop);
        panelMonitor.add(botonReverse);

        this.add(panelMonitor, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Low" -> {
                try
                {
                    iniciarIndividual.MoverAbanico(indice, 150);
                }
                catch (RemoteException ex)
                {
                    ex.printStackTrace();
                }
            }
            case "Mid" -> {
                try
                {
                    iniciarIndividual.MoverAbanico(indice, 65);
                }
                catch (RemoteException ex)
                {
                    ex.printStackTrace();
                }
            }
            case "High" -> {
                try
                {
                    iniciarIndividual.MoverAbanico(indice, 20);
                }
                catch (RemoteException ex)
                {
                    ex.printStackTrace();
                }
            }
            case "Stop" -> {
                try
                {
                    detenerIndividual.DetenerAbanico(indice);
                }
                catch (RemoteException ex)
                {
                    ex.printStackTrace();
                }
            }
            case "Reverse" -> {
                try
                {
                    reverseIndividual.ReverseAbanico(indice);
                }
                catch (RemoteException ex)
                {
                    ex.printStackTrace();
                }
            }
            default -> System.out.println("Entrada inválida");
        }
    }
}
