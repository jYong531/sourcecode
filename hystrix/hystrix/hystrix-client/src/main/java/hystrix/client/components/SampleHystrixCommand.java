package hystrix.client.components;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SampleHystrixCommand extends HystrixCommand<String> {
    public SampleHystrixCommand() {
        //super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("as")));
        /*super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("as")).
                andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(6000)));*/
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("as"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        .withExecutionTimeoutEnabled(true)
                        //.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000))
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(5)
                        //该属性用来配置当 HystrixCommand 执行超时的时候，是否需要将他中断，默认值 true
                        .withExecutionIsolationThreadInterruptOnTimeout(false)
                        //该属性用来设置服务降级策略是否启用，默认值 true ，如果设置为false，当请求失败或拒绝发生时，将不会调用 HystrixCommand.getFallback() 来执行服务降级逻辑
                        .withFallbackEnabled(false)
                        //该属性用来确定当服务请求命令失败时，是否使用断路器来跟踪其健康指标和熔断请求，默认值 true
                        .withCircuitBreakerEnabled(true)
                        //设置在一个滚动窗口中，打开断路器的最少请求数。 比如：如果值是20，在一个窗口内（比如10秒），收到19个请求，即使这19个请求都失败了，断路器也不会打开。
                        .withCircuitBreakerRequestVolumeThreshold(10)
                        //该属性用来设置当断路器打开之后的休眠时间窗。默认值 5000 毫秒，休眠时间窗结束之后，会将断路器设置为"半开"状态，尝试熔断的请求命令，如果依然失败就将断路器继续设置为"打开"状态，如果成功就设置为"关闭"状态。
                        .withCircuitBreakerSleepWindowInMilliseconds(5000)
                        //该属性用来设置断路器打开的错误百分比条件。例如，默认值为 50 的情况下，表示在滚动时间窗中，在请求数量超过
                        .withCircuitBreakerErrorThresholdPercentage(5)
                        //该属性用来设置断路器强制进入"打开"状态，会拒绝所有请求，该属性优先于 circuitBreaker.forceClosed
                        .withCircuitBreakerForceOpen(false)
                )
                        //该属性用来设置断路器强制进入"关闭"状态，会接收所有请求。
                        //.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerForceClosed(true))
        );
    }

    @Override
    protected String run() throws Exception {
        throw new Exception("Exception");
       /* Random rand = new Random();
        if(1==rand.nextInt(2)){
            throw new Exception("Exception");
        }
        TimeUnit.SECONDS.sleep(1);
        return "run done";*/
    }

    @Override
    protected String getFallback() {
        return "as failure";
    }
}
