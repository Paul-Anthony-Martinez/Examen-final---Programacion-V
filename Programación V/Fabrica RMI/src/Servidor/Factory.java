import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Factory extends Remote
{
    void startFactory(int boxAmount, int paperAmount) throws RemoteException;
    void cleanFields() throws RemoteException;
}
