import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControlesIndividuales extends Remote
{
    public void MoverAbanico(int indice, int velocidad) throws RemoteException;
    public void DetenerAbanico(int indice) throws RemoteException;
    public void ReverseAbanico(int indice) throws RemoteException;

    public void MoverTodos() throws RemoteException;
    public void suspenderTodos() throws RemoteException;
    public void ReanudarTodos() throws RemoteException;
    public void DetenerTodos() throws RemoteException;
}