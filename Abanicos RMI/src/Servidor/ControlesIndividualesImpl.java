import java.rmi.RemoteException;

public class ControlesIndividualesImpl implements ControlesIndividuales
{
    private MonitorArcsPanel[] monitorArcsPanels;

    /**
     * Constructor de la implementación de la interfaz ControlesIndividuales que recibe como argumento un arreglo con los abanicos instanciados en un panel junto a sus controles individuales.
     * @see ControlesIndividuales
     * @param monitorArcsPanels
     */
    public ControlesIndividualesImpl(MonitorArcsPanel[] monitorArcsPanels)
    {
        this.monitorArcsPanels = monitorArcsPanels;
    }

    @Override
    public void MoverAbanico(int indice, int velocidad) throws RemoteException
    {
        monitorArcsPanels[indice].startFan(velocidad);
    }

    @Override
    public void SuspenderAbanico(int indice) throws RemoteException
    {
        monitorArcsPanels[indice].suspendFan();
    }

    @Override
    public void ReanudarAbanico(int indice) throws RemoteException
    {
        monitorArcsPanels[indice].resumeFan();
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
}
