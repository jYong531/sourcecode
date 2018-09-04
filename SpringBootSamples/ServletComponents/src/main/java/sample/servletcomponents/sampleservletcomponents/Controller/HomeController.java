package sample.servletcomponents.sampleservletcomponents.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sample.servletcomponents.sampleservletcomponents.Models.Person;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class HomeController {
    @GetMapping("/home")
    public String home(HttpServletRequest request)
    {
        request.getSession().setAttribute("s1","测试data1");
        request.getSession().setAttribute("s2","测试data2");

        ServletContext ctx =  request.getServletContext();
        String numSessions = ctx.getAttribute("numSessions").toString();
        return numSessions;
    }

    @GetMapping("/xlh")
    @ResponseBody
    public Person xlh()
    {
        Person person = new Person();
        /*这里故意设置name为空，看配置的解码器是否处理*/
        /*实际上并没有处理，还是继续不显示该字段，需要进步探索*/
        person.setName(null);
        person.setAge(10);
        person.setBirthday(new Date());

        return person;
    }
}
