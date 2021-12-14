import javax.swing.*;
import java.rmi.RemoteException;

public class ControlesIndividualesImpl implements ControlesIndividuales
{
    private ControlGrupalAbanicos panelGrupal;
    private MonitorArcsPanel[] monitorArcsPanels;

    /**
     * Constructor de la implementación de la interfaz ControlesIndividuales que recibe como argumento un arreglo con los abanicos instanciados en un panel junto a sus controles individuales.
     * @see ControlesIndividuales
     * @param monitorArcsPanels
     */
    public ControlesIndividualesImpl(MonitorArcsPanel[] monitorArcsPanels, ControlGrupalAbanicos panelGrupal)
    {
        this.monitorArcsPanels = monitorArcsPanels;
        this.panelGrupal = panelGrupal;
    }

    @Override
    public void MoverAbanico(int indice, int velocidad) throws RemoteException
    {
        monitorArcsPanels[indice].startFan(velocidad);
    }

    @Override
    public void DetenerAbanico(int indice) throws RemoteException
    {
        monitorArcsPanels[indice].stopFan();
    }

    @Override
    public void ReverseAbanico(int indice) throws RemoteException
    {
        monitorArcsPanels[indice].ReverseFan();
    }

    @Override
    public void MoverTodos() throws RemoteException
    {
        panelGrupal.iniciarTodos();
    }

    @Override
    public void suspenderTodos() throws RemoteException
    {
        panelGrupal.suspenderTodos();
    }

    @Override
    public void ReanudarTodos() throws RemoteException
    {
        panelGrupal.reanudarTodos();
    }

    @Override
    public void DetenerTodos() throws RemoteException
    {
        panelGrupal.detenerTodos();
    }
}
