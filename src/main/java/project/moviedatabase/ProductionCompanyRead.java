package project.moviedatabase;

import java.io.IOException;
import java.io.Serializable;

public class ProductionCompanyRead implements Runnable
{
    private final Main main;
    private final Thread thread;
    public ProductionCompanyRead(Main main)
    {
        this.main = main;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        /*try
        {
            while(true)
            {
                Object obj = main.getNetworkIO().read();
                if(obj instanceof ProductionCompany)
                {
                    ProductionCompany productionCompany = (ProductionCompany) obj;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }
}
