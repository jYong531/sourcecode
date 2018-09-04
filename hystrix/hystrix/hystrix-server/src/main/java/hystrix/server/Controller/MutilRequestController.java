package hystrix.server.Controller;

import hystrix.server.components.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MutilRequestController {
    @GetMapping("/homes")
    public void homes()
    {
        String url = "http://localhost:8080/home1";
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++)
        {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long startTime = System.currentTimeMillis();
                    try {

                        String response = HttpRequest.sendGet(url,"");
                        long endTime = System.currentTimeMillis();
                        System.out.println(String.format("Response String:%s; totalMillis:%s; result:%s", response,endTime - startTime, "sucess"));
                    } catch (Exception e) {
                        long endTime = System.currentTimeMillis();
                        System.out.println(String.format("Response String:%s; totalMillis:%s; result:%s", e ,endTime - startTime, "failure"));
                    }
                }
            });
        }
    }
}
