package kafka.Componets.Consumer;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaConsumerListener implements MessageListener<String, String> {

    /**
     * 默认线程池大小 设置为1是为了调试方便；实际使用时应该配置为池的优化大小
     */
    private static Integer CORE_THREAD_NUM = 10;

    private ExecutorService threadPool = Executors.newFixedThreadPool(getDefaultThreadNum());

    private KafkaConsumerHandler kafkaConsumerHandler;

    public KafkaConsumerListener(KafkaConsumerHandler kafkaConsumerHandler){
        this.kafkaConsumerHandler = kafkaConsumerHandler;
    }

    @Override
    public void onMessage(final ConsumerRecord<String, String> data) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String businessData = data.value();
                try {
                    kafkaConsumerHandler.handle(data.topic(), businessData);
                } catch (Exception e) {
                    System.out.println("kafka消息处理失败：" + JSONObject.toJSONString(data.value()) + ";Error:" + e);
                }
                System.out.println("【kafka消息接收 结束：" + "topic="+data.topic()+",partition="+data.partition()+",offset="+data.offset()+"】");
            }
        });
    }

    /**
     * 默认配置用与 cpu核数 相同个数的线程
     *
     * @return
     */
    protected int getDefaultThreadNum() {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        if(cpuNum > 0) {
            return cpuNum;
        } else {
            return CORE_THREAD_NUM;
        }
    }
}
