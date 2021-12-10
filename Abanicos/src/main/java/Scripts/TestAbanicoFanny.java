import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TestAbanicoFanny extends JFrame implements ActionListener
{
    //arreglo de MonitorArcsPanel
    public MonitorArcsPanel[] panelAbanico;
    public JButton botonStartAll;
    public JButton botonResumeAll;
    public JButton botonSuspendAll;
    public JButton botonStopAll;
    
    public TestAbanicoFanny()
    {
        //INTERFAZ GRAFICA
        //panelContenedorAbanicos almacenara los 4 abanicos
        JPanel panelContenedorAbanicos = new JPanel(new GridLayout(2, 2));

        /*
         * Se crean 4 instancias de la clase MonitorArcsPanel /*que contiene el
         * dibujo del abanico*
         */
        
        panelAbanico = new MonitorArcsPanel[4];
        for (int i = 0; i < 4; i++)
        {
            panelAbanico[i] = new MonitorArcsPanel();

            panelContenedorAbanicos.add(panelAbanico[i]);
        }
        
        this.add(panelContenedorAbanicos, BorderLayout.CENTER);

        JPanel panelAll = new JPanel(new FlowLayout(1));

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
        this.add(panelAll, BorderLayout.SOUTH);
        //FIN INTERFAZ GRAFICA
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
                panelAbanico[0].startFan(-1);
                panelAbanico[1].startFan(-1);
                panelAbanico[2].startFan(-1);
                panelAbanico[3].startFan(-1);
                asignarEnabled(false, false, true, true);
            }
            case "Resume All" ->
            {
                panelAbanico[0].resumeFan();
                panelAbanico[1].resumeFan();
                panelAbanico[2].resumeFan();
                panelAbanico[3].resumeFan();
                asignarEnabled(false, false, true, true);
            }
            case "Suspend All" ->
            {
                panelAbanico[0].suspendFan();
                panelAbanico[1].suspendFan();
                panelAbanico[2].suspendFan();
                panelAbanico[3].suspendFan();
                asignarEnabled(false, true, false, true);
            }
            case "Stop All" ->
            {
                panelAbanico[0].stopFan();
                panelAbanico[1].stopFan();
                panelAbanico[2].stopFan();
                panelAbanico[3].stopFan();
                asignarEnabled(true, false, false, false);
            }
            default -> System.out.println("This shouldn´t happen");
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new TestAbanicoFanny();
        frame.setTitle("Stefany: Abanico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 675);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
