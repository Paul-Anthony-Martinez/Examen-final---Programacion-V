public class Supervisor extends Thread
{
    private TheBox box;
    private int id;
    private int i = 0;
    private Machine maquina;

    public Supervisor(TheBox box, int id, Machine maquina)
    {
        this.box = box;
        this.id = id;
        this.maquina = maquina;
    }

    public void run()
    {
        int tiempo = 0;
        while (true)
        {
            synchronized (box)
            {
                while (box.isNoTengo())
                {
                    if (box.getCantCajaActual() != box.getMaxCantCajas())
                    {
                        try
                        {
                            System.out.println("Servidor.Scripts.Supervisor: " + id + " is waiting...");

                            String actualString = maquina.outputField.getText();
                            maquina.outputField.setText(actualString + "\n" + "Servidor.Scripts.Supervisor: " + id + " is waiting...");

                            box.wait(100);
                        }
                        catch (InterruptedException e)
                        {}
                    }
                    break;
                }
            } // end synchronized
            //Consumer try to take  the box
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {}

            if (box.getCantPapelActual() == box.getCantMaxPapel())
            {
                box.setNoTengo(false);
                synchronized (box)
                {
                    if (box.getCantCajaActual() != box.getMaxCantCajas())
                    {
                        quitarCaja();
                        System.out.println("Servidor.Scripts.Supervisor: " + id +
                                " Quita la Cajeta:" + box.getCantCajaActual());

                        String actualString = maquina.outputField.getText();
                        maquina.outputField.setText(actualString + "\n" + "Servidor.Scripts.Supervisor: " + id + " Quita la Cajeta:" + box.getCantCajaActual());

                        box.notifyAll();
                        box.setNoTengo(true);
                    } else
                    {
                        box.notifyAll();
                        break;
                    }
                }
            }
        }
    }

    public synchronized void quitarCaja()
    {
        box.setCantPapelActual(0);
        box.setCantCajaActual(++i);
    }
}
