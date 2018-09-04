package redis.demo.Concurrent;

import org.springframework.data.redis.core.RedisTemplate;
import redis.demo.Common.SpringUtils;
import redis.demo.Components.RedisUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisDataCommonOperator implements Runnable {

    private String key = "increaseKey1";
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss sss");
    private String StringOutput = "[DateTime]:%s ; [Thread]:%s ; [ValueChange]:%s -> :%s";


    RedisUtils redisUtils = new RedisUtils();

    public RedisDataCommonOperator(RedisUtils redisUtils){
        this.redisUtils = redisUtils;
    }

    @Override
    public void run() {
        Integer initialValue = (Integer)redisUtils.get(key);
        if(initialValue == null)
            initialValue = 0;
        Integer value = initialValue + 1;
        redisUtils.set(key,value);
        System.out.println(String.format(StringOutput, format.format(new Date()), Thread.currentThread().getId(), initialValue, value));
    }
}
