package redis.demo.RedisLock;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.demo.Components.RedisUtils;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

@Component
@Configuration
@PropertySource(value = {"classpath:RedisConfig.properties"})
public class RedisLockHelper implements InitializingBean {

    @Resource(name = "redisConnectionFactory")
    RedisConnectionFactory redisConnectionFactory;

    @Value("${Spring.Redis.Lock.LockDurableTime}")
    private int lockDurableTime;

    @Value("${Spring.Redis.Lock.LockWaiteTime}")
    private int lockWaiteTime;

    @Value("${Spring.Redis.Lock.LockKey}")
    private String lockKey;

    @Resource
    RedisUtils redisUtils;

    public String doLock()
    {
        RedisConnection redisConnection = redisConnectionFactory.getConnection();
        //获取锁的超时时间，超过这个时间则"放弃获取锁"
        long end = System.currentTimeMillis() + lockWaiteTime;
        String identifier = UUID.randomUUID().toString();
        while (System.currentTimeMillis() < end) {
            boolean isSuccess = redisConnection.setNX(lockKey.getBytes(), identifier.getBytes());
            if(isSuccess) {
                redisConnection.expire(lockKey.getBytes(), lockDurableTime);
                return identifier;
            }

            // 返回-1代表key没有设置超时时间，为key设置一个超时时间
            if (redisConnection.ttl(lockKey.getBytes()) == -1) {
                redisConnection.expire(lockKey.getBytes(), lockDurableTime);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

    public boolean releaseLock(String identifier)
    {
        if(identifier == null || "".equals(identifier)){
            return false;
        }
        RedisConnection redisConnection = redisConnectionFactory.getConnection();
        boolean releaseFlag = false;
        while (true) {
            try{
                // 监视lock，准备开始事务
                redisConnection.watch(lockKey.getBytes());
                // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
                byte[] valueBytes = redisConnection.get(lockKey.getBytes());
                if(valueBytes == null){
                    redisConnection.unwatch();
                    releaseFlag = false;
                    break;
                }
                String identifierValue = new String(valueBytes);
                if (identifier.equals(identifierValue)) {
                    redisConnection.multi();
                    redisConnection.del(lockKey.getBytes());
                    List<Object> results = redisConnection.exec();
                    if (results == null) {
                        continue;
                    }
                    releaseFlag = true;
                }
                redisConnection.unwatch();
                break;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return releaseFlag;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(redisUtils.hasKey(lockKey))
        {
            redisUtils.del(lockKey);
        }
    }
}
