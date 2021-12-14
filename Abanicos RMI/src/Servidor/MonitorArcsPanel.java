import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MonitorArcsPanel extends JPanel implements ActionListener, Runnable
{
    JPanel panelMonitor;

    public int indice;
    private int tiempo;
    private boolean stop;
    private boolean pausar;
    private Thread hiloMonitor;
    private ArcsPanel abanicoPanel;
    private JButton botonStop, botonReverse;
    private JRadioButton botonLow, botonMid, botonHigh;

    public MonitorArcsPanel(int indice)
    {
        this.indice = indice;

        //el abanico esta apagado y no esta pausado
        stop = true;
        pausar = false;
        setLayout(new BorderLayout());
        interfaz();
    }

    public void interfaz()
    {
        panelMonitor = new JPanel(new FlowLayout(FlowLayout.CENTER));

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

        abanicoPanel = new ArcsPanel();

        this.add(abanicoPanel, BorderLayout.CENTER);
        this.add(panelMonitor, BorderLayout.SOUTH);
    }

    public synchronized void startFan(int v)
    {
        if (v == -1)
        {
            botonLow.setSelected(true);
            v = 150;
        }

        tiempo = v;

        if (hiloMonitor == null)
        {
            stop = false;
            hiloMonitor = new Thread(this);
            hiloMonitor.start();
        }
        else if (pausar)
        {
            resumeFan();
        }
    }

    public synchronized void suspendFan()
    {
        pausar = true;
    }

    public synchronized void resumeFan()
    {
        stop = false;
        pausar = false;
        notifyAll();
    }

    public synchronized void stopFan()
    {
        pausar = false;
        stop = true;
        hiloMonitor = null;
        System.gc();//Llama al recolector de basura
    }

    public synchronized void ReverseFan()
    {
        abanicoPanel.cambiarGiro();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Low" -> startFan(150);
            case "Mid" -> startFan(65);
            case "High" -> startFan(20);
            case "Stop" -> stopFan();
            case "Reverse" -> ReverseFan();
            default -> System.out.println("Entrada inválida");
        }
    }

    @Override
    public void run()
    {
        while (!stop)
        {
            abanicoPanel.repaint();
            try
            {
                Thread.sleep(tiempo);
                synchronized (this)
                {
                    while (pausar)
                    {
                        wait();
                    }
                }
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
