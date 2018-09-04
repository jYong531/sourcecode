package kafka.Componets;

import com.alibaba.fastjson.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import kafka.Common.SpringUtils;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

public class KafkaSerder {
    public static void send(String topic,String object) throws  Exception
    {
        KafkaTemplate<String,String> kafkaTemplate = SpringUtils.getBean("kafkaTemplate");
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic,object);
        final SendResult<String,String> sendResult = listenableFuture.get();
        listenableFuture.addCallback(new SuccessCallback() {
                                         @Override
                                         public void onSuccess(Object object) {
                                             System.out.println("kafka Producer发送消息成功！topic=" + sendResult.getRecordMetadata().topic()+",partition"+sendResult.getRecordMetadata().partition()+",offset="+sendResult.getRecordMetadata().offset());
                                         }
                                     },new FailureCallback() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("kafka Producer发送消息失败！sendResult=" + JSONObject.toJSONString(sendResult.getProducerRecord()));
                    }
                }
        );
    }

    //指定 topic、分区 和 message
    public static void send(String topic, int partition, String data) throws  Exception {
        System.out.println("kafka Producer发送消息，topic=" + topic);
        KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate<String, String>) SpringUtils.getBean("kafkaTemplate");
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, partition, data);

        final SendResult<String, String> sendResult = listenableFuture.get();
        listenableFuture.addCallback(new SuccessCallback() {
                                         @Override
                                         public void onSuccess(Object object) {
                                             System.out.println("kafka Producer发送消息成功！topic=" + sendResult.getRecordMetadata().topic() + ",partition" + sendResult.getRecordMetadata().partition() + ",offset=" + sendResult.getRecordMetadata().offset());
                                         }
                                     }, new FailureCallback() {
                                         @Override
                                         public void onFailure(Throwable throwable) {
                                             System.out.println("kafka Producer发送消息失败！sendResult=" + JSONObject.toJSONString(sendResult.getProducerRecord()));
                                         }
                                     }
        );

    }
}
