package kafka.Componets.Producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerListener implements ProducerListener<String,String> {
    @Override
    public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
        System.out.println(String.format("KafkaProducerListener[Success]: top:%s,partition:%s, key:%s,value:%s,recordMetadata:%s",topic,partition,key,value,recordMetadata));
    }

    @Override
    public void onError(String topic, Integer partition, String key, String value, Exception exception) {
        System.out.println(String.format("KafkaProducerListener[error]:topic:%s,partition:%s,key:%s,value:%s,exception:%s",topic,partition,key,value,exception));
    }

    @Override
    public boolean isInterestedInSuccess() {
        return false;
    }
}
