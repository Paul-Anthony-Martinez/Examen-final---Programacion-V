import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

class AbanicoServer extends JFrame
{
    //arreglo de MonitorArcsPanel
    public MonitorArcsPanel[] panelAbanico;
    public ControlGrupalAbanicos panelGrupal;

    public AbanicoServer()
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
            panelAbanico[i] = new MonitorArcsPanel(i);

            panelContenedorAbanicos.add(panelAbanico[i]);
        }

        this.add(panelContenedorAbanicos, BorderLayout.CENTER);

        panelGrupal = new ControlGrupalAbanicos(this.panelAbanico);

        this.add(panelGrupal.getPanelGrupales(), BorderLayout.SOUTH);
        //FIN INTERFAZ GRAFICA
    }

    public MonitorArcsPanel[] getPanelAbanicos()
    {
        return this.panelAbanico;
    }

    public static void main(String[] args) throws RemoteException
    {
        JFrame frame = new AbanicoServer();
        frame.setTitle("Stefany: Abanico");

        //ControlAbanicos cr = new ControlAbanicos();

        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.out.println("Server has started!");

        ControlesIndividualesImpl controlesIndividualesImpl1 = new ControlesIndividualesImpl(((AbanicoServer) frame).getPanelAbanicos(), ((AbanicoServer) frame).panelGrupal);
        ControlesIndividualesImpl controlesIndividualesImpl2 = new ControlesIndividualesImpl(((AbanicoServer) frame).getPanelAbanicos(), ((AbanicoServer) frame).panelGrupal);
        ControlesIndividualesImpl controlesIndividualesImpl3 = new ControlesIndividualesImpl(((AbanicoServer) frame).getPanelAbanicos(), ((AbanicoServer) frame).panelGrupal);

        ControlesIndividualesImpl controlesGrupalesImp1 = new ControlesIndividualesImpl(((AbanicoServer) frame).getPanelAbanicos(), ((AbanicoServer) frame).panelGrupal);
        ControlesIndividualesImpl controlesGrupalesImp2 = new ControlesIndividualesImpl(((AbanicoServer) frame).getPanelAbanicos(), ((AbanicoServer) frame).panelGrupal);
        ControlesIndividualesImpl controlesGrupalesImp3 = new ControlesIndividualesImpl(((AbanicoServer) frame).getPanelAbanicos(), ((AbanicoServer) frame).panelGrupal);
        ControlesIndividualesImpl controlesGrupalesImp4 = new ControlesIndividualesImpl(((AbanicoServer) frame).getPanelAbanicos(), ((AbanicoServer) frame).panelGrupal);
        System.out.println("Implementación creada!");

        //export
        ControlesIndividuales stub1 = (ControlesIndividuales) UnicastRemoteObject.exportObject(controlesIndividualesImpl1, 0);
        ControlesIndividuales stub2 = (ControlesIndividuales) UnicastRemoteObject.exportObject(controlesIndividualesImpl2, 0);
        ControlesIndividuales stub3 = (ControlesIndividuales) UnicastRemoteObject.exportObject(controlesIndividualesImpl3, 0);

        ControlesIndividuales stub4 = (ControlesIndividuales) UnicastRemoteObject.exportObject(controlesGrupalesImp1, 0);
        ControlesIndividuales stub5 = (ControlesIndividuales) UnicastRemoteObject.exportObject(controlesGrupalesImp2, 0);
        ControlesIndividuales stub6 = (ControlesIndividuales) UnicastRemoteObject.exportObject(controlesGrupalesImp3, 0);
        ControlesIndividuales stub7 = (ControlesIndividuales) UnicastRemoteObject.exportObject(controlesGrupalesImp4, 0);

        //Register
        Registry reg = LocateRegistry.getRegistry("127.0.0.1", 9100);
        reg.rebind("start", stub1);
        reg.rebind("stop", stub2);
        reg.rebind("reverse", stub3);

        reg.rebind("startAll", stub4);
        reg.rebind("suspendAll", stub5);
        reg.rebind("resumeAll", stub6);
        reg.rebind("stopAll", stub7);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 675);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
