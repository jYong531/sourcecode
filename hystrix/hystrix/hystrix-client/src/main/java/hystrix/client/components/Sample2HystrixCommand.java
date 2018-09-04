package hystrix.client.components;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Sample2HystrixCommand extends HystrixObservableCommand<String> {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public Sample2HystrixCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected Observable<String> construct() {
        System.out.println("Sample2HystrixCommand:in construct! thread:" + Thread.currentThread().getName());
        return (Observable<String>) Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(format.format(new Date()) + "observer onNext 1");
                        TimeUnit.SECONDS.sleep(2);
                        observer.onNext(format.format(new Date()) + "observer onNext 2");
                        TimeUnit.SECONDS.sleep(2);
                        observer.onCompleted(); // 不会往下执行observer的任何方法
                        observer.onNext(format.format(new Date()) + "observer onNext 3");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<String> resumeWithFallback() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(format.format(new Date()) + "observer error");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
