import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente
{
    private static ControlesIndividuales controlesIndividuales;
    private static PanelControles panelControles;

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Client has started!");

            //Localizar registro
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 9100);

            //Get reference
            ControlesIndividuales iniciarIndividual = (ControlesIndividuales) reg.lookup("start");
            ControlesIndividuales detenerIndividual = (ControlesIndividuales) reg.lookup("stop");
            ControlesIndividuales reverseIndividual = (ControlesIndividuales) reg.lookup("reverse");

            //ctrl = new ControlesFabrica(fab);
            panelControles = new PanelControles(iniciarIndividual, detenerIndividual, reverseIndividual);
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
