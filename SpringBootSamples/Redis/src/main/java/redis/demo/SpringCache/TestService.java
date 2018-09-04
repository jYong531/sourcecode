package redis.demo.SpringCache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class TestService {

    @Cacheable(key = "#id", value = "TestData")
    public TestModel getModel(String id) throws Exception
    {
        Thread.sleep(3000);
        TestModel model = new TestModel();
        model.setId("10000");
        model.setName("johnson");
        return model;
    }
}
