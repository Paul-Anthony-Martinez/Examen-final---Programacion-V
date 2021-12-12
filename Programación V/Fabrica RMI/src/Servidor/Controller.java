import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Controller
{
    public static Machine window;

    public static void main(String[] args)
    {
        window = new Machine();
        window.drawFrame(700,450, "Fábrica de Papel Gráfica");

        try
        {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("Server has started!");

            FactoryImpl f1 = new FactoryImpl(window);
            System.out.println("Implementación creada!");

            //export
            Factory stub1 = (Factory) UnicastRemoteObject.exportObject(f1, 0);

            //Register
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 9100);
            reg.rebind("start", stub1);
            //Naming.rebind("rmi://localhost:9100" + "/start", stub1);
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

}
