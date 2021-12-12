public class Worker extends Thread
{
    private TheBox box;
    private int id;
    Machine maquina;

    public Worker(TheBox box, int id, Machine maquina)
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
                while (!box.isNoTengo())
                {
                    try
                    {
                        System.out.println("Servidor.Scripts.Worker: " + id +
                                " is waiting...");

                        String actualString = maquina.outputField.getText();
                        maquina.outputField.setText(actualString + "\n" + "Servidor.Scripts.Worker: " + id + " is waiting...");

                        box.wait(1000);
                    }
                    catch (InterruptedException e) {}
                    break;
                }
            }
            //Produce the paper
            if (box.getCantCajaActual() != box.getMaxCantCajas())
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {}

                if (box.getCantPapelActual() < box.getCantMaxPapel() && box.getCantCajaActual() != box.getMaxCantCajas())
                {
                    box.setNoTengo(false);

                    synchronized (box)
                    {
                        addPapel();
                        System.out.println("Id: " + id + " Puso papel: " + box.getCantPapelActual());

                        maquina.currentPaperLabel.setText("Papel cargado:" + box.getCantPapelActual());
                        maquina.activeBoxLabel.setText("Caja en proceso: " + (box.getCantCajaActual() + 1) );

                        String actualString = maquina.outputField.getText();
                        maquina.outputField.setText(actualString + "\n" + "Id: " + id + " Puso papel: " + box.getCantPapelActual());

                        box.notifyAll();
                    }
                }
            }
            else
            {
                maquina.cleanButton.setEnabled(true);
                String actualString = maquina.outputField.getText();
                maquina.outputField.setText(actualString + "\n" + "Proceso terminado.");

                break;
            }
        } //fin del while
        return;
    }

    public synchronized void addPapel()
    {
        box.agregarPapel();
    }
}
