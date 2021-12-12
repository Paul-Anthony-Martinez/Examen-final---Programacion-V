public class Hilo extends Thread
{
    int cantCajas = 0;
    int cantPapel = 0;
    boolean done = false;
    Machine maquina;

    public Hilo(int cantCajas, int cantPapel, Machine maquina)
    {
        this.cantCajas = cantCajas;
        this.cantPapel = cantPapel;
        this.maquina = maquina;
    }

    public void run()
    {
        TheBox box = new TheBox(cantCajas, cantPapel);

        ThreadGroup g1 = new ThreadGroup("t");
        Thread[] thread = new Thread[3];
        Worker workers[] = new Worker[3];

        String actualText = maquina.boxesAmountLabel.getText();
        maquina.boxesAmountLabel.setText(actualText + ": " + box.getMaxCantCajas());

        actualText = maquina.paperAmountLabel.getText();
        maquina.paperAmountLabel.setText(actualText + ": " + box.getCantMaxPapel());

        actualText = maquina.producedPaperLabel.getText();
        maquina.producedPaperLabel.setText(actualText + ": " + (box.getCantCajaActual() * box.getCantPapelActual() + 1) + "/" + (box.getMaxCantCajas() * box.getCantMaxPapel()));

        try
        {

            for (int i = 0; i < 3; i++)
            {
                workers[i] = new Worker(box, i + 1, this.maquina);

                thread[i] = new Thread(g1, workers[i], "t");
                thread[i].start();
                thread[i].join(1000);
            }
        }
        catch (InterruptedException e) {}

        Supervisor supervisor = new Supervisor(box, 4, this.maquina);

        Thread supervisorThread = new Thread(g1, supervisor, "t");
        supervisorThread.setDaemon(true);
        supervisorThread.start();

        try
        {
            sleep(1000);
            this.join(1000);
        }
        catch (InterruptedException e) {}

        while (!done)
        {
            if (g1.activeGroupCount() == 0)
            {
                done = true;
            }
        }
    }
}
