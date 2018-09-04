package kafka.Controller;

import kafka.Componets.KafkaSerder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HomeController {

    @GetMapping("/index")
    public String index()
    {
        return "hello world!";
    }

    @GetMapping("/home")
    public void home()
    {
        try {
            KafkaSerder.send("mytopic", "mytopicvalues");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*    @KafkaListener(topics = {"mytopic"})
    public void consumers(ConsumerRecord<?, ?> cr)
    {
        System.out.println(String.format("{%s} - {%s} : {%s}",cr.topic(), cr.key(), cr.value()));
    }*/

}
