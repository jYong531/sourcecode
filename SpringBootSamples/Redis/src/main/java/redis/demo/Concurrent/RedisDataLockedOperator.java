package redis.demo.Concurrent;

import redis.demo.Common.SpringUtils;
import redis.demo.Components.RedisUtils;
import redis.demo.RedisLock.RedisLockHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RedisDataLockedOperator implements Runnable {

    private String key = "increaseKey3";
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss sss");
    private String StringOutput = "[DateTime]:%s ; [Thread]:%s ; [ValueTarget]:%s";
    private String StringOutput2 = "[Thread]:%s require lock timeout!";

    RedisUtils redisUtils = new RedisUtils();

    public RedisDataLockedOperator(RedisUtils redisUtils){
        this.redisUtils = redisUtils;
    }

    @Override
    public void run() {
        RedisLockHelper lockHelper = (RedisLockHelper)SpringUtils.getBean("redisLockHelper");
        String identifier = lockHelper.doLock();
        if(identifier == null)
        {
            System.out.println(String.format(StringOutput2,Thread.currentThread().getId()));
            return;
        }
        Integer initialValue = (Integer)redisUtils.get(key);
        if(initialValue == null)
            initialValue = 0;
        Integer value = initialValue + 1;
        redisUtils.set(key,value);

        lockHelper.releaseLock(identifier);
        System.out.println(String.format(StringOutput, format.format(new Date()), Thread.currentThread().getId(), initialValue, value));
    }
}
