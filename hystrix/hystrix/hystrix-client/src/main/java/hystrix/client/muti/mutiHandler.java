package hystrix.client.muti;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class mutiHandler {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    public static void process(IProcess process) throws Exception
    {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i=0;i<50;i++)
        {
            Thread.sleep(500);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long startTime = System.currentTimeMillis();
                    try {

                        process.process();
                        long endTime = System.currentTimeMillis();
                        System.out.println(String.format(format.format(new Date()) + "ThreadID:%s; totalMillis:%s; result:%s", Thread.currentThread().getId(),endTime - startTime, "sucess"));
                    } catch (Exception e) {
                        long endTime = System.currentTimeMillis();
                        System.out.println(String.format(format.format(new Date()) + "ThreadID:%s; totalMillis:%s; result:%s", Thread.currentThread().getId() ,endTime - startTime, "Exception"));
                    }
                }
            });
        }
    }
}
