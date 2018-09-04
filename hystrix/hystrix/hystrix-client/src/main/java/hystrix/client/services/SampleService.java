package hystrix.client.services;

import com.netflix.hystrix.HystrixCommand;
import hystrix.client.components.Sample2HystrixCommand;
import hystrix.client.components.SampleHystrixCommand;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import rx.Observable;
import rx.Observer;
import java.util.concurrent.Future;


@Service
public class SampleService {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public void HystrixCommand_Synchronization()
    {
        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "-------------------------------------同步方式执行【HystrixCommand】------------------------------------");
        HystrixCommand command = new SampleHystrixCommand();
        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + command.execute() + "  " + "isCircuitBreakerOpen:" + command.isCircuitBreakerOpen());
        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "execute done"+ "  " + "isCircuitBreakerOpen:" + command.isCircuitBreakerOpen());
    }



    public void HystrixCommand_Asynchronization()  throws Exception
    {
        HystrixCommand command = new SampleHystrixCommand();
        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "-------------------------------------异步方式执行【HystrixCommand】------------------------------------");
        Future future = command.queue();
        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "execute done" + "  " + "isCircuitBreakerOpen:" + command.isCircuitBreakerOpen());
        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + future.get() + "  " + "isCircuitBreakerOpen:" + command.isCircuitBreakerOpen());
    }

    public void HystrixCommand_Observe()  throws Exception
    {
        HystrixCommand command = new SampleHystrixCommand();
        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "-------------------------------------Observe方式执行【HystrixCommand】------------------------------------");
        Observable<String> observable = command.observe();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "onCompleted" + "  " + "isCircuitBreakerOpen:" + command.isCircuitBreakerOpen());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "onError: " + e);
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "onNext: " + s);
            }
        });

        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "execute done");
    }

    public void HystrixObservableCommand_Asynchronization()
    {
        Sample2HystrixCommand command2 = new Sample2HystrixCommand();
        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "-------------------------------------执行【HystrixObservableCommand】------------------------------------");
        Observable<String> observable = command2.observe();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "onError: " + e);
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "onNext: " + s);
            }
        });

        System.out.println("ThreadID:" + Thread.currentThread().getId() + "  " + format.format(new Date()) + " " + "execute done");
    }
}
