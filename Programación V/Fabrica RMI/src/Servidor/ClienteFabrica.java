import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteFabrica
{
    private static Factory fab;
    private static ControlesFabrica ctrl;

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Server has started!");

            //Localizar registro
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 9100);

            //Get reference
            fab = (Factory) reg.lookup("start");

            //fab = (Factory) Naming.lookup("rmi://localhost:9100" + "/start");

            ctrl = new ControlesFabrica(fab);
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
