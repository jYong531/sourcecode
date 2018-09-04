package hystrix.client.Controller;
import hystrix.client.muti.IProcess;
import hystrix.client.muti.mutiHandler;
import hystrix.client.services.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HomeController {

    @Resource
    SampleService sampleService;

    @GetMapping("/home1")
    public void Home() throws Exception
    {
        mutiHandler.process(new IProcess() {
            @Override
            public void process() {
                sampleService.HystrixCommand_Synchronization();
            }
        });
    }

    @GetMapping("/home2")
    public void Home2() throws Exception
    {
        mutiHandler.process(new IProcess() {
            @Override
            public void process() throws Exception {
                sampleService.HystrixCommand_Asynchronization();
            }
        });
    }

    @GetMapping("/home3")
    public void Home3() throws Exception
    {
        sampleService.HystrixCommand_Observe();
    }

    @GetMapping("/home4")
    public void Home4() throws Exception
    {
        sampleService.HystrixObservableCommand_Asynchronization();
    }
}
