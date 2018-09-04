package redis.demo.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.demo.Common.SpringUtils;
import redis.demo.Components.RedisUtils;
import redis.demo.Concurrent.RedisDataCommonOperator;
import redis.demo.Concurrent.RedisDataIncrOperator;
import redis.demo.Concurrent.RedisDataLockedOperator;
import redis.demo.SpringCache.TestModel;
import redis.demo.SpringCache.TestService;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HomeController {

    @Resource
    RedisUtils redisUtils;
    @Resource
    TestService testService;

    @GetMapping("/home")
    public String home()
    {
        redisUtils.set("myname","cyua");
        return "";
    }

    @GetMapping("/springcache")
    @ResponseBody
    public TestModel springcache() throws Exception
    {

        TestModel model = null;
        model = testService.getModel(String.valueOf("1"));
        redisUtils.hdel("TestData","1");
        for (int i=0;i<3;i++)
        {
            long beginTime = System.currentTimeMillis();
            model = testService.getModel(String.valueOf("1"));
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("第%s次取值，耗时%s毫秒" ,i+1, endTime - beginTime));
        }

        return model;
    }

    @GetMapping("/concurrent")
    public void  concurrent() throws Exception
    {
        redisUtils.setRedisTemplate(SpringUtils.getBean("redisTemplate"));
        ExecutorService executorService = Executors.newFixedThreadPool(100);
/*        for (int i=0;i<20;i++)
        {
            executorService.submit(new RedisDataCommonOperator(redisUtils));
        }

        Thread.sleep(2000);
        System.out.println(String.format("RedisDataCommonOperator: value is:%s", redisUtils.get("increaseKey1")));

        for (int i=0;i<20;i++)
        {
            executorService.submit(new RedisDataIncrOperator(redisUtils));
        }

        Thread.sleep(2000);
        System.out.println(String.format("RedisDataIncrOperator: value is:%s", redisUtils.get("increaseKey2")));*/

        for (int i=0;i<20;i++)
        {
            executorService.submit(new RedisDataLockedOperator(redisUtils));
        }

        Thread.sleep(2000);
        System.out.println(String.format("RedisDataLockedOperator: value is:%s", redisUtils.get("increaseKey3")));
    }
}
