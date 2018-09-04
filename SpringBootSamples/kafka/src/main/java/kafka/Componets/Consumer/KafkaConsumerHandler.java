package kafka.Componets.Consumer;

public class KafkaConsumerHandler{
    public void handle(String topic, String businessData) throws Exception
    {
        System.out.println(String.format("KafkaConsumerHandler:topic:%s, businessData:%s",topic,businessData));
    }
}
