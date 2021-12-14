import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControlesIndividuales extends Remote
{
    public void MoverAbanico(int indice, int velocidad) throws RemoteException;
    public void SuspenderAbanico(int indice) throws RemoteException;
    public void ReanudarAbanico(int indice) throws RemoteException;
    public void DetenerAbanico(int indice) throws RemoteException;
    public void ReverseAbanico(int indice) throws RemoteException;
}