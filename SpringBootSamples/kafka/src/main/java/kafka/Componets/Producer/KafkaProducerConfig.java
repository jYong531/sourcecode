package kafka.Componets.Producer;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value={"classpath:kafka.properties"})
public class KafkaProducerConfig {
    @Value("${kafka.producer.servers}")
    private String servers;

    @Value("${kafka.producer.retries}")
    private String retries;

    @Value("${kafka.producer.batchsize}")
    private String batchsize;

    @Value("${kafka.producer.lingerms}")
    private String lingerms;

    @Value("${kafka.producer.buffermemory}")
    private String buffermemory;

    @Value("${kafka.producer.maxblockms}")
    private String maxblockms;

    @Resource
    KafkaProducerListener kafkaProducerListener;

    private Map<String,Object> producerConfigs()
    {
        Map<String,Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,batchsize);
        props.put(ProducerConfig.LINGER_MS_CONFIG,lingerms);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,buffermemory);
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,maxblockms);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    private ProducerFactory<String,String> producerFactory()
    {
        return new DefaultKafkaProducerFactory<String, String>(producerConfigs());
    }

    @Bean
    public KafkaTemplate kafkaTemplate()
    {
        KafkaTemplate<String,String> kafkaTemplate = new KafkaTemplate<String, String>(producerFactory(),true);
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        return kafkaTemplate;
    }
}
