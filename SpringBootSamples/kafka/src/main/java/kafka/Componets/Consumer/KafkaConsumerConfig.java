package kafka.Componets.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:kafka.properties"})
public class KafkaConsumerConfig {

    /*@Value("${kafka.consumer.servers}")
    private String servers;
    @Value("${kafka.consumer.enable.auto.commit}")
    private boolean enableAutoCommit;
    @Value("${kafka.consumer.session.timeout}")
    private String sessionTimeout;
    @Value("${kafka.consumer.auto.commit.interval}")
    private String autoCommitInterval;
    @Value("${kafka.consumer.group.id}")
    private String groupId;
    @Value("${kafka.consumer.auto.offset.reset}")
    private String autoOffsetReset;
    @Value("${kafka.consumer.topic}")
    private String topic;

    private Map<String, Object> consumerConfigs(){
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return propsMap;
    }

    private ConsumerFactory<String, String> consumerFactory;
    public ConsumerFactory<String, String> consumerFactory() {
        if(consumerFactory == null){
            consumerFactory = new DefaultKafkaConsumerFactory<String, String>(consumerConfigs());
        }
        return consumerFactory;
    }

    @Bean
    public KafkaConsumerHandler kafkaConsumerHandler()
    {
        return new KafkaConsumerHandler();
    }

    @Bean
    public KafkaConsumerListener kafkaConsumerListener(KafkaConsumerHandler kafkaConsumerHandler)
    {
        return new KafkaConsumerListener(kafkaConsumerHandler);
    }

    @Bean
    public ContainerProperties containerProperties(KafkaConsumerListener kafkaConsumerListener)
    {
        ContainerProperties containerProperties = new ContainerProperties(topic.split(","));
        containerProperties.setMessageListener(kafkaConsumerListener);
        return containerProperties;
    }

    @Bean
    public KafkaMessageListenerContainer<String,String> kafkaMessageListenerContainer(ContainerProperties containerProperties)
    {
        return new KafkaMessageListenerContainer<String, String>(consumerFactory(), containerProperties);
    }

    @Bean
    public KafkaListenerContainerFactory kafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
*/
}
