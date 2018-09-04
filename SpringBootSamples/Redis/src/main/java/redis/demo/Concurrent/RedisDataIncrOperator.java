package redis.demo.Concurrent;

import javafx.scene.chart.PieChart;
import redis.demo.Components.RedisUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RedisDataIncrOperator implements Runnable {
    private String key = "increaseKey2";
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss sss");
    private String StringOutput = "[DateTime]:%s ; [Thread]:%s ; [ValueTarget]:%s";


    RedisUtils redisUtils = new RedisUtils();

    public RedisDataIncrOperator(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public void run() {
        //Integer initialValue = (Integer)redisUtils.get(key);
        long returnValue = redisUtils.incr(key,1);
        System.out.println(String.format(StringOutput,format.format(new Date()), Thread.currentThread().getId(),returnValue));
    }

}
