import java.rmi.RemoteException;

class FactoryImpl implements Factory
{
    public int boxAmount;
    public int paperAmount;
    public Machine maquina;

    public FactoryImpl(Machine maquina) throws RemoteException
    {
        this.maquina = maquina;
    }

    public void startFactory(int boxAmount, int paperAmount)throws RemoteException
    {
        this.boxAmount = boxAmount;
        this.paperAmount = paperAmount;

        this.maquina.startFactory(this.boxAmount, this.paperAmount);
    }

    @Override
    public void cleanFields() throws RemoteException
    {
        this.maquina.cleanFields();
    }
}
